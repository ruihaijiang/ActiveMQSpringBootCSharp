/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.jmsclient;

import com.example.demo.message.NodeStatus;
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
public class NodeStatusProducer {
    
    @Autowired
    @Qualifier("topicJmsTemplate")
    JmsTemplate jmsTemplate;

    @Value("${app.node-status-topic-name}")
    private String topicName;

    
    /* when a node is online or offline, send node status message to all nodes 
    Topic1: "node-status"
    */
    public void sendMessage( NodeStatus status ){
        try{
            //logger.debug("NodeStatusProducer send message to queue: "+ topicName+", object = "+status);
            String json = objectMapper.writeValueAsString(status);
            logger.info("NodeStatusProducer send message to queue: "+ topicName+", json = "+json);
            jmsTemplate.convertAndSend(topicName, json);
        } catch(Exception e){
           logger.error("NodeStatusProducer got exception: ", e);
        }
    }   
    private static final Logger logger = LoggerFactory.getLogger(NodeStatusProducer.class);
    ObjectMapper objectMapper = new ObjectMapper();   
}
