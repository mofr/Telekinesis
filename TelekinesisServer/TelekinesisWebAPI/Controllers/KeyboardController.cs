using Microsoft.AspNetCore.Mvc;
using OS;

namespace TelekinesisWebAPI.Controllers
{
    [Route("api/keyboard")]
    [ApiController]
    public class KeyboardController : ControllerBase
    {
        [HttpPost("press")]
        public void Press([FromQuery(Name="button")] KeyboardButton[] buttons)
        {
            foreach (var button in buttons)
            {
                handleButton(button);
            }
        }
        
        private void handleButton(KeyboardButton button)
        {
            switch (button)
            {
                case KeyboardButton.AltTab:
                    // TODO find the way to release Alt key after this call 
                    Windows.AltTab();
                    break;
                case KeyboardButton.VolumeMute:
                    Windows.Click(Windows.VirtualKeyShort.VOLUME_MUTE);
                    break;
                case KeyboardButton.VolumeUp:
                    Windows.Click(Windows.VirtualKeyShort.VOLUME_UP);
                    break;
                case KeyboardButton.VolumeDown:
                    Windows.Click(Windows.VirtualKeyShort.VOLUME_DOWN);
                    break;
                case KeyboardButton.Left:
                    Windows.Click(Windows.VirtualKeyShort.LEFT);
                    break;
                case KeyboardButton.Right:
                    Windows.Click(Windows.VirtualKeyShort.RIGHT);
                    break;
                case KeyboardButton.Up:
                    Windows.Click(Windows.VirtualKeyShort.UP);
                    break;
                case KeyboardButton.Down:
                    Windows.Click(Windows.VirtualKeyShort.DOWN);
                    break;
                case KeyboardButton.Space:
                    Windows.Click(Windows.VirtualKeyShort.SPACE);
                    break;
            }
        }
    }
}