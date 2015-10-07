package com.biavan.brend.plugin.bootgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BootgridJS {
	
	private String cssId;
	private String objId;
	private String cssClass;
	private String url;
	private String cssOptions;
	private List<String> funcs;
	private Map<String, String> extraParams;
	private Map<Integer, BootgridAction> botoes = new HashMap<Integer, BootgridAction>();
	private Map<Integer, BootgridColumn> columns = new HashMap<Integer, BootgridColumn>();
	
	public String getCssId() {
		return cssId;
	}
	
	public void setCssId(String cssId) {
		this.cssId = cssId;
	}
	
	public String getObjId() {
		return objId;
	}
	
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCssOptions() {
		return cssOptions;
	}

	public void setCssOptions(String cssOptions) {
		this.cssOptions = cssOptions;
	}

	public Map<Integer, BootgridAction> getBotoes() {
		return botoes;
	}
	
	public void setBotoes(Map<Integer, BootgridAction> botoes) {
		this.botoes = botoes;
	}

	public Map<Integer, BootgridColumn> getColumns() {
		return columns;
	}

	public void setColumns(Map<Integer, BootgridColumn> columns) {
		this.columns = columns;
	}
	
	public void addFunc(String func) {
		if (funcs == null) {
			funcs = new ArrayList<String>();
		}
		funcs.add(func);
	}
	
	public List<String> getFuncs() {
		return funcs;
	}

	public void addExtraParam(String id, String param) {
		if (extraParams == null) {
			extraParams = new HashMap<String, String>();
		}
		extraParams.put(id, param);
	}
	
	public Map<String, String> getExtraParams() {
		return extraParams;
	}
	
}
