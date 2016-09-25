using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Media;
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
        private NotifyIcon notifyIcon1;
        private Icon icon;

        private List<LiveUser> newList = new List<LiveUser>();

        private delegate void InvokeDelegate();

        public LiveDataControl(string url, DataGridView dataGrid, NotifyIcon notifyIcon1, Icon icon)
        {
            this.url = url;
            this.icon = icon;
            this.liveData = new LiveData(url);
            this.dataGrid = dataGrid;
            this.notifyIcon1 = notifyIcon1;

            worker = new BackgroundWorker();
            worker.DoWork += worker_DoWork;

            System.Timers.Timer timer = new System.Timers.Timer(5000);
            timer.Elapsed += timer_Elapsed;
            timer.Start();

        }



        void timer_Elapsed(object sender, ElapsedEventArgs e)
        {
            if (!worker.IsBusy)
            {
                worker.RunWorkerAsync();
            }

        }

        void worker_DoWork(object sender, DoWorkEventArgs e)
        {
            liveData.update(); //grab the new data
            //reset();
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

        private void live()
        {
            reset();
            //load the list to the data grid
            foreach (LiveUser user in newList)
            {
                //string fullName = user.Firstname + " " + user.Lastname;
                
                dataGrid.Rows.Add(user.Firstname + " " + user.Lastname, user.Risklevel,
                    user.Checkin1, user.Checkin2, user.Checkin3, user.Checkin4,
                    user.Checkin5, user.Checkin6, user.Checkin7, user.Checkin8);
                
                //if sos is = 1 make notifications
                if (user.NeedSOS == 1)
                {
                    sos(user.Firstname + " " + user.Lastname, user.Username);
                }

                //if checkin 1 is escalated
                if(user.Checkin1 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin1", 1);
                }
                else if(user.Checkin1 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin1", 2);
                }
                else if (user.Checkin1 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin1", 3);
                }

                //if checkin 2 is escalated
                if (user.Checkin2 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin2", 1);
                }
                else if (user.Checkin2 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin2", 2);
                }
                else if (user.Checkin2 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin2", 3);
                }

                //if checkin 3 is escalated
                if (user.Checkin3 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin3", 1);
                }
                else if (user.Checkin3 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin3", 2);
                }
                else if (user.Checkin3 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin3", 3);
                }

                //if checkin 4 is escalated
                if (user.Checkin4 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin4", 1);
                }
                else if (user.Checkin4 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin4", 2);
                }
                else if (user.Checkin4 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin4", 3);
                }

                //if checkin 5 is escalated
                if (user.Checkin5 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin5", 1);
                }
                else if (user.Checkin5 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin5", 2);
                }
                else if (user.Checkin5 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin5", 3);
                }

                //if checkin 6 is escalated
                if (user.Checkin6 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin6", 1);
                }
                else if (user.Checkin6 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin6", 2);
                }
                else if (user.Checkin6 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin6", 3);
                }

                //if checkin 7 is escalated
                if (user.Checkin7 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin7", 1);
                }
                else if (user.Checkin7 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin7", 2);
                }
                else if (user.Checkin7 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin7", 3);
                }

                //if checkin 8 is escalated
                if (user.Checkin8 == "l1")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin8", 1);
                }
                else if (user.Checkin8 == "l2")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin8", 2);
                }
                else if (user.Checkin8 == "l3")
                {
                    notification(user.Firstname + " " + user.Lastname, user.Username, "checkin8", 3);
                }

            }
        }
        //escalation notifications
        private void notification(string fullName, string username, string checkin, int esc)
        {
            string es = "Escalation " + esc.ToString();

            SystemSounds.Hand.Play(); //play windows beep
                                      //Console.Beep();
                                      //make windows notification in the tool bar
            notifyIcon1.Visible = true;
            notifyIcon1.Icon = icon;// SystemIcons.Application;
            notifyIcon1.BalloonTipTitle = es;
            notifyIcon1.BalloonTipText = fullName + " might be in trouble";
            notifyIcon1.BalloonTipIcon = ToolTipIcon.Warning;
            notifyIcon1.ShowBalloonTip(10000);
            //make window notification
            if (MessageBox.Show(fullName + " might be in trouble\n Please contact him", es,
                MessageBoxButtons.OK, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                == DialogResult.OK)
            {
                // if ok is pressed, set sos back to 0
                Connection.notified(url, username, checkin, es);
            }
        }
        //sos notifications
        private void sos(string fullName, string username)
        {
            SystemSounds.Hand.Play(); //play windows beep
                                      //Console.Beep();
                                      //make windows notification in the tool bar
            notifyIcon1.Visible = true;
            notifyIcon1.Icon = icon;// SystemIcons.Application;
            notifyIcon1.BalloonTipTitle = "SOS NEEDED";
            notifyIcon1.BalloonTipText = fullName + " needs your help";
            notifyIcon1.BalloonTipIcon = ToolTipIcon.Error;
            notifyIcon1.ShowBalloonTip(10000);
            //make window notification
            if (MessageBox.Show(fullName + " needs your help", "SOS NEEDED",
                MessageBoxButtons.OK, MessageBoxIcon.Error, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                == DialogResult.OK)
            {
                // if ok is pressed, set sos back to 0
                Connection.noMoreSOS(url, username);
            }
        }
    }
}
