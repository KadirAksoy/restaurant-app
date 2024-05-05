package com.kadiraksoy.restaurantapp.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {


    @After("execution(* com.kadiraksoy.restaurantapp.controller.*.*(..))")
    public void logBeforeCategoryControllerMethods(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("--->" + className + " içinde " + methodName + " metodu çağrıldı.");
    }

}
