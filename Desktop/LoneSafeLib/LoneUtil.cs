using System;
using System.Text;

namespace LoneSafeLib
{
    public class LoneUtil
    {
        //hash the password
        public static string sha256(string password)
        {
            System.Security.Cryptography.SHA256Managed crypt = new System.Security.Cryptography.SHA256Managed();
            System.Text.StringBuilder hash = new System.Text.StringBuilder();
            byte[] crypto = crypt.ComputeHash(Encoding.UTF8.GetBytes(password), 0, Encoding.UTF8.GetByteCount(password));
            foreach (byte theByte in crypto)
            {
                hash.Append(theByte.ToString("x2"));
            }
            return hash.ToString();
        }

        public static string SECRET_KEY = "(G1)MEGA#X!A1**"; //secret key

        //convert words to string
        public static void getInt(string number, ref int num)
        {
            try
            {
                int.TryParse(number, out num);
            }
            catch(FormatException e)
            {
                
                Console.WriteLine(e.Message);
               
            }
            
        }
        

    }
}
