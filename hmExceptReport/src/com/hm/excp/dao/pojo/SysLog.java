package com.hm.excp.dao.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class SysLog implements Serializable
{
    private String sid;
    private String username;
    private String ip;
    private String logMsg;
    private String logType;
    private Timestamp logTime;
    
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
    public String getIp()
    {
        return ip;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    public String getLogMsg()
    {
        return logMsg;
    }
    public void setLogMsg(String logMsg)
    {
        this.logMsg = logMsg;
    }
    public String getLogType()
    {
        return logType;
    }
    public void setLogType(String logType)
    {
        this.logType = logType;
    }
    public Timestamp getLogTime()
    {
        return logTime;
    }
    public void setLogTime(Timestamp logTime)
    {
        this.logTime = logTime;
    }
    
}
