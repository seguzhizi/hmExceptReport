package com.hm.excp.dao.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class People implements Serializable
{
    private static final long serialVersionUID = 1L;
 
    private String sid;
    private String username;            //用户名
    private String password;            //密码
    private String realName;            //真实姓名
    private String status;              //状态
    private String email;       
    private String idNo;                //身份证号
    private String celphone;            //手机号码
    private String phone;               //电话号码
    private String remark;              //备注

    private String roleId;
//    private Role roleId;                //关联的角色
    
    private Timestamp enableTime;       //生效时间--默认为创建时间
    private Timestamp disableTime;      //失效时间
    private Timestamp changeTime;       //变更时间
    
    public String getSid()
    {
        return sid;
    }
    public void setSid(String sid)
    {
        this.sid = sid;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getRealName()
    {
        return realName;
    }
    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getCelphone()
    {
        return celphone;
    }
    public void setCelphone(String celphone)
    {
        this.celphone = celphone;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getIdNo()
    {
        return idNo;
    }
    public void setIdNo(String idNo)
    {
        this.idNo = idNo;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public Timestamp getEnableTime()
    {
        return enableTime;
    }
    public void setEnableTime(Timestamp enableTime)
    {
        this.enableTime = enableTime;
    }
    public Timestamp getDisableTime()
    {
        return disableTime;
    }
    public void setDisableTime(Timestamp disableTime)
    {
        this.disableTime = disableTime;
    }
    public Timestamp getChangeTime()
    {
        return changeTime;
    }
    public void setChangeTime(Timestamp changeTime)
    {
        this.changeTime = changeTime;
    }
    public String getRoleId()
    {
        return roleId;
    }
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }
    
}
