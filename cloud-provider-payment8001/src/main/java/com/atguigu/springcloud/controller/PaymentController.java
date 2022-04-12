package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.Service.PaymentService;
import com.atguigu.springcloud.Service.impl.PaymentServiceImpl;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j // 日志监控
public class PaymentController {

    @Autowired
    private PaymentService paymentService; // 注入 Service 接口

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 插入数据
     * @param payment
     * @return
     */
    @PostMapping("/payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        int creat = paymentService.creat(payment);
        log.info("插入结果"+creat);
        if (creat > 0){
            return new CommonResult(200,"插入成功,服务端口serverPort："+serverPort,creat);
        }else {
            return new CommonResult(444,"插入失败",null);
        }
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        if (payment != null){
            return new CommonResult(200,"查询成功，服务端口serverPort："+serverPort,payment);
        }else{
            return new CommonResult(444,"查询失败",null);
        }
    }



    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD_PAYMENT_SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getMetadata());
        }
        return this.discoveryClient;

    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return serverPort;

    }



}
