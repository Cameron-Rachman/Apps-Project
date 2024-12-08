package com.example.labai.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.labai.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before executing: {}", joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.example.labai.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("After executing: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.example.labai.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.labai.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in {}.{} with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), error.getCause() != null ? error.getCause() : "NULL");
    }

    @Around("execution(* com.example.labai.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info("Method {} executed in {}ms", joinPoint.getSignature().toShortString(), elapsedTime);
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {} in {}", e.getMessage(), joinPoint.getSignature().toShortString());
            throw e;
        }
    }
}