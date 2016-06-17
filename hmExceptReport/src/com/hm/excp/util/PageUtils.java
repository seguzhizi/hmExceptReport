/*
 * 文件名：PageUtils.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：处理分页的工具类
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.util;

import com.hm.excp.dto.PageParams;
/**
 * 计算分页的工具类
 * @author zjj 2016-2-2
 * @since 1.0
 */
public class PageUtils
{
    /**
     * 计算分页元素,需要数据总数量和分页bean
     * @param totalCount
     *         需要分页的记录总数
     * @param pageParams
     *          分页bean
     * @return
     *          pageParams : 计算完分页后的bean
     * @author zjj 2016-2-2
     * @since 1.0
     */
    public static PageParams queryByPage(int totalCount,PageParams pageParams){
        
        pageParams.setTotalRecords(totalCount);
        
        int k = 0;
        if(totalCount % pageParams.getPageSize() == 0){
            k = totalCount/pageParams.getPageSize();
        } else {
            k = totalCount/pageParams.getPageSize() + 1;
        }
        pageParams.setTotalPages(k);
        
        //如果当前页小于第一页,即访问第一页
        if(pageParams.getCurPage()<=1){
            pageParams.setCurPage(1);
        }
        //如果当前页大于等于最大页数,即访问最后一页
        if(pageParams.getCurPage() >= pageParams.getTotalPages()){
            pageParams.setCurPage(pageParams.getTotalPages());
        }
        //每页显示的第一条记录
        int firstResult = (pageParams.getCurPage()-1)*pageParams.getPageSize();
        
        pageParams.setFirstResult(firstResult);
        
        return pageParams;
        
    }

}
