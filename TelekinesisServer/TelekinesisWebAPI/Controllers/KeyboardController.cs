using Microsoft.AspNetCore.Mvc;
using OS;

namespace TelekinesisWebAPI.Controllers
{
    [Route("api/keyboard")]
    [ApiController]
    public class KeyboardController : ControllerBase
    {
        [HttpPost("press")]
        public void Press([FromQuery(Name="key")] KeyboardKey[] keys)
        {
            foreach (var key in keys)
            {
                handleKey(key);
            }
        }
        
        private void handleKey(KeyboardKey key)
        {
            switch (key)
            {
                case KeyboardKey.AltTab:
                    // TODO find the way to release Alt key after this call 
                    Windows.AltTab();
                    break;
                case KeyboardKey.VolumeMute:
                    Windows.Click(Windows.VirtualKeyShort.VOLUME_MUTE);
                    break;
                case KeyboardKey.VolumeUp:
                    Windows.Click(Windows.VirtualKeyShort.VOLUME_UP);
                    break;
                case KeyboardKey.VolumeDown:
                    Windows.Click(Windows.VirtualKeyShort.VOLUME_DOWN);
                    break;
                case KeyboardKey.Left:
                    Windows.Click(Windows.VirtualKeyShort.LEFT);
                    break;
                case KeyboardKey.Right:
                    Windows.Click(Windows.VirtualKeyShort.RIGHT);
                    break;
                case KeyboardKey.Up:
                    Windows.Click(Windows.VirtualKeyShort.UP);
                    break;
                case KeyboardKey.Down:
                    Windows.Click(Windows.VirtualKeyShort.DOWN);
                    break;
                case KeyboardKey.Space:
                    Windows.Click(Windows.VirtualKeyShort.SPACE);
                    break;
            }
        }
    }
}