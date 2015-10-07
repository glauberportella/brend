package com.biavan.brend.plugin.bootgrid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final public class BootgridUtil {

	public static BootgridParam makeParam(String params) {

		String[] splitedParams = params.split("&");

		boolean hasSortField = false;

		Map<String, String> mapParam = new HashMap<String, String>();
		for (String param : splitedParams) {
			String[] fields = param.split("=");

			String name = "";
			String value = "";

			if (fields[0].contains("sort")) {
				name = "sort";
				// TODO: Se atualizar a versão do bootgrid é preciso revisar
				// esta instrução, pois o Bug já foi corrigido.
				value = fields[0].replace("sort%5B", "").replace("%5D", "")
						+ "=" + fields[1];
				hasSortField = true;
			} else {
				name = fields[0];
				value = (fields.length > 1) ? fields[1].replace("+", " ") : "";
			}

			mapParam.put(name, value);
		}

		BootgridParam bootgridParam = new BootgridParam();

		bootgridParam.setCurrent(Integer.valueOf(mapParam.get("current")));
		mapParam.remove("current");

		bootgridParam.setRowCount(Integer.valueOf(mapParam.get("rowCount")));
		mapParam.remove("rowCount");

		bootgridParam.setSearchPhrase(mapParam.get("searchPhrase"));
		mapParam.remove("searchPhrase");

		if (hasSortField) {
			bootgridParam.setSortColumn(mapParam.get("sort").split("=")[0]);
			bootgridParam.setSortType(mapParam.get("sort").split("=")[1]);
			mapParam.remove("sort");
		}

		for (Iterator<Map.Entry<String, String>> iterator = mapParam.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, String> entry = iterator.next();
			bootgridParam.addExtra(entry.getKey(), entry.getValue());
		}

		return bootgridParam;
	}

	public static String renderJS(BootgridJS bootgridJS) {

		StringBuilder js = new StringBuilder();
		js.append("<script type=\"text/javascript\">"
				+ "$(document).ready(function() { " + "var grid = $(\"#"
				+ bootgridJS.getCssId() + "\").bootgrid({ " + "ajax: true, ");

		if (bootgridJS.getExtraParams() != null) {
			js.append("post: function () { ");
			js.append("return { ");

			int count = 0;
			for (String key : bootgridJS.getExtraParams().keySet()) {
				if (count != 0) {
					js.append(", ");
				}

				js.append(key + ": \"" + bootgridJS.getExtraParams().get(key)
						+ "\" ");

				count++;
			}

			js.append("}; }, ");
		}

		js.append("url: \"" + bootgridJS.getUrl() + "\", ");

		if (bootgridJS.getFuncs() != null) {
			if (bootgridJS.getFuncs().contains("select")) {
				js.append("selection: true, multiSelect: true, rowSelect: true, keepSelection: true, ");
			}
		}

		js.append("formatters: { ");

		int count = 0;
		for (Integer key : bootgridJS.getBotoes().keySet()) {
			BootgridAction action = bootgridJS.getBotoes().get(key);
			if (count != 0) {
				js.append(", ");
			}

			js.append("\"" + action.getId() + "\": function(column, row) "
					+ "{ return \"<a href=\\\"" + action.getUri()
					+ "/\" + row.id + \"\\\" ");
			if (action.isRemove())
				js.append("class=\\\"remove-line\\\" ");
			js.append(">[ " + action.getLabel().substring(0, 1)
					+ " ]</a>\"; } ");

			count++;
		}

		js.append("} })");
		if (bootgridJS.getFuncs() != null) {
			if (bootgridJS.getFuncs().contains("select")) {
				js.append(".on(\"selected.rs.jquery.bootgrid\", function(e, rows) "
						+ "{ "
						+ "var rowIds = []; "
						+ "for (var i = 0; i < rows.length; i++) "
						+ "{ "
						+ "rowIds.push(rows[i].id); "
						+ "} "
						+ "}).on(\"deselected.rs.jquery.bootgrid\", function(e, rows) "
						+ "{ "
						+ "var rowIds = []; "
						+ "for (var i = 0; i < rows.length; i++) "
						+ "{ "
						+ "rowIds.push(rows[i].id); " + "} " + "})");
			}
		}
		js.append(".on(\"loaded.rs.jquery.bootgrid\", function() " + "{ "
				+ "grid.find(\".remove-line\").on(\"click\", function(e) "
				+ "{ " + "return confirm('Desejam remover este registro?'); "
				+ "}); " + "})");
		js.append("; ");
		js.append("}); </script>"); // Finaliza o document ready

		return js.toString();
	}

	public static String renderGrid(BootgridJS bootgridJS) {

		StringBuilder grid = new StringBuilder();
		grid.append("<table id=\"" + bootgridJS.getCssId() + "\" " + "class=\""
				+ bootgridJS.getCssClass() + "\"><thead><tr>");

		for (Integer chave : bootgridJS.getColumns().keySet()) {
			BootgridColumn column = bootgridJS.getColumns().get(chave);
			grid.append("<th data-column-id=\"" + column.getId() + "\" "
					+ "data-type=\"numeric\" data-identifier=\"true\" ");
			if (!column.isSortable()) {
				grid.append(" data-sortable=\"false\" ");
			}
			grid.append(">" + column.getLabel() + "</th>");
		}

		for (Integer chave : bootgridJS.getBotoes().keySet()) {
			BootgridAction action = bootgridJS.getBotoes().get(chave);
			grid.append("<th data-column-id=\"" + action.getId()
					+ "\" data-formatter=\"" + action.getId()
					+ "\" data-sortable=\"false\">" + action.getLabel()
					+ "</th>");
		}

		grid.append("</tr></thead></table>");

		return grid.toString();
	}

	public static String renderCSS(BootgridJS bootgridJS) {

		String[] opts = bootgridJS.getCssOptions().split(",");
		StringBuilder sb = new StringBuilder();

		if (opts.length > 0) {
			List<String> options = Arrays.asList(opts);

			sb.append("<script type=\"text/javascript\"> "
					+ "$(document).ready(function() { ");

			if (options.contains("hide-search")) {
				sb.append(" $(\"div.search\").hide(); ");
			}

			sb.append(" }); </script>");
		} else {
			return "";
		}

		return sb.toString();

	}

}
