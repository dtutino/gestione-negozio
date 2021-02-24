package gestionenegozio.service;

import gestionenegozio.dao.ArticoloDAO;
import gestionenegozio.dao.ArticoloDAOImpl;
import gestionenegozio.dao.CategoriaDAO;
import gestionenegozio.dao.CategoriaDAOImpl;
import gestionenegozio.dao.RuoloDAO;
import gestionenegozio.dao.RuoloDAOImpl;
import gestionenegozio.dao.UtenteDAO;
import gestionenegozio.dao.UtenteDAOImpl;

public class MyServiceFactory {

	private static UtenteService UTENTE_SERVICE_INSTANCE;
	private static RuoloService RUOLO_SERVICE_INSTANCE;
	private static CategoriaService CATEGORIA_SERVICE_INSTANCE;
	private static ArticoloService ARTICOLO_SERVICE_INSTANCE;
	
	private static UtenteDAO UTENTE_DAO_INSTANCE = null;
	private static RuoloDAO RUOLO_DAO_INSTANCE = null;
	private static CategoriaDAO CATEGORIA_DAO_INSTANCE = null;
	private static ArticoloDAO ARTICOLO_DAO_INSTANCE = null;

	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTE_DAO_INSTANCE);
		return UTENTE_SERVICE_INSTANCE;
	}

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDAO(RUOLO_DAO_INSTANCE);
		return RUOLO_SERVICE_INSTANCE;
	}
	
	public static CategoriaService getCategoriaServiceInstance() {
		if (CATEGORIA_SERVICE_INSTANCE == null)
			CATEGORIA_SERVICE_INSTANCE = new CategoriaServiceImpl();

		if (CATEGORIA_DAO_INSTANCE == null)
			CATEGORIA_DAO_INSTANCE = new CategoriaDAOImpl();

		CATEGORIA_SERVICE_INSTANCE.setCategoriaDAO(CATEGORIA_DAO_INSTANCE);
		return CATEGORIA_SERVICE_INSTANCE;
	}
	
	public static ArticoloService getArticoloServiceInstance() {
		if (ARTICOLO_SERVICE_INSTANCE == null)
			ARTICOLO_SERVICE_INSTANCE = new ArticoloServiceImpl();

		if (ARTICOLO_DAO_INSTANCE == null)
			ARTICOLO_DAO_INSTANCE = new ArticoloDAOImpl();

		ARTICOLO_SERVICE_INSTANCE.setArticoloDAO(ARTICOLO_DAO_INSTANCE);
		return ARTICOLO_SERVICE_INSTANCE;
	}

}
