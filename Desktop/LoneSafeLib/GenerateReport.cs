using iTextSharp.text;
using iTextSharp.text.pdf;
using System;
using System.IO;
using System.Windows.Forms;

namespace LoneSafeLib
{
    public class GenerateReport
    {
        private string type;
        private User user;

        public GenerateReport(DataGridView reportGrid, string folderPath, int typee, User user)
        {
            this.user = user;
            this.type = selectType(typee);

            //title
            Font titleFont = FontFactory.GetFont("Corbel", 14);
            Paragraph title = new Paragraph("LoneSafe Report\n\n", titleFont);
            title.Alignment = Element.ALIGN_CENTER;

            //key words
            Font detailsFont = FontFactory.GetFont("Arial", 7);
            Paragraph keywords = new Paragraph("DR = Duration in minutes | RL = Level of Risk (1 lowest - 5 highest)", detailsFont);
            keywords.Alignment = Element.ALIGN_LEFT;

            //details
            string currentDate = DateTime.Now.ToString();
            //Font detailsFont = FontFactory.GetFont("Arial", 7);
            Paragraph details = new Paragraph(type + " report created by: " + user.Firstname +" "+ user.Lastname + " (" + user.Username +") " +" on " + currentDate, detailsFont);
            details.Alignment = Element.ALIGN_CENTER;


            float[] widthpercentages = new float[] {
            120f, 200f, 40f, 40f, //date name duration risklevel
            100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f}; //checkins

            //Creating iTextSharp Table from the DataTable data
            PdfPTable pdfTable = new PdfPTable(reportGrid.ColumnCount);
            pdfTable.DefaultCell.Padding = 3;
            pdfTable.WidthPercentage = 100;
            pdfTable.HorizontalAlignment = Element.ALIGN_CENTER;
            pdfTable.SetWidths(widthpercentages);


            //BaseFont bf = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);
            //Font font = new Font(bf, 9); //font size
            Font font = FontFactory.GetFont("Arial", 8);

            //Adding Header row
            foreach (DataGridViewColumn column in reportGrid.Columns)
            {


                if(column.HeaderText == "Duration")
                {
                    column.HeaderText = "DR";
                }
                else if(column.HeaderText == "Risk Level")
                {
                    column.HeaderText = "RL";
                }

                //PdfPCell cell = new PdfPCell(new Phrase(column.HeaderText));
                PdfPCell cell = new PdfPCell(new Phrase(column.HeaderText, font));
                


                cell.BackgroundColor = new Color(24, 143, 251);
                

                pdfTable.AddCell(cell);
            }

            //Adding DataRow
            foreach (DataGridViewRow row in reportGrid.Rows)
            {
                foreach (DataGridViewCell cell in row.Cells)
                {
                    if (cell.Value == null)
                    {
                        cell.Value = "";
                    }

                    PdfPCell cl = new PdfPCell(new Phrase(cell.Value.ToString(), font));
                    


                    //PdfPCell cl = new PdfPCell(new Phrase(cell.Value.ToString(), font));
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
                pdfDoc.Add(title);
                pdfDoc.Add(pdfTable);
                pdfDoc.Add(keywords);
                pdfDoc.Add(details);
                pdfDoc.Close();
                stream.Close();

            }





        }

        private string selectType(int type)
        {
            string x = "";

            if (type == 0)
            {
                x = "Weekly";
            }
            else if (type == 1)
            {
                x = "Monthly";
            }
            else if (type == 2)
            {
                x = "Yearly";
            }
            else
            {
                x = "";
            }
            return x;
        }

    }
}
