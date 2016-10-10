using System;
using System.Windows.Forms;
using System.Net;
using System.Text.RegularExpressions;
using LoneSafeLib;

namespace LoneSafe
{
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
            
            this.AcceptButton = loginButton;
        }

        private User user;
        private Connection conn;
        public Server server = new Server("http://202.89.41.210");
        

       
        private void loginButton_Click(object sender, EventArgs e)
        {
            
            conn = new Connection(server.GetURL, userInput.Text, passInput.Text);

            user = conn.User;

            if (user.Success)
            {

                if(user.Manager)
                {
                    MainWindow main = new MainWindow(user, this);
                    main.Show();

                    this.Hide();
                }
                else
                {
                    MessageBox.Show("You dont have permission to access this application\nIf you believe this is a mistake, please contact the system admin",
                        "Warning!",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Warning
                        );
                }
                
            }
            else
            {
                MessageBox.Show("Login Failed!\nPlease check your password and username.",
                    "Something is wrong..",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
            }

    
        }

        private void exit_MouseHover(object sender, EventArgs e)
        {
            exit.ForeColor = System.Drawing.Color.Red;
        }

        private void exit_MouseLeave(object sender, EventArgs e)
        {
            exit.ForeColor = System.Drawing.Color.White;
        }

        private void exit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
