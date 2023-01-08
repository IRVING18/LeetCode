package com.jiejiedai.api.annation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//设置运行时可获取的生命周期
@Inherited//设置可继承
@Documented//设置可用于javadoc
@Target({ElementType.FIELD})//设置可在成员变量上使用该注解
public @interface Init {

    //定义注解方法
    public String set() default "";
}
