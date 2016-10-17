using Newtonsoft.Json;
using System.Collections.Generic;
using System.Net;

namespace LoneSafeLib
{
    public class ReportData
    {

        private string url;
        private List<ReportUserJson> reportUsersList = new List<ReportUserJson>();



        public ReportData(string url)
        {
            this.url = url;
        }



        public void update(string fromDate)
        {
            store(url, fromDate);
        }


        private void store(string URL, string fromDate)
        {
            foreach (ReportUserJson xx in reportUsersList)
            {
                xx.ReportUser = null;
            }

            try
            {
                string m_URL = URL + "/scripts/reportUser.php";
                string param = "fromDate=" + fromDate + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string htmlResult = server.UploadString(m_URL, param);
                //string htmlResult = server.DownloadString(m_URL);

                reportUsersList = JsonConvert.DeserializeObject<List<ReportUserJson>>(htmlResult);

            }
            catch (WebException e)
            {
                System.Windows.Forms.MessageBox.Show("ERROR: " + e);
            }
            
        }


        public List<ReportUserJson> ReportUsersList
        {
            get
            {
                return reportUsersList;
            }
        }
    }
}
