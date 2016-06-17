/*
 * 文件名：WebUtil.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：与web相关的工具类，可以获取常用的一些对象
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hm.excp.dao.pojo.People;
import com.hm.excp.util.ExceptConstant.XPEOPLE_INFO;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 与web相关的工具类，可以获取常用的一些对象
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public final class WebUtil
{
    
//    private static People xpeople;
    
    /**spring的容器对象：ApplicationContext*/
    static ApplicationContext context;
    
    /**
     * 获取ApplicationContext
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static ApplicationContext getApplicationContext(){
        if(context == null){
            context = WebApplicationContextUtils.getWebApplicationContext(WebUtil.getServletContext());
        }
        return context;
    }
    
    /**
     * 获取struts2的值栈：ValueStack
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static ValueStack getValueStack(String key){
        ValueStack vs = ServletActionContext.getValueStack(getHttpRequest());
        return vs;
    }
    
    /**
     * 获取ServletContext
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static ServletContext getServletContext(){
        ServletContext context = ServletActionContext.getServletContext();
        return context;
    }
    
    /**
     * 获取HttpServletRequest
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static HttpServletRequest getHttpRequest(){
        getServletContext();
        HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
        return request;
    }
    /**
     * 获取HttpServletResponse
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static HttpServletResponse getHttpResponse(){
        HttpServletResponse response = (HttpServletResponse) ServletActionContext.getResponse();
        return response;
    }
    /**
     * 获取HttpSession
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static HttpSession getHttpSession(){
        HttpSession session = getHttpRequest().getSession();
        return session;
    }
    /**
     * 获取登陆用户
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static People getLoginPeople(){
        People p = null;
        Object o  = WebUtil.getHttpSession().getAttribute(XPEOPLE_INFO.XPEOPLE);
        if(o != null){
            p = (People) o;
        }
        return p;
    }
    
    /**
     * 这里需要注意一下，只能用response.getWriter()向网页中输出内容，而不能用respon.getOutputStream(),
     * 因为后面的方法会和jsp中的response.getWriter()方法相冲突
     * TODO
     * @param outString
     * @throws IOException
     */
    public static void outputWrite(String outString) throws IOException {
        PrintWriter out = null;
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html; charset=UTF-8");
            out = response.getWriter();
            /*byte[] jsonBytes = outString.getBytes("UTF-8");
            String s = new String(jsonBytes);
            out.write(s);*/
            out.write(outString);
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    
}
