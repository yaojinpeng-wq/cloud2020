package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component // 交给 spring 容器托管
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") // eureka 上面 微服务的名称
public interface PaymentFeignService {

    /**
     * 接口+ feignClient 注解
     * 调用的 controller 层 实际也是调用的 service 层
     * 根据 feignClient 所显示的微服务名称，去调用服务侧所对外提供的方法
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout();
}

