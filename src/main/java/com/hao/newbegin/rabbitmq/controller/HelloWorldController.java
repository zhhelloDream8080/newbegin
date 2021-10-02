package com.hao.newbegin.rabbitmq.controller;

import com.hao.newbegin.rabbitmq.hellworld.ConsumerService;
import com.hao.newbegin.rabbitmq.hellworld.ProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 测试helloworld类型的消息队列
 */
@RestController
public class HelloWorldController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/helloWorldprovider")
    public void testProvider(String provide){
        if (StringUtils.isNoneBlank(provide)){
            try {
                providerService.testSendMessage(provide);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
    @GetMapping("/helloWorldConsumer")
    public void testConsumer(String consume){
        if (StringUtils.isNoneBlank(consume)){
            try {
                consumerService.consumeMessage();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
