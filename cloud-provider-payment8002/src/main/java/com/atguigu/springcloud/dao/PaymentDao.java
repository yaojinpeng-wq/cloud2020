package com.atguigu.springcloud.dao;


import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

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
