package com.digisold.core.util;

import java.util.ArrayList;
import java.util.List;

public class Page implements java.io.Serializable {

	/**
	 * 分页对象
	 */
	private static final long serialVersionUID = -5823201508567186870L;

	private int first = 1; // 第一页

	private int last; // 最后一页

	private int previous; // 上一页

	private int next; // 下一页

	private int currentPage = 1; // 当前页

	private int pageSize = 15; // 每页的数量

	private int pageIndex; // 页码

	private int totalPages; // 总页数

	private long totalRecords; // 总记录数

	@SuppressWarnings("rawtypes")
	private List pagesList; // 页数集合

	private int pageChange = 9; // 显示的页数

	private int offset = 0; // 每页记录数开始

	private int below = 0; // 每页记录数的结束

	public Page() {
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPrevious() {
		if (currentPage > 0) {
			previous = (currentPage - 1) > 0 ? (currentPage - 1) : first;
		}
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		if (currentPage > 0) {
			next = (currentPage + 1) <= getTotalPages() ? (currentPage + 1)
					: getTotalPages();
		}
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		if (currentPage > 0) {
			pageIndex = (currentPage - 1) * pageSize;
		}
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPages() {
		if (totalRecords > 0) {
			totalPages = (int) (totalRecords - 1L) / pageSize + 1;
			last = totalPages;
		}
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getPagesList() {
		if (currentPage > 0) {
			pagesList = new ArrayList();
			int changePage = 4;
			int begin = currentPage - changePage > 0 ? currentPage - changePage
					: first;
			int end = currentPage - changePage > 0 ? (begin + pageChange) - 1 < getTotalPages() ? (begin + pageChange) - 1 < totalPages ? (begin + pageChange) - 1
					: getTotalPages()
					: getTotalPages()
					: pageChange < getTotalPages() ? pageChange
							: getTotalPages();
			for (int i = begin; i <= end; i++) {
				pagesList.add(i);
			}
		}
		return pagesList;
	}

	@SuppressWarnings("rawtypes")
	public void setPagesList(List pagesList) {
		this.pagesList = pagesList;
	}

	public int getPageChange() {
		return pageChange;
	}

	public void setPageChange(int pageChange) {
		this.pageChange = pageChange;
	}

	public int getOffset() {
		if (currentPage > 0) {
			offset = (currentPage - 1) * pageSize + 1;
		}
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getBelow() {
		if (currentPage > 0) {
			below = currentPage * pageSize;
		}
		return below;
	}

	public void setBelow(int below) {
		this.below = below;
	}

}
