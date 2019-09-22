using Microsoft.AspNetCore.Mvc;
using OS;

namespace TelekinesisWebAPI.Controllers
{
    [Route("api/system")]
    public class SystemController : ControllerBase
    {
        [HttpGet("lock-screen")]
        public void LockScreen()
        {
            Windows.LockScreen();
        }
        
        [HttpGet("reboot")]
        public void Reboot([FromQuery] uint timeout = 30)
        {
            Windows.Shutdown(timeout, true);
        }
        
        [HttpGet("shutdown")]
        public void Shutdown([FromQuery] uint timeout = 30)
        {
            Windows.Shutdown(timeout);
        }
        
        [HttpGet("abort-shutdown")]
        public void AbortShutdown()
        {
            Windows.AbortShutdown();
        }
    }
}