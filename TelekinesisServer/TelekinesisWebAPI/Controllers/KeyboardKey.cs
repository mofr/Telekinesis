using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace TelekinesisWebAPI.Controllers
{
    [JsonConverter(typeof(StringEnumConverter))] 
    public enum KeyboardKey
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