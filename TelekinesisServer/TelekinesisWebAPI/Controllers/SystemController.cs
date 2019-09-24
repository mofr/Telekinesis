using Microsoft.AspNetCore.Mvc;
using OS;

namespace TelekinesisWebAPI.Controllers
{
    [Route("api/system")]
    public class SystemController : ControllerBase
    {
        [HttpPost("lock-screen")]
        public void LockScreen()
        {
            Windows.LockScreen();
        }
        
        [HttpPost("reboot")]
        public void Reboot([FromQuery] uint timeout = 30)
        {
            Windows.Shutdown(timeout, true);
        }
        
        [HttpPost("shutdown")]
        public void Shutdown([FromQuery] uint timeout = 30)
        {
            Windows.Shutdown(timeout);
        }
        
        [HttpPost("abort-shutdown")]
        public void AbortShutdown()
        {
            Windows.AbortShutdown();
        }
    }
}