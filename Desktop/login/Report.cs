using iTextSharp.text;
using iTextSharp.text.pdf;
using LoneSafeLib;
using System;
using System.IO;
using System.Windows.Forms;

namespace LoneSafe
{
    public partial class Report : Form
    {
        private string url;
        private ReportData reportData;

        public Report(string url)
        {
            InitializeComponent();
            this.url = url;
            reportData = new ReportData(url);
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

            if(folderPath != null)
            {

                //Creating iTextSharp Table from the DataTable data
                PdfPTable pdfTable = new PdfPTable(reportGrid.ColumnCount);
                pdfTable.DefaultCell.Padding = 3;
                pdfTable.WidthPercentage = 100;
                // pdfTable.HorizontalAlignment = Element.ALIGN_LEFT;
                pdfTable.HorizontalAlignment = Element.ALIGN_CENTER;
               // pdfTable.DefaultCell.BorderWidth = 1;
               

                BaseFont bf = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);
                Font font = new Font(bf, 7); //font size
                
                //Adding Header row
                foreach (DataGridViewColumn column in reportGrid.Columns)
                {

                    
                    
                    //PdfPCell cell = new PdfPCell(new Phrase(column.HeaderText));
                    PdfPCell cell = new PdfPCell(new Phrase(column.HeaderText, font));
                    cell.BackgroundColor = new Color(0, 200, 220);

                    pdfTable.AddCell(cell);
                }

                //Adding DataRow
                foreach (DataGridViewRow row in reportGrid.Rows)
                {
                    foreach (DataGridViewCell cell in row.Cells)
                    {
                        if(cell.Value == null)
                        {
                            cell.Value = "";
                        }
                        PdfPCell cl = new PdfPCell(new Phrase(cell.Value.ToString(), font));
                        //pdfTable.AddCell(cell.Value.ToString());
                        pdfTable.AddCell(cl);
                    }
                }

                //Exporting to PDF
                using (FileStream stream = new FileStream(folderPath, FileMode.Create))
                {
                    Document pdfDoc = new Document(PageSize.A4, 10f, 10f, 10f, 0f);
                    PdfWriter.GetInstance(pdfDoc, stream);
                    pdfDoc.Open();
                    pdfDoc.Add(pdfTable);
                    pdfDoc.Close();
                    stream.Close();
                }
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
