using Newtonsoft.Json;

namespace LoneSafeLib
{
    public class ReportUserJson
    {
        [JsonProperty("ReportUser")]
        public ReportUser ReportUser { get; set; }
    }
}