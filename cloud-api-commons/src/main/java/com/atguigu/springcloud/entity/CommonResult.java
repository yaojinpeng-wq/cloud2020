package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Json 封装体
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    public Integer code; // 状态码
    public String message; // 提示消息
    public T      data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }


}
