using Newtonsoft.Json;

namespace LoneSafeLib
{
    public class LiveUser
    {
        [JsonProperty("firstname")]
        public string Firstname { get; set; }

        [JsonProperty("lastname")]
        public string Lastname { get; set; }

        [JsonProperty("username")]
        public string Username { get; set; }

        [JsonProperty("mobile")]
        public string Mobile { get; set; }

        [JsonProperty("phone")]
        public string Phone { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("rego")]
        public string Rego { get; set; }

        [JsonProperty("starttime")]
        public string StartTime { get; set; }

        [JsonProperty("endtime")]
        public string EndTime { get; set; }

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

        [JsonProperty("emtime")]
        public string EmTime { get; set; }

        [JsonProperty("latitude")]
        public string Latitude { get; set; }

        [JsonProperty("longitude")]
        public string Longitude { get; set; }
        
    }
}
