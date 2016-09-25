using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LoneSafe
{
    public partial class Contact : Form
    {
        string subject = "?subject=[LoneSafe] Query"; 
        public Contact()
        {
            InitializeComponent();
        }

        private void label3_Click(object sender, EventArgs e)
        {
            string command = "mailto:bilal.j@me.com" + subject;
            Process.Start(command);
        }


    }
}
