/*
 * 文件名：StringUtil.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理字符串相关的工具类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

/**
 * 处理与字符串相关的工具类
 * @author zjj 2016-2-4
 * @since 1.0
 */
public class StringUtil
{
    
    /**
     * 判断字符串是否为空
     * @param str   待检测的字符串
     * @return
     *          true : 表示为空
     *          <p>false : 表示为不为空
     * @author zjj 2016-2-4
     * @since 1.0
     */
    public static boolean isEmpty(String str){
        boolean flag = true;
        if(str != null && !str.trim().equals("")){
            flag = false;
        }
        return flag;
    }

}
