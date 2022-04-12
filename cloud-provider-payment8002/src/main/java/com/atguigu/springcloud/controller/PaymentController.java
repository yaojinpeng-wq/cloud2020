package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.Service.PaymentService;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j // 日志监控
public class PaymentController {

    @Autowired
    private PaymentService paymentService; // 注入 Service 接口

    @Value("${server.port}")
    private String serverPort;

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



}
