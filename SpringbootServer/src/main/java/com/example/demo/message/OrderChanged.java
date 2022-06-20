
package com.example.demo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author ruihaijiang
 */
public class OrderChanged {
    
    public String orderId; 
    
   @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")        
    public Date changeTime;
   
   @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")       
    public Date sendTime;

    public OrderChanged()
    {
        this.changeTime = this.sendTime = new Date();
    }
    
    public OrderChanged( String order_id )
    {
        this.orderId = order_id;
        this.changeTime = this.sendTime = new Date();
    }
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
        return "OrderChanged{" + "orderId=" + orderId + ", changeTime=" + changeTime + ", sendTime=" + sendTime + '}';
    }
  
    
}
