using System.IO;
using System.Net;

namespace LoneSafeLib
{
    public class RequestData
    {
        private string data = null;

        private HttpWebRequest req;

        public RequestData(string url)
        {
            req = WebRequest.Create(url) as HttpWebRequest;

            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                data = reader.ReadToEnd();
            }

        }

      

        public string Data
        {
            get { return data; }
        }

    }
}
