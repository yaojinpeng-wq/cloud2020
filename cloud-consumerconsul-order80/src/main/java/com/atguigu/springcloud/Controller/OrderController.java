package com.atguigu.springcloud.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    private static final String CONSUL_URL = "http://localhost:8006";

    /**
     * 服务调用者
     */
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/consul")
    public String consumerConsul(){
        return restTemplate.getForObject(CONSUL_URL+"/payment/consul",String.class);
    }
}
