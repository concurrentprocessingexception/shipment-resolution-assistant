package com.example.genai.shipment.resolution.app.observability;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AiAgentObservabilityAspect {

    @Around("execution(* com.example.genai.shipment.resolution.app.agent.*.*(..))")
    public Object observeAgentInvocation(ProceedingJoinPoint joinPoint) throws Throwable {

        String agentName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        boolean success = true;

        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            success = false;
            throw ex;
        } finally {
            long duration = System.currentTimeMillis() - start;

            log.info("""
                AI Agent Invocation:
                Agent     : {}
                Method    : {}
                Duration  : {} ms
                Success   : {}
                """,
                    agentName,
                    methodName,
                    duration,
                    success
            );
        }
    }
}
