using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using LoneSafeLib;

namespace LoneSafe
{
    public partial class DeleteUser : Form
    {
        private string url;

        public DeleteUser(string URL)
        {
            InitializeComponent();
            this.url = URL;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {

            string uname = userInput.Text;
            User user = new User();

            user = User.Check(url, uname);

            if (user.Success)
            {
                string msg = "Are you sure you would like to remove\n" + user.Firstname + " " + user.Lastname + " from the system?";

                DialogResult confirm = MessageBox.Show(msg, "Confirmation", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                this.Enabled = false;
                this.Opacity = 50;

                if (confirm == DialogResult.Yes)
                {
                    User.Delete(url, uname);
                    user = null; //remove the temp object
                    this.Close();
                }
                else
                {
                    this.Enabled = true;
                    this.Opacity = 100;
                }
            }

            else
            {
                user = null; //remove the temp object
                lblError.Text = "User " + uname + " doesnt exist in the system";
                userInput.BackColor = Color.MistyRose;
            }
        }

        private void userInput_TextChanged(object sender, EventArgs e)
        {
            lblError.Text = "";
            userInput.BackColor = Color.White;
        }
    }
}
