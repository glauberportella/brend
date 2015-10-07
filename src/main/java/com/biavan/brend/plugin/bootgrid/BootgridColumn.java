package com.biavan.brend.plugin.bootgrid;

public class BootgridColumn {

	private String id;
	
	private String label;
	
	private boolean sortable;

	public BootgridColumn(String id, String label) {
		super();
		this.id = id;
		this.label = label;
		this.sortable = true;
	}
	
	public BootgridColumn(String id, String label, boolean sortable) {
		super();
		this.id = id;
		this.label = label;
		this.sortable = sortable;
	}
	

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public boolean isSortable() {
		return sortable;
	}
	
}
