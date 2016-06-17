/*
 * 文件名：Mail.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：邮件处理的实体类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dto;

import java.io.Serializable;

import org.apache.commons.mail.EmailAttachment;

/**
 * 发送邮件的实体类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
@SuppressWarnings("serial")
public class Mail implements Serializable {
	public static final String ENCODEING = "UTF-8";
	private String host; // 服务器地址
	private String sender; // 发件人的邮箱
	private String receiver; // 收件人的邮箱
	private String name; // 发件人昵称
	private String username; // 账号
	private String password; // 密码
	private String subject; // 主题
	private String message; // 信息(支持HTML)
	private EmailAttachment[] attachments;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailAttachment[] getAttachments() {
		return attachments;
	}

	public void setAttachments(EmailAttachment[] attachments) {
		this.attachments = attachments;
	}

}
