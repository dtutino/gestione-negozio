package gestionenegozio.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class UtilityParse {
	
	public static Long parseIdArrivoFromString(String idArrivoStringParam) {
		if (StringUtils.isBlank(idArrivoStringParam))
			return null;

		try {
			return Long.parseLong(idArrivoStringParam);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static int parsePrezzoFromString(String prezzoStringParam) {
		if (StringUtils.isBlank(prezzoStringParam))
			return 0;

		try {
			return Integer.parseInt(prezzoStringParam);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
