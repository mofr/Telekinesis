using System.ComponentModel.DataAnnotations;

namespace TelekinesisWebAPI.DTO
{
    public class WindowDTO
    {
        [Required]
        public string ProcessName;
        
        [Required]
        public string Title;
    }
}