
package com.example.demo.jmsclient;

import com.example.demo.message.Heartbeat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;



/**
 *
 * @author ruihaijiang
 */
@Component
public class HeartbeatProducer {
    
    @Autowired
    @Qualifier("queueJmsTemplate")        
    JmsTemplate jmsTemplate;

    @Value("${app.heartbeat-queue-name}")
    private String queueName;

    
    /* 
    Queue1: "heart-beat"
    */
    public void sendMessage(Heartbeat message){
        try{
            logger.info("HeartbeatProducer send heartbeat to queue: "+ queueName+", message = "+message.toString());
            String json = objectMapper.writeValueAsString(message);
            jmsTemplate.convertAndSend(queueName, json);
        } catch(Exception e){
           logger.error("HeartbeatProducer got exception: ", e);
        }
    }   
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatProducer.class);
    ObjectMapper objectMapper = new ObjectMapper();     
   
}
