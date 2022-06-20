
package com.example.demo.jmsclient;

import com.example.demo.message.OrderChanged;
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
public class OrderChangedConsumer {
    /*
        Topic2: "heart-beat"
    */
    @JmsListener(destination = "${app.order-changed-topic-name}", containerFactory="${app.topic-factory-name}")
    public void onMessage(ActiveMQTextMessage  message) throws JMSException, JsonProcessingException {
        
        if (message !=null ) {
            String text = message.getText();
            //logger.info("OrderChangedConsumer received OrderChanged message:"+text);
            OrderChanged  object = objectMapper.readValue(text, OrderChanged.class);
            logger.info("OrderChangedConsumer received object = :"+object.toString());

        } else {
            logger.info("TopicConsumer2 received null");
        }

    }
        
    private static final Logger logger = LoggerFactory.getLogger(OrderChangedConsumer.class);
    ObjectMapper objectMapper = new ObjectMapper();

}
