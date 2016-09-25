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
            string param = "username=" + username + "&password=" + p + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

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
            string param = "username=" + username + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            _user = JsonConvert.DeserializeObject<User>(HtmlResult);

            return _user;
        }

        //change the status of sos back to 0 after being notified
        public static void noMoreSOS(string URL, string username)
        {
          
            string m_URL = URL + "/scripts/sosDone.php";
            string param = "username=" + username + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);
            
        }


        //alter escalation
        public static void notified(string URL, string username, string checkin, string esc)
        {

            string m_URL = URL + "/scripts/escDone.php";
            string param = "username=" + username + "&checkin=" + checkin + "&esc=" + esc + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            Console.WriteLine(HtmlResult);

        }


        public static List<DisplayUserJson> allUsersList(string URL)
        {
            List<DisplayUserJson> usersList = new List<DisplayUserJson>();
            

            string m_URL = URL + "/scripts/displayData.php";

            string param = "ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            //string HtmlResult = server.UploadString(m_URL, null);
           // string htmlResult = server.DownloadString(m_URL);
            string HtmlResult = server.UploadString(m_URL, param);

            usersList = JsonConvert.DeserializeObject<List<DisplayUserJson>>(HtmlResult);
            

            return usersList;

        }


    }
}
