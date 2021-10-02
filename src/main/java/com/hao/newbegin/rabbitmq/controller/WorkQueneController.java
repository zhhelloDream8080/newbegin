package com.hao.newbegin.rabbitmq.controller;

import com.hao.newbegin.rabbitmq.workquene.Consumer1Service;
import com.hao.newbegin.rabbitmq.workquene.Consumer2Service;
import com.hao.newbegin.rabbitmq.workquene.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkQueneController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private Consumer1Service consumer1Service;
    @Autowired
    private Consumer2Service consumer2Service;

    @GetMapping("/workQueneProvider")
    public void testProvider(){
        try {
            providerService.sendMessage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/workQueneConsumer1")
    public void testConsumer1(){
      try {
          consumer1Service.consumeMessage();
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @GetMapping("/workQueneConsumer2")
    public void testConsumer2(){
        try {
            consumer2Service.consumeMessage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
