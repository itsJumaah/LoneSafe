using System;
using System.Drawing;
using System.Windows.Forms;
using LoneSafeLib;

namespace LoneSafe
{
    public partial class AddUser : Form
    {
        private string url;

        public AddUser(string URL)
        {
            InitializeComponent();
            this.url = URL;
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            
            this.Close();
        }


        private void btnDone_Click(object sender, EventArgs e)
        {

            User user = new User();

            user = User.Check(url, uname.Text);

            if (fname.Text == "" || lname.Text == "" || uname.Text == "" || password.Text == "" || title.SelectedIndex == -1 || password.Text != password2.Text || user.Success)
            {

                //error checking
                if (fname.Text == "")
                {
                    fname.BackColor = Color.MistyRose;
                    rerror.Text = "Required fields";
                }
                if (lname.Text == "")
                {
                    lname.BackColor = Color.MistyRose;
                    rerror.Text = "Required fields";
                }
                if (uname.Text == "")
                {
                    uname.BackColor = Color.MistyRose;
                    rerror.Text = "Required fields";
                }
                if (password.Text == "")
                {
                    password.BackColor = Color.MistyRose;
                    rerror.Text = "Required fields";
                }
                if (title.SelectedIndex == -1)
                {
                    title.BackColor = Color.MistyRose;
                    rerror.Text = "Required fields";
                }

                //password match
                if (password.Text != password2.Text)
                {
                    password2.BackColor = Color.MistyRose;
                    perror.Text = "Password doesn't match";
                }

                //username error checking
                if (user.Success)
                {
                    uname.BackColor = Color.MistyRose;
                    uerror.Text = "Username " + uname.Text + " already exists in the system";
                }
            }

            else if (title.SelectedIndex == 1 && email.Text == "")
            {

                email.BackColor = Color.MistyRose;
                rerror.Text = "You must set an email for the manager";
            }

            else if (title.SelectedIndex == 0 && mobile.Text == "")
            {

                mobile.BackColor = Color.MistyRose;
                rerror.Text = "You must enter a mobile number for the lone worker";
            }

            else
            {
                User.Add(url, fname.Text, lname.Text, uname.Text, password.Text, email.Text, rego.Text, mobile.Text, home.Text, title.SelectedIndex);

                user = null;

                this.Close();
            }
                

            


        }

        private void fname_TextChanged(object sender, EventArgs e)
        {
            fname.BackColor = Color.White;
            rerror.Text = "";
        }

        private void lname_TextChanged(object sender, EventArgs e)
        {
            lname.BackColor = Color.White;
            rerror.Text = "";
        }

        private void uname_TextChanged(object sender, EventArgs e)
        {
            uname.BackColor = Color.White;
            rerror.Text = "";
            uerror.Text = "";
        }

        private void password_TextChanged(object sender, EventArgs e)
        {
            password.BackColor = Color.White;
            rerror.Text = "";
            perror.Text = "";
        }

        private void password2_TextChanged(object sender, EventArgs e)
        {
            password2.BackColor = Color.White;
            rerror.Text = "";
            perror.Text = "";
        }

        private void title_SelectedValueChanged(object sender, EventArgs e)
        {
            title.BackColor = Color.White;
            rerror.Text = "";
        }

        private void email_TextChanged(object sender, EventArgs e)
        {
            email.BackColor = Color.White;
            rerror.Text = "";
        }

        private void mobile_TextChanged(object sender, EventArgs e)
        {
            mobile.BackColor = Color.White;
            rerror.Text = "";
        }
    }
}
