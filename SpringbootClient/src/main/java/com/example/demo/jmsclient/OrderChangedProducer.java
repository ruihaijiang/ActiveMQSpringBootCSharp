
package com.example.demo.jmsclient;

import com.example.demo.message.OrderChanged;
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
public class OrderChangedProducer {
    @Autowired   
    @Qualifier("topicJmsTemplate")
    JmsTemplate jmsTemplate;

    @Value("${app.order-changed-topic-name}")
    private String topicName;

    
    /* 
    Topic2: "order-changed"
    */
    public void sendMessage(OrderChanged message){
        try{
           logger.info("OrderChangedProducer send order-changed to topic: "+ topicName+", object = "+message.toString() );
            String json = objectMapper.writeValueAsString(message);
            jmsTemplate.convertAndSend(topicName, json);
        } catch(Exception e){
           logger.error("OrderChangedProducer got exception: ", e);
        }
    }   
    private static final Logger logger = LoggerFactory.getLogger(OrderChangedProducer.class);
    ObjectMapper objectMapper = new ObjectMapper();     
}
