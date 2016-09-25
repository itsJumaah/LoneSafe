using LoneSafeLib;
using System;
using System.Windows.Forms;

namespace LoneSafe
{
    public partial class Report : Form
    {
        private string url;
        private ReportData reportData;
        private GenerateReport generateReport;
        private User user;

        public Report(string url, User user)
        {
            InitializeComponent();
            this.url = url;
            reportData = new ReportData(url);
            this.user = user;
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            
            string folderPath = null;

            SaveFileDialog x = new SaveFileDialog();
            x.Filter = "Portable Document Format (*.pdf) | *.pdf | All files(*.*) | *.*";
            if (x.ShowDialog() == DialogResult.OK)
            {
                folderPath = x.FileName; //Use here x.FileName
            }

            if (folderPath != null)
            {
                generateReport = new GenerateReport(reportGrid, folderPath, select.SelectedIndex, user);
            }

        }

        private void btnGen_Click(object sender, EventArgs e)
        {
            btnSave.Enabled = true;
            reportGrid.Enabled = true;

            string date = "";
            string lastweek = DateTime.Today.AddDays(-7).ToString("yyyy-MM-dd"); //0
            string lastmonth = DateTime.Today.AddMonths(-1).ToString("yyyy-MM-dd"); //1
            string lastyear = DateTime.Today.AddYears(-1).ToString("yyyy-MM-dd"); //2

            if(select.SelectedIndex == -1)
            {
                date = "";
                MessageBox.Show("Please select a report type");
            }

            else if (select.SelectedIndex == 0)
            {
                date = lastweek;
            }
            else if (select.SelectedIndex == 1)
            {
                date = lastmonth;
            }
            else if (select.SelectedIndex == 2)
            {
                date = lastyear;
            }

            if (date != "")
            {
                reportData.update(date);

                //foreach (LiveUserJson liveUser in liveData.LiveUsersList)
                for (int r = 0; r < reportData.ReportUsersList.Count; r++) //rows as big as the list
                {

                    reportGrid.Rows.Insert(r,
                        reportData.ReportUsersList[r].ReportUser.Date,
                        reportData.ReportUsersList[r].ReportUser.Firstname
                        + " " + reportData.ReportUsersList[r].ReportUser.Lastname,
                        reportData.ReportUsersList[r].ReportUser.WorkingHours,
                        reportData.ReportUsersList[r].ReportUser.Risklevel,

                        reportData.ReportUsersList[r].ReportUser.Checkin1,
                        reportData.ReportUsersList[r].ReportUser.Checkin2, reportData.ReportUsersList[r].ReportUser.Checkin3,
                        reportData.ReportUsersList[r].ReportUser.Checkin4, reportData.ReportUsersList[r].ReportUser.Checkin5,
                        reportData.ReportUsersList[r].ReportUser.Checkin6, reportData.ReportUsersList[r].ReportUser.Checkin7,
                        reportData.ReportUsersList[r].ReportUser.Checkin8);


                }
            }
            
        }

        private void Report_Load(object sender, EventArgs e)
        {
            reportGrid.Enabled = false;
            btnGen.Enabled = false;
            btnSave.Enabled = false;
        }

        private void select_SelectedIndexChanged(object sender, EventArgs e)
        {
            btnGen.Enabled = true;
            reportGrid.Rows.Clear();
            btnSave.Enabled = false;
            reportGrid.Enabled = false;

        }
    }
}
