package com.hm.excp.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.poi.util.SystemOutLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.hm.excp.dao.pojo.People;

@Aspect
public class SysLogInterceptor
{

//    @Pointcut("execution(public * com.hm.excp.action..*.*(..))")
//    public void myMethod(){
//        
//    }

//    @Around("myMethod()")
//    public void before(ProceedingJoinPoint pjp) throws Throwable{
//        System.out.println("method before");
//        pjp.proceed();
//    }
//    @AfterReturning("myMethod()")
//    public void afterReturning() throws Throwable{
//        System.out.println("method afterReturning");
//    }
//    @After("myMethod()")
//    public void afterFinily() throws Throwable{
//        System.out.println("method end");
//    }

    
    /**Spring + AspecrJ(基于注解：通过AspectJ annotation表达式拦截方法)
                        这个是基于方法级别的拦截,并且拦截的方式是被下面配置的Annotation注解了的形式
     * */                
    @Around("@annotation(com.hm.excp.util.NeedLog)")
    public Object aroundTag(ProceedingJoinPoint pjp) throws Throwable{
        
        long start = System.currentTimeMillis();
//        beforeTag();
        
        Object result = pjp.proceed();
        try{
            Object aa = pjp.getTarget();
            Class clazz = aa.getClass();

            //People com.hm.excp.service.LoginServiceImpl.checkLogin(People)
            System.out.println(pjp.getSignature());
            System.out.println("---------------------------------------");
            
//            Method getMethod = clazz.getMethod("checkLogin", People.class);
            
//            if(getMethod.isAnnotationPresent(NeedLog.class)){
//                NeedLog log = getMethod.getAnnotation(NeedLog.class);
//                String logMsg = log.logMsg();
//                System.out.println(logMsg+"---------------------------");
//            }
            
//            Logger logger = (Logger) getMethod.invoke(aa);
            
        }catch(Throwable e){
            e.printStackTrace();
            throw e;
        }
//        afterTag();
        return result;
    }
    private void beforeTag() {
        System.out.println("before in HelloAspect in tag");
    }
    private void afterTag() {
        System.out.println("after in HelloAspect in tag");
    }
    
}
