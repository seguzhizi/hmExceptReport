/*
 * 文件名：ILoginService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和用户处理相关联的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import com.hm.excp.dao.pojo.People;

/**
 * 处理登陆相关联的Service
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface ILoginService
{
    /**
     * 通过用户名查询用户是否存在，用以判断用户是否已注册
     * @author zjj 2016-2-8
     * @see
     * @since 1.0
     */
    public People queryPeopleByName(People people);

}
