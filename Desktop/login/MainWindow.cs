using System;
using System.Windows.Forms;
using LoneSafeLib;
using System.Threading.Tasks;
using System.Collections.Generic;
using System.Media;
using System.Drawing;

namespace LoneSafe
{
    public partial class MainWindow : Form
    {
        Login login; //create a new version of login
        User user;
        Server server;
        LiveData liveData;

        //List<LiveUser> newList = new List<LiveUser>();
        


        public MainWindow(User user, Login login)
        //public MainWindow(User user)
        {
            InitializeComponent();

            

            this.user = user;
            this.login = login; //this will copy the login hidden window across
            this.server = login.server;
            this.liveData = new LiveData(server.GetURL);
            this.FormClosing += MainWindow_FormClosing; //create a closing window event

           InitTimer();

            
           

        }
       


        //close the hidden window aka login
        private void MainWindow_FormClosing(object sender, FormClosingEventArgs e)
        {
            login.Close();
        }

        private void MainWindow_Load(object sender, EventArgs e)
        {
            this.Text = "LoneSafe - Logged in as: " + user.Username;
            greetings.Text = "Hello " + user.Firstname + " " + user.Lastname;


            DisableGridviewSorting(dataGrid, dataGrid.Columns.Count);



        }

        public void DisableGridviewSorting(DataGridView grid, int index)
        {
            //Index = DataGridView.Columns.Count
            for (int i = 0; i < index; i++)
            {
                grid.Columns[i].SortMode = DataGridViewColumnSortMode.NotSortable;
            }
        }

        //new timer
        private Timer timer1;
        public void InitTimer()
        {
            timer1 = new Timer();
            timer1.Tick += new EventHandler(timer1_Tick);
            timer1.Interval = 5000; // in miliseconds
            timer1.Start();
        }
        
        private void timer1_Tick(object sender, EventArgs e)
        {

            liveData.update();
            
            if(dataGrid != null)
            {
                dataGrid.Rows.Clear();
                
            }

            //foreach (LiveUserJson liveUser in liveData.LiveUsersList)
            for (int r = 0; r < liveData.LiveUsersList.Count; r++) //rows as big as the list
            {
               
                dataGrid.Rows.Insert(r, liveData.LiveUsersList[r].LiveUser.Firstname + " " + liveData.LiveUsersList[r].LiveUser.Lastname, 
                    liveData.LiveUsersList[r].LiveUser.Risklevel, liveData.LiveUsersList[r].LiveUser.Checkin1, 
                    liveData.LiveUsersList[r].LiveUser.Checkin2, liveData.LiveUsersList[r].LiveUser.Checkin3, 
                    liveData.LiveUsersList[r].LiveUser.Checkin4, liveData.LiveUsersList[r].LiveUser.Checkin5, 
                    liveData.LiveUsersList[r].LiveUser.Checkin6, liveData.LiveUsersList[r].LiveUser.Checkin7, 
                    liveData.LiveUsersList[r].LiveUser.Checkin8);

                if(liveData.LiveUsersList[r].LiveUser.NeedSOS == 1)
                {
                    SystemSounds.Hand.Play();
                    //Console.Beep();
                    notifyIcon1.Visible = true;
                    notifyIcon1.Icon = SystemIcons.Application;
                    notifyIcon1.BalloonTipTitle = "SOS NEEDED";
                    notifyIcon1.BalloonTipText = liveData.LiveUsersList[r].LiveUser.Firstname + " " +
                        liveData.LiveUsersList[r].LiveUser.Lastname + " needs your help";
                    notifyIcon1.BalloonTipIcon = ToolTipIcon.Warning;
                    notifyIcon1.ShowBalloonTip(10000);

                    if ( MessageBox.Show(liveData.LiveUsersList[r].LiveUser.Firstname + " " +
                        liveData.LiveUsersList[r].LiveUser.Lastname + " needs your help", "SOS NEEDED",
                        MessageBoxButtons.OK, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1, MessageBoxOptions.ServiceNotification)
                        == DialogResult.OK)
                    {
                       // if ok is pressed, set sos back to 0






                    }
                    
                }
                
            }
            


        }


        //timer ends
        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            login.Close();
        }

        private void logOutToolStripMenuItem_Click(object sender, EventArgs e)
        {

            //Reset all the values here


            this.Hide();
            login.Show();
        }

        private void addANewLoneWorkerToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //open add a new lone worker form

            AddUser add = new AddUser(server.GetURL);
            add.Show();

        }

        private void deleteAUserToolStripMenuItem2_Click(object sender, EventArgs e)
        {
            DeleteUser del = new DeleteUser(server.GetURL);
            del.Show();
        }

        private void deleteAUserToolStripMenuItem_Click(object sender, EventArgs e)
        {
            EditUser edt = new EditUser(server.GetURL);
            edt.Show();
        }

        private void usersToolStripMenuItem_Click(object sender, EventArgs e)
        {
            

            UsersView usrView = new UsersView(server.GetURL);
            usrView.Show();
        }

        private void reportToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            Report rp = new Report(server.GetURL);
            rp.Show();
        }
    }
}
