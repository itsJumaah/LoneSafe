using Newtonsoft.Json;
using System.Net;
using System.Collections.Generic;
using System.Windows.Forms;

namespace LoneSafeLib
{
    public class Connection
    {
        private User user;

       

        public Connection(string URL, string username, string password)
        {
            try
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
            catch(WebException e)
            {
                MessageBox.Show("Cannot log-in: " + e);
            }
            

        }


        public User User
        {
            get { return user; }
        }

        /*
        public static User CheckUser(string URL, string username) {
            try
            {
                User _user = new User();

                string m_URL = URL + "/scripts/delPrepare.php";
                string param = "username=" + username + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string HtmlResult = server.UploadString(m_URL, param);

                _user = JsonConvert.DeserializeObject<User>(HtmlResult);

                return _user;
            }
            catch (WebException e)
            {
                MessageBox.Show("Cannot check the user: " + e);
                return null;
            }
            
        }*/

        //change the status of sos back to 0 after being notified
        public static void noMoreSOS(string URL, string username)
        {
          
            try
            {
                string m_URL = URL + "/scripts/sosDone.php";
                string param = "username=" + username + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string HtmlResult = server.UploadString(m_URL, param);
            }
            catch (WebException e)
            {
                MessageBox.Show("ERROR: " + e);
            }

        }


        //alter escalation
        public static void notified(string URL, string username, string checkin, string esc)
        {
            try
            {
                string m_URL = URL + "/scripts/escDone.php";
                string param = "username=" + username + "&checkin=" + checkin + "&esc=" + esc + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string HtmlResult = server.UploadString(m_URL, param);
            }
            catch (WebException e)
            {
                MessageBox.Show("ERROR: " + e);
            }


            //Console.WriteLine(HtmlResult);

        }

        //sends email
        public static void email(string URL, string type, string adminfname,
     string adminLname, string userFname, string userLname,
     string username, string eventTime, string risklevel,
     string startTime, string endTime, string mobile,
     string email, string phone, string rego, string longi,
     string lati, string adminEmail)
        {
            try
            {
                string m_URL = URL + "/scripts/sendmail.php";

                string param = "eType=" + type
                            + "&aFname=" + adminfname
                            + "&aLname=" + adminLname

                            + "&uFname=" + userFname
                            + "&uLname=" + userLname
                            + "&username=" + username

                            + "&eTime=" + eventTime

                            + "&risklevel=" + risklevel
                            + "&startTime=" + startTime
                            + "&endTime=" + endTime

                            + "&mobile=" + mobile
                            + "&email=" + email
                            + "&phone=" + phone
                            + "&rego=" + rego

                            + "&longi=" + longi
                            + "&lati=" + lati

                            + "&aemail=" + adminEmail;

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string HtmlResult = server.UploadString(m_URL, param);
            }
            catch (WebException e)
            {
                MessageBox.Show("ERROR: " + e);
            }
            

            //Console.WriteLine(HtmlResult);

        }


        public static List<DisplayUserJson> allUsersList(string URL)
        {
            try
            {
                List<DisplayUserJson> usersList = new List<DisplayUserJson>();


                string m_URL = URL + "/scripts/displayData.php";

                string param = "ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

                WebClient server = new WebClient();
                server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                string HtmlResult = server.UploadString(m_URL, param);

                usersList = JsonConvert.DeserializeObject<List<DisplayUserJson>>(HtmlResult);


                return usersList;
            }
            catch (WebException e)
            {
                MessageBox.Show("ERROR: " + e);
                return null;
            }
           

        }


    }
}
