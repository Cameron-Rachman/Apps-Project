package com.example.assignment_1.aspects;

import com.example.assignment_1.entities.Household;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
@Component
public class HouseholdServiceAOP {

    @Before("execution(* com.example.assignment_1.services.HouseholdService.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        log.info("Executing method: {}.{} with arguments {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs()
        );
    }

    @After("execution(* com.example.assignment_1.services.HouseholdService.*(..))")
    public void afterExecutingMethod(JoinPoint joinPoint) {
        log.info("Done with {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.assignment_1.services.HouseholdService.findByEircodeWithPetsLazy(..))", returning = "household")
    public void afterReturningFromFindHouseLazy(JoinPoint joinPoint, Household household) {
        log.info("Leaving {} with {}", joinPoint.getSignature().getName(), household);
    }
}