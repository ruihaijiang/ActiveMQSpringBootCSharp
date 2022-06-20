
package com.example.demo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author ruihaijiang
 */
public class NodeStatus {
   public String nodeId;
    
    static public final int ONLINE  = 1; /*the node is online*/
    static public final int OFFLINE = 2; /*the node is offline*/

 
    public int status=1;
    
   @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")    
    public Date sendTime;
    
   
    public NodeStatus()
    {
        sendTime = new Date();
    }
    
    public NodeStatus( String node_id, int status )
    {
        nodeId = node_id;
        this.status = status;
        sendTime = new Date();
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

    @Override
    public String toString() {
        return "NodeStatus{" + "nodeId=" + nodeId + ", status=" + status + '}';
    }
        
}
