/*
 * 文件名：MailUtil.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：邮件发送的工具类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.hm.excp.dto.Mail;

/**
 * 邮件发送工具实现类
 * @author shadow
 * @see
 * @since 1.0
 * @create 2013/07/12
 */
public class MailUtil {

	protected static final Logger logger = Logger.getLogger(MailUtil.class);
	/**
	 * 发送邮件--不含附件
	 * @param mail
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static boolean send(Mail mail) {
		// 发送email
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			email.setHostName(mail.getHost());
			// 字符编码集的设置
			email.setCharset(Mail.ENCODEING);
			// 收件人的邮箱
			email.addTo(mail.getReceiver());
			// 发送人的邮箱
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getSubject());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg(mail.getMessage());

            EmailAttachment[] attaches = mail.getAttachments();
            //暂时不添加附件
            if(attaches != null && attaches.length > 0){
                for(int i=0;i<attaches.length;i++){
                    if(attaches[i] != null){
                        email.attach(attaches[i]);
                    }
                }
            }
            
			// 发送
			email.send();
			if (logger.isDebugEnabled()) {
				logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
			}
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
					+ " 失败");
			return false;
		}
	}
	
	/**
	 * 发送邮件--带有附件
	 * @param mail
	 * @return
	 * @see
	 * @since 1.0 
	 * @throws EmailException
	 */
//	public static boolean sendmailWithAttachment(Mail mail) throws EmailException{
//
//		// 发送email
//		HtmlEmail email = new HtmlEmail();
//		try {
//			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
//			email.setHostName(mail.getHost());
//			// 字符编码集的设置
//			email.setCharset(Mail.ENCODEING);
//			// 收件人的邮箱
//			email.addTo(mail.getReceiver());
//			// 发送人的邮箱
//			email.setFrom(mail.getSender(), mail.getName());
//			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
//			email.setAuthentication(mail.getUsername(), mail.getPassword());
//			// 要发送的邮件主题
//			email.setSubject(mail.getSubject());
//			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
//			email.setMsg(mail.getMessage());
//			
//			EmailAttachment[] attaches = mail.getAttachments();
//			//暂时不添加附件
////			if(attaches != null && attaches.length > 0){
////    			//处理附件
////    			for(int i=0;i<attaches.length;i++){
////    				if(attaches[i] != null){
////    					email.attach(attaches[i]);
////    				}
////    			}
////			}
//			// 发送
//			email.send();
//			
//			if (logger.isDebugEnabled()) {
//				logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
//			}
//			return true;
//			
//		} catch (EmailException e) {
//			e.printStackTrace();
//			logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver() + " 失败");
//			return false;
//		}
//		
//    }
	

}
