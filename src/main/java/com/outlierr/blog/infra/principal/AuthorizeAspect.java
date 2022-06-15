package com.outlierr.blog.infra.principal;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
@Slf4j
public final class AuthorizeAspect {
    @Pointcut("@within(com.outlierr.blog.infra.principal.RequirePermission)")
    private void clazz(){};

    @Pointcut("@annotation(com.outlierr.blog.infra.principal.RequirePermission)")
    private void method(){};

    /**
     * clazz() && !method() 类上有且类中的方法没有则走这个切点 否则会走下面的切点
     * 类上存在注解但方法上不存在时，以类上的注解来鉴权 并且方法是 controller 包下的
     *
     * @param joinPoint 切点
     * @throws Exception 如果鉴权失败则抛出异常。
     */
    @Before("clazz() && !method() && execution(public * com.outlierr.blog.api.controller..*.*(..))")
    @SuppressWarnings("unchecked")
    public void beforeClass(JoinPoint joinPoint) throws Exception {
        RequirePermission annotation = (RequirePermission) joinPoint.getSignature()
                .getDeclaringType()
                .getDeclaredAnnotation(RequirePermission.class);
        check(annotation, joinPoint);
    }

    /**
     * 方法上存在注解，需要鉴权。
     *
     * @param joinPoint 切点
     * @throws Exception 如果鉴权失败则抛出异常。
     */
    @Before("method() && execution(public * com.outlierr.blog.api.controller..*.*(..))")
    private void beforeMethod(JoinPoint joinPoint) throws Exception{
        RequirePermission annotation = ((MethodSignature) joinPoint.getSignature())
                .getMethod()
                .getDeclaredAnnotation(RequirePermission.class);
        check(annotation, joinPoint);
    }

    private void check(RequirePermission annotation, JoinPoint joinPoint) throws Exception {
        if (annotation.value() && !SecurityContext.getPrincipal().hasPermission()) {
            log.info("Permission check failed for method: " + joinPoint.getSignature());
            throw annotation.error().getConstructor().newInstance();
        }
    }
}
