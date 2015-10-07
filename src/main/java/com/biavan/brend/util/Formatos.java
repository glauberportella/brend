package com.biavan.brend.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class Formatos {

	public static SimpleDateFormat getFormatoDeData() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato;
	}
	
	public static SimpleDateFormat getFormatoDeDataHora() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		return formato;
	}


	public static Date parseToDate(String data) {
		try {
			return getFormatoDeData().parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getUltimaData() {
		try {
			return getFormatoDeData().parse("01/01/3000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static float formatDecimal(float valor) {
		DecimalFormat df = new DecimalFormat("#.#");
		return Float.parseFloat(df.format(valor).replace(",", "."));
	}

	public static double formatDecimal(double valor) {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(valor).replace(",", "."));
	}
	
	public static String currentFormat(double valor) {
		Locale loc = new Locale("pt_BR");
		NumberFormat nf = NumberFormat.getNumberInstance(loc);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("R$ #.00");
		return df.format(valor);
	}

	public static boolean validaData(final String date) {

		Pattern pattern = Pattern
				.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
		Matcher matcher = pattern.matcher(date);

		if (matcher.matches()) {

			matcher.reset();

			if (matcher.find()) {

				String day = matcher.group(1);
				String month = matcher.group(2);
				int year = Integer.parseInt(matcher.group(3));

				if (day.equals("31")
						&& (month.equals("4") || month.equals("6")
								|| month.equals("9") || month.equals("11")
								|| month.equals("04") || month.equals("06") || month
									.equals("09"))) {
					return false; // only 1,3,5,7,8,10,12 has 31 days
				} else if (month.equals("2") || month.equals("02")) {
					// leap year
					if (year % 4 == 0) {
						if (day.equals("30") || day.equals("31")) {
							return false;
						} else {
							return true;
						}
					} else {
						if (day.equals("29") || day.equals("30")
								|| day.equals("31")) {
							return false;
						} else {
							return true;
						}
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
