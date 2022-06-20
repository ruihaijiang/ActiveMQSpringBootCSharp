
package com.example.demo.jmsclient;

import com.example.demo.message.StockChanged;
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
public class StockChangedConsumer {
    /*
        Topic2: "heart-beat"
    */
    @JmsListener(destination = "${app.stock-changed-topic-name}", containerFactory="${app.topic-factory-name}")
    public void onMessage(ActiveMQTextMessage  message) throws JMSException, JsonProcessingException {
        
        if (message !=null ) {
            String text = message.getText();
            //logger.info("StockChangedConsumer received StockChanged message:"+text);
            StockChanged  object = objectMapper.readValue(text, StockChanged.class);
            logger.info("StockChangedConsumer received StockChanged object = :"+object.toString());

        } else {
            logger.info("StockChangedConsumer received null");
        }

    }
    
   
    private static final Logger logger = LoggerFactory.getLogger(StockChangedConsumer.class);
    ObjectMapper objectMapper = new ObjectMapper();

}
