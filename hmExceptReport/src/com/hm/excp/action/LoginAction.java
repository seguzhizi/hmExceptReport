/*
 * 文件名：LoginAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理登陆的Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.service.ILoginService;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.InitParamsLoad;
import com.hm.excp.util.MD5Util;
import com.hm.excp.util.NeedLog;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 页面登陆的Action
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public class LoginAction extends ActionSupport implements Preparable
{
    private static final long serialVersionUID = 1L;

//    Logger logger = Logger.getLogger(LoginAction.class);
    /**日志处理对象*/
    HMLogger logger = HMLogger.getHMLogger(getClass());
    
    /**角色和角色描述的对应关系 */
    public Map<String,String> role_map = InitParamsLoad.role_map;
    
    /**处理登陆的Service*/
    private ILoginService loginService;
    
    /**系统的登陆用户，登陆成功将会被set到Session的属性中*/
    private People xpeople;
    
    /**页面左侧显示的菜单在整个菜单栏中对应的下标值,用以回显该页面底色*/
    private String menuId = "0";
    
    @NeedLog(logMsg = "测试-----------------------------------")
    public void testLogin(){
        System.out.println("-------------------------------------------");
    }
    
    /**
     * 处理登陆系统的方法
     * @return
     * @author zjj 2016-2-8
     * @since 1.0
     */
    public String login(){
//        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(WebUtil.getServletContext());
//        logger.info("---------------------------" + context.getBean("LoginAction").toString());
        
        //如果已经登陆过了,再次访问时，直接返回主界面，不再验证
        People p = (People) WebUtil.getHttpSession().getAttribute(XPEOPLE_INFO.XPEOPLE);
        if(p != null){
            return "success";
        }
        
        logger.info("接收到:[" + xpeople.getUsername()+"]的登陆请求:");
        
        String pwd = "";
        try {
            pwd = MD5Util.MD5Encode(xpeople.getPassword());
            //logger.info("对输入密码加密后的结果为：" + pwd);
        }
        catch (NoSuchAlgorithmException e){
            logger.error("对输入密码加密出现异常" + e.getMessage());
            e.printStackTrace();
        }
        
        String return_code = null;
        
        //检测用户是否已注册,密码是否正确
        xpeople = loginService.queryPeopleByName(xpeople);
        
        if(xpeople == null){
            addActionError("该用户名不存在");
            logger.warn("该用户名" + xpeople.getUsername() + "不存在");
            return_code = "login";
        } else {
            if(xpeople.getPassword().equals(pwd)){
                WebUtil.getHttpSession().setAttribute(XPEOPLE_INFO.XPEOPLE, xpeople);
                logger.info("用户[" + xpeople.getUsername() + "]登陆成功");
//                logger.info(true, "用户[" + xpeople.getUsername() + "]登陆成功");
                return_code = "success";
            } else {
                addActionError("输入密码错误");
                logger.warn("该用户" + xpeople.getUsername() + "密码输入错误!");
                return_code = "login";
            }
        }
        
        return return_code;
    }
    
    /**
     * 使用了AddActionError后，当用户名或者密码错误的时候，错误信息就为非空了
     * <p>当再次提交的时候，由于这个非空信息将会导致页面直接返回input结果
     * <p>而input并未定义，则会导致系统找不见对应的action，页面出现404
     * @see com.opensymphony.xwork2.Preparable#prepare()
     */
    @Override
    public void prepare() throws Exception
    {
        clearErrorsAndMessages();
    }
    
    /****************************setter/getter**************************/
    
    public People getXpeople()
    {
        return xpeople;
    }

    public void setXpeople(People xpeople)
    {
        this.xpeople = xpeople;
    }
    public ILoginService getLoginService()
    {
        return loginService;
    }
    public void setLoginService(ILoginService loginService)
    {
        this.loginService = loginService;
    }

    public String getMenuId()
    {
        return menuId;
    }
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public Map<String, String> getRole_map()
    {
        return role_map;
    }

    public void setRole_map(Map<String, String> role_map)
    {
        this.role_map = role_map;
    }

}
