/*
 * 文件名：LogoutAction.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理与日期相关的工具类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 处理与日期相关的工具类
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public class DateUtil
{
    /**
     * 将Timestamp类型的数据转换为指定的yyyy-MM-dd HH:mm:ss格式
     * <p>该工具类在jsp页面转换日期格式时调用
     * @author zjj 2016-1-19
     * @see
     * @since 1.0
     */
    public static String changeTimestamp(Timestamp date){
        Date date2 = new Date(date.getTime());
        String ddd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2);
        return ddd;
    }
    
}
