/*
 * 文件名：QueryPeopleBean.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：查询人员信息结果时，页面查询条件组合而成的一个DTO类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dto;
/**
 * 查询人员信息结果时，页面查询条件组合而成的一个DTO类
 * <p>其属性数量应不小于对应的页面查询属性的数量
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class QueryPeopleBean
{

    private String username;
    private String realName;
    private String roleId;
    private String status;
    private String startTime;
    private String endTime;
    
    
    public String getRoleId()
    {
        return roleId;
    }
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }
    public String getRealName()
    {
        return realName;
    }
    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStartTime()
    {
        return startTime;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    public String getEndTime()
    {
        return endTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    
}
