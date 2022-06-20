
package com.example.demo.jmsclient;

import com.example.demo.message.Heartbeat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.jms.JMSException;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author ruihaijiang
 */
@Component
public class HeartbeatConsumer {
    /*
    
    Every client node keeps sending heartbeat messages to the server. 
    The server listens and processes all the heartbeat messages.    
    Queue1: "heart-beat"
    */
    @JmsListener(destination = "${app.heartbeat-queue-name}", containerFactory="${app.queue-factory-name}")  
    public void onMessage(ActiveMQTextMessage message) throws JMSException, JsonProcessingException {
        
        if (message !=null ) {
            String text = message.getText();
            //logger.info("HeartbeatConsumer received hearbeat message:"+text);
            Heartbeat  object = objectMapper.readValue(text, Heartbeat.class);
            logger.info("HeartbeatConsumer received object = :"+object.toString());
        } else {
            logger.info("HeartbeatConsumer received null");
        }
    }
  
    ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatConsumer.class);

}
