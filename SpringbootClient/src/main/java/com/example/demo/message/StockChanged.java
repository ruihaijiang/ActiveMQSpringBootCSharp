
package com.example.demo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author ruihaijiang
 */
public class StockChanged {
    
    public String stockId; 
    
   @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")        
    public Date changeTime;
   
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")      
    public Date sendTime;

    
    public StockChanged()
    {
        this.changeTime = this.sendTime = new Date();
    }
    
    public String getOrderId() {
        return stockId;
    }

    public void setOrderId(String orderId) {
        this.stockId = orderId;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "StoreChanged{" + "stockId=" + stockId + ", changeTime=" + changeTime + ", sendTime=" + sendTime + '}';
    }
  
    
}
