package gestionenegozio.dao;

import java.util.List;

import gestionenegozio.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	
	public Categoria findByDescrizione(String descrizioneInput) throws Exception;

	public List<Categoria> findByExample(Categoria example) throws Exception;

	public Categoria findOneEager(Long id) throws Exception;

}
