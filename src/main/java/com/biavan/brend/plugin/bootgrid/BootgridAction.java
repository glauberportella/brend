package com.biavan.brend.plugin.bootgrid;

public class BootgridAction {

	private String id;
	
	private String label;

	private String uri;
	
	private boolean remove;
	
	public BootgridAction(String id, String label, String uri) {
		super();
		this.id = id;
		this.label = label;
		this.uri = uri;
		this.remove = false;
	}
	
	public BootgridAction(String id, String label, String uri, boolean remove) {
		super();
		this.id = id;
		this.label = label;
		this.uri = uri;
		this.remove = remove;
	}


	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public String getUri() {
		return uri;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
}
