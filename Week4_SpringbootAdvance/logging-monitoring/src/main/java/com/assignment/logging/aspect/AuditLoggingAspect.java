package com.assignment.logging.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuditLoggingAspect {

    // Named AUDIT_LOGGER to match the dedicated appender in logback-spring.xml
    private static final Logger auditLog = LoggerFactory.getLogger("AUDIT_LOGGER");
    private static final Logger log = LoggerFactory.getLogger(AuditLoggingAspect.class);

    @Pointcut("execution(* com.assignment.logging.service.*.*(..))")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object logServiceCall(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().toShortString();
        String args = Arrays.toString(pjp.getArgs());

        auditLog.info("CALL | method={} | args={}", method, args);
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        long elapsed = System.currentTimeMillis() - start;
        auditLog.info("SUCCESS | method={} | duration={}ms", method, elapsed);

        return result;
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
    public void logServiceException(JoinPoint jp, Throwable ex) {
        String method = jp.getSignature().toShortString();
        auditLog.error("FAILURE | method={} | exception={} | message={}",
                method, ex.getClass().getSimpleName(), ex.getMessage());
    }
}