using System;
using System.Diagnostics;
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

        /*public static void wait(int milliseconds)
        {
          
            int time = Environment.TickCount;

            while (true) {
                if (Environment.TickCount - time >= milliseconds)
                {
                    break;
                }

            }
                   


            /*
            Stopwatch sw = new Stopwatch(); // sw cotructor
            sw.Start();                     // starts the stopwatch
            for (int i = 0; ; i++)
            {
                if (i % 100000 == 0) // if in 100000th iteration (could be any other large number
                                     // depending on how often you want the time to be checked) 
                {
                    sw.Stop(); // stop the time measurement
                    if (sw.ElapsedMilliseconds > milliseconds) // check if desired period of time has elapsed
                    {
                        break; // if more than 5000 milliseconds have passed, stop looping and return
                               // to the existing code
                    }
                    else
                    {
                        sw.Start(); // if less than 5000 milliseconds have elapsed, continue looping
                                    // and resume time measurement
                    }
                } 
            }
        }*/

    }
}
