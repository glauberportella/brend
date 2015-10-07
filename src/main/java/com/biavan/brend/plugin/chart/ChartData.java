package com.biavan.brend.plugin.chart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChartData {

	private String chartId;
	private String type;
	private List<Chartable> listData = new ArrayList<Chartable>();
	
	public String getChartId() {
		return chartId;
	}
	
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public List<Chartable> getListData() {
		return listData;
	}

	public void setListData(List<Chartable> listData) {
		this.listData = listData;
	}
	
	public String getJson() {
		String json = "";
		
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			json = mapper.writeValueAsString(listData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
