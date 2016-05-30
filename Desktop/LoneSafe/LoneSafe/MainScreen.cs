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
using MySql.Data.MySqlClient;


namespace LoneSafe
{
    public partial class MainActivity : Form
    {
        public MainActivity()
        {
            Thread loading = new Thread(new ThreadStart(StartLoading));
            loading.Start();
            Thread.Sleep(1000);


            InitializeComponent();

            loading.Abort();
          
        }

        public void StartLoading()
        {
            Application.Run(new LoadingScreen());
        }


        public void linkDatabase()
        {
          
            string connString = "server=mysql8.000webhost.com;database=a2076561_DB;uid=root;pwd=LonesafeDB16";

            MySqlConnection conn = new MySqlConnection(connString);
            MySqlCommand command = conn.CreateCommand();
            command.CommandText = "SELECT name FROM user WHERE id =2";
            try
            {
                conn.Open();
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

            MySqlDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {


                button1.Text = reader["name"].ToString();

         
            }
            Console.ReadLine();
          

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

        private void tableLayout_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
