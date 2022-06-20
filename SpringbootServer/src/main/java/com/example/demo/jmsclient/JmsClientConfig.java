/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.jmsclient;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.format.DateTimeFormatter;
import javax.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 *
 * @author ruihaijiang
 */
@Configuration
@EnableJms
public class JmsClientConfig {
   
  
    @Bean
    public JmsListenerContainerFactory<?> queueListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        //factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        //factory.setMessageConverter(messageConverter());
        return factory;
    }
    
        
	@Bean
	public JmsTemplate queueJmsTemplate( ConnectionFactory connectionFactory ) {
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory);
            //template.setMessageConverter(messageConverter());
	    return template;
	}  
    
	@Bean
	public JmsTemplate topicJmsTemplate( ConnectionFactory connectionFactory ) {
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory);
            //template.setMessageConverter(messageConverter());
            template.setPubSubDomain(true);
	    return template;
	}    
}
