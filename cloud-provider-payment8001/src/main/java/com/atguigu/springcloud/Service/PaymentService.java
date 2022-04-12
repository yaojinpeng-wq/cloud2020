package com.atguigu.springcloud.Service;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    /**
     * 增加用户
     * @param payment
     */
    public  int creat(Payment payment);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    public Payment getPaymentById(@Param("id") Long id);

}
