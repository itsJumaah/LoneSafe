using System;
using System.Windows.Forms;
using LoneSafeLib;

namespace LoneSafe
{
    public partial class EditWindow : Form
    {
        private EditUser editUser;
        private string url;
        private User user;


        public EditWindow(string url, User user, EditUser editUser)
        {
            InitializeComponent();
            this.url = url;
            this.user = user;
            this.editUser = editUser;
        }

        private void btnDone_Click(object sender, EventArgs e)
        {
            User.Edit(url, fname.Text, lname.Text, uname.Text, email.Text, rego.Text, mobile.Text, home.Text);
            editUser.Close();
            user = null;
            this.Close();
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            editUser.Show();
            user = null;
            this.Close();
        }

        private void EditWindow_Load(object sender, EventArgs e)
        {
            editUser.Hide();

            this.Text = "Editing " + user.Username + " details";

            fname.Text = user.Firstname;
            lname.Text = user.Lastname;

            uname.Text = user.Username;
            password.Text = "password";
            password2.Text = "password";

            if (user.Manager)
            {
                title.SelectedIndex = 1;
            }
            else
            {
                title.SelectedIndex = 0;
            }

            mobile.Text = user.Mobile;
            home.Text = user.Phone;
            email.Text = user.Email;
            rego.Text = user.Rego;


        }
    }
}
