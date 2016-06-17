/*
 * 文件名：ExceptJob.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：simple introduction
 * 修改人： Administrator
 * 修改时间：上午11:48:03 - 2016-2-29
 * 修改内容：新增
 */
package com.hm.excp.service;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author Administrator 2016-2-29
 * @see
 * @since 1.0
 */
public class ExceptJob implements Job {

    /**
     * 执行任务的方法
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("================执行任务一....");

        //do more...这里可以执行其他需要执行的任务  
    }

    public String getJobId(){
        return "1--1";
    }

    /**
     * simple introduction
     * <p>detailed comment
     * @return
     * @author Administrator 2016-2-29
     * @since 1.0 
     */
    public Date getStartTime()
    {
        // TODO Auto-generated method stub
        return new Date();
    }
}