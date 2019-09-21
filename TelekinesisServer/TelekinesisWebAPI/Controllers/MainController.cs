using Microsoft.AspNetCore.Mvc;

namespace TelekinesisWebAPI.Controllers
{
    [ApiExplorerSettings(IgnoreApi = true)]
    [Route("/")]
    [ApiController]
    public class MainController : ControllerBase
    {
        [HttpGet("")]
        public ActionResult Index()
        {
            return Redirect("/swagger");
        }
    }
}