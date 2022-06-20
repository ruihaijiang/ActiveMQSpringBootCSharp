using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSharpClient
{
    public class OrderChanged
    {
        public String OrderId { get; set; }
        public DateTime ChangeTime { get; set; }
        public DateTime SendTime { get; set; }


        override public string ToString()
        {
            return string.Format("OrderChanged[OrderId={0},ChangeTime={1},SendTime={2}]",
                OrderId, ChangeTime, SendTime);
        }
    }
}
