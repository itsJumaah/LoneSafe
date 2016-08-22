using System;
using System.Net;
using System.Text.RegularExpressions;

namespace LoneSafeLib
{
    public class Connection
    {
        private bool success;
        private User user;

        public Connection(string URL, string username, string password)
        {

            string m_URL =  URL + "/login.php";
            string param = "username=" + username + "&password=" + password;

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            string[] words = Regex.Split(HtmlResult, @"([A-Za-z0-9_\-]+)");

            /*
            foreach (string word in words) {
                Console.WriteLine(word);
            }
            */

            success = Convert.ToBoolean(words[3]);


            if (success)
            {
                string name = words[7];
                int x = Int32.Parse(words[27]);
                bool isAdmin = Convert.ToBoolean(x);

                user = new User(username, password, name, isAdmin);

            }

        }

        public bool Success
        {
            get { return success; }
        }

        public User User
        {
            get { return user; }
        }

       

    }
}
