using LoneSafeLib;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LoneSafe
{
    public partial class EditUser : Form
    {
        private string url;
        public EditUser(string URL)
        {
            InitializeComponent();
            this.url = URL;
        }

        private void cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void userInput_TextChanged(object sender, EventArgs e)
        {
            error.Text = "";
            userInput.BackColor = Color.White;
        }

        private void edit_Click(object sender, EventArgs e)
        {
            string uname = userInput.Text;
            User user = new User();

            user = User.Check(url, uname);

            if (user.Success)
            {
                EditWindow edit = new EditWindow(url, user, this);
                edit.Show();
                
            }

            else
            {
                user = null; //remove the temp object
                error.Text = "User " + uname + " doesnt exist in the system";
                userInput.BackColor = Color.MistyRose;
            }
        }
    }
}
