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

        public static string URL = "http://202.89.41.210";


        private void loginButton_Click(object sender, EventArgs e)
        {
            conn = new Connection(URL, userInput.Text, passInput.Text);

            if (conn.Success)
            {
                user = conn.User;

                if(user.IsAdmin)
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

    }
}
