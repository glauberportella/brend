package com.biavan.brend.plugin.bootgrid;

import java.util.HashMap;
import java.util.Map;

public class BootgridParam {
	
	private int current;
	private int rowCount;
	private String sortColumn;
	private String sortType;
	private String searchPhrase;
	private String id;
	private Map<String, String> extras = new HashMap<String, String>();
	
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
	
	public String getSortColumn() {
		if (sortColumn == null) {
			return "id";
		}
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortType() {
		if (sortType == null) {
			return "asc";
		}
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}
	
	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getFirst() {
		if (getCurrent() == 1) {
			return getCurrent() - 1;
		}
		return ((getCurrent() - 1) * getRowCount());

	}
	
	public void addExtra(String campo, String valor) {
		extras.put(campo, valor);
	}
	
	public Map<String, String> getExtras() {
		return extras;
	}
	
	@Override
	public String toString() {
		return "current=" + current + "&"
				+ "rowCount==" + rowCount + "&"
				+ "searchPhrase=" + searchPhrase + "&"
				+ "id=" + id + "";
	}

}
