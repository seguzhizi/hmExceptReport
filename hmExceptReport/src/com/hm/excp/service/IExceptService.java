/*
 * 文件名：IExceptService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和故障处理相关的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.util.List;
import java.util.Map;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.TerminalExcept;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryExceptBean;
import com.hm.excp.dto.TerminalExceptExportBean;

/**
 * 和故障处理相关联的Service
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface IExceptService
{
    /**
     * 根据查询条件获取所有故障信息列表
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public List<TerminalExcept> getExceptList(QueryExceptBean queryExceptBean);
    
    /**
     * 根据查询条件查询出故障信息列表，用于excel导出
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public List<TerminalExceptExportBean> getExceptListForExport(List<TerminalExcept> list);
    /**
     * 根据查询条件，分页查询所有记录
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public Map<List<TerminalExcept>,PageParams> getExceptListByPage(QueryExceptBean queryExceptBean, PageParams pageParams);
    /**
     * 通过主键获取故障信息实体
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
    /**
     * 分页显示故障处理页面中获取故障信息列表
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    Map<List<TerminalExcept>, PageParams> getDealExceptListByPage(QueryExceptBean queryExceptBean, PageParams pageParams);
    /**
     * 由字符串提取邮件接收人的姓名
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     * @param
     *         mailPeopleString ： 形如[zzz]或者[zzz][ccc][xxx]等
     */
    List<People> getMailList(String mailPeopleString);
    /**
     * 由界面传入的故障处理人进行格式解析
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     * @param
     *         mailPeopleString ： 形如[zzz]或者[zzz][ccc][xxx]等
     */
    public String getDealPeople(String dp);
    
    
}
