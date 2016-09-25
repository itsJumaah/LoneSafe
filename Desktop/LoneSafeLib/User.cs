

using Newtonsoft.Json;
using System;
using System.Net;

namespace LoneSafeLib
{
    public class User
    {
        //variables

        private bool success = false;
        private int id = 0;
        private string firstname = "";
        private string lastname = "";
        private string username = "";
        private string mobile = "";
        private string phone = "";
        private string email = "";
        private string rego = "";

        private bool manager = false;
        private bool onjob = false;


        
        //default const
        public User()
        {

        }

        //constructor
        public User(bool success, int id, string firstname, string lastname, string username, int manager, int onjob, string mobile, string phone, string email, string rego)
        {
            this.success = success;
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.username = username;
            
            this.mobile = mobile;
            this.phone = phone;
            this.email = email;
            this.rego = rego;

            if (manager == 1) {
                this.manager = true;
            } else {
                this.manager = false;
            }

            if(onjob == 1)
            {
                this.onjob = true;
            }
            else
            {
                this.onjob = false;
            }
        }

       


        //Methods
        //add a new user to the db
        public static void Add(string URL, string fname, string lname, string username, string password, string email, string rego, string mnum, string hnum, int manager)
        {

            string p = LoneUtil.sha256(password);

            string m_URL = URL + "/scripts/addUser.php";
            
            string param = "username=" + username +
                           "&password=" + p +
                           "&firstname=" + fname +
                           "&lastname=" + lname +
                           "&email=" + email +
                           "&rego=" + rego +
                           "&mobile=" + mnum +
                           "&phone=" + hnum +
                           "&manager=" + manager
                           + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);
            

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            Console.WriteLine(HtmlResult);

        }
        //edit a user in the db
        public static void Edit(string URL, string fname, string lname, string username, string email, string rego, string mnum, string hnum)
        {

            string m_URL = URL + "/scripts/editUser.php";

            string param = "username=" + username +
                           "&firstname=" + fname +
                           "&lastname=" + lname +
                           "&email=" + email +
                           "&rego=" + rego +
                           "&mobile=" + mnum +
                           "&phone=" + hnum
                           + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);


            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            Console.WriteLine(HtmlResult);

        }

        //delete a user from the db
        public static void Delete(string URL, string username)
        {

            string m_URL = URL + "/scripts/delUser.php";
            
            string param = "username=" + username + "&ABEX=" + LoneUtil.sha256(LoneUtil.SECRET_KEY);

            WebClient server = new WebClient();
            server.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            string HtmlResult = server.UploadString(m_URL, param);

            Console.WriteLine(HtmlResult);

        }
        //check user
        public static User Check(string URL, string username)
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


        //properties

        public int Id
        {
            get
            {
                return id;
            }

            set
            {
                id = value;
            }
        }

        public string Firstname
        {
            get
            {
                return firstname;
            }

            set
            {
                firstname = value;
            }
        }

        public string Lastname
        {
            get
            {
                return lastname;
            }

            set
            {
                lastname = value;
            }
        }

        public string Username
        {
            get
            {
                return username;
            }

            set
            {
                username = value;
            }
        }

        public string Mobile
        {
            get
            {
                return mobile;
            }

            set
            {
                mobile = value;
            }
        }

        public string Phone
        {
            get
            {
                return phone;
            }

            set
            {
                phone = value;
            }
        }

        public string Email
        {
            get
            {
                return email;
            }

            set
            {
                email = value;
            }
        }

        public string Rego
        {
            get
            {
                return rego;
            }

            set
            {
                rego = value;
            }
        }

        public bool Manager
        {
            get
            {
                return manager;
            }

            set
            {
                manager = value;
            }
        }

        public bool Onjob
        {
            get
            {
                return onjob;
            }

            set
            {
                onjob = value;
            }
        }

        public bool Success
        {
            get
            {
                return success;
            }

            set
            {
                success = value;
            }
        }
    }
}
