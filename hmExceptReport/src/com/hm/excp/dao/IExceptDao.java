/*
 * 文件名：IExceptDao.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：故障处理相关的数据库访问类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dao;

import java.util.List;

import com.hm.excp.dao.pojo.TerminalExcept;

/**
 * 故障处理相关的数据库访问类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface IExceptDao
{
    /**
     * 根据hql查询故障实体TerminalExcept
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public List<TerminalExcept> getExceptList(String hql);
    /**
     * 根据hql和分页条件查询故障实体TerminalExcept
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     * @param
     *         firstResult  本次查询的起始条数
     *         maxResult    本次查询的最大条数
     *         hql          指定的查询hql
     */
    public List<TerminalExcept> getExceptListByPage(int firstResult ,int maxResult, String hql);
    /**
     * 根据指定的hql查询出故障记录总数
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public List<Integer> getExceptCountList(String hql);
    /**
     * 通过主键查询出对应的故障实体
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public TerminalExcept getExceptById(String id);
    /**
     * 更新故障实体
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public TerminalExcept updateExcept(TerminalExcept except) throws Exception;
    /**
     * 删除故障实体
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public void deleteExcept();
    
}
