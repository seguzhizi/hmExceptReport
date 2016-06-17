/*
 * 文件名：LogoutAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理登出的Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 页面登出的Action
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public class LogoutAction extends ActionSupport
{

    /**日志处理对象*/
    HMLogger logger = HMLogger.getHMLogger(this.getClass());
    
    /**系统登陆用户*/
    private People xpeople;
    
    /**
     * 用户登出，主要是删除session
     * @return
     * @author zjj 2016-2-4
     * @since 1.0
     */
    public String logout(){
        xpeople = (People) WebUtil.getHttpSession().getAttribute(XPEOPLE_INFO.XPEOPLE);
        logger.info("用户[" + xpeople.getUsername() + "]退出系统!");
//        logger.info(true,"用户[" + xpeople.getUsername() + "]退出系统!");
        if(xpeople != null){
            WebUtil.getHttpSession().invalidate();
//            WebUtil.getHttpSession().removeAttribute(XPEOPLE_INFO.XPEOPLE);
        }
        return "login";
    }

}
