/*
 * 文件名：PeopleDaoImpl.java
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

public class PeopleDaoImpl extends BaseDao<People> implements IPeopleDao
{
    /**
     * @see com.hm.excp.dao.IPeopleDao#getPeopleBySid(java.lang.String)
     */
    @Override
    public People getPeopleBySid(String sid)
    {
        People p = get(sid);
        return  p;
        
//        Object o = get(People.class,sid);
//        People p = null;
//        if(o != null){
//            p = (People) o;
//        }
//        return  p;
    }

    /**
     * @see com.hm.excp.dao.IPeopleDao#getPeopleByName(java.lang.String)
     */
    @Override
    public People getPeopleByName(String username)
    {
        String hql = "from People p where 1=1 and p.username = '" + username + "'";
        List<People> list = findByHql(hql);
        if(list != null && list.size() > 0){
            return list.get(0);
        } else {
            return null;
        }
    }

    
    
    /**
     * @see com.hm.excp.dao.IPeopleDao#queryPeopleByHql(java.lang.String)
     */
    @Override
    public List<People> queryPeopleByHql(String hql)
    {
        return findByHql(hql);
    }
    
    /**
     * @see com.hm.excp.dao.IPeopleDao#queryPeopleCountByHql(java.lang.String)
     */
    @Override
    public List queryPeopleCountByHql(String hql)
    {
        return findByHql(hql);
    }

    /**
     * @see com.hm.excp.dao.IPeopleDao#addPeople(com.hm.excp.dao.pojo.People)
     */
    @Override
    public People addPeople(People people)
    {
        return (People) save(people);
    }
    
    /**
     * @see com.hm.excp.dao.IPeopleDao#updatePeople(com.hm.excp.dao.pojo.People)
     */
    @Override
    public void updatePeople(People people)
    {
        update(people);
    }

    /**
     * @see com.hm.excp.dao.IPeopleDao#queryPeopleByPage(int, int, java.lang.String)
     */
    @Override
    public List<People> queryPeopleByPage(int firstResult ,int maxResult, String hql)
    {
        return findByPage(firstResult, maxResult, hql);
    }

    /**
     * @see com.hm.excp.dao.IPeopleDao#deletePeople(java.lang.String)
     */
    @Override
    public void deletePeople(String sid) throws Exception
    {
        People p = getPeopleBySid(sid);
        if(p != null){
            delete(p);
        } else {
            throw new Exception("删除用户出现异常,由主键[" + sid + "]未找到对应的用户");
        }
    }

}
