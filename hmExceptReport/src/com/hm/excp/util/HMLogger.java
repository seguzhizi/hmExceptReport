/*
 * 文件名：HMLogger.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：与日志记录，存储相关的类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.SysLog;
import com.hm.excp.service.ISysLogService;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;

/**
 * 轻度封装Log4j，充当一个代理，主要的方法还是由Log4j提供
 * <p>只提供了    #info   和   #error  两个方法,在该方法中提供一个判断标志位，表示此日志信息是否需要存储
 * <p>其他的方法未提供，可以通过getLogger获取原生Log进行调用
 * <p>注：目前只是在Action类中使用，在service，dao层使用会出现异常
 * <p>因为在spring初始化时，HMLogger会使用ApplicationContext,而此时可能service层本身还没有被初始化
 * <p>即ApplicationContext目前还未完成bean的初始化，所以它还不可用，即会导致调用失败
 * @author zjj 2016-2-18
 * @see
 * @since 1.0
 */
public class HMLogger {

    /**spring的容器对象ApplicationContext*/
    private static ApplicationContext context = WebUtil.getApplicationContext();
    
    /* 由于HMLogger需要记录类的信息，并且由于组合了Log4J,它的get方法也需要包含一个Clazz类型的参数
     * 所以HMLogger必须提供一个带有Class类型的构造器
     * 但是由于此Class类型的参数是不固定的（由传入的参数决定），所以不能使用spring构造的方式注入（NullPointer）
     * 另一方面，如果使用set方法注入，那么HMLogger必须提供一个构造方法，由上所述，其已经提供了一个带有Class的构造方法了，
     * 所以spring不会调用默认的无参构造方法，所以set方法也会失效
     * 接口注入就更不行了
     * 所以这里就没有使用注入的方式，而是直接整合了spring的容器，直接从容器中硬编码获取指定的bean
     * */
    /**日志处理相关的service类*/
    private ISysLogService logService;
    
    /**封装的Log4J对象*/
    private Logger logger;
    
    /**操作用户,需要从数据库中查询出来*/
    private People xpeople;
    
    /**
     * 构造方法
     * <p>可用构造方法来获取HMLogger对象
     * @param 
     *        Class clazz
     * @author zjj 2016-2-18
     * @see
     * @since 1.0
     */
    public HMLogger(Class clazz){
        this.logger = Logger.getLogger(clazz);
        this.logService = (ISysLogService) context.getBean("SysLogService");
    }
    
    /**
     * get方法
     * <p>可用get方法来获取HMLogger对象
     * @param 
     *        Class clazz
     * @author zjj 2016-2-18
     * @see
     * @since 1.0
     */
    public static HMLogger getHMLogger(Class clazz){
        return new HMLogger(clazz);
    }
    
    /**
     * 返回原生的Logger，可以使用它的原生方法
     * @return
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public Logger getLogger()
    {
        return logger;
    }
    /********************************info*************************************/
    
    /**
     * 代理原生的info方法,
     */
    public void info(String msg){
        logger.info(msg);
    }
    
    /**
     * 代理原生的info方法,不过添加了存储日志信息功能
     * @param flag
     *          true：表示存储日志信息；fasle：表示不存储
     * @param logMsg
     *          需要存储的具体日志信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void info(boolean flag,String logMsg){
        logger.info(logMsg);
        if(flag){
//            System.out.println("---------存储日志-----" + logMsg);
            saveLog(logMsg);
        }
    }
    
    /**
     *  代理原生的info方法,包含错误信息的形参 
     * @param logMsg
     *          显示具体的错误信息
     * @param e
     *          显示错误的异常信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public <T extends Exception> void info(String logMsg,T e){
        logger.info(logMsg,e);
    }
    
    /**
     * 代理原生的info方法,包含错误信息的形参，提供存储日志功能
     * @param flag
     *          true：表示存储日志信息；fasle：表示不存储
     * @param logMsg
     *          需要存储的具体日志信息
     * @param e
     *          显示错误的异常信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void info(boolean flag,String logMsg,Throwable e){
        logger.info(logMsg,e);
        if(flag){
            saveLog(logMsg);
        }
    }
    
    /********************************warn*************************************/
    /**
     * 代理原生的warn方法,
     */
    public void warn(String msg){
        logger.warn(msg);
    }
    
    /**
     * 代理原生的warn方法,不过添加了存储日志信息功能
     * @param flag
     *          true：表示存储日志信息；fasle：表示不存储
     * @param logMsg
     *          需要存储的具体日志信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void warn(boolean flag,String logMsg){
        logger.warn(logMsg);
        if(flag){
//            System.out.println("---------存储日志-----" + logMsg);
            saveLog(logMsg);
        }
    }
    
    /**
     *  代理原生的warn方法,包含错误信息的形参 
     * @param logMsg
     *          显示具体的错误信息
     * @param e
     *          显示错误的异常信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public <T extends Exception> void warn(String logMsg,T e){
        logger.warn(logMsg,e);
    }
    
    /**
     * 代理原生的warn方法,包含错误信息的形参，提供存储日志功能
     * @param flag
     *          true：表示存储日志信息；fasle：表示不存储
     * @param logMsg
     *          需要存储的具体日志信息
     * @param e
     *          显示错误的异常信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void warn(boolean flag,String logMsg,Throwable e){
        logger.warn(logMsg,e);
        if(flag){
            saveLog(logMsg);
        }
    }
    /********************************error*************************************/
    public void error(String logMsg){
        logger.error(logMsg);
    }
    
    /**
     * 代理原生的error方法,不过添加了存储日志信息功能
     * @param flag
     *          true：表示存储日志信息；fasle：表示不存储
     * @param logMsg
     *          需要存储的具体日志信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void error(boolean flag ,String logMsg){
        logger.error(logMsg);
        if(flag){
            saveLog(logMsg);
        }
    }
    /**
     *  代理原生的error方法,包含错误信息的形参 
     * @param logMsg
     *          显示具体的错误信息
     * @param e
     *          显示错误的异常信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void error(String logMsg,Throwable t){
        logger.error(logMsg, t);
    }
    /**
     * 代理原生的error方法,包含错误信息的形参，提供存储日志功能
     * @param flag
     *          true：表示存储日志信息；fasle：表示不存储
     * @param logMsg
     *          需要存储的具体日志信息
     * @param e
     *          显示错误的异常信息
     * @author zjj 2016-2-18
     * @since 1.0
     */
    public void error(boolean flag,String logMsg,Throwable t){
        logger.error(logMsg, t);
        if(flag){
            saveLog(logMsg);
        }
    }
    
    /**
     * 存储日志的功能
     * @param logMsg
     * @author zjj 2016-2-18
     * @since 1.0
     */
    protected void saveLog(String logMsg){
        xpeople = (People) WebUtil.getHttpSession().getAttribute(XPEOPLE_INFO.XPEOPLE);
        SysLog log = new SysLog();
        
        if(xpeople != null){
            log.setUsername(xpeople.getUsername());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            log.setLogTime(ts);
            log.setLogType("1");
            log.setLogMsg(logMsg);
            
            String ip = WebUtil.getHttpRequest().getRemoteAddr();
            log.setIp(ip);
            
            logService.saveLog(log);
        }
        log = null;
    }
    
//    public void setLogger(Logger logger)
//    {
//        this.logger = logger;
//    }

    public ISysLogService getLogService()
    {
        return logService;
    }

    public void setLogService(ISysLogService logService)
    {
        this.logService = logService;
    }

    public People getXpeople()
    {
        return xpeople;
    }

    public void setXpeople(People xpeople)
    {
        this.xpeople = xpeople;
    }

}
