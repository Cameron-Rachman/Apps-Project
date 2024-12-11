package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.demo.services.*.*(..))")
    public void logBefore() {
        logger.info("Executing method...");
    }

    @After("execution(* com.example.demo.services.*.*(..))")
    public void logAfter() {
        logger.info("Method executed.");
    }

    @AfterReturning(value = "execution(* com.example.demo.services.*.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        logger.info("Method returned with result: {}", result);
    }

    @AfterThrowing(value = "execution(* com.example.demo.services.*.*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        logger.error("An exception occurred: {}", exception.getMessage());
    }

    @Around("execution(* com.example.demo.services.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method execution started: {}", joinPoint.getSignature());
        Object result = joinPoint.proceed();
        logger.info("Method execution finished: {}", joinPoint.getSignature());
        return result;
    }
}