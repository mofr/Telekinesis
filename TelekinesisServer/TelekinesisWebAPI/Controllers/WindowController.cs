using System;
using System.Collections.Generic;
using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using OS;
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
                    var dto = new WindowDTO
                    {
                        Id = process.Id,
                        ProcessName = process.ProcessName,
                        Title = process.MainWindowTitle,
                    };
                    windows.Add(dto);
                }
            }
            return windows;
        }

        [HttpGet("{id}/activate")]
        public void ActivateWindow(int id)
        {
            var process = Process.GetProcessById(id);
            if (!Windows.SetForegroundWindow(process.MainWindowHandle))
                Console.WriteLine("SetForegroundWindows failed on process " + process.ProcessName);
            if (!Windows.OpenIcon(process.MainWindowHandle))
                Console.WriteLine("OpenIcon failed on process " + process.ProcessName);
        }
    }
}