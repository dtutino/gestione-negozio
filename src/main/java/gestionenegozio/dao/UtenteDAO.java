package gestionenegozio.dao;

import java.util.List;
import java.util.Optional;

import gestionenegozio.model.Ruolo;
import gestionenegozio.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public List<Utente> findAllByRuolo(Ruolo ruoloInput);
	public Optional<Utente> findByUsernameAndPassword(String username,String password);
	public Optional<Utente> login(String username,String password);

}
