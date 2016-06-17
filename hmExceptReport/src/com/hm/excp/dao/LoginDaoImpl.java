/*
 * 文件名：LoginDaoImpl.java
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
public class LoginDaoImpl extends BaseDao<Object> implements ILoginDao
{

    /**
     * @see com.hm.excp.dao.ILoginDao#getPeopleByName(java.lang.String)
     */
    @Override
    public People getPeopleByName(String username)
    {
        People people = null;
        String hql = "from People p where 1=1 and p.username = '" + username + "'";
        Object o = getUniqueByHql(hql,null);
        if(o != null){
            people = (People) o;
        } else {
            people = null;
        }
        return people;
    }

}
