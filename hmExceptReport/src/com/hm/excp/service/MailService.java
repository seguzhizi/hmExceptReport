/*
 * 文件名：MailService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和邮件处理相关的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import sun.swing.StringUIClientPropertyKey;

import com.hm.excp.dao.BaseDao;
import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.TerminalExcept;
import com.hm.excp.dto.Mail;
import com.hm.excp.util.ExceptConstant.ROLE_ID;
import com.hm.excp.util.ExceptConstant.XPEOPLE_STATUS;
import com.hm.excp.util.StringUtil;

/**
 * 和邮件处理相关的Service
 * @author zjj 2016-2-15
 * @since 1.0
 */
public class MailService {
    
    /**日志处理对象*/
    Logger logger = Logger.getLogger(getClass());

    /**基础的数据库处理dao*/
    private BaseDao dao;
//
//	/**
//	 * @param
//	 *         except 具体的故障信息
//	 *         peopleList 发送邮件的通知人集合，如果为空，则默认发给admin
//	 *         <p>time:8,超时8小时,将发送邮件至部门领导和主管
//	 *         <p>time:24,超时24小时,将发送邮件至部门经理和银行主管
//	 * @return
//	 * @throws EmailException
//	 */
//	public List<Mail> generateMail(TerminalExcept except,int time,List<People> peopleList) throws EmailException{
//	    
//	    if(!(peopleList != null && peopleList.size() > 0)){
//	        //如果peopleList为空，则默认装载admin
//	        peopleList = getCheif(0);
//	    }
//	    
//	    //待返回的邮件
//	    List<Mail> mailList = new ArrayList<Mail>();
//        for(People p : peopleList){
//            String content = generateContent(except,p,time);
//            
//    		Mail mail = new Mail();
//    		/*
//    		 * 设置接收人,输入收件人的正确的邮箱 地址 注意收件箱要开启stmp或者 pop3的协议,具体操作就是登陆收件箱进行设置
//    		 * (ps:如不开启就可以被随意的发送邮箱，那邮箱安全性太低了)
//    		 */
//    //		mail.setReceiver("zhuangyan@ceiec-electric.com");
//    		mail.setReceiver("seguzhizi@163.com");
//    		/* 这个邮件的服务器也是可以登陆发件箱，在设置里面查看的 */
//    		mail.setHost("smtp.sina.com"); // 设置邮件服务器,如果不用sina的,自己找找看相关的
//    		/* 设置发件箱,即发件箱的地址 */
//    		mail.setSender("seguzhizi@sina.com");
//    		/* 设置登陆收件箱的用户名 */
//    		mail.setUsername("seguzhizi@sina.com"); // 登录账号,一般都是和邮箱名一样吧
//    		/* 设置登陆收件箱的密码 */
//    		mail.setPassword("zhuangyan520"); // 发件人邮箱的登录密码
//    
//    		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//    		mail.setSubject("故障邮件"+date);
//    		
//    		mail.setMessage(content);
////    		mail.setMessage("故障测试,您好，终端机[" + except.getTerminalId() + "]出现故障异常，且已超过" + time + "小时未被处理，"
////    		                    + "请知晓并处理，谢谢。<b>和美信息<b>");
//    		EmailAttachment[] emailAttachments = new EmailAttachment[2];
//    		EmailAttachment emailAttachment = new EmailAttachment();
//    		//emailAttachment.setPath("d:/zy.jpg");
//    		emailAttachments[0] = emailAttachment;
//    		mail.setAttachments(emailAttachments);
//    		
//    		mailList.add(mail);
//        }
//
//		//new MailUtil().sendmailWithAttachment(mail);
//		return mailList;
//	}

	/**
	 * 生成一封邮件
	 * @author zjj
	 * @param
	 *         email 邮件接收地址
	 *         <p>邮件接收人如果为空，则默认发给admin
	 * @param
	 *         content 邮件内容
	 * @param
	 *         emailAttachments 数组形式的EmailAttachment的附件
	 * @return
	 * @throws EmailException
	 */
	public Mail generateMail(String email,String content,EmailAttachment[] emailAttachments) throws EmailException{
	    
	    if(StringUtil.isEmpty(email)){
	        //如果peopleList为空，则默认装载admin
	        email = "seguzhizi@163.com";
	    }
	    
		Mail mail = new Mail();
		/*
		 * 设置接收人,输入收件人的正确的邮箱 地址 注意收件箱要开启stmp或者 pop3的协议,具体操作就是登陆收件箱进行设置
		 * (ps:如不开启就可以被随意的发送邮箱，那邮箱安全性太低了)
		 */
//		mail.setReceiver("zhuangyan@ceiec-electric.com");
		mail.setReceiver(email);
		
		/* 这个邮件的服务器也是可以登陆发件箱，在设置里面查看的 */
		mail.setHost("smtp.sina.com"); // 设置邮件服务器,如果不用sina的,自己找找看相关的
		/* 设置发件箱,即发件箱的地址 */
		mail.setSender("seguzhizi@sina.com");
		/* 设置登陆收件箱的用户名 */
		mail.setUsername("seguzhizi@sina.com"); // 登录账号,一般都是和邮箱名一样吧
		/* 设置登陆收件箱的密码 */
		mail.setPassword("zhuangyan520"); // 发件人邮箱的登录密码

		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		mail.setSubject("故障邮件"+date);
		
		mail.setMessage(content);
//    		mail.setMessage("故障测试,您好，终端机[" + except.getTerminalId() + "]出现故障异常，且已超过" + time + "小时未被处理，"
//    		                    + "请知晓并处理，谢谢。<b>和美信息<b>");
		//设置附件
		mail.setAttachments(emailAttachments);
//		EmailAttachment[] emailAttachments = new EmailAttachment[2];
//		EmailAttachment emailAttachment = new EmailAttachment();
		//emailAttachment.setPath("d:/zy.jpg");
//		emailAttachments[0] = emailAttachment;
//		mail.setAttachments(emailAttachments);
		
		return mail;
	}
	
	/**
     * <p>故障处理完毕，生成处理完毕模板
     * @author zjj 2016-2-15
     * @since 1.0
     * @see
     * @param
     *      except  故障信息，用于生成邮件内容
     * @param
     *      username 用于生成邮件内容
     */
	public String generateDealMsg(TerminalExcept except,String username){
	    
	    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String content = username + ":"
                + "<br/>&nbsp;&nbsp;您好，当前有一个终端机故障被成功解决，请知晓!"
                + "<br/>相关故障信息如下：" 
                + "<br/>终端编号：" + except.getTerminalId()
                + "<br/>故障编号：" + except.getExceptId()
                + "<br/>故障等级：" + except.getLevel()
                + "<br/>处理员：" + except.getDealPerson()
                + "<br/>故障发生时间：" + format.format(except.getHandleDate())
//              + "<br/>故障发生时间：" + except.getHandleDate() + except.getHandleTime()
                + "<br/><br/>该邮件是系统自动发送的，请勿回复。"
                + "<br/><br/><b>和美信息故障处理系统<b>";
	    return content;
	}
	
	/**
	 * <p>根据故障信息和超时时间，生成邮件内容模板
	 * @author zjj 2016-2-15
	 * @since 1.0
	 * @see
	 */
	public String generateContent(TerminalExcept except,People people,int time) {
        String level = "2级";
	    if(time == 24){
	        level = "3级";
	    }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String content = people.getUsername() + ":"
                + "<br/>&nbsp;&nbsp;您好，当前有终端机发生了故障，并已超过" + time + "小时未处理，请知晓并协助解决，谢谢!"
                + "<br/>相关故障信息如下：" 
                + "<br/>终端编号：" + except.getTerminalId()
                + "<br/>故障编号：" + except.getExceptId()
                + "<br/>故障等级：" + level
                + "<br/>维护员：" + except.getDealPerson()
                + "<br/>故障发生时间：" + format.format(except.getHandleDate())
//              + "<br/>故障发生时间：" + except.getHandleDate() + except.getHandleTime()
                + "<br/><br/>该邮件是系统自动发送的，请勿回复。"
                + "<br/><br/><b>和美信息故障处理系统<b>";
        return content;
    }

	/**
     * <p>根据超时的时间不同，查询出不同的领导
     * @author zjj 2016-2-15
     * @since 1.0
     * @see
     * time 超时时间
     *  <p>0    :   查询admin
     *  <p>8    :   查询主管和部门领导
     *  <p>24   :   查询部门经理和银行主管
     */
	private List<People> getCheif(int time){
	    String hql = "";
	    
	    if(time == 0){
            hql = "from People p where 1=1 and  p.username = 'admin'";
        }
	    
	    //查询[部门领导]和[主管]
        if(time == 8){
            hql = "from People p where 1=1 and p.roleId in ('"+ROLE_ID.Role_3+"','"+ROLE_ID.Role_4+"') " +
                    "and p.status = '" + XPEOPLE_STATUS.NORMAL + "'";
        }
        //查询[部门经理]和[银行主管]
        if(time == 24){
            hql = "from People p where 1=1 and p.roleId in ('"+ROLE_ID.Role_5+"','"+ROLE_ID.Role_6+"') " +
                    "and p.status = '" + XPEOPLE_STATUS.NORMAL + "'";
        }
        List<People> peopleList = dao.findByHql(hql);
        return peopleList;
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
