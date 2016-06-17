/*
 * 文件名：TerminalExceptExportBean.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：查询人员信息结果时，页面查询条件组合而成的一个DTO类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dto;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 由于导出时需要信息进行数据类型转换，所以使用这个bean来装载实际的故障信息，且全部使用String类型，简单处理
 * 此bean的字段应该与对应的jasper文件的Field一一对应
 * @author zjj 2016-2-20
 * @see
 * @since 1.0
 */
@SuppressWarnings("serial")
public class TerminalExceptExportBean implements Serializable{
    
	private String terminalId;		//终端编号
	private String type;			//设备类型	ACI,ATM,VTM
	private String tradeType;		//交易类型	1000,1001
	private String handleDate;		//上报日期	终端上送报文的日期
	private String handleTime; 		//上报时间	终端上送报文的时间
	private String runStatus;			//设备运行状态	F:Fault故障（可用）E:故障（无法使用）
	private String mkzt;			//模块状态	各模块的状态PTR0|IDR1...
	private String createTime;	//故障case(记录)生成时间(插入数据库的时间)
	private String dealPerson;		//故障处理人		处理故障的操作仍
	private String level;			//故障等级		超时未处理，故障自动升级
	private String processState; 	//故障处理状态	已分发、完成处理
	private String remark;			//故障处理完成补充说明
	private String dealTime;		//故障完成时间
	
	
    public String getTerminalId()
    {
        return terminalId;
    }
    public void setTerminalId(String terminalId)
    {
        this.terminalId = terminalId;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getTradeType()
    {
        return tradeType;
    }
    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }
    public String getHandleDate()
    {
        return handleDate;
    }
    public void setHandleDate(String handleDate)
    {
        this.handleDate = handleDate;
    }
    public String getHandleTime()
    {
        return handleTime;
    }
    public void setHandleTime(String handleTime)
    {
        this.handleTime = handleTime;
    }
    public String getRunStatus()
    {
        return runStatus;
    }
    public void setRunStatus(String runStatus)
    {
        this.runStatus = runStatus;
    }
    public String getMkzt()
    {
        return mkzt;
    }
    public void setMkzt(String mkzt)
    {
        this.mkzt = mkzt;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public String getDealPerson()
    {
        return dealPerson;
    }
    public void setDealPerson(String dealPerson)
    {
        this.dealPerson = dealPerson;
    }
    public String getLevel()
    {
        return level;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }
    public String getProcessState()
    {
        return processState;
    }
    public void setProcessState(String processState)
    {
        this.processState = processState;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getDealTime()
    {
        return dealTime;
    }
    public void setDealTime(String dealTime)
    {
        this.dealTime = dealTime;
    }
}
