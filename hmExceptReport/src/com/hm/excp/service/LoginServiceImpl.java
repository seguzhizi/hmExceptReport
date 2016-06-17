/*
 * 文件名：ILoginService.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：和用户处理相关联的Service
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.service;

import com.hm.excp.dao.ILoginDao;
import com.hm.excp.dao.pojo.People;
import com.hm.excp.util.HMLogger;
import com.hm.excp.util.NeedLog;
/**
 * 处理登陆相关联的Service
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 */
public class LoginServiceImpl implements ILoginService
{

    /**登陆相关的dao*/
    private ILoginDao loginDao;
    
    /**
     * @see com.hm.excp.service.ILoginService#queryPeopleByName(com.hm.excp.dao.pojo.People)
     */
    @Override
    public People queryPeopleByName(People people)
    {
        people = loginDao.getPeopleByName(people.getUsername());
        return people;
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
    
