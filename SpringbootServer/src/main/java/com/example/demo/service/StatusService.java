
package com.example.demo.service;

import com.example.demo.jmsclient.NodeStatusProducer;
import com.example.demo.message.NodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruihaijiang
 */
@Service
public class StatusService {
   
    public void sendStatus( String nodeId, int status) 
    {
        NodeStatus nodeStatus = new NodeStatus( nodeId, status);
        producer.sendMessage(nodeStatus);
    }
    
    @Autowired 
    NodeStatusProducer  producer;
}
