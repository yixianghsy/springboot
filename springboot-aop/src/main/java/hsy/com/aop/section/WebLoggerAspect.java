package hsy.com.aop.section;

import com.google.gson.Gson;
import hsy.com.aop.validator.WebLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class WebLoggerAspect{

    @Pointcut("@annotation(hsy.com.aop.validator.WebLogger)")
    public void log(){}

    /**
     计算接口调用时间
     统计请求的参数时有个bug，joinpoint.getArgs返回值是一个Object数组，但是数组里只有参数值，没有参数名。
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        System.out.println("Response："+new Gson().toJson(result));
        System.out.println("耗时："+(System.currentTimeMillis()-startTime));
        return result;
    }

    /**
     统计request请求相关参数
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        //获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println("==================Start=================");
        System.out.println("URL：" + request.getRequestURL().toString());
        System.out.println("Description：" + getLogValue(joinPoint));//获取目标方法的描述
        System.out.println("Method：" + request.getMethod().toString());//获取目标请求方式 GET

        //打印controller全路径及method
        System.out.println("Class Method：" + joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());//获取目标请求全类名和

        System.out.println("客户端IP：" + request.getRemoteAddr());
        System.out.println("请求参数：" + new Gson().toJson(joinPoint.getArgs()));//获取请求参数
    }

    private String getLogValue(JoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        WebLogger webLogger = method.getAnnotation(WebLogger.class);//获取注解对象

        return webLogger.value();//获取注解对象的值
    }

    @After("log()")
    public void doAfter() {
        System.out.println("==================End=================");
    }
}
