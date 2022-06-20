
package com.example.demo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author ruihaijiang
 */
public class Heartbeat {
    public String nodeId;
    
    static public final int LOGON  = 1; /*hello, I just logged in*/
    static public final int ONLINE = 2; /*I'm still online*/
    static public final int LOGOFF = 3; /*bye, I'm logging out*/
 
    public int status=1;

    
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date sendTime;
    
    public Heartbeat() 
    {
        sendTime = new Date();
    }
    
    public Heartbeat( String node_id, int status )
    {
        this.nodeId = node_id;
        this.status = status;
        this.sendTime = new Date();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Heartbeat{" + "nodeId=" + nodeId + ", status=" + status + ", sendTime=" + sendTime + '}';
    }
    

    
    
}
