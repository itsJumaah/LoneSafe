using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.IO;
using System.Media;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Threading;
using System.Timers;
using System.util;
using System.Windows.Forms;

namespace LoneSafeLib
{
    public class LiveDataControl
    {
        private string url;

        private LiveData liveData;
        private BackgroundWorker worker;
        private DataGridView dataGrid;
        private NotifyIcon escLittleNotif;
        private NotifyIcon sosLittleNotif;
        private Icon icon;

        private List<LiveUser> newList = new List<LiveUser>();

        private string adminName;
        private string adminEmail;

        private System.Timers.Timer timer = new System.Timers.Timer(5000); //5 sec
        private delegate void InvokeDelegate();

        private SoundPlayer sosSound;
        private SoundPlayer escalationSound;
        
        public LiveDataControl(string url, DataGridView dataGrid, Icon icon, string adminName, string adminEmail)
        {
            this.url = url;
            this.icon = icon;
            this.liveData = new LiveData(url);
            this.dataGrid = dataGrid;

            this.adminEmail = adminEmail;
            this.adminName = adminName;

            worker = new BackgroundWorker();
            worker.DoWork += worker_DoWork;

            
            timer.Elapsed += timer_Elapsed;
            timer.Start();

            GC.KeepAlive(timer);
            GC.KeepAlive(worker);
            
        }
        
        private void timer_Elapsed(object sender, ElapsedEventArgs e)
        {
            if (!worker.IsBusy)
            {
                worker.RunWorkerAsync();
            }

        }

        private void worker_DoWork(object sender, DoWorkEventArgs e)
        {
            liveData.update(); //grab the new data
            dataGrid.BeginInvoke(new InvokeDelegate(live));
            
        }

        private void reset()
        {
            //if data already displayed clear and delete them all
            if (dataGrid != null)
            {
                dataGrid.Rows.Clear();
                newList.Clear();
            }
            //load the new data back to the list
            foreach (LiveUserJson liveUser in liveData.LiveUsersList)
            {
                newList.Add(liveUser.LiveUser);
            }
        }
        
        private void wait()
        {
            int ms = 2000;
            int time = Environment.TickCount;

            while (true)
            {
                if (Environment.TickCount - time >= ms)
                {
                    break;
                }

            }
        }
        private void live()
        {
            reset();
            //load the list to the data grid
            foreach (LiveUser user in newList)
            {
                if (user != null)
                {
                    
                    string[] checkin = new string[8] { user.Checkin1, user.Checkin2,
                    user.Checkin3, user.Checkin4, user.Checkin5,
                    user.Checkin6, user.Checkin7, user.Checkin8};

                    string fullName = user.Firstname + " " + user.Lastname;

                    dataGrid.Rows.Add(fullName, user.Risklevel, user.StartTime, user.EndTime,
                        checkin[0], checkin[1], checkin[2], checkin[3],
                        checkin[4], checkin[5], checkin[6], checkin[7]);

                    //if sos is = 1 make notifications
                    if (user.NeedSOS == 1)
                    {
                        if (adminEmail != null)
                        {
                            //send an email to the admin as long as his email provided
                            notifyEmail(adminName, adminEmail, fullName, user.Username, "S.O.S.");
                        }
                        
                        //push sos notification to the gui
                        sos(user);
                    }


                    //if not any checkin is between 1 to 3 makes that an escalation
                    for (int x = 0; x < 8; x++)
                    {
                        int userEsc = 0;
                        LoneUtil.getInt(checkin[x], ref userEsc);

                        int num = x + 1;

                        for (int i = 1; i < 4; i++)
                        {
                            if (userEsc == i)
                            {
                                if (adminEmail != null)
                                {//send admin email as long as its provided
                                    notifyEmail(adminName, adminEmail, fullName, user.Username, "ESCALATION LEVEL " + i.ToString());
                                }
                                //push gui notification
                                notification(user, "checkin" + num.ToString(), i);
                            }
                        }
                    }
                    //ended
                    
                }
            }
        }
        //escalation notifications
        private void notification(LiveUser user, string checkin, int esc)
        {
            string es = "Escalation " + esc.ToString();

            string fullName = user.Firstname + " " + user.Lastname;

            //play sound
            try
            {
                escalationSound = new SoundPlayer(Properties.Resources.escalation);
               // escalationSound = new SoundPlayer(@"res\escalation.wav");
                escalationSound.PlayLooping();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }

            //make windows notification in the tool bar
            escLittleNotif = new NotifyIcon();
            escLittleNotif.Visible = true;
            escLittleNotif.Icon = icon;// SystemIcons.Application;
            escLittleNotif.Text = es + " Alert!";
            escLittleNotif.BalloonTipTitle = es;
            escLittleNotif.BalloonTipText = fullName + " might be in trouble";
            escLittleNotif.BalloonTipIcon = ToolTipIcon.Warning;
            escLittleNotif.ShowBalloonTip(15000);

            escLittleNotif.BalloonTipClosed += (sender, e) => {
                var thisIcon = (NotifyIcon)sender;
                thisIcon.Visible = false;
                thisIcon.Dispose();
                escalationSound.Stop();
            };
            //make window notification

            string msg = fullName + " might be in trouble\n\n" +
                "More Info about " + user.Firstname + ":\n" +
                "Latitude: " + " " + "Longitude" + " \n"


                ;
           
            if (MessageBox.Show(fullName + " might be in trouble\n Please contact him", es,
                 MessageBoxButtons.OKCancel, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                 == DialogResult.OK)
             {
                 // if ok is pressed, set sos back to 0
                 Connection.notified(url, user.Username, checkin, es);
                escalationSound.Stop();
                Thread.Sleep(2000);
            }
            else
            {
                Connection.notified(url, user.Username, checkin, es);
                escalationSound.Stop();
                Thread.Sleep(2000);
                Console.WriteLine("open window");
            }
            
        }
        //sos notifications
        private void sos(LiveUser user)
        {
            string fullName = user.Firstname + " " + user.Lastname;

            try
            {
                sosSound = new SoundPlayer(Properties.Resources.sos);
                //sosSound = new SoundPlayer(@"res\sos.wav");
                sosSound.PlayLooping();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }

            //make windows notification in the tool bar
            sosLittleNotif = new NotifyIcon();
            sosLittleNotif.Visible = true;
            sosLittleNotif.Icon = icon;// SystemIcons.Application;
            sosLittleNotif.Text = "SOS Alert!";
            sosLittleNotif.BalloonTipTitle = "SOS NEEDED";
            sosLittleNotif.BalloonTipText = fullName + " needs your help";
            sosLittleNotif.BalloonTipIcon = ToolTipIcon.Error;
            sosLittleNotif.ShowBalloonTip(15000);

            sosLittleNotif.BalloonTipClosed += (sender, e) => {
                var thisIcon = (NotifyIcon)sender;
                thisIcon.Visible = false;
                thisIcon.Dispose();
            };

            string msg = fullName + " needs your help\n\n" +
                "More Info about " + user.Firstname + ":\n" +
                "Latitude: " + " " + "Longitude" + " \n" 
                

                ;
            

            if (MessageBox.Show(msg, "SOS ALERT!!",
                MessageBoxButtons.OKCancel, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                == DialogResult.OK)
            {
                // if ok is pressed, set sos back to 0
                Connection.noMoreSOS(url, user.Username);
                sosSound.Stop();
                Thread.Sleep(2000);
            }
            else
            {
                Connection.noMoreSOS(url, user.Username);
                sosSound.Stop();
                Thread.Sleep(2000);
                Console.WriteLine("open window");
            }
            
        }
        
        

        //email notification
        private void notifyEmail(string adminName, string toEmail, string fullname, string username, string eventType)
        {

            string senderEmail = "lonesafeapp@gmail.com";
            string senderPassword = "Lonesafe2016";

            string header = "LoneSafe Alert";

            string body = "Hello " + adminName + ",\n\n" +
                            "The user " + fullname + " (" + username + ") has triggered " +
                            eventType + " event! Please make sure someone is following up on this.\n\n\n" +
                            "You have recieved this email because you were logged in to LoneSafe system during this event.\n\n" +
                            "If you wish to stop recieving this email, ask an admin to remove it from the system.\n\n" +
                            "This is an auto generated email, please do NOT reply!\n\n\n" +
                            "Thanks,\nLoneSafe by BAIT\n";

            SmtpClient client = new SmtpClient();
            client.Port = 587; //465
            client.Host = "smtp.gmail.com";
            client.EnableSsl = true;
            client.Timeout = 10000;
            client.DeliveryMethod = SmtpDeliveryMethod.Network;
            client.UseDefaultCredentials = false;
            client.Credentials = new NetworkCredential(senderEmail, senderPassword);

            MailMessage mail = new MailMessage(senderEmail, toEmail, header, body);
            mail.BodyEncoding = UTF8Encoding.UTF8;
            mail.DeliveryNotificationOptions = DeliveryNotificationOptions.OnFailure;
            try
            {
                client.Send(mail);
            }
            catch(SmtpException e)
            {
                Console.WriteLine(e.Message);
            }
            
        }
    }
}
