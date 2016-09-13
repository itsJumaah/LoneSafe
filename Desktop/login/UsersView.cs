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
    public partial class UsersView : Form
    {
        private string url;
        List<DisplayUserJson> usersList = new List<DisplayUserJson>();
        

        public UsersView(string url)
        {
            InitializeComponent();
            this.url = url;

            usersList = Connection.allUsersList(url);
        }


        private void UsersView_Load(object sender, EventArgs e)
        {


            foreach (DisplayUserJson user in usersList)
            {
                //if the user is not a manager list them into the lone workers list
                if(user.DisplayUser.Manager == 0)
                {
                    ListViewItem u = new ListViewItem(user.DisplayUser.Username);
                    u.Text = user.DisplayUser.Firstname + " " + user.DisplayUser.Lastname;
                    u.Tag = user;
                    loneworkersList.Items.Add(u);
                }
                //if not then they're a manager
                else
                {
                    ListViewItem u = new ListViewItem(user.DisplayUser.Username);
                    u.Text = user.DisplayUser.Firstname + " " + user.DisplayUser.Lastname;
                    u.Tag = user;
                    managersList.Items.Add(u);
                }
                
            }
            
        }

        private void loneworkersList_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            foreach (DisplayUserJson user in usersList)
            {
                if(e.Item.Tag.Equals(user))
                {
                    fname.Text = user.DisplayUser.Firstname;
                    lname.Text = user.DisplayUser.Lastname;
                    uname.Text = user.DisplayUser.Username;
                    mobile.Text = user.DisplayUser.Mobile;
                    home.Text = user.DisplayUser.Phone;
                    email.Text = user.DisplayUser.Email;
                    rego.Text = user.DisplayUser.Rego;

                    if(user.DisplayUser.Onjob == 1)
                    {
                        onJob.Text = "Currently on the field";
                    }
                    else
                    {
                        onJob.Text = "";
                    }
                }
                
            }

        }

        private void managersList_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            foreach (DisplayUserJson user in usersList)
            {
                if (e.Item.Tag.Equals(user))
                {
                    mfname.Text = user.DisplayUser.Firstname;
                    mlname.Text = user.DisplayUser.Lastname;
                    muname.Text = user.DisplayUser.Username;
                    mmobile.Text = user.DisplayUser.Mobile;
                    mhome.Text = user.DisplayUser.Phone;
                    memail.Text = user.DisplayUser.Email;
                    mrego.Text = user.DisplayUser.Rego;
                }

            }
        }
    }
    
    
}
