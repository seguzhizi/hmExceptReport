/*
 * 文件名：ExceptServiceImpl.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和故障处理相关的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hm.excp.dao.IExceptDao;
import com.hm.excp.dao.IPeopleDao;
import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.TerminalExcept;
import com.hm.excp.dto.PageParams;
import com.hm.excp.dto.QueryExceptBean;
import com.hm.excp.dto.TerminalExceptExportBean;
import com.hm.excp.util.ExceptConstant;
import com.hm.excp.util.ExceptConstant.EXCEPT_STATE;
import com.hm.excp.util.PageUtils;
import com.hm.excp.util.StringUtil;
/**
 * 和故障处理相关联的Service
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class ExceptServiceImpl implements IExceptService
{
    Logger logger = Logger.getLogger(this.getClass());

    private IExceptDao exceptDao;
    private IPeopleDao peopleDao;
    /**
     * 故障处理结果查询--导出表格时查询数据集合
     * @see com.hm.excp.service.IExceptService#getExceptListForExport(java.util.List)
     */
    @Override
    public List<TerminalExceptExportBean> getExceptListForExport(List<TerminalExcept> list){
        
        List<TerminalExceptExportBean> exportList = new ArrayList<TerminalExceptExportBean>();
        
        for(TerminalExcept except : list){
            TerminalExceptExportBean bean = new TerminalExceptExportBean();
            
            bean.setTerminalId(except.getTerminalId());
            bean.setType(except.getType());
            bean.setTradeType(except.getTradeType());
            
            String date = new SimpleDateFormat("yyyy-MM-dd").format(except.getHandleDate());
            String time = new SimpleDateFormat("HH:mm:ss").format(except.getHandleTime());
            bean.setHandleDate(date + " " + time);
            bean.setHandleTime(time);   //不用
            
            if((except.getRunStatus() + "").equals(ExceptConstant.RETURN_RST.RETURN_RST_SUCCESS)){
                bean.setRunStatus("运行正常");
            } else {
                bean.setRunStatus("运行异常");
            }
            bean.setMkzt(except.getMkzt());
            
            
            String ctime =  "";
            if(except.getCreateTime() != null){
                ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(except.getCreateTime().getTime());
            }
            bean.setCreateTime(ctime);
            
            if(null != except.getDealPerson()){
                bean.setDealPerson(except.getDealPerson());
            } else {
                bean.setDealPerson("");
            }
            bean.setLevel(except.getLevel());
            
            if(except.getProcessState() != null){
                if(except.getProcessState().equals(ExceptConstant.EXCEPT_STATE.DISPATCH)){
                    bean.setProcessState("已分发");
                } else {
                    bean.setProcessState("完成处理");
                }
                
            } else {
                bean.setProcessState("状态异常");
            }
            
            if(except.getRemark() != null){
                bean.setRemark(except.getRemark());
            } else {
                bean.setRemark("");
            }
            
            String dtime = "";
            if(except.getDealTime() != null){
                dtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(except.getDealTime().getTime());
            }
            bean.setDealTime(dtime);
            
            exportList.add(bean);
        }
        return exportList;
    }
  
    /**
     * [故障处理结果查询功能],查询需要被处理的故障信息列表--分页查询
     * @see com.hm.excp.service.IExceptService#getExceptListByPage(com.hm.excp.dto.QueryExceptBean, com.hm.excp.dto.PageParams)
     */
    @Override
    public Map<List<TerminalExcept>,PageParams> getExceptListByPage(QueryExceptBean queryExceptBean, PageParams pageParams)
    {
        StringBuilder hql = new StringBuilder("from TerminalExcept t where 1=1 ");
        StringBuilder countHql = new StringBuilder("select count(*) from TerminalExcept t where 1=1 ");
        StringBuilder condition = getCondition(queryExceptBean);
        
//        if(!StringUtil.isEmpty(queryExceptBean.getTerminalId())){
//            condition.append(" and t.terminalId = '" + queryExceptBean.getTerminalId() + "'");
//        }
//        //由于存在多个故障处理人-又存储在同一个字段内,故使用[user1][user2]...这种格式存储
//        //每个用户都使用[]包括,所以使用下列的查询格式 :like '%[user1]%' 
//        if(!StringUtil.isEmpty(queryExceptBean.getDealPerson())){
//            condition.append(" and t.dealPerson like '%[" + queryExceptBean.getDealPerson() + "]%'");
//        }
//        if(!StringUtil.isEmpty(queryExceptBean.getProcessState()) && !queryExceptBean.getProcessState().equals("0")){
//            condition.append(" and t.processState = '" + queryExceptBean.getProcessState() + "'");
//        }
        
        countHql.append(condition.toString());
        hql.append(condition.toString()).append(" order by t.createTime desc ");
        
        logger.info("[故障处理查询]-查询终端故障结果总数的hql:[" + countHql.toString() + "]");
        List<Integer>  countList = exceptDao.getExceptCountList(countHql.toString());
        int totalRecords = 0;
        if(countList != null && countList.size() > 0){
            totalRecords = Integer.parseInt(countList.get(0) + "");
        }
        //计算分页要素
        pageParams = PageUtils.queryByPage(totalRecords, pageParams);
        
        //根据分页元素查询结果
        logger.info("[故障处理查询]-查询终端故障结果的hql:[" + hql.toString() + "]");
        List<TerminalExcept> list = exceptDao.getExceptListByPage(pageParams.getFirstResult(), pageParams.getPageSize(), hql.toString());
        
        Map<List<TerminalExcept>,PageParams> map = new HashMap<List<TerminalExcept>,PageParams>();
        
        map.put(list, pageParams);
        
        return map;
    }
    
    /**提供[故障信息查询]的查询和导出的sql条件，保证查询的结果和导出的总结果是一致的*/
    private StringBuilder getCondition(QueryExceptBean queryExceptBean){
        StringBuilder condition = new StringBuilder("");
        if(!StringUtil.isEmpty(queryExceptBean.getTerminalId())){
            condition.append(" and t.terminalId = '" + queryExceptBean.getTerminalId() + "'");
        }
        //由于存在多个故障处理人-又存储在同一个字段内,故使用[user1][user2]...这种格式存储
        //每个用户都使用[]包括,所以使用下列的查询格式 :like '%[user1]%' 
        if(!StringUtil.isEmpty(queryExceptBean.getDealPerson())){
            condition.append(" and t.dealPerson like '%[" + queryExceptBean.getDealPerson() + "]%'");
        }
        if(!StringUtil.isEmpty(queryExceptBean.getMailPerson())){
            condition.append(" and t.mailPeople like '%[" + queryExceptBean.getMailPerson() + "]%'");
        }
        if(!StringUtil.isEmpty(queryExceptBean.getProcessState()) && !queryExceptBean.getProcessState().equals("0")){
            condition.append(" and t.processState = '" + queryExceptBean.getProcessState() + "'");
        }
        return condition;
    }
    
    /**
     * [故障处理结果查询功能],导出故障信息列表时查询符合条件的结果--不分页查询
     * @see com.hm.excp.service.IExceptService#getExceptList(com.hm.excp.dto.QueryExceptBean)
     */
    @Override
    public List<TerminalExcept> getExceptList(QueryExceptBean queryExceptBean)
    {
        StringBuilder hql = new StringBuilder("from TerminalExcept t where 1=1 ");
        StringBuilder condition = getCondition(queryExceptBean);
        
//        if(!StringUtil.isEmpty(queryExceptBean.getTerminalId())){
//            condition.append(" and t.terminalId = '" + queryExceptBean.getTerminalId() + "'");
//        }
//        //由于存在多个故障处理人-又存储在同一个字段内,故使用[user1][user2]...这种格式存储
//        //每个用户都使用[]包括,所以使用下列的查询格式 :like '%[user1]%' 
//        if(!StringUtil.isEmpty(queryExceptBean.getDealPerson())){
//            condition.append(" and t.dealPerson like '%[" + queryExceptBean.getDealPerson() + "]%'");
//        }
//        if(!StringUtil.isEmpty(queryExceptBean.getProcessState()) && !queryExceptBean.getProcessState().equals("0")){
//            condition.append(" and t.processState = '" + queryExceptBean.getProcessState() + "'");
//        }
        hql.append(condition.toString()).append(" order by t.createTime desc ");
        
        logger.info("[故障处理查询导出]-查询终端故障结果的hql:[" + hql.toString() + "]");
        List<TerminalExcept> list = exceptDao.getExceptList(hql.toString());
        return list;
    }
    
    /**
     * [故障处理功能],查询需要被处理的故障信息列表
     * @see com.hm.excp.service.IExceptService#getDealExceptListByPage(com.hm.excp.dto.QueryExceptBean, com.hm.excp.dto.PageParams)
     */
    @Override
    public Map<List<TerminalExcept>,PageParams> getDealExceptListByPage(QueryExceptBean queryExceptBean, PageParams pageParams)
    {
        StringBuilder condition = new StringBuilder("");
        StringBuilder hql = new StringBuilder("from TerminalExcept t where 1=1 ");
        StringBuilder countHql = new StringBuilder("select count(*) from TerminalExcept t where 1=1 ");
        
        
        ////由于存在多个故障处理人-又存储在同一个字段内,故使用[user1][user2]...这种格式存储
        //每个用户都使用[]包括,所以使用下列的查询格式 :like '%[user1]%' 
        if(!StringUtil.isEmpty(queryExceptBean.getDealPerson())){
            condition.append(" and t.dealPerson like '%[" + queryExceptBean.getDealPerson() + "]%'");
        }
        if(!StringUtil.isEmpty(queryExceptBean.getProcessState()) && !queryExceptBean.getProcessState().equals("0")){
            condition.append(" and t.processState = '" + queryExceptBean.getProcessState() + "'");
        }
        
        countHql.append(condition.toString());
        hql.append(condition.toString()).append(" order by t.createTime desc ");
        
        logger.info("[故障处理]-查询终端故障结果总数的hql:[" + countHql.toString() + "]");
        List<Integer>  countList = exceptDao.getExceptCountList(countHql.toString());
        int totalRecords = 0;
        if(countList != null && countList.size() > 0){
            totalRecords = Integer.parseInt(countList.get(0) + "");
        }
        //计算分页要素
        pageParams = PageUtils.queryByPage(totalRecords, pageParams);
        
        //根据分页元素查询结果
        logger.info("[故障处理]-查询终端故障结果的hql:[" + hql.toString() + "]");
        List<TerminalExcept> list = exceptDao.getExceptListByPage(pageParams.getFirstResult(), pageParams.getPageSize(), hql.toString());
        
        Map<List<TerminalExcept>,PageParams> map = new HashMap<List<TerminalExcept>,PageParams>();
        
        map.put(list, pageParams);
        
        return map;
    }
    

    /**
     * @see com.hm.excp.service.IExceptService#getExceptById(java.lang.String)
     */
    @Override
    public TerminalExcept getExceptById(String id)
    {
        return exceptDao.getExceptById(id);
    }

    /**
     * @see com.hm.excp.service.IExceptService#updateExcept(com.hm.excp.dao.pojo.TerminalExcept)
     */
    @Override
    public TerminalExcept updateExcept(TerminalExcept except) throws Exception
    {
        //设置故障处理完成的时间
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        except.setDealTime(ts);
        //设置故障的状态
        except.setProcessState(EXCEPT_STATE.DONE);
        except = exceptDao.updateExcept(except);
        
        return except;
    }

    /**
     * @see com.hm.excp.service.IExceptService#deleteExcept()
     */
    @Override
    public void deleteExcept()
    {
    }

    /**
     * @see com.hm.excp.service.IExceptService#getMailList(java.lang.String)
     */
    @Override
    public List<People> getMailList(String mailPeopleString)
    {
        if(!(mailPeopleString != null && !mailPeopleString.equals(""))){
            throw new RuntimeException("要发送的邮件接收人为空");
        }
        List<People> list = new ArrayList<People>();
        int index = mailPeopleString.indexOf("][");
        //确定是否是多个用户
        if(index > 0){
            String[] nameArray = mailPeopleString.split("\\]\\[");
            String first = nameArray[0];
            String last = nameArray[nameArray.length - 1];
            nameArray[0] = first.substring(1);
            nameArray[nameArray.length - 1] = last.substring(0,last.length() - 1);
            for(int i = 0 ; i < nameArray.length ; i++){
                People p = peopleDao.getPeopleByName(nameArray[i]);
                if(p != null){
                    list.add(p);
                } else {
                    throw new NullPointerException("使用" + nameArray[i] + "查到的用户为空");
                }
//                System.out.println(nameArray[i]);
            }
        } else {
            String name = mailPeopleString.substring(1,mailPeopleString.length() - 1);
            People p = peopleDao.getPeopleByName(name);
            if(p != null){
                list.add(p);
            } else {
                throw new NullPointerException("使用" + name + "查到的用户为空");
            }
        }
        return list;
    }
    
    public String getDealPeople(String dp){
        if(StringUtil.isEmpty(dp)){
           return null; 
        }
        
        String dealPeople = "";
        //包含分割符","，认为是多个处理人
        if(dp.indexOf(",") > 0){
            String[] arr = dp.split(",");
            for(int i = 0 ; i < arr.length; i++){
                if(arr[i].startsWith("\\[") && arr[i].endsWith("\\]")){
                    dealPeople += arr[i];
                } else {
                    dealPeople += "[" + arr[i] + "]";
                }
            }
        } else {
            if(dp.startsWith("[") && dp.endsWith("]")){
                dealPeople = dp;
            } else {
                dealPeople = "[" + dp + "]";
            }
        }
        return dealPeople;
    }
    
    public static void main(String[] args)
    {
//        new ExceptServiceImpl().getDealPeople("[uyjuyjzzz][xxx][ccc]");
        new ExceptServiceImpl().getMailList("[uyjuyjzzz][xxx][ccc]");
    }
    
    public IExceptDao getExceptDao()
    {
        return exceptDao;
    }

    public void setExceptDao(IExceptDao exceptDao)
    {
        this.exceptDao = exceptDao;
    }

    public IPeopleDao getPeopleDao()
    {
        return peopleDao;
    }

    public void setPeopleDao(IPeopleDao peopleDao)
    {
        this.peopleDao = peopleDao;
    }

}
