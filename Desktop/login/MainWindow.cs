using System;
using LoneSafeLib;
using System.Windows.Forms;

namespace LoneSafe
{
    public partial class MainWindow : Form
    {

        private Login login; //create a new version of login
        private User user;
        private Server server;
        private LiveDataControl LDC;


        public MainWindow(User user, Login login)
        {
            InitializeComponent();

            
            this.user = user;
            this.login = login; //this will copy the login hidden window across
            this.server = login.server;
            this.FormClosing += MainWindow_FormClosing; //create a closing window event
            
            LDC = new LiveDataControl(server.GetURL, dataGrid, this.Icon, user.Firstname, user.Email);
            
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
        
        
        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            login.Close();
        }

        private void logOutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Hide();
            login.Show();
        }

        private void addANewLoneWorkerToolStripMenuItem_Click(object sender, EventArgs e)
        {
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
            Report rp = new Report(server.GetURL, user);
            rp.Show();
        }

        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            About ab = new About();
            ab.Show();
        }

        private void manualToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                System.Diagnostics.Process.Start(@"res\manual.pdf");
            }
            catch(SystemException er)
            {
                Console.WriteLine(er);
            }
            
        }

        private void contactToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Contact cc = new Contact();
            cc.Show();
        }
    }
}
