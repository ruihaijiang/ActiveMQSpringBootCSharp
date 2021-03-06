
package com.example.demo.controller;

import com.example.demo.jmsclient.NodeStatusProducer;
import com.example.demo.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ruihaijiang
 */
@RestController
@RequestMapping(path = "/test")
public class TestController {
    
    
    /*
    curl -X POST "http://localhost:8091/test/SendStatus?nodeId=node1&status=2"
    */
    @PostMapping(path = "/SendStatus")
    public void sendNodeStatus(@RequestParam("nodeId") String nodeId, 
            @RequestParam("status") Integer status ){
        logger.info("sendNodeStatus(), nodeId="+nodeId+", status = " +status);
        statusService.sendStatus(nodeId, status);
    }    
    
    
    @Autowired
    StatusService statusService;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);    
}
