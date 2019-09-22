using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace TelekinesisWebAPI.Controllers
{
    [JsonConverter(typeof(StringEnumConverter))] 
    public enum KeyboardButton
    {
        AltTab,
        VolumeMute,
        VolumeUp,
        VolumeDown,
        Left,
        Right,
        Up,
        Down,
        Space,
    }
}