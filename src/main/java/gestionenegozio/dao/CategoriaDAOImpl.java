package gestionenegozio.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import gestionenegozio.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Optional<Categoria> findOne(Long id) throws Exception {
		Categoria categoriaResult = entityManager.find(Categoria.class, id);

		if (categoriaResult == null)
			return Optional.empty();

		return Optional.of(categoriaResult);
	}
	
	@Override
	public Categoria findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Categoria c left join fetch c.articoli where c.id=:idCategoria", Categoria.class)
				.setParameter("idCategoria", id).getSingleResult();
	}

	@Override
	public void update(Categoria o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.merge(o);

	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(categoriaInstance);
	}

	@Override
	public void delete(Categoria o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));

	}
	
	@Override
	public Categoria findByDescrizione(String descrizioneInput) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select c from Categoria c where c.descrizione=?1", Categoria.class)
				.setParameter(1, descrizioneInput);
		
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Categoria> findByExample(Categoria example) throws Exception {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Categoria c where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" c.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" c.codice like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(queryBuilder.toString(), Categoria.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
