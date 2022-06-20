
package com.example.demo.service;

import com.example.demo.jmsclient.HeartbeatProducer;
import com.example.demo.jmsclient.OrderChangedProducer;
import com.example.demo.message.Heartbeat;
import com.example.demo.message.OrderChanged;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruihaijiang
 */
@Service
public class MyService {

    @Value("${app.node-id}")
    public String nodeId;

    public int status = 1;

    public MyService() {

        thisThread = new Thread(
                new Runnable() {
            @Override
            public void run() {
                serviceRun();
            }
        }
        );
        
        thisThread.start();
    }

    
    /* This thread keeps running, sending the heartbeat message and order-changed message. */
    void serviceRun() {

        while (true) {
            
            try {
                Thread.sleep(1000*10);
            } 
            catch(Exception ex) 
            {
            }
            
            //send heartbeat message
            Heartbeat heartbeat = new Heartbeat(nodeId, status);
            heartbeatProducer.sendMessage(heartbeat);
            status++;
            if (status == 4) {
                status = 1;
            }

            
            //send the order-changed message
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            OrderChanged orderchanged = new OrderChanged(uuidAsString);
            orderChangedProducer.sendMessage(orderchanged);
        }
    }

    Thread thisThread;

    @Autowired
    HeartbeatProducer heartbeatProducer;

    @Autowired
    OrderChangedProducer orderChangedProducer;
}
