

using System;

namespace LoneSafeLib
{
    public class RetrData
    {

        private string fullStr;
        private string[] sep = {"#", "!", "<br>" };
        private string[] usrsStr;
        public RetrData(string fullStr)
        {
            this.fullStr = fullStr;

            usrsStr = fullStr.Split(sep, StringSplitOptions.RemoveEmptyEntries);
        }

    }
}
