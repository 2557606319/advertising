package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * base entity
 * @author joey
 *
 */
public class BaseEntity {

	/**
	 * 当前页数
	 */
	@TableField(exist = false)
	private Integer currentPage=1;
	
	/**
	 * 开始条目数
	 */
	@TableField(exist = false)
	private Integer offset=0;
	
	/**
	 * 结束条目数
	 */
	@TableField(exist = false)
	private Integer endItem=10;
	
	/**
	 * 总页数
	 */
	@TableField(exist = false)
	private Integer totalPage;
	
	/**
	 * 每页条目数
	 */
	@TableField(exist = false)
	private Integer limit=10;
	
	/**
	 * 排序
	 */
	@TableField(exist = false)
	private String order;
	
	/**
	 * 牌序列
	 */
	@TableField(exist = false)
	private String sort;

	
	
	
	
	 /**
	  * 通过当前页数 和每页条目数 算出起始数和结束数
	  * @param currentPage
	  * @param pageSize
	  */
	public BaseEntity(Integer currentPage, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.limit = pageSize;
		this.offset=(this.currentPage-1)*pageSize;
		this.endItem=this.offset+pageSize;
	}
	
	public BaseEntity(){};


	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		this.offset=(this.currentPage-1)*this.limit;
		this.endItem=this.offset+this.limit;
	}

 

	public Integer getOffset() {
		return offset;
	}


	public void setOffset(Integer offset) {
		this.offset = offset;
		if(this.limit!=10) {
			this.endItem=offset+this.limit;
		}
		
	}


	public Integer getEndItem() {
		return endItem;
	}

	public void setEndItem(Integer endItem) {
		this.endItem = endItem;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
			this.limit = limit; 
			this.offset=(this.currentPage-1)*limit;
			this.endItem=this.offset+limit;  
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

 
	
	
	
	
}
