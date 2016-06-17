/*
 * 文件名：ScheduleJob.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：simple introduction
 * 修改人： Administrator
 * 修改时间：下午4:56:17 - 2016-2-29
 * 修改内容：新增
 */
package com.hm.excp.dto;

import java.util.Date;

import com.hm.excp.dao.pojo.TerminalExcept;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author Administrator 2016-2-29
 * @see
 * @since 1.0
 */
public class ScheduleJob
{
    /** 任务id */
    private String jobId;
    /** 任务名称 */
    private String jobName;
    /** 任务分组 */
    private String jobGroup;
    /** 任务状态 0禁用 1启用 2删除*/
    private String jobStatus;
    /** 任务运行时间表达式 */
    private String cronExpression;
    /** 任务描述 */
    private TerminalExcept except;
    private Date date;
    
    
    public TerminalExcept getExcept()
    {
        return except;
    }
    public void setExcept(TerminalExcept except)
    {
        this.except = except;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public String getJobId()
    {
        return jobId;
    }
    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }
    public String getJobName()
    {
        return jobName;
    }
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }
    public String getJobGroup()
    {
        return jobGroup;
    }
    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }
    public String getJobStatus()
    {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus)
    {
        this.jobStatus = jobStatus;
    }
    public String getCronExpression()
    {
        return cronExpression;
    }
    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }
}
