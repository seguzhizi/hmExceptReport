/*
 * 文件名：InitParamsLoad.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：系统中需要使用到的一些参数，在容器启动时就初始化
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 系统中需要使用到的一些参数，在容器启动时就初始化
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public class InitParamsLoad
{
    /**日志处理对象*/
    static Logger logger = Logger.getLogger(InitParamsLoad.class);
    
    /**角色和角色描述对应关系 */
    public static Map<String,String> role_map = new HashMap<String,String>();
    
    /**
     * 构造方法--spring在初始化该类的时候会调用无参的构造方法，即会执行里面调用的其他方法
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public InitParamsLoad(){
        initRoleMap();
        logger.info("---initParams success");
    }
    
    /**
     * 角色定义参数，这个是需要动态调用的
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static void initRoleMap(){
        role_map.put("1", "维护员");
        role_map.put("2", "管机员");
        role_map.put("3", "部门领导");
        role_map.put("4", "主管");
        role_map.put("5", "部门经理");
        role_map.put("6", "银行主管");
        role_map.put("7", "超级管理员");
    }
    
}
