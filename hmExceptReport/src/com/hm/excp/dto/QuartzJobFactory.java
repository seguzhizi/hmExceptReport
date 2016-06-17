/*
 * 文件名：QuartzJobFactory.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：simple introduction
 * 修改人： Administrator
 * 修改时间：下午5:03:28 - 2016-2-29
 * 修改内容：新增
 */
package com.hm.excp.dto;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hm.excp.dao.pojo.TerminalExcept;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author Administrator 2016-2-29
 * @see
 * @since 1.0
 */
public class QuartzJobFactory implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        TerminalExcept except = scheduleJob.getExcept();
        System.out.println("任务名称 = [" + scheduleJob.getJobName() 
                                + "],故障编号：["+ except.getExceptId()
                                + "],执行时间：[" + scheduleJob.getDate()
                                + "]");
    }
    
    
    
}