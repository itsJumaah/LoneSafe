using Newtonsoft.Json;
using System.Net;
using System;
using System.Collections.Generic;

using System.Text.RegularExpressions;

namespace LoneSafeLib
{
    public class Connection
    {
        private User user;

       

        public Connection(string URL, string username, string password)
        {
            string p = LoneUtil.sha256(password);

            string m_URL =  URL + "/scripts/login.php";
            string param = "username=" + username + "&password=" + p;

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);
            
            user = new User();

            user = JsonConvert.DeserializeObject<User>(HtmlResult);

        }


        public User User
        {
            get { return user; }
        }


        public static User CheckUser(string URL, string username) {

            User _user = new User();

            string m_URL = URL + "/scripts/delPrepare.php";
            string param = "username=" + username;

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            _user = JsonConvert.DeserializeObject<User>(HtmlResult);

            return _user;
        }


        public static List<DisplayUserJson> allUsersList(string URL)
        {
            List<DisplayUserJson> usersList = new List<DisplayUserJson>();
            

            string m_URL = URL + "/scripts/displayData.php";


            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            //string HtmlResult = server.UploadString(m_URL, null);
            string htmlResult = server.DownloadString(m_URL);
            

            usersList = JsonConvert.DeserializeObject<List<DisplayUserJson>>(htmlResult);
            

            return usersList;

        }


    }
}
