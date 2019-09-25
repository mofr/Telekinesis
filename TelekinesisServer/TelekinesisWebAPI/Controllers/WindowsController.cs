using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using OS;
using SixLabors.ImageSharp;
using SixLabors.ImageSharp.Formats;
using SixLabors.ImageSharp.Formats.Png;
using TelekinesisWebAPI.DTO;

namespace TelekinesisWebAPI.Controllers
{
    [Route("api/windows")]
    [ApiController]
    public class WindowsController : ControllerBase
    {
        private IImageEncoder imageEncoder;
        
        public WindowsController()
        {
            imageEncoder = new PngEncoder();
        }

        [HttpGet]
        public ActionResult<IEnumerable<WindowDTO>> GetAllWindows()
        {
            Process[] processlist = Process.GetProcesses();
            var windows = new List<WindowDTO>();
            foreach (Process process in processlist)
            {
                if (!String.IsNullOrEmpty(process.MainWindowTitle))
                {
                    string iconLink = Url.RouteUrl("get-window-icon", new 
                    {
                        id = process.Id,
                    }, Request.Scheme);
                    var dto = new WindowDTO
                    {
                        Id = process.Id.ToString(),
                        Title = process.MainWindowTitle,
                        IconLink = new Uri(iconLink),
                        ProcessId = process.Id.ToString(),
                        ProcessName = process.ProcessName,
                    };
                    windows.Add(dto);
                }
            }
            return windows;
        }

        [HttpGet("{id}/icon", Name="get-window-icon")]
        public async Task<IActionResult> GetWindowIcon(int id)
        {
            var process = Process.GetProcessById(id);
            var stream = new MemoryStream();
            using (Image icon = Windows.GetIcon(process))
            {
                icon.Save(stream, imageEncoder);
            }
            stream.Seek(0, SeekOrigin.Begin);
            return File(stream, "image/png");
        }

        [HttpPost("{id}/activate")]
        public void ActivateWindow(int id)
        {
            var process = Process.GetProcessById(id);
            Windows.ActivateWindow(process.MainWindowHandle);
        }

        [HttpPost("{id}/close")]
        public void CloseWindow(int id)
        {
            var process = Process.GetProcessById(id);
            process.CloseMainWindow();
        }
    }
}