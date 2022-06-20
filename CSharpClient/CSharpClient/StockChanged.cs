using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSharpClient
{
    public class StockChanged
    {
        public String StockId { get; set; }
        public DateTime ChangeTime { get; set; }
        public DateTime SendTime { get; set; }


        override public string ToString()
        {
            return string.Format("StoreChanged[StockId={0},ChangeTime={1},SendTime={2}]",
                StockId, ChangeTime, SendTime);
        }
    }
}
