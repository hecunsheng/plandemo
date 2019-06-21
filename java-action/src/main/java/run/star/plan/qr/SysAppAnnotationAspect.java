package run.star.plan.qr;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 *  模板方法切入点
 */
@Component
@Aspect
public class SysAppAnnotationAspect {

    /** 默认业务日志 */
    private static Logger logger  = LoggerFactory.getLogger(SysAppAnnotationAspect.class);

    public static ThreadLocal<SysAppMethod>  sysMethod = new ThreadLocal<>();

    private static final String POINTCUT = "@annotation(com.qr.commons.spring.template.annotation.SysAppAnnotation)";

    /**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
     * 而且环绕通知必须有返回值，返回值即为目标方法的返回值
     */
    @Around(POINTCUT)
    public Object aroundMethod(ProceedingJoinPoint pjd) {

        Object result ;
        long startTime = System.currentTimeMillis();
        SysAppMethod sysAppMethod = new SysAppMethod();
        try {
            Object[] args = pjd.getArgs();
            Signature sig = pjd.getSignature();
            if (sig instanceof MethodSignature) {
                String methodName=pjd.getSignature().getName();
                Class<?> classTarget=pjd.getTarget().getClass();
                Class<?>[] par=((MethodSignature) sig).getParameterTypes();
                Method objMethod=classTarget.getMethod(methodName, par);
                SysAppAnnotation annotation = objMethod.getAnnotation(SysAppAnnotation.class);
                if (annotation != null) {
                    setSysAppMethod(sysAppMethod,annotation, args,sig);
                }
            }
            result = pjd.proceed(args);
        } catch (Throwable e) {
            result = Results.newFailedResult(null, CommonStateCode.INNER_SERVER_ERROR,
                    e.getMessage());
        } finally {
            logger.info(sysAppMethod.toString() + " 耗时：" + (System.currentTimeMillis() - startTime));
        }
        return result;
    }

    /**
     * 设置方法变量
     * @param sysAppMethod
     * @param annotation
     * @param args
     * @param sig
     */
    private void setSysAppMethod(SysAppMethod sysAppMethod, SysAppAnnotation annotation, Object[] args, Signature sig) {
        sysAppMethod.setLock(annotation.lock());
        sysAppMethod.setTransaction(annotation.tran());
        sysAppMethod.setNeedUnLock(annotation.needUnLock());
        sysAppMethod.setObj(args);
        sysAppMethod.setClassName(sig.getDeclaringTypeName());
        sysAppMethod.setMethodName(sig.getName());
        sysAppMethod.setSysName(sig.getDeclaringTypeName().substring(9, sig.getDeclaringTypeName().indexOf(".",10)));
        if (!StringUtils.isBlank(annotation.bizKey())) {
            sysAppMethod.setDigestTag(value(args, annotation.bizKey()));
        }
        if (!StringUtils.isBlank(annotation.lockKey())) {
            sysAppMethod.setLockKey(value(args,annotation.lockKey()));
        }
        sysMethod.set(sysAppMethod);
    }


    /**
     *
     * @param objs
     * @param formula  prdexprdex[1.userid]_[2.storeid]    [] 内为参数取值， 1 代表第一个参数， userid 参数下的属性字段
     * @return
     */
    private String value(Object[] objs, String formula) {
        String[] params = formula.split("\\[");
        if (params == null || params.length == 0){
            return "";
        }
        StringBuffer buffer = new StringBuffer();

        for (String param : params) {
            if (param.length() <= 1 || param.indexOf("]") <= 0){
                buffer.append(param);
                continue;
            }
            String[] ps1 = param.split("]");
            String[] ps = ps1[0].split("\\.");
            Object o = null;
            if (ps.length >= 1 && (Integer.parseInt(ps[0])-1) < objs.length) {
                o = objs[Integer.parseInt(ps[0]) - 1 ];
            }
            if (o == null){
                buffer.append("null");
            }else if (ps.length == 1){
                buffer.append(o.toString());
            }else{
                try {
                    Method getter = o.getClass().getMethod(
                            "get" + ps[1].substring(0, 1).toUpperCase() + ps[1].substring(1));
                    Object ob = getter.invoke(o);
                    if (ob != null ){
                        buffer.append(ob.toString());
                    }
                } catch (Exception e) {
                    logger.warn("方法模板提取参数数据异常"+ formula, e);
                }
            }
            if (ps1.length>1){
                buffer.append(ps1[1]);
            }
        }

        return buffer.toString();
    }



}