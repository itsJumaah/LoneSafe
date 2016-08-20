using System;
using System.Windows.Forms;
using LoneSafeLib;


namespace LoneSafe
{
    public partial class MainWindow : Form
    {
        Login login; //create a new version of login
        User user;

        public MainWindow(User user, Login login)
        {
            InitializeComponent();

            this.user = user;
            this.login = login; //this will copy the login hidden window across

            this.FormClosing += MainWindow_FormClosing; //create a closing window event

        }

        //close the hidden window aka login
        private void MainWindow_FormClosing(object sender, FormClosingEventArgs e)
        {
            login.Close();
        }

        private void MainWindow_Load(object sender, EventArgs e)
        {
            this.Text = "LoneSafe - Logged in as: " + user.Username;
            toolStripStatusLabel1.Text = "Hello " + user.Name;

           
            

        }
        
        
    }
}
