package gestionenegozio.dao;

public class MyDaoFactory {
	
	private static RuoloDAO ruoloDaoInstance = null;
	private static UtenteDAO utenteDaoInstance = null;
	
	private static CategoriaDAO categoriaDaoInstance = null;
	private static ArticoloDAO articoloDaoInstance = null;

	public static RuoloDAO getRuoloDAOInstance() {
		if (ruoloDaoInstance == null)
			ruoloDaoInstance = new RuoloDAOImpl();

		return ruoloDaoInstance;
	}
	
	public static UtenteDAO getUtenteDAOInstance() {
		if (utenteDaoInstance == null)
			utenteDaoInstance = new UtenteDAOImpl();

		return utenteDaoInstance;
	}
	
	public static CategoriaDAO getCategoriaDAOInstance() {
		if (categoriaDaoInstance == null)
			categoriaDaoInstance = new CategoriaDAOImpl();

		return categoriaDaoInstance;
	}
	
	public static ArticoloDAO getArticoloDAOInstance() {
		if (articoloDaoInstance == null)
			articoloDaoInstance = new ArticoloDAOImpl();

		return articoloDaoInstance;
	}
	
}
