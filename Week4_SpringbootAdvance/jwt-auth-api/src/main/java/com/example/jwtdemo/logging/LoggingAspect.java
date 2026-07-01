package com.example.jwtdemo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(LoggingAspect.class);

    private static final Logger AUDIT_LOG =
            LoggerFactory.getLogger("AUDIT_LOGGER");

    // ── Pointcut: all methods in controller, service, repository ──────────

    @Pointcut("within(com.example.jwtdemo.controller..*)" +
              " || within(com.example.jwtdemo.service..*)")
    public void applicationLayer() {}

    // ── Around: wrap every service/controller call ────────────────────────

    @Around("applicationLayer()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String className  = joinPoint.getSignature()
                                     .getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.debug("→ Entering {}.{}() with args: {}",
                  className, methodName,
                  Arrays.toString(joinPoint.getArgs()));

        long start = System.currentTimeMillis();
        Object result;

        try {
            result = joinPoint.proceed();   // Execute the actual method
        } catch (Exception ex) {
            log.error("✗ Exception in {}.{}() → {}: {}",
                      className, methodName,
                      ex.getClass().getSimpleName(), ex.getMessage());
            throw ex;  // Re-throw so GlobalExceptionHandler still catches it
        }

        long elapsed = System.currentTimeMillis() - start;
        log.debug("← Exiting  {}.{}() — took {}ms",
                  className, methodName, elapsed);

        return result;
    }

    // ── After: audit controller method invocations ────────────────────────

    @AfterReturning(
        pointcut = "within(com.example.jwtdemo.controller..*)",
        returning = "result"
    )
    public void auditControllerCall(JoinPoint joinPoint, Object result) {
        String className  = joinPoint.getSignature()
                                     .getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        AUDIT_LOG.info("[AOP-AUDIT] {}.{}() completed successfully",
                       className, methodName);
    }

    // ── AfterThrowing: log any exception thrown by service/controller ──────

    @AfterThrowing(
        pointcut = "applicationLayer()",
        throwing  = "ex"
    )
    public void logException(JoinPoint joinPoint, Exception ex) {
        String className  = joinPoint.getSignature()
                                     .getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.error("[EXCEPTION] {}.{}() threw {}: {}",
                  className, methodName,
                  ex.getClass().getSimpleName(), ex.getMessage());
    }
}
