
package com.example.demo.jmsclient;

import com.example.demo.message.NodeStatus;
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
public class NodeStatusConsumer {
    /* 
    Topic: "node-status"
    */
    @JmsListener(destination = "${app.node-status-topic-name}", containerFactory="${app.topic-factory-name}")
    public void onMessage( ActiveMQTextMessage message) throws JMSException, JsonProcessingException {

        if (message !=null ) {
            String text = message.getText();
            //logger.info("NodeStatusConsumer received hearbeat message:"+text);
            NodeStatus  object = objectMapper.readValue(text, NodeStatus.class);
            logger.info("NodeStatusConsumer received object = :"+object.toString());
        } else {
            logger.info("NodeStatusConsumer received null");
        }
    }
    
    ObjectMapper objectMapper = new ObjectMapper();
 
    private static final Logger logger = LoggerFactory.getLogger(NodeStatusConsumer.class);

}
