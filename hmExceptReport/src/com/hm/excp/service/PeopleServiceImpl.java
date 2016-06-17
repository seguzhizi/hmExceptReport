/*
 * 文件名：PeopleServiceImpl.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和用户处理相关联的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hm.excp.dao.ILoginDao;
import com.hm.excp.dao.IPeopleDao;
import com.hm.excp.dao.pojo.People;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryPeopleBean;
import com.hm.excp.util.ExceptConstant.XPEOPLE_STATUS;
import com.hm.excp.util.MD5Util;
import com.hm.excp.util.PageUtils;
import com.hm.excp.util.StringUtil;
/**
 * 和用户处理相关联的Service
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class PeopleServiceImpl implements IPeopleService
{

    /**日志处理对象*/
    Logger logger = Logger.getLogger(this.getClass());
    
    /**用户处理相关的dao*/
    private IPeopleDao peopleDao;
    
    /**登陆相关的dao*/
    private ILoginDao loginDao;

    
    @Override
    public People getPeopleBySid(String sid){
        People p = peopleDao.getPeopleBySid(sid);
        return p;
    }
    
    /**
     * 检测用户名是否被占用
     * @return boolean
     * <p>true:表示被占用   false:表示可用
     * @see com.hm.excp.service.IPeopleService#checkRegister(java.lang.String)
     */
    @Override
    public boolean checkIsRegistered(String username)
    {
        People p = loginDao.getPeopleByName(username);
        if(p != null){
            //不为空，表示被占用
            return true;
        } else {
            return false;
        }
    }

    /**
     * @see com.hm.excp.service.IPeopleService#updatePeople(com.hm.excp.dao.pojo.People)
     */
    @Override
    public void updatePeople(People people)
    {
        //修改变更时间
        Timestamp st = new Timestamp(new Date().getTime());
        people.setChangeTime(st);
        
        peopleDao.updatePeople(people);
    }

    /**
     * @see com.hm.excp.service.IPeopleService#addPeople(com.hm.excp.dao.pojo.People)
     */
    @Override
    public People addPeople(People people)
    {
        //默认的生效时间就是创建时间，即当前时间
        Timestamp st = new Timestamp(new Date().getTime());
        people.setEnableTime(st);
        
        //默认的失效时间是[2099-12-31 23:59:59]
        Calendar cal = Calendar.getInstance();
        cal.set(2099,11,31,23,59,59);
        Timestamp et = new Timestamp(cal.getTimeInMillis());
        people.setDisableTime(et);
        
        //密码加密
        String pwd = ""; 
        try
        {
            pwd = MD5Util.MD5Encode(people.getPassword());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        //logger.info("新增用户，加密后的密码为:" + pwd);
        people.setPassword(pwd);
        
        people = peopleDao.addPeople(people);
        return people;
    }
    
    /**
     * @see com.hm.excp.service.IPeopleService#deletePeopleBySid(java.lang.String)
     */
    @Override
    public void deletePeopleBySid(String sid) throws Exception
    {
        peopleDao.deletePeople(sid);
    }

    /**
     * @see com.hm.excp.service.IPeopleService#queryPeople(com.hm.excp.dto.QueryPeopleBean)
     */
    @Override
    public List<People> queryPeople(QueryPeopleBean bean)
    {
        StringBuilder hql = new StringBuilder("from People p where  1=1 ");
        if(bean.getUsername() != null && !bean.getUsername().trim().equals("")){
            hql.append(" and p.username = '" + bean.getUsername() + "'");
        }
        if(bean.getStatus() != null && !bean.getStatus().trim().equals("")){
            hql.append(" and p.status = '" + bean.getStatus() + "'");
        }
//        if(bean.getStartTime() != null && !bean.getStartTime().trim().equals("")){
//            hql.append(" and p.enableTime >= '" + bean.getStartTime() + "'");
//        }
//        if(bean.getEndTime() != null && !bean.getEndTime().trim().equals("")){
//            hql.append(" and p.username = '" + bean.getEndTime() + "'");
//        }
        
        hql.append(" order by p.enableTime desc ");
        
        logger.info("查询用户hql:[" + hql.toString() + "]");
        List<People> list = peopleDao.queryPeopleByHql(hql.toString());
        
        return list;
    }
    
    /**
     * @see com.hm.excp.service.IPeopleService#queryPeopleByPage(com.hm.excp.dto.QueryPeopleBean, com.hm.excp.dto.PageParams)
     */
    @Override
    public Map<List<People>,PageParams> queryPeopleByPage(QueryPeopleBean bean,PageParams queryParam)
    {
        StringBuilder hql = new StringBuilder("from People p where  1=1 ");
        StringBuilder hql2 = new StringBuilder("select count(*) from People p where 1=1 ");
        
        if(!StringUtil.isEmpty(bean.getUsername())){
            hql.append(" and p.username = '" + bean.getUsername() + "'");
            hql2.append(" and p.username = '" + bean.getUsername() + "'");
        }
        if(!StringUtil.isEmpty(bean.getRealName())){
            hql.append(" and p.realName = '" + bean.getRealName() + "'");
            hql2.append(" and p.realName = '" + bean.getRealName() + "'");
        }
        if(!StringUtil.isEmpty(bean.getRoleId()) && !bean.getRoleId().equals("0")){
            hql.append(" and p.roleId = '" + bean.getRoleId() + "'");
            hql2.append(" and p.roleId = '" + bean.getRoleId() + "'");
        }
        if(!StringUtil.isEmpty(bean.getStatus()) && !bean.getStatus().equals("0")){
        //只能查询出[正常]状态下的用户
            hql.append(" and p.status = '" + bean.getStatus() + "'");
            hql2.append(" and p.status = '" + bean.getStatus() + "'");
        }
        
        hql.append(" order by p.enableTime desc ");
            
            
        logger.info("查询用户数目hql:[" + hql2.toString() + "]");
        List<Integer> countlist = peopleDao.queryPeopleCountByHql(hql2.toString());
        
        //得到查询数据的总数量
        int totalRecords = 0;
        if(countlist != null && countlist.size() > 0){
            Object t = countlist.get(0);
            totalRecords = Integer.parseInt(t.toString());
            logger.info("查询到的结果总数为:" + totalRecords);
        }
        
        //计算分页要素
        queryParam = PageUtils.queryByPage(totalRecords, queryParam);
        
        //根据分页元素查询结果
        logger.info("查询用户hql:[" + hql.toString() + "]");
        List<People> list = peopleDao.queryPeopleByPage(queryParam.getFirstResult(), queryParam.getPageSize(), hql.toString());
        
        Map<List<People>,PageParams> map = new HashMap<List<People>,PageParams>();
        
        map.put(list, queryParam);
        
        return map;
    }
    
    
    public IPeopleDao getPeopleDao()
    {
        return peopleDao;
    }
    public void setPeopleDao(IPeopleDao peopleDao)
    {
        this.peopleDao = peopleDao;
    }
    public ILoginDao getLoginDao()
    {
        return loginDao;
    }
    public void setLoginDao(ILoginDao loginDao)
    {
        this.loginDao = loginDao;
    }

}
