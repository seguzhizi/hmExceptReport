/*
 * 文件名：PeopleAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理用户个人信息相关的Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.service.IPeopleService;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.InitParamsLoad;
import com.hm.excp.util.MD5Util;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理用户信息相关的Action
 * @author zjj 2016-2-1
 * @see
 * @since 1.0
 */
public class PeopleAction extends ActionSupport
{

    /**日志处理对象*/
    HMLogger logger = HMLogger.getHMLogger(this.getClass());
    
    /**与用户个人信息处理相关的Service*/
    private IPeopleService peopleService;
    
    /**当前登陆用户*/
    private People xpeople;
    
    /**修改用户信息时，需要被修改的用户记录的主键*/
    private String sid = "";
    
    /**新增用户时的用户实体（bean）*/
    private People updPeople;
    
    /**当跳转不同的页面时，该页面在main.jsp中所占据的位置(在class属性中id),用于将左侧对应的菜单底色置蓝*/
    private String menuId = "";
    
    /**修改密码时，页面输入的原密码*/
    private String oldPwd;
    
    /**修改密码时，页面输入的新密码*/
    private String newPwd;
    
    /**角色和角色描述对应关系 */
    public Map<String,String> role_map = InitParamsLoad.role_map;
   
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
     * 查看个人资料
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String listPeopleInfo(){
        if(!isLogin()){
            return "login";
        }
        menuId = "0";
        logger.info(xpeople.getUsername() + "查看个人资料:");
        return "people";
    }
    
    /**
     * 修改密码-页面初始化
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String initUpdatePwd(){
        if(!isLogin()){
            return "login";
        }
        menuId = "1";
        logger.info(xpeople.getUsername() + "进入修改密码界面:");
        return "pwd";
    }
    
    /**
     * 检测原密码是否正确，并且修改密码
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public void checkPwdCorrect() throws NoSuchAlgorithmException, IOException{
        logger.info("检测原密码是否输入正确");
        String encryptPwd = MD5Util.MD5Encode(oldPwd);
        People p = (People) WebUtil.getHttpSession().getAttribute(XPEOPLE_INFO.XPEOPLE);
        if(p.getPassword().equals(encryptPwd)){
            try{
                p.setPassword(MD5Util.MD5Encode(newPwd));
                peopleService.updatePeople(p);
                //修改成功的话，替换session中原来存储的登陆用户
                WebUtil.getHttpSession().setAttribute(XPEOPLE_INFO.XPEOPLE,p);
                WebUtil.outputWrite("ok");
                logger.info(true,"用户[" + p.getUsername() + "]密码修改成功");
            } catch(Exception e){
                logger.error("用户[" + p.getUsername() + "]密码修改出现异常：" + e.getMessage());
                WebUtil.outputWrite("excp");
            }
        } else {
            logger.warn("用户[" + p.getUsername() + "]密码修改失败：旧密码输入错误");
            WebUtil.outputWrite("differ");
        }
    }
 
    /**
     * 修改密码
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    @Deprecated
    public String updatePwd(){
        if(!isLogin()){
            return "login";
        }
        logger.info("密码修改成功!");
        return "pwd";
    }
    
    /**
     * 修改个人资料--页面初始化
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String initUpdPersonProfile(){
        if(!isLogin()){
            return "login";
        }
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(WebUtil.getServletContext());
//        logger.info(ctx.getBean("PeopleAction"));
        if(sid != null && !sid.equals("")){
            //管理员修改指定用户的信息,sid该用户记录的主键
            updPeople = peopleService.getPeopleBySid(sid);
        } else {
            //登陆用户修改自己的信息
            updPeople = xpeople;
        }
        sid = "";
        menuId = "2";
        logger.info("用户[" + xpeople.getUsername() + "]开始修改用户[" + updPeople.getUsername()  + "]个人信息");
        return "profile";
    }
    
    /**
     * 修改个人资料
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String updPersonProfile(){
        
        peopleService.updatePeople(updPeople);
        if(updPeople.getSid().equals(xpeople.getSid())){
            WebUtil.getHttpSession().setAttribute(XPEOPLE_INFO.XPEOPLE,updPeople);
        }
        logger.info(true,"修改[" + updPeople.getUsername() + "]个人信息完毕");
        
        xpeople = updPeople;
        sid = "";
        updPeople = new People();
        menuId = "0";
        return "people";
    }
    

    /****************************setter/getter**************************/
    
    public IPeopleService getPeopleService()
    {
        return peopleService;
    }

    public void setPeopleService(IPeopleService peopleService)
    {
        this.peopleService = peopleService;
    }

    public People getXpeople()
    {
        return xpeople;
    }

    public void setXpeople(People xpeople)
    {
        this.xpeople = xpeople;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public People getUpdPeople()
    {
        return updPeople;
    }

    public void setUpdPeople(People updPeople)
    {
        this.updPeople = updPeople;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public String getOldPwd()
    {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd()
    {
        return newPwd;
    }

    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
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
