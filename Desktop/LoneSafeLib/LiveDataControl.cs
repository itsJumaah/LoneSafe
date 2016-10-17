using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Media;
using System.Threading;
using System.Timers;
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
        private string adminLname;
        private string adminEmail;

        private System.Timers.Timer timer = new System.Timers.Timer(4000); //4 sec
        private delegate void InvokeDelegate();

        private SoundPlayer sosSound;
        private SoundPlayer escalationSound;

        private AlertES esInfo;
        private AlertSO sosInfo;

        public LiveDataControl(string url, DataGridView dataGrid, Icon icon, string adminName, string adminLname, string adminEmail)
        {
            this.url = url;
            this.icon = icon;
            this.liveData = new LiveData(url);
            this.dataGrid = dataGrid;
            
            this.adminEmail = adminEmail;
            this.adminName = adminName;
            this.adminLname = adminLname;

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
            try
            {
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
                                notifyEmail(adminName, adminLname, adminEmail, user, "S.O.S.");
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
                                        notifyEmail(adminName, adminLname, adminEmail, user, "ESCALATION LEVEL " + i.ToString());
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
            catch(InvalidOperationException e)
            {
                Console.WriteLine(e);
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
                //escalationSound.Stop();
            };
            //make window notification
            

            string msg = fullName + " might be in trouble\n\n\n" +
                "View more info about " + user.Firstname + "'s escalation alert?!";
           
            if (MessageBox.Show(msg, es,
                 MessageBoxButtons.YesNo, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                 == DialogResult.Yes)
             {
                 // if ok is pressed, set sos back to 0
                
                esInfo = new AlertES(user, es);

                Thread.Sleep(3000); //new untested
                Connection.notified(url, user.Username, checkin, es);
                escalationSound.Stop();
                Thread.Sleep(3000);

                
                esInfo.Show();
            }
            else
            {
                
                Connection.notified(url, user.Username, checkin, es);
                escalationSound.Stop();
                Thread.Sleep(3000);
               
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
                "View more info about " + user.Firstname + "'s SOS alert?!";
            
            

            if (MessageBox.Show(msg, "SOS ALERT!!",
                MessageBoxButtons.YesNo, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                == DialogResult.Yes)
            {
                sosInfo = new AlertSO(user);
                //construct
                Thread.Sleep(3000); //new untested

                Connection.noMoreSOS(url, user.Username); // if ok is pressed, set sos back to 0
                sosSound.Stop();
                Thread.Sleep(3000);

                //show
                sosInfo.Show();
            }
            else
            {
                Connection.noMoreSOS(url, user.Username);
                sosSound.Stop();
                Thread.Sleep(3000);
                Console.WriteLine("open window");
            }
            
        }
        
        

        //email notification
        private void notifyEmail(string adminName, string adminLname, string adminEmail, LiveUser user, string eventType)
        {
            Connection.email(url, eventType, adminName, adminLname, user.Firstname, user.Lastname, user.Username, user.EmTime, user.Risklevel.ToString(), user.StartTime, user.EndTime, user.Mobile, user.Email, user.Phone, user.Rego, user.Longitude, user.Latitude, adminEmail);
 
        }
    }
}
