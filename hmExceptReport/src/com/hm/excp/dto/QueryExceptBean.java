/*
 * 文件名：QueryExceptBean.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：查询故障信息结果时，页面查询条件组合而成的一个DTO类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dto;

/**
 * 查询故障信息结果时，页面查询条件组合而成的一个DTO类
 * <p>其属性数量应不小于对应的页面查询属性的数量
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class QueryExceptBean
{

    private String terminalId;      //终端编号
//    private String type;           //设备类型  ACI,ATM,VTM
    private String dealPerson;      //故障处理人     处理故障的操作仍
//    private String level;          //故障等级      超时未处理，故障自动升级
    private String processState;    //故障处理状态    已分发、完成处理
    private String mailPerson;      //邮件通知人
    
    
    
    public String getMailPerson()
    {
        return mailPerson;
    }
    public void setMailPerson(String mailPerson)
    {
        this.mailPerson = mailPerson;
    }
    public String getTerminalId()
    {
        return terminalId;
    }
    public void setTerminalId(String terminalId)
    {
        this.terminalId = terminalId;
    }
    public String getDealPerson()
    {
        return dealPerson;
    }
    public void setDealPerson(String dealPerson)
    {
        this.dealPerson = dealPerson;
    }
    public String getProcessState()
    {
        return processState;
    }
    public void setProcessState(String processState)
    {
        this.processState = processState;
    }
    
}
