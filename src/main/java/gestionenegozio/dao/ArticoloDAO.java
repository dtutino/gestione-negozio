package gestionenegozio.dao;

import java.util.List;
import java.util.Optional;

import gestionenegozio.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {
	
public Optional<Articolo> findOneEager(Long id) throws Exception;
	
	public List<Articolo> findByExample(Articolo example) throws Exception;
	
}
