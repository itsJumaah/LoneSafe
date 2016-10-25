using Newtonsoft.Json;

namespace LoneSafeLib
{
    public class LiveUserJson
    {
        [JsonProperty("LiveUser")]
        public LiveUser LiveUser { get; set; }
    }
}
