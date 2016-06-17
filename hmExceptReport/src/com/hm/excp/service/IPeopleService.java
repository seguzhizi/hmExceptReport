/*
 * 文件名：ISysLogService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和用户处理相关联的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.util.List;
import java.util.Map;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryPeopleBean;

/**
 * 和用户处理相关联的Service
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface IPeopleService
{

    /**
     * 添加一个用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    People addPeople(People people);
    /**
     * 不分页查询出所有的用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    List<People> queryPeople(QueryPeopleBean bean);
    /**
     * 根据页面查询条件分页查询出所有符合要求的用户
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public Map<List<People>,PageParams> queryPeopleByPage(QueryPeopleBean bean,PageParams queryParam);
    /**
     * 更新一个用户实体
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    void updatePeople(People people);
    /**
     * 检测用户是否已经注册
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    boolean checkIsRegistered(String username);
    /**
     * 通过数据库主键查询出对应的用户实体
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    People getPeopleBySid(String sid);
    /**
     * 通过数据库主键删除数据库中对应的实体
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    void deletePeopleBySid(String sid) throws Exception;
    
}
