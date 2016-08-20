

namespace LoneSafeLib
{
    public class User
    {
        //variables
        private string username = "";
        private string password = "";

        private string name = "";

        private bool isAdmin = false;
        
        //constructor
        public User(string username, string password, string name, bool isAdmin)
        {
            this.username = username;
            this.password = password;

            this.name = name;
            this.isAdmin = isAdmin;
        }

        //properties

        public string Username
        {
            get { return username; }
            set { username = value; }
        }

        public string Password
        {
            get { return password; }
            set { password = value; }
        }

        public string Name
        {
            get { return name; }
        }

        public bool IsAdmin
        {
            get { return isAdmin; }
        }

 
    }
}
