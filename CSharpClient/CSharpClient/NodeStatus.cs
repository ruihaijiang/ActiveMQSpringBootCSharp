using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSharpClient
{
    class NodeStatus
    {
        public String NodeId { get; set; }


        public int Status { get; set; }
        public DateTime SendTime { get; set; }

        override public string ToString()
        {
            return string.Format("NodeStatus[NodeId={0},SendTime={2}]",
                NodeId, SendTime);
        }
    }
}
