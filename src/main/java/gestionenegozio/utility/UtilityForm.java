package gestionenegozio.utility;

import org.apache.commons.lang3.StringUtils;

public class UtilityForm {

	public static boolean validateCategoriaFormInput(String descrizioneInputParam, String codiceInputParam) {
		if (StringUtils.isBlank(descrizioneInputParam) || StringUtils.isBlank(codiceInputParam)) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUtenteFormInput(String usernameParam, String passwordParam,
			String nomeParam, String cognomeParam, String dataCreazioneParam) {
		if (StringUtils.isBlank(usernameParam) || StringUtils.isBlank(passwordParam)
				|| StringUtils.isBlank(nomeParam) || StringUtils.isBlank(cognomeParam) || StringUtils.isBlank(dataCreazioneParam)) {
			return false;
		}
		return true;
	}

	public static boolean validateArticoloFormInput(String nomeInputParam, String prezzoInputParam,
			String dataInserimentoInputParam) {
		if (StringUtils.isBlank(nomeInputParam) || StringUtils.isBlank(prezzoInputParam)
				|| StringUtils.isBlank(dataInserimentoInputParam)) {
			return false;
		}
		return true;
	}

}
