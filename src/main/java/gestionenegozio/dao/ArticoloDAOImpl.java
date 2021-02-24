package gestionenegozio.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import gestionenegozio.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}
	
	@Override
	public List<Articolo> listAllEager() throws Exception {
		return entityManager.createQuery("from Articolo a join fetch a.categorie", Articolo.class)
				.getResultList();
	}

	@Override
	public Optional<Articolo> findOne(Long id) throws Exception {
		Articolo articoloResult = entityManager.find(Articolo.class, id);
		
		if (articoloResult == null) 
			return Optional.empty();
		
		return Optional.of(articoloResult);
	}

	@Override
	public void update(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.merge(articoloInstance);

	}

	@Override
	public void insert(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(articoloInstance);
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(articoloInstance));

	}

	@Override
	public Optional<Articolo> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Articolo a left join fetch a.categorie where a.id=:idArticolo", Articolo.class)
				.setParameter("idArticolo", id).getResultList().stream().findFirst();
	}
	
	@Override
	public List<Articolo> findByExample(Articolo example) throws Exception {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Articolo a where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" a.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (example.getPrezzo() != 0) {
			whereClauses.add(" a.prezzo like :prezzo ");
			paramaterMap.put("prezzo", "%" + example.getPrezzo() + "%");
		}
		if (example.getDataInserimento() != null) {
			whereClauses.add(" a.dataInserimento like :dataInserimento ");
			paramaterMap.put("dataInserimento", "%" + example.getDataInserimento() + "%");
		}
		if (example.getStato() != null) {
			whereClauses.add(" a.stato =:stato ");
			paramaterMap.put("stato", example.getStato());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Articolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Articolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
