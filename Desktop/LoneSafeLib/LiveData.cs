using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Net;
using System.Threading.Tasks;

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
            
            
            string m_URL = URL + "/scripts/liveData.php";
            
            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            //string HtmlResult = server.UploadString(m_URL, null);
            string htmlResult = server.DownloadString(m_URL);
            
            liveUsersList = JsonConvert.DeserializeObject<List<LiveUserJson>>(htmlResult);
            
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
