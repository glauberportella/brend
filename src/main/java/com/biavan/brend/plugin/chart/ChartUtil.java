package com.biavan.brend.plugin.chart;

public class ChartUtil {
	
	public static String renderJS(ChartData data) {
		StringBuilder out = new StringBuilder();
		
		out.append("<script type=\"text/javascript\"> var data" + data.getType() + " = " + data.getJson() + ";");

		out.append("window.onload = function() { "
				+ "var ctx = $(\"#" + data.getChartId() + "\").get(0).getContext(\"2d\"); "
				+ "window.myPie = new Chart(ctx)." + data.getType() + "(data" + data.getType() + "); }; </script>");
		
		return out.toString();
	}
}
