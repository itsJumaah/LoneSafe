using Newtonsoft.Json;
using System;

namespace LoneSafeLib
{
    public class ReportUser
    {
        [JsonProperty("date")]
        public string Date { get; set; }

        [JsonProperty("firstname")]
        public string Firstname { get; set; }

        [JsonProperty("lastname")]
        public string Lastname { get; set; }

        [JsonProperty("workinghours")]
        public string WorkingHours { get; set; }

        [JsonProperty("risklevel")]
        public int Risklevel { get; set; }

        [JsonProperty("needsos")]
        public int NeedSOS { get; set; }

        [JsonProperty("checkin1")]
        public string Checkin1 { get; set; }

        [JsonProperty("checkin2")]
        public string Checkin2 { get; set; }

        [JsonProperty("checkin3")]
        public string Checkin3 { get; set; }

        [JsonProperty("checkin4")]
        public string Checkin4 { get; set; }

        [JsonProperty("checkin5")]
        public string Checkin5 { get; set; }

        [JsonProperty("checkin6")]
        public string Checkin6 { get; set; }

        [JsonProperty("checkin7")]
        public string Checkin7 { get; set; }

        [JsonProperty("checkin8")]
        public string Checkin8 { get; set; }


    }
}
