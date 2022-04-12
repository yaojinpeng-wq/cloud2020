package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 *  服务之间的调用：原始阶段 用 HttpClient。进化之后、用 RestTemplate
 *
 *  使用 RestTemplate 访问 restful 接口非常简单粗暴无脑
 *  (url、requestMap、ResponseBean.class) 这三个参数分别代表
 *  REST请求地址、请求参数、HTTP响应转换被转换成的对象类型
 *
 *  要是用 RestTemplate 对象，需要把它注入到容器中
 *
 *
 *  负载均衡算法： rest接口第几次请求数 % 服务器集群数量 = 实际调用服务器位置下标 ，每次重启之后 rest 接口技术从1开始
 *
 */
@RestController
@Slf4j
public class OrderController {

    // private static final String PAYMENT_URL = "http://localhost:8001"; // 生产者url
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; // 生产者url

    @Resource
    private RestTemplate restTemplate;

    /**
     * 添加数据
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/creat")
    public CommonResult<Payment> creat(Payment payment){

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    /**
     * 根据 id 查询数据
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
     *  resTemplate.getEntity() 方法
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<>(444,"查询失败");
        }
    }



}
