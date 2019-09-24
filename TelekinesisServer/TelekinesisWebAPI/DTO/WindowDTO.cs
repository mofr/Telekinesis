using System.ComponentModel.DataAnnotations;

namespace TelekinesisWebAPI.DTO
{
    public class WindowDTO
    {
        [Required]
        public int Id;
        
        [Required]
        public string ProcessName;
        
        [Required]
        public string Title;
        
        [Required]
        public string IconLink;
    }
}