package star.plan.demo.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 基于注解定义切面
 * @Author: hecs
 * @Date: 2018/11/8 14:45
 * @Description:
 */
@Component//声明这个类是被SpringIOC容器来管理的，如果不声明，就无法做到自动织入
@Aspect//这个类被声明为是一个需要动态织入到我们的虚拟切面中的类
public class AnnotationAspect {
    private final static Logger log = Logger.getLogger(AnnotationAspect.class);

    @Pointcut("execution(* star.plan.demo.aop.service..*(..))")
    public void pointcutConfig(){}

    @Before("pointcutConfig()")
    public void before(JoinPoint joinPoint){
        log.info("方法调用之前执行");
    }
    @After("pointcutConfig()")
    public void after(JoinPoint joinPoint){
        log.info("调用之后执行" + joinPoint);
    }

    @AfterReturning(returning="returnValue",value="pointcutConfig()")
    public void afterReturn(JoinPoint joinPoint,Object returnValue){
        log.info("调用获得返回值" + returnValue);
    }


    @AfterThrowing("pointcutConfig()")
    public void afterThrow(JoinPoint joinPoint){
        System.out.println("切点的参数" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("切点的方法" + joinPoint.getKind());
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget()); //生成以后的代理对象
        System.out.println(joinPoint.getThis());//当前类的本身(通过反射机制去掉用)

        log.info("抛出异常之后执行" + joinPoint);
    }

}
