/*
 * 文件名：ILoginDao.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：登陆相关的数据库访问类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dao;

import com.hm.excp.dao.pojo.People;

/**
 * 登陆相关的数据库访问类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public interface ILoginDao
{

/**
 * 通过用户名获取人员信息
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
    People getPeopleByName(String username);
    
}
