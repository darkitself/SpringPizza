package com.example.springpizza.domain.audit;

import com.example.springpizza.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService auditService;

    @Around("@annotation(Auditable)")
    public Object handleAudit(ProceedingJoinPoint jp) throws Throwable {
        log.info("Before jp");
        Object result;
        try {
            result = jp.proceed();
        } catch (Throwable ex) {
            log.error("Exception in jp");
            throw ex;
        }
        log.info("After jp");
        return result;
    }

    @AfterReturning("@annotation(Auditable)")
    public Object handleAudit(JoinPoint jp) {

        Object[] args = jp.getArgs();
        var method = ((MethodSignature) jp.getSignature()).getMethod();
        var annotation = method.getAnnotation(Auditable.class);
        // TODO: append request to message
        handleAudit(annotation, AuditableResult.SUCCESS, args, null);
        return jp;
    }

    //@AfterThrowing(value = "@annotation(Auditable)", throwing = "ex")
    @AfterThrowing(value = "execution(* com.example.springpizza.service.OrderService.getOrder(..))", throwing = "ex")
    public Object handleAuditEx(JoinPoint jp, Exception ex) {

        Object[] args = jp.getArgs();
        var method = ((MethodSignature) jp.getSignature()).getMethod();
        var annotation = method.getAnnotation(Auditable.class);
        handleAudit(annotation, AuditableResult.ERROR, args, ex);
        return jp;
    }

    private void handleAudit(Auditable auditable, AuditableResult result, Object[] args, Exception ex) {
        if (args[0] instanceof UserEntity user) {
            auditService.updateAudit(auditable.type(), user.getId(), result);
        } else {
            auditService.updateAudit(auditable.type(), null, result, ex.getLocalizedMessage());
        }
    }
}
