package com.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.aop.demo..*.*(..))")
    public void webLog() {
    }

    //aop环绕通知拦截来搞一下
    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //获取请求的类名
        String className = point.getTarget().getClass().getName();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取请求的方法名
        String methodName = signature.getMethod().getName();
        log.info(String.format("类名：%s，方法名：%s，入参：%s", className, methodName ));
        StopWatch watch = new StopWatch();
        watch.start();
        //执行方法
        Object result = point.proceed();
        watch.stop();
        log.info(String.format("类名：%s，方法名：%s，耗时：%s，出参：%s", className, methodName, watch.getLastTaskTimeMillis()));
        return result;
    }
}
