using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSharpClient
{
    class Heartbeat
    {
        public string NodeId { get; set; }
        public int Status { get; set; }
        public DateTime SendTime { get; set; }
    }
}
