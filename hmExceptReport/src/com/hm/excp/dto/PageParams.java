/*
 * 文件名：PageParams.java
 * 版权：Copyright WORKWAY INFORMATION,All Rights Reserved. 
 * 描述：分页空间
 * 修改人： zjj
 * 修改时间：下午2:10:36 - 2016-2-23
 * 修改内容：按照要求修改编码风格和格式
 */
package com.hm.excp.dto;

import java.io.Serializable;

/**
 * 该类可以使用继承的方式，也可以使用组合的方式，为了灵活，建议使用后者
 * <p>使用继承时：
 * <p>当查询的页面需要分页的时候，页面查询要素组成一个bean,该bean继承PageParams
 * <p>那么该bean就拥有了下面六个分页要素-以实现分页
 * <p>如果没有查询要素，那么就直接将此类作为分页bean即可
 * <p>不使用继承时
 * <p>在查询方法中，该bean作为一个独立的参数，配合PageUtil参与分页的计算
 * <p>可以和查询到的值一起,作为键值对返回使用
 * @author zjj 2016-2-8
 * @see
 * @since 1.0
 * */
@SuppressWarnings("serial")
public class PageParams implements Serializable{

	private int firstResult = 1;	//每页显示的第一条
	private int LastResult = 1;		//每页显示的最后一条-暂时未使用上哦
	private int pageSize = 5;		//每页显示条数
	private int curPage = 1;		//当前显示第几页
	private int totalRecords = 0;	//总记录数
	private int totalPages = 0;		//总页数
	
	
	public int getFirstResult() {
		return firstResult;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getCurPage() {
		return curPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getLastResult() {
		return LastResult;
	}
	public void setLastResult(int lastResult) {
		LastResult = lastResult;
	}

}
