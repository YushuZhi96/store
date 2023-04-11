package com.zys.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect//将当前类标记为切面类
@Component//将当前类的对象创建使用维护交由Spring容器维护
public class TimerAspect {


    @Around("execution(* com.zys.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        //开始时间
        long start = System.currentTimeMillis();
        //调用目标方法
        Object result = joinPoint.proceed();
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end-start));
        return result;
    }
}
