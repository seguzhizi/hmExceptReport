/*
 * 文件名：BaseDao.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：数据库访问的基础类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * 数据库访问的基础类
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class BaseDao<T>  
{
    //自动注入对应的模板类,以注入数据库连接及模板类的相应方法
    private HibernateTemplate hibernateTemplate;
    
    //DAO的泛型类，即子类所指定的T所对应的类型
    private Class entityClass;
    
    //构造方法中通过反射获取子类DAO对应的泛型实体类
    public BaseDao(){
        /*
         * clazz:表示当前运行类
         * 比如有一个com.yzj.model.Dog类,
         *      一个com.yzj.dao.DogDao类,
         *      一个com.yzj.base.dao.impl.BaseDao<T>
         * 并且：DogDao extends BaseDao<Dog>
         * 那么当DogDao执行到父类BaseDao中的方法时
         * clazz就表示当前运行的DogDao类
         * 即com.yzj.model.Dog
         * */
        Class clazz = getClass();
        /*
         * clazz.getGenericSuperclass();表示clazz继承的直接父类
         * 正如上面所说,DogDao直接继承了BaseDao<Dog>
         * 所以genType就代表BaseDao<Dog>,并且是全路径类
         * 即com.yzj.base.dao.impl.BaseDao<com.yzj.Model.Dog>
         * */
        Type genType = clazz.getGenericSuperclass();
        /*
         * 获取genType的入参类型,即上面的BaseDao<Dog>中的Dog类
         * */
        if (!(genType instanceof ParameterizedType)) {
            entityClass = Object.class;
        } else {
            /*
             * entityClass即是泛型类
             * 如果调用者传入的Dog则这里是Dog，如果传入Cat则这里获取到Cat类
             * */
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            entityClass = (Class) params[0];
        }
        System.out.println(clazz+"------" + genType + "--------"+entityClass);
    }
    
    /**使用指定的类型获取*/
    public T get(Class entityClass,Serializable id){
        return (T) hibernateTemplate.get(entityClass, id);
    }
    
    /**利用泛型的类型获取*/
    public T get(Serializable id){
        return (T) hibernateTemplate.get(entityClass, id);
    }
    
    public T save(T entity){
        hibernateTemplate.save(entity);
        return entity;
    }
    
    public void batchSave(List<T> list){
        
    }
    
    public void delete(T entity){
        hibernateTemplate.delete(entity);
        
    }
    
    public void update(T entity){
        hibernateTemplate.update(entity);
    }
    
    public List<T> findByHql(String hql){
        return hibernateTemplate.find(hql);
    }

    /**
     * 根据HQL的唯一查询(如select count(*))。
     * 
     * @param hql hql语句
     * @param objects 参数
     * @return Object 查询出的唯一对象
     * @since DMS1.0
     */
    @SuppressWarnings("unchecked")
    public Object getUniqueByHql(final String hql, final Object... objects)
    {
        try
        {
            return getHibernateTemplate().execute(new HibernateCallback()
            {
                public Object doInHibernate(Session session)
                        throws HibernateException, SQLException
                {
                    Query query = session.createQuery(hql);
                    bindingParams(query, objects);
                    return query.uniqueResult();
                }
            });
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    /**
     * 绑定HQL Query参数，仅自己用。
     * 
     * @param query 查询对象
     * @param objects 参数
     * @since DMS1.0
     */
    private static void bindingParams(Query query, Object... objects)
    {
        // 非空才绑定参数
        if (query != null && objects != null)
        {
            for (int i = 0; i < objects.length; i++)
            {
                query.setParameter(i, objects[i]);
            }
        }
    }

    
    /**
     * 分页查询数据
     * */
    @SuppressWarnings("unchecked")
    public List<T> findByPage(final int firstResult ,final int maxResult, final String hql){
        
        List<T> list = new ArrayList<T>();
        list =  (List<T>) this.getHibernateTemplate().execute(new HibernateCallback(){
            @Override
            public Object doInHibernate(Session sesison)
                    throws HibernateException, SQLException {
                    Query query = sesison.createQuery(hql);
                    query.setFirstResult(firstResult);
                    query.setMaxResults(maxResult);
                    List resultList = query.list();
                return resultList;
            }
        });
        return list;
    }
    
    
    
    public HibernateTemplate getHibernateTemplate()
    {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
    {
        this.hibernateTemplate = hibernateTemplate;
    }
    
}
