/*
 * 文件名：IExceptService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和故障处理相关的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hm.excp.dao.BaseDao;
import com.hm.excp.dao.pojo.People;
import com.hm.excp.dao.pojo.TerminalExcept;
import com.hm.excp.dto.Mail;
import com.hm.excp.util.ExceptConstant;
import com.hm.excp.util.ExceptConstant.EXCEPT_LEVEL;
import com.hm.excp.util.ExceptConstant.ROLE_ID;
import com.hm.excp.util.ExceptConstant.TIME_SECTION;
import com.hm.excp.util.ExceptConstant.XPEOPLE_STATUS;
import com.hm.excp.util.MailUtil;

/**
 * 自动检测后台故障信息是否超过规定时间未处理的Service
 * <p>如果超过8小时未被解决，则自动升级为2级，并发送邮件
 * <p>如果超过24小时未被解决，则自动升级为3级，并发送邮件
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class AutoDealExceptService_bak {
    

    /**日志操作对象*/
    Logger logger = Logger.getLogger(getClass());
    private MailService mailService;
    private BaseDao dao;
    
    public void getExceptList(){
        System.out.println("开始自动程序：" + 
                new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new Date()));
    }
    
    public static void main(String[] args) throws ParseException
    {
        String dateString = "2016-02-15 03:10:00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设定格式
        java.util.Date date = df.parse(dateString); //将字符串转化为日期
        long start = date.getTime();
        long now = System.currentTimeMillis();
        long result = now - start;
        System.out.println(result);
        System.out.println(result > 28800000);
        
    }
    
    /**
     * <p>自动处理故障信息
     * @author Administrator 2016-2-15
     * @since 1.0
     */
    public void autoDealExcept(){
        
        logger.info(new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new Date()) 
                + "开始自动获取故障信息,并判断故障等级");
        
        //只获取[已分发]状态且不是level=3级的故障信息
        String hql = "from TerminalExcept t where 1=1 and t.processState = '" + ExceptConstant.EXCEPT_STATE.DISPATCH + "'"
                + " and t.level != '" + EXCEPT_LEVEL.LEVEL_3 + "'";
        List<TerminalExcept> list = dao.findByHql(hql);
        if(list != null && list.size() > 0){
            logger.info("获取到故障信息" + list.size() + "条");
            
            Iterator<TerminalExcept> it = list.iterator();
            while(it.hasNext()){
                TerminalExcept except = it.next();
                //获取该故障的创建时间
                long start = except.getCreateTime().getTime();
                long now = System.currentTimeMillis();
                long result = now - start;
                
                try{
                    //只判断"已分发"状态的故障信息
                    if(except.getProcessState().equals(ExceptConstant.EXCEPT_STATE.DISPATCH)){
                        //故障默认为1级，在此基础上超过8小时，将自动升级为2级
                        if(except.getLevel().equals(EXCEPT_LEVEL.LEVEL_1)){
                            if(result > TIME_SECTION.T_8){
                                logger.info("此条故障信息" + except.getExceptId() + "已超过8小时未处理,将升级为" + EXCEPT_LEVEL.LEVEL_2 + "级");
                                //邮件通知人
                                StringBuilder bp = new StringBuilder("");
                                //超过8小时，查询出对应角色的领导(主管)
                                List<People> peopleList = getCheif(TIME_SECTION.T_8);
                                //找到此角色的用户
                                if(peopleList != null && peopleList.size() > 0){
                                    for(People p : peopleList){
                                        bp.append("[").append(p.getUsername()).append("]");
                                    }
                                } else {
                                    //没有主管级别，则直接添加admin
                                    peopleList = new ArrayList<People>();
                                    peopleList.add(getCheif(0).get(0));
                                    bp.append("[admin]");
                                }
                                //更新邮件接收人
                                except.setMailPeople(except.getMailPeople() + bp.toString());
                                except.setMailTime(new Timestamp(System.currentTimeMillis()));
                                except.setLevel(EXCEPT_LEVEL.LEVEL_2);
                                dao.update(except);
                                
                                List<Mail> mailList = null;
                                try{
                                    logger.info("故障升级成功,开始发送邮件通知[主管]和[银行部门领导]");
                                    
                                    for(People p : peopleList){
                                        //生成文件内容
                                        String content = mailService.generateContent(except, p, 8);
                                        //生成mail
                                        Mail mail = mailService.generateMail(p.getEmail(), content, null);
                                        MailUtil.send(mail);
                                        logger.info("邮件发送成功!");
                                    }
                                } catch(Exception e){
                                    logger.error("邮件发送异常");
                                    e.printStackTrace();
                                }
                                
                            }
                        //在2级的基础上超过24小时，将自动升级为3级
                        } else if(except.getLevel().equals(EXCEPT_LEVEL.LEVEL_2)){
                            if(result > TIME_SECTION.T_32){
                                logger.info("此条故障信息" + except.getExceptId() + "已超过24小时未处理,将升级为" + EXCEPT_LEVEL.LEVEL_3 + "级");
                              //邮件通知人
                                StringBuilder bp = new StringBuilder("");
                                //超过24小时，查询出对应角色的领导(主管)
                                List<People> peopleList = getCheif(TIME_SECTION.T_24);
                                //找到此角色的用户
                                if(peopleList != null && peopleList.size() > 0){
                                    for(People p : peopleList){
                                        bp.append("[").append(p.getUsername()).append("]");
                                    }
                                } else {
                                    //没有主管级别，则直接添加admin
                                    peopleList = new ArrayList<People>();
                                    peopleList.add(getCheif(0).get(0));
                                    bp.append("[admin]");
                                }
                                //更新邮件接收人
                                except.setMailPeople(except.getMailPeople() + bp.toString());
                                except.setMailTime(new Timestamp(System.currentTimeMillis()));
                                except.setLevel(EXCEPT_LEVEL.LEVEL_3);
                                dao.update(except);
                                
                                List<Mail> mailList = null;
                                try{
                                    logger.info("故障升级成功,开始发送邮件通知[部门经理]和[银行主管]");
                                    
                                    for(People p : peopleList){
                                        //生成文件内容
                                        String content = mailService.generateContent(except, p, 24);
                                        //生成mail
                                        Mail mail = mailService.generateMail(p.getEmail(), content, null);
                                        MailUtil.send(mail);
                                        logger.info("邮件发送成功!");
                                    }
                                } catch(Exception e){
                                    logger.error("邮件发送异常");
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            //故障为3级之后,将不再处理
                            //logger.info("3级故障不予处理!");
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * <p>根据超时的时间不同，查询出不同的领导
     * @author zjj 2016-2-15
     * @since 1.0
     * @see
     * @param
     *  time 超时时间
     *  <p>0    :   查询admin
     *  <p>8    :   查询主管和部门领导
     *  <p>24   :   查询部门经理和银行主管
     */
    private List<People> getCheif(long time){
        String hql = "";
        
        if(time == 0){
            hql = "from People p where 1=1 and  p.username = 'admin'";
        }
        //查询[部门领导]和[主管]
        if(time == TIME_SECTION.T_8){
            hql = "from People p where 1=1 and p.roleId in ('"+ROLE_ID.Role_3+"','"+ROLE_ID.Role_4+"') " +
                    "and p.status = '" + XPEOPLE_STATUS.NORMAL + "'";
        }
        //查询[部门经理]和[银行主管]
        if(time == TIME_SECTION.T_24){
            hql = "from People p where 1=1 and p.roleId in ('"+ROLE_ID.Role_5+"','"+ROLE_ID.Role_6+"') " +
                    "and p.status = '" + XPEOPLE_STATUS.NORMAL + "'";
        }
        List<People> peopleList = dao.findByHql(hql);
        return peopleList;
    }
    
    
    public BaseDao getDao()
    {
        return dao;
    }

    public void setDao(BaseDao dao)
    {
        this.dao = dao;
    }

    public MailService getMailService()
    {
        return mailService;
    }

    public void setMailService(MailService mailService)
    {
        this.mailService = mailService;
    }
    
}
