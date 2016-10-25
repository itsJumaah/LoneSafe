using Newtonsoft.Json;

namespace LoneSafeLib
{
    public class DisplayUser
    {
        [JsonProperty("id")]
        public string ID { get; set; }

        [JsonProperty("firstname")]
        public string Firstname { get; set; }

        [JsonProperty("lastname")]
        public string Lastname { get; set; }

        [JsonProperty("username")]
        public string Username { get; set; }

        [JsonProperty("manager")]
        public int Manager { get; set; }

        [JsonProperty("onjob")]
        public int Onjob { get; set; }

        [JsonProperty("mobile")]
        public string Mobile { get; set; }

        [JsonProperty("phone")]
        public string Phone { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("rego")]
        public string Rego { get; set; }

    }
}
