package com.biavan.brend.plugin.chart;

public interface Chartable {

	public float getValue();
	
	public void setValue(float value);
	
	public String getColor();
	
	public void setColor(String color);
	
	public String getHighlight();
	
	public void setHighlight(String highlight);
	
	public String getLabel();
	
	public void setLabel(String label);
}
