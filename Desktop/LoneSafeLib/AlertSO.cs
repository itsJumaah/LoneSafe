using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Media;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LoneSafeLib
{
    internal partial class AlertSO : Form
    {
        private LiveUser user = null;

        public AlertSO(LiveUser user)
        {
            InitializeComponent();
            
            this.user = user;

            setup(user);
        }
        
        private void setup(LiveUser user)
        {
            this.Text = "SOS Info";
            header.Text = user.Firstname + "'s SOS";

            fname.Text = user.Firstname;
            lname.Text = user.Lastname;
            username.Text = user.Username;

            if (user.Mobile != "" || user.Mobile != null)
            {
                mobile.Text = user.Mobile;
            }
            else
            {
                mobile.Text = "N/A";
            }
            
            if (user.Rego != "" || user.Rego != null)
            {
                rego.Text = user.Rego;
            }
            else
            {
                rego.Text = "N/A";
            }

            if (user.Phone != "" || user.Phone != null)
            {
                phone.Text = user.Phone;
            }
            else
            {
                phone.Text = "N/A";
            }

            if (user.Email != "" || user.Email != null)
            {
                email.Text = user.Email;
            }
            else
            {
                email.Text = "N/A";
            }

            longitude.Text = user.Longitude;
            latitude.Text = user.Latitude;

            var page = @"
                 <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>
				<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>
				<head>
				<style>
				*{margin:0;padding:0}
				html, body {height:100%;width:100%;overflow:hidden}
				table {height:100%;width:100%;table-layout:static;border-collapse:collapse}
				iframe {height:100%;width:100%}

				.content {height:100%}
				</style>
				</head>
				<body>
				<table>
                    <tr><td class='content'>
                        <iframe

                            src='https://www.google.com/maps/embed/v1/place?key=AIzaSyBD7AjCRto1zWiZ1D3cW5JZZpz_ZoMBgDY&q=loc:" + user.Latitude + "," + user.Longitude +


                            @"' frameborder='0'>
                        </iframe>
                    </td></tr>
				</table>
				</body>
				</html>";
            map.DocumentText = page;

            map.ScriptErrorsSuppressed = true;
            map.ScrollBarsEnabled = false;



        }

        private void dismiss_Click(object sender, EventArgs e)
        {
            this.Dispose();
            this.Close();
        }

    }
}
