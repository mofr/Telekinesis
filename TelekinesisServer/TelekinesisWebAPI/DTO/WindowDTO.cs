using System;
using System.ComponentModel.DataAnnotations;

namespace TelekinesisWebAPI.DTO
{
    public class WindowDTO
    {
        [Required]
        public string Id;
        
        [Required]
        public string Title;

        [Required]
        public Uri IconLink;

        [Required]
        public string ProcessId;
        
        [Required]
        public string ProcessName;
    }
}