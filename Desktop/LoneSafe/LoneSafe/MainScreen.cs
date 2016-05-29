using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;

namespace LoneSafe
{
    public partial class MainActivity : Form
    {
        public MainActivity()
        {
            Thread loading = new Thread(new ThreadStart(StartLoading));
            loading.Start();
            Thread.Sleep(1500);


            InitializeComponent();

            loading.Abort();
        }

        public void StartLoading()
        {
            Application.Run(new LoadingScreen());
        }

        private void MainActivity_Load(object sender, EventArgs e)
        {

        }


        //About pressed
        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            About about = new About();

            about.Show();
        }


    }
}
