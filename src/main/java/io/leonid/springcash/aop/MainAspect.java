package io.leonid.springcash.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leonid on 04.11.14.
 */
@Aspect
public class MainAspect {
    private static final Logger logger = LoggerFactory.getLogger(MainAspect.class);

    @Before("allServiceMethodsPointcut()")
    public void allServiceMethodsAdvice(JoinPoint joinPoint){
        logger.info("Service method called: {}", joinPoint.toString());
    }

    //Pointcut to execute on all the methods of service classes
    @Pointcut("within(io.leonid.springcash.service.impl.*)")
    public void allServiceMethodsPointcut(){
    }
}
