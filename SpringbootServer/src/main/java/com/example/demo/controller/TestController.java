
package com.example.demo.controller;

import com.example.demo.service.StatusService;
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
    curl -X POST "http://localhost:8080/test/sendStatus?nodeId=node1&status=2"
    */
    @PostMapping(path = "/sendStatus")
    public void publishCustomer(@RequestParam("nodeId") String nodeId, 
            @RequestParam("status") Integer status ){
        statusService.sendStatus(nodeId, status);
    }    
    
    
    @Autowired
    StatusService statusService;
}
