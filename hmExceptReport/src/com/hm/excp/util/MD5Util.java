/*
 * 文件名：MD5Util.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理加密相关的工具类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 处理加密相关的工具类
 * @author zjj 2016-1-19
 * @see
 * @since 1.0
 */
public class MD5Util
{
    /**
     * The hex digits.
     */
    private static final String[] hexDigits =
    { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * Transform the byte array to hex string.
     * @param b
     * @return
     */
    public static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
        {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    /**
     * Transform a byte to hex string.
     * @param b
     * @return
     */
    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
            n = 256 + n;
        // get the first four bit
        int d1 = n / 16;
        // get the second four bit
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    /**
     * Get the MD5 encrypt hex string of the origin string. <br/>
     * The origin string won't validate here, so who use the API should validate
     * by himself.
     * @param origin
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String MD5Encode(String origin) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToHexString(md.digest(origin.getBytes()));
    }

    public static void main(String[] args)
    {
        try
        {
            String code = "123456";
            System.out.println(code + "=[" + MD5Util.MD5Encode(code) +
            "],length=" + MD5Util.MD5Encode(code).length());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
}