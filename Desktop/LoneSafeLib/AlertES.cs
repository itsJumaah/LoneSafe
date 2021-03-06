﻿using System;
using System.Windows.Forms;

namespace LoneSafeLib
{
    internal partial class AlertES : Form
    {
        private LiveUser user = null;
        private string type = "";
        

        public AlertES(LiveUser user, string es)
        {
            InitializeComponent();
            
            this.user = user;
            this.type = es;

            setup(user, es);
        }
        
        private void setup(LiveUser user, string es)
        {
            this.Text = es + " Info";
            header.Text = user.Firstname + "'s " + es;

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

            if(user.Longitude == null || user.Longitude == "" || user.Latitude == null || user.Latitude == "")
            {
                longitude.Text = "UNKNOWN";
                latitude.Text = "UNKNOWN";


                var page = @"
                     <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>
				    <html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>
				    
				    <body>
				    <br/>
                    <br/>
                    <center>
                        Location is not available.
                    </center>

				    </body>
				    </html>";
                map.DocumentText = page;

                map.ScriptErrorsSuppressed = true;
                map.ScrollBarsEnabled = false;

            }
            else
            {
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
            
        }

        private void dismiss_Click(object sender, EventArgs e)
        {
            this.Dispose();
            this.Close();
        }

    }
}
