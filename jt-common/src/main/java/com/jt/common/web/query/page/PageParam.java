package com.jt.common.web.query.page;

import com.jt.common.query.Param;

public class PageParam extends Param {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5278850428796606838L;
	
	private static class PageParamHolder{
		private static final ThreadLocal<PageParam> threadLocal = new ThreadLocal<PageParam>();
	}
	private PageParam(){}
	
	public static PageParam getPageParam(){
		return PageParamHolder.threadLocal.get();
	}
	
	public static void setPageParam(PageParam pageParam){
		PageParamHolder.threadLocal.set(pageParam);
	}
	
	public static void clear(){
		PageParamHolder.threadLocal.set(null);
	}
	
	private int pageNo;
	private int pageSize;
	private int offset;
	private String sort;
	private String orderBy;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
