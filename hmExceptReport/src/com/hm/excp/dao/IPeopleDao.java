/*
 * 文件名：IPeopleDao.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：用户处理相关的数据库操作类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dao;

import java.util.List;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryPeopleBean;

/**
 * 用户处理相关的数据库操作类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface IPeopleDao
{
    /**
     * 根据主键查询对应的用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */ 
    People getPeopleBySid(String sid);
    /**
     * 根据用户名查询对应的用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */ 
    People getPeopleByName(String username);
    /**
     * 添加用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    People addPeople(People people) ;
    /**
     * 通过主键删除用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    void deletePeople(String sid) throws Exception ;
    /**
     * 更新用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public void updatePeople(People people);
    /**
     * 根据给定的hql查询用户list
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    List<People> queryPeopleByHql(String hql);
    /**
     * 根据给定的hql查询用户数目
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    List<Integer> queryPeopleCountByHql(String hql);
    /**
     * 根据hql和分页条件查询用户数目
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     * @param
     *         firstResult  本次查询的起始条数
     *         maxResult    本次查询的最大条数
     *         hql          指定的查询hql
     */
    public List<People> queryPeopleByPage(int firstResult ,int maxResult, String hql);
}
