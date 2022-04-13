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
        System.out.println("我是服务调用者");
        System.out.println("master-01-------->   我是微服务调用者");
        System.out.println("master---主分支提交-->主分支的为服务调用者");
        System.out.println("master-01-- 第二次提交 -------->   我是微服务调用者");
        System.out.println("Github 提交到远程库");
        System.out.println("Github push到本地仓库");
        return restTemplate.getForObject(CONSUL_URL+"/payment/consul",String.class);
    }
}
