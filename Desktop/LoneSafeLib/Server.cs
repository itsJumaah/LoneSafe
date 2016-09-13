
namespace LoneSafeLib
{
    public class Server
    {

        private string url;

        public Server(string url)
        {
            this.url = url;
        }

        public string GetURL
        {
            get
            {
                return url;
            }
        }
    }
}
