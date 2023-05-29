package com.hbut.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.hbut.common.aop.LogAnnotation)")
    public void pt(){}

    //环绕通知
    @Around("pt()")
    public Object log(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis()-beginTime;
        recordLog(joinPoint,time,result);
        return result;
        //执行时长
    }

    private void recordLog(@NotNull ProceedingJoinPoint joinPoint, long time,Object data){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("========================log start========================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());

        //请求的方法名
        String classname = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",classname+"."+methodName+"()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);

        // String params = JSON.toJSONString(args.toString());
        log.info("params:{}",params);
        //获取响应体
        String response = data.toString();
        log.info("response : {}",response);
        // //获取request 设置IP地址
        // HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // log.info("ip{}", IpUtils.getIpaddr(request));

        log.info("excute time : {} ms",time);
        log.info("========================log end==========================");
    }
}
