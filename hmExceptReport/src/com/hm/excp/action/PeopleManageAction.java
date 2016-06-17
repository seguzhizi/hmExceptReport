/*
 * 文件名：PeopleManageAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理系统用户信息相关的Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-24
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryPeopleBean;
import com.hm.excp.service.IPeopleService;
import com.hm.excp.util.ExceptConstant;
import com.hm.excp.util.ExceptConstant.DEFAULT_PWD;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.InitParamsLoad;
import com.hm.excp.util.MD5Util;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理系统用户相关的类，包括增,删,改,查
 * @author zjj 2016-2-1
 * @see
 * @since 1.0
 */
public class PeopleManageAction extends ActionSupport
{

    private static final long serialVersionUID = 1L;
    
    /**日志处理对象*/
    HMLogger logger = HMLogger.getHMLogger(this.getClass());
    
    /**与Action对应的Service*/
    private IPeopleService peopleService;
    
    /**分页控件*/
    private PageParams pageParams;
    
    /**当前登陆用户*/
    private People xpeople;
    
    /**新增用户时的用户实体（bean）*/
    private People newPeople;
    
    /**新增用户时，关联的roleId*/
    private String roleId;
    
    /**修改用户信息时，需要被修改的用户记录的主键*/
    private String sid = "";
    
    /**修改用户时的用户实体（bean）*/
    private People updPeople;
    
    /**查询用户列表时的结果*/
    private List<People> xpeopleList = new ArrayList<People>();
    
    /**页面查询条件的组合bean*/
    private QueryPeopleBean queryPeopleBean = new QueryPeopleBean();
    
    /**当跳转不同的页面时，该页面在main.jsp中所占据的位置(在class属性中id),用于将左侧对应的菜单底色置蓝*/
    private String menuId = "";
    
    /**新增用户时，页面输入的用户名，用于检测是否被占用*/
    private String username;
    
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
     * 用户添加页面初始化
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String initAddPeople(){
        if(!isLogin()){
            return "login";
        }
        
        newPeople = new People();
        String result = "addPeople";
        roleId = "";
        menuId = "3";
        logger.info("用户[" + xpeople.getUsername() + "]登陆用户管理-用户添加界面：");
        return result;
    }
    /**
     * 检测用户名是否被占用
     * @author zjj 2016-2-2
     * @throws IOException 
     * @since 1.0
     */
    public void checkUsernameRegistered() throws IOException{
        boolean flag = peopleService.checkIsRegistered(username);
        if(flag){
            WebUtil.outputWrite("no");
        }else {
            WebUtil.outputWrite("yes");
        }
    }
    
    /**
     * 用户添加提交
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String addPeople(){
        String result = "addok";
        logger.info("开始添加用户[" + newPeople.getUsername() + "]:");
        try{
            peopleService.addPeople(newPeople);
            logger.info(true,"添加用户[" + newPeople.getUsername() + "]成功");
            xpeople = newPeople;
            newPeople = null;
        } catch (Exception e){
            //to do
            newPeople = null;
            logger.error("添加用户出现异常" + e.getMessage());
        }
        menuId = "0";
        return result;
    }
    
    
    /**
     * 查看所有用户页面初始化
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String initListAllPeople(){
        logger.info("用户[" + xpeople.getUsername() + "]用户查看界面，页面初始化:");
        xpeopleList.clear();
        pageParams = new PageParams();
        queryPeopleBean = new QueryPeopleBean();
        menuId = "3";
        return "list";
    }
    /**
     * 查看所有用户
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String listAllPeople(){
        if(!isLogin()){
            return "login";
        }
        
        logger.info("开始按条件查询所有用户：");
        
        Map<List<People>,PageParams> map = new HashMap<List<People>,PageParams>();  
        map = (HashMap<List<People>, PageParams>) peopleService.queryPeopleByPage(queryPeopleBean,pageParams);
        
        for(Entry<List<People>, PageParams> entry : map.entrySet()){
            xpeopleList = (List<People>) entry.getKey();
            pageParams = (PageParams) entry.getValue();
        }
        menuId = "3";
        return "list";
    }

    /**
     * 
     * <p>目前的删除采用的是逻辑删除
     * @return
     * @author zjj 2016-2-16
     * @since 1.0
     */
    public String deletePersonProfile(){
        if(!isLogin()){
            return "login";
        }
        try{
            //admin用户不允许删除
            if(sid != null && !sid.equals("") && !sid.equalsIgnoreCase("admin")){
                //下面的这句是物理删除
                //peopleService.deletePeopleBySid(sid)
                People temp = null;
                for(People p : xpeopleList){
                    if(p.getSid().equals(sid)){
                        temp = p;
                        break;
                    }
                }
                if(temp != null){
                    temp.setStatus(ExceptConstant.XPEOPLE_STATUS.LOCK);
                    peopleService.updatePeople(temp);
                } else {
                    throw new RuntimeException("删除用户["+sid+"]出现异常：该用户不存在!");
                }
            }
            logger.info(true,xpeople.getUsername() + "删除[" + sid  + "]个人信息成功");
        } catch (Exception e){
            logger.error(xpeople.getUsername() + "删除[" + sid  + "]个人信息出现异常：" + e.getMessage());
            e.printStackTrace();
        }
        sid = "";
        return listAllPeople();
    }
    
    /**
     * 重置密码
     * @return
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public String recoverPwd() throws IOException{
        if(!isLogin()){
            return "login";
        }
        
        People p = null;
        try{
            p = peopleService.getPeopleBySid(sid);
            String pwd = MD5Util.MD5Encode(DEFAULT_PWD.DEFAULT_PWD);
            p.setPassword(pwd);
            peopleService.updatePeople(p);
            logger.info(true,xpeople.getUsername() + "重置[" + p.getUsername()  + "]密码成功");
            WebUtil.outputWrite("ok");
        } catch (Exception e){
            logger.error(xpeople.getUsername() + "重置[" + p.getUsername()  + "]密码成功出现异常：" + e.getMessage());
            e.printStackTrace();
            WebUtil.outputWrite("no");
        }
        
        sid = "";
        return null;
    }
    

    public IPeopleService getPeopleService()
    {
        return peopleService;
    }

    public void setPeopleService(IPeopleService peopleService)
    {
        this.peopleService = peopleService;
    }

    public PageParams getPageParams()
    {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams)
    {
        this.pageParams = pageParams;
    }

    public People getXpeople()
    {
        return xpeople;
    }

    public void setXpeople(People xpeople)
    {
        this.xpeople = xpeople;
    }

    public People getNewPeople()
    {
        return newPeople;
    }

    public void setNewPeople(People newPeople)
    {
        this.newPeople = newPeople;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
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

    public List<People> getXpeopleList()
    {
        return xpeopleList;
    }

    public void setXpeopleList(List<People> xpeopleList)
    {
        this.xpeopleList = xpeopleList;
    }

    public QueryPeopleBean getQueryPeopleBean()
    {
        return queryPeopleBean;
    }

    public void setQueryPeopleBean(QueryPeopleBean queryPeopleBean)
    {
        this.queryPeopleBean = queryPeopleBean;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
