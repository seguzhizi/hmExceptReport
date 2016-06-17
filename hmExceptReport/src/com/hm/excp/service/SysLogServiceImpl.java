/*
 * 文件名：SysLogServiceImpl.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：系统日志查询的相关类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hm.excp.dao.BaseDao;
import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.SysLog;
import com.hm.excp.dto.PageParams;
import com.hm.excp.util.PageUtils;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * 系统日志查询的相关类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class SysLogServiceImpl implements ISysLogService
{
    Logger logger = Logger.getLogger(this.getClass());
    private BaseDao dao;

    /**
     * @see com.hm.excp.service.ISysLogService#getExceptListByPage(java.lang.String, com.hm.excp.dto.PageParams)
     */
    @Override
    public Map<List<SysLog>, PageParams> getExceptListByPage(String username, PageParams pageParams)
    {
        String hql = "from SysLog s where 1=1 and s.logType = '1' ";
        String conutHql = "select count(*) from SysLog s where 1=1 and s.logType = '1' ";
        
        if(username != null && !username.equals("") && !username.equals("null")){
            hql += "and s.username = '" + username + "'";
            conutHql += "and s.username = '" + username + "'";
        }
        
        hql += " order by s.logTime desc";
        
        List<Object> countList = dao.findByHql(conutHql);
        int totalRecords = 0;
        if(countList != null && countList.size() > 0){
            totalRecords = Integer.parseInt(countList.get(0).toString());
        }
        //计算分页要素
        pageParams = PageUtils.queryByPage(totalRecords, pageParams);
        
        //根据分页元素查询结果
        logger.info("查询用户hql:[" + hql.toString() + "]");
        List<SysLog> list = dao.findByPage(pageParams.getFirstResult(), pageParams.getPageSize(), hql);
        
        Map<List<SysLog>,PageParams> map = new HashMap<List<SysLog>,PageParams>();
        
        map.put(list, pageParams);
        
        return map;
    }
    
    /**
     * @see com.hm.excp.service.ISysLogService#saveLog(com.hm.excp.dao.pojo.SysLog)
     */
    @Override
    public void saveLog(SysLog log){
        dao.save(log);
    }
    

    public BaseDao getDao()
    {
        return dao;
    }

    public void setDao(BaseDao dao)
    {
        this.dao = dao;
    }

}
