package com.biavan.brend.plugin.bootgrid;

import java.util.List;

public class BootgridData<E> {

	private int current;
	private int rowCount;
	private List<E> rows;
	private long total;
	
	public int getCurrent() {
		return current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public List<E> getRows() {
		return rows;
	}
	
	public void setRows(List<E> dados) {
		this.rows = dados;
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
