
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Net;

namespace LoneSafeLib
{
    public class LiveData
    {
        
        private string url;
        private List<LiveUserJson> liveUsersList = new List<LiveUserJson>();

       

        public LiveData(string url)
        {
            this.url = url;
        }

       

        public void update()
        {
            store(url);
        }


        private void store(string URL)
        {
            foreach(LiveUserJson xx in liveUsersList)
            {
                xx.LiveUser = null;
            }
            
            try
            {
                string m_URL = URL + "/scripts/liveData.php";

                string param = "ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string htmlResult = server.UploadString(m_URL, param);
            
                liveUsersList = JsonConvert.DeserializeObject<List<LiveUserJson>>(htmlResult);
                //Console.WriteLine(htmlResult);
            }
            catch (WebException e)
            {
                System.Windows.Forms.MessageBox.Show("ERROR: " + e);
            }
            
            
        }


        public List<LiveUserJson> LiveUsersList
        {
            get
            {
                return liveUsersList;
            }
        }


    }
}
