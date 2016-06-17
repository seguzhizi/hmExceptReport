/*
 * 文件名：ExceptAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：故障处理Action类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.TerminalExcept;
import com.hm.excp.dto.Mail;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryExceptBean;
import com.hm.excp.service.IExceptService;
import com.hm.excp.service.MailService;
import com.hm.excp.util.ExceptConstant.EXCEPT_LEVEL;
import com.hm.excp.util.ExceptConstant.EXCEPT_STATE;
import com.hm.excp.util.ExceptConstant.ROLE_ID;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.MailUtil;
import com.hm.excp.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 页面轮询发送请求获取最新的故障信息，填写故障处理信息的Action
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class ExceptAction extends ActionSupport
{
    /**日志处理对象*/
    HMLogger logger = HMLogger.getHMLogger(this.getClass());
    
    /**故障处理对应的Service*/
    private IExceptService exceptService;
    
    /**邮件发送service*/
    private MailService mailService;
    
    /**查询时的分页控件bean*/
    private PageParams pageParams;
    
    /**填写故障处理信息时对应的故障实体类*/
    private TerminalExcept except;
    
    /**页面轮询是显示的故障信息列表*/
    private List<TerminalExcept> exceptList;
    
    /**填写故障处理信息时对应的故障实体的主键*/
    private String exceptId;
    
    /**页面左侧显示的菜单在整个菜单栏中对应的下标值,用以回显该页面底色*/
    private String menuId;
    
    /**系统登陆用户*/
    private People xpeople;
    
    /**
     * 初始化方法，点击左侧菜单时触发，进入该功能的入口
     * <p>执行必要属性的初始化
     * @return
     * @author zjj 2016-2-8
     * @since 1.0
     */
    public String init(){
        if(!isLogin()){
            return "login";
        }
        logger.info("用户[" + xpeople.getUsername() + "]登陆故障处理界面");
        exceptList = new ArrayList<TerminalExcept>();
        except = new TerminalExcept();
        pageParams = new PageParams();
        menuId = "5";
        exceptId = "";
        
        //初始化完毕之后即查询出结果
        return listAllExcept();
    }
    
    /**
     * 检测用户是否已经登陆
     * @return boolean 
     * <p>true:已登陆;false:未登陆
     * @author zjj 2016-2-1
     * @since 1.0
     */
    public boolean isLogin()
    {
        boolean flag = false;
        xpeople = (People) WebUtil.getLoginPeople();
        if(xpeople != null){
            flag = true;
        }
        return flag;
    }   
    
    /**
     * 页面轮询时查询所有符合要求的故障信息列表
     * @return
     * @author zjj 2016-2-8
     * @since 1.0
     */
    public String listAllExcept(){
        if(!isLogin()){
            return "login";
        }
        logger.info("查询故障信息，更新页面故障记录列表：");
        QueryExceptBean queryExceptBean = new QueryExceptBean();
        //只能查询自己名下且是[已分发]状态的故障信息
        queryExceptBean.setProcessState(EXCEPT_STATE.DISPATCH);
        
        //只有[管机员]或者[维护员]才能获取到故障信息
        if(xpeople.getRoleId().equals(ROLE_ID.Role_1) || xpeople.getRoleId().equals(ROLE_ID.Role_2)){
            //queryExceptBean.setDealPerson(xpeople.getUsername());
            Map<List<TerminalExcept>,PageParams> map = new HashMap<List<TerminalExcept>,PageParams>();
            map = (HashMap<List<TerminalExcept>, PageParams>) exceptService.getDealExceptListByPage(queryExceptBean,pageParams);
            
            for(Entry<List<TerminalExcept>, PageParams> entry : map.entrySet()){
                exceptList = (List<TerminalExcept>) entry.getKey();
                pageParams = (PageParams) entry.getValue();
            }
        }
        
        return "init";
    }
    
    /**
     * <p>当故障被处理之后，需要填写故障处理信息，此方法即为填写故障信息的页面初始化方法
     * @return
     * @author zjj 2016-2-14
     * @since 1.0
     */
    public String initUpdExcept(){
        if(!isLogin()){
            return "login";
        }
        logger.info("用户[" + xpeople.getUsername() + "]开始处理故障记录");
        except = exceptService.getExceptById(exceptId);
        
        return "deal";
    }
    
    /**
     * <p>当完成故障信息的填写之后，提交故障处理信息
     * @return
     * @author zjj 2016-2-14
     * @since 1.0
     */
    public String updExcept(){
        if(!isLogin()){
            return "login";
        }
        try
        {
            String dp = exceptService.getDealPeople(except.getDealPerson());
            except.setDealPerson(dp);
            except.setMailTime(new Timestamp(System.currentTimeMillis()));
            except = exceptService.updateExcept(except);
            logger.info(true,"用户[" + xpeople.getUsername() + "]处理故障记录[终端号为："+except.getTerminalId()+"]完毕");
            
            //如果是第2级或者第3级的故障，那么处理完毕之后需要通知其主管
            if(except.getLevel().equals(EXCEPT_LEVEL.LEVEL_2) || except.getLevel().equals(EXCEPT_LEVEL.LEVEL_3)){
                //获得被通知人的列表
                String mailPeopleString = except.getMailPeople();
                List<People> mailPeopleList = exceptService.getMailList(mailPeopleString);
                
                if(mailPeopleList != null && mailPeopleList.size() > 0){
                    for(int i = 0 ; i < mailPeopleList.size() ; i++){
                        //生成故障处理完毕的邮件内容
                        String content = mailService.generateDealMsg(except, mailPeopleList.get(i).getUsername());
                        //生成故障处理完毕的邮件
                        Mail mail = mailService.generateMail(mailPeopleList.get(i).getEmail(), content, null);
                        MailUtil.send(mail);
                        logger.info("发送邮件至[" + mail.getReceiver() + "]成功");
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("用户[" + xpeople.getUsername() + "]处理故障记录出现异常:" + e.getMessage());
            e.printStackTrace();
        }
        
        return listAllExcept();
    }
    
    
    public IExceptService getExceptService()
    {
        return exceptService;
    }

    public void setExceptService(IExceptService exceptService)
    {
        this.exceptService = exceptService;
    }

    public PageParams getPageParams()
    {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams)
    {
        this.pageParams = pageParams;
    }

    public TerminalExcept getExcept()
    {
        return except;
    }

    public void setExcept(TerminalExcept except)
    {
        this.except = except;
    }

    public List<TerminalExcept> getExceptList()
    {
        return exceptList;
    }

    public void setExceptList(List<TerminalExcept> exceptList)
    {
        this.exceptList = exceptList;
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

    public String getExceptId()
    {
        return exceptId;
    }

    public void setExceptId(String exceptId)
    {
        this.exceptId = exceptId;
    }

    public MailService getMailService()
    {
        return mailService;
    }

    public void setMailService(MailService mailService)
    {
        this.mailService = mailService;
    }

}
