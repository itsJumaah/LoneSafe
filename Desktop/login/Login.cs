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

        public User user;

        private bool connect(string username, string password)
        {


            string URL = "http://202.89.41.210/login.php";
            string param = "username=" + username + "&password=" + password;

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(URL, param);

            string[] words = Regex.Split(HtmlResult, @"([A-Za-z0-9_\-]+)");
            
           
            bool success =  Convert.ToBoolean(words[3]);
           

            if (success)
            {
                string name = words[7];
                int x = Int32.Parse(words[27]);
                bool isAdmin = Convert.ToBoolean(x);

                user = new User(username, password, name, isAdmin);
                
                return true;
            }
            else
            {
                return false;
            }


        }
        
        private void loginButton_Click(object sender, EventArgs e)
        {
            if (connect(userInput.Text, passInput.Text))
            {
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
