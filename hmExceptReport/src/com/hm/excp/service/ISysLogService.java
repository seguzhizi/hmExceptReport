/*
 * 文件名：ISysLogService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：系统日志查询的相关类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.util.List;
import java.util.Map;

import com.hm.excp.dao.pojo.SysLog;
import com.hm.excp.dto.PageParams;
/**
 * 系统日志查询的相关类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface ISysLogService
{
   
    /**
     * 存储一条日志
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public void saveLog(SysLog log);
    /**
     * 按用户名分页查询日志记录
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */ 
    public Map<List<SysLog>,PageParams> getExceptListByPage(String username, PageParams pageParams);
    

}
