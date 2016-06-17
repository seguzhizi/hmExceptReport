/*
 * 文件名：ExceptDaoImpl.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：故障处理相关的数据库访问类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dao;

import java.util.List;

import com.hm.excp.dao.pojo.TerminalExcept;

public class ExceptDaoImpl extends BaseDao<TerminalExcept> implements IExceptDao
{
    /**
     * @see com.hm.excp.dao.IExceptDao#getExceptList(java.lang.String)
     */
    @Override
    public List<TerminalExcept> getExceptList(String hql)
    {
        return findByHql(hql);
    }
    
    /**
     * @see com.hm.excp.dao.IExceptDao#getExceptListByPage(int, int, java.lang.String)
     */
    @Override
    public List<TerminalExcept> getExceptListByPage(int firstResult ,int maxResult, String hql)
    {
        return findByPage(firstResult, maxResult, hql);
    }
    
    /**
     * @see com.hm.excp.dao.IExceptDao#getExceptCountList(java.lang.String)
     */
    @Override
    public List getExceptCountList(String hql)
    {
        return findByHql(hql);
    }

    /**
     * @see com.hm.excp.dao.IExceptDao#getExceptById(java.lang.String)
     */
    @Override
    public TerminalExcept getExceptById(String id)
    {
//        return (TerminalExcept) get(TerminalExcept.class, id);
        return (TerminalExcept) get(id);
    }

    /**
     * @see com.hm.excp.dao.IExceptDao#updateExcept(com.hm.excp.dao.pojo.TerminalExcept)
     */
    @Override
    public TerminalExcept updateExcept(TerminalExcept except) throws Exception
    {
        update(except);
        except = getExceptById(except.getExceptId());
        return except;
    }

    /**
     * @see com.hm.excp.dao.IExceptDao#deleteExcept()
     */
    @Override
    public void deleteExcept()
    {
        // TODO Auto-generated method stub
        
    }

}
