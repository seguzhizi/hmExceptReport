/*
 * 文件名：SysLogAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理系统查询操作日志的Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.SysLog;
import com.hm.excp.dto.PageParams;
import com.hm.excp.service.ISysLogService;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理系统操作日志的类，主要是提供分页查询
 * @return
 * @author zjj 2016-2-16
 * @since 1.0
 */
public class SysLogAction extends ActionSupport
{
    /**日志处理对象*/
    HMLogger logger = HMLogger.getHMLogger(this.getClass());
    
    /**对应的日志处理Service*/
    private ISysLogService sysLogService;
    
    /**查询分页空间*/
    private PageParams pageParams;
    
    /**在页面显示的日志列表*/
    private List<SysLog> logList;
    
    /**页面左侧显示的菜单在整个菜单栏中对应的下标值,用以回显该页面底色*/
    private String menuId;
    
    /**系统登陆用户*/
    private People xpeople;
    
    /**页面按照用户名查询的查询条件*/
    private String username;
    
    /**
     * 初始化方法，点击左侧菜单时触发，进入日志查询功能的入口
     * <p>执行必要属性的初始化
     * @return
     * @author zjj 2016-2-16
     * @since 1.0
     */
    public String init(){
        if(!isLogin()){
            return "login";
        }
        xpeople = WebUtil.getLoginPeople();
        logger.info("用户[" + xpeople.getUsername() + "]登陆[操作日志查询]界面，页面初始化");
        logList = new ArrayList<SysLog>();
        pageParams = new PageParams();
        menuId = "4";
        return "init";
    }
    
    /**
     * 检测用户是否已经登陆
     * @return boolean 
     * <p>true:已登陆;false:未登陆
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public boolean isLogin(){
        boolean flag = false;
        xpeople = (People) WebUtil.getHttpSession().getAttribute(XPEOPLE_INFO.XPEOPLE);
        if(xpeople != null){
            flag = true;
        }
        return flag;
    }   
    
    /**
     * 按照用户名查询该用户的操作日志，目前：用户被删除之后仍然可以查询
     * @return
     * @author zjj 2016-2-20
     * @since 1.0
     */
    public String listAllLogByPage(){
        
        if(!isLogin()){
            return "login";
        }
        
        logger.info("开始按条件查询日志列表：");
        
        Map<List<SysLog>,PageParams> map = new HashMap<List<SysLog>,PageParams>();  
        map = (HashMap<List<SysLog>, PageParams>) sysLogService.getExceptListByPage(username, pageParams);
        for(Entry<List<SysLog>, PageParams> entry : map.entrySet()){
            logList = (List<SysLog>) entry.getKey();
        }
        
        return "init";
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public People getXpeople()
    {
        return xpeople;
    }

    public void setXpeople(People xpeople)
    {
        this.xpeople = xpeople;
    }

    public List<SysLog> getLogList()
    {
        return logList;
    }

    public void setLogList(List<SysLog> logList)
    {
        this.logList = logList;
    }

    public ISysLogService getSysLogService()
    {
        return sysLogService;
    }

    public void setSysLogService(ISysLogService sysLogService)
    {
        this.sysLogService = sysLogService;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public PageParams getPageParams()
    {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams)
    {
        this.pageParams = pageParams;
    }

}
