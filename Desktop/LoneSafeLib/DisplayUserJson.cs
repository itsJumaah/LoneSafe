using Newtonsoft.Json;

namespace LoneSafeLib
{
    public class DisplayUserJson
    {
        [JsonProperty("DisplayUser")]
        public DisplayUser DisplayUser { get; set; }
    }
}
