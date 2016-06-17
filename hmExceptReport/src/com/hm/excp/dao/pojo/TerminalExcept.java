package com.hm.excp.dao.pojo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class TerminalExcept implements Serializable{
	/*
	 create table TerminalExcept 
	 (exceptId varchar(255) not null, 
		terminalId varchar(32), 
		type varchar(4), 
		reportDate date, 
		reportTime time, 
		status char(1), 
		mkzt varchar(80), 
		exceptState varchar(8), 
		dealTime datetime, 
		primary key (exceptId)) 
	 */
	
	private String exceptId;		//故障编号
	private String terminalId;		//终端编号
	private String type;			//设备类型	ACI,ATM,VTM
	private String tradeType;		//交易类型	1000,1001
	private Date handleDate;		//上报日期	终端上送报文的日期
	private Time handleTime; 		//上报时间	终端上送报文的时间
	private char runStatus;			//设备运行状态	F:Fault故障（可用）E:故障（无法使用）
	private String mkzt;			//模块状态	各模块的状态PTR0|IDR1...
	
	private Timestamp createTime;	//故障case(记录)生成时间(插入数据库的时间)
	private String dealPerson;		//故障处理人		处理故障的操作仍
	private String level;			//故障等级		超时未处理，故障自动升级
	
	private String processState; 	//故障处理状态	已分发、完成处理
	
	private String remark;			//故障处理完成补充说明
	private Timestamp dealTime;		//故障完成时间	
	
	private String mailPeople;		//邮件通知人
	private Timestamp mailTime;		//邮件通知时间
	
	public String getExceptId() {
		return exceptId;
	}
	public void setExceptId(String exceptId) {
		this.exceptId = exceptId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public Date getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	public Time getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Time handleTime) {
		this.handleTime = handleTime;
	}
	public char getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(char runStatus) {
		this.runStatus = runStatus;
	}
	public String getMkzt() {
		return mkzt;
	}
	public void setMkzt(String mkzt) {
		this.mkzt = mkzt;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getDealPerson() {
		return dealPerson;
	}
	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getDealTime() {
		return dealTime;
	}
	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}
    public String getMailPeople()
    {
        return mailPeople;
    }
    public void setMailPeople(String mailPeople)
    {
        this.mailPeople = mailPeople;
    }
    public Timestamp getMailTime()
    {
        return mailTime;
    }
    public void setMailTime(Timestamp mailTime)
    {
        this.mailTime = mailTime;
    }
	
}
