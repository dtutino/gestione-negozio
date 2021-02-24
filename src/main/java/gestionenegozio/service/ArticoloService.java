package gestionenegozio.service;

import java.util.List;

import gestionenegozio.dao.ArticoloDAO;
import gestionenegozio.model.Articolo;

public interface ArticoloService {
	
	public List<Articolo> listAllElements() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;
	
	public Articolo caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	public List<Articolo> findByExample(Articolo example) throws Exception;

	// per injection
	public void setArticoloDAO(ArticoloDAO articoloDAO);

}
