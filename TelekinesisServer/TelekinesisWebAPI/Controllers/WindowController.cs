using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TelekinesisWebAPI.DTO;

namespace TelekinesisWebAPI.Controllers
{
    [Route("api/windows")]
    [ApiController]
    public class WindowController : ControllerBase
    {
        [HttpGet]
        public ActionResult<IEnumerable<WindowDTO>> Get()
        {
            Process[] processlist = Process.GetProcesses();
            var windows = new List<WindowDTO>();
            foreach (Process process in processlist)
            {
                if (!String.IsNullOrEmpty(process.MainWindowTitle))
                {
                    var dto = new WindowDTO();
                    dto.ProcessName = process.ProcessName;
                    dto.Title = process.MainWindowTitle;
                    windows.Add(dto);
                }
            }
            return windows;
        }

        [HttpGet("{id}")]
        public ActionResult<string> Get(int id)
        {
            return "value";
        }

        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}