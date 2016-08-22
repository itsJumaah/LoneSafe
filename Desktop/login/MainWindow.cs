using System;
using System.Windows.Forms;
using LoneSafeLib;
using System.ComponentModel;

namespace LoneSafe
{
    public partial class MainWindow : Form
    {
        Login login; //create a new version of login
        User user;

        RequestData rd;


        public MainWindow(User user, Login login)
        {
            InitializeComponent();

            this.user = user;
            this.login = login; //this will copy the login hidden window across

            this.FormClosing += MainWindow_FormClosing; //create a closing window event


           

            rd = new RequestData(Login.URL + "/displayData.php");

            Console.WriteLine(rd.Data);


        }

        //close the hidden window aka login
        private void MainWindow_FormClosing(object sender, FormClosingEventArgs e)
        {
            login.Close();
        }

        private void MainWindow_Load(object sender, EventArgs e)
        {
            this.Text = "LoneSafe - Logged in as: " + user.Username;
            greetings.Text = "Hello " + user.Name;

            dataGrid.Hide();
            setupProgressBar();

        }

        private void setupProgressBar()
        {
            progBar.Maximum = 100;
            progBar.Step = 1;
            progBar.Value = 0;

            backgroundWorker.RunWorkerAsync();
        }

        private void backgroundWorker_DoWork(object sender, DoWorkEventArgs e)
        {
            backgroundWorker = sender as BackgroundWorker;

            int loop = 99999;
            //the loop to generate the data
            for(int i=0; i < loop; i++)
            {
                //do stuff
                

                //report progress
                
                backgroundWorker.ReportProgress((i * 100) / loop);
            }
        }

        private void backgroundWorker_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progBar.Value = e.ProgressPercentage;

        }

        private void backgroundWorker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            //todo: make the thing show now

            progBar.Value = 0;
            progBar.Visible = false;

            dataGrid.Show();
        }
    }
}
