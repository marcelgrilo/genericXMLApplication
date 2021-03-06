package control.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * Classe de DAO generica.
 *
 * @author marcel
 * @param <T>
 *            tipo da classe modelo.
 * @param <K>
 *            tipo do Id.
 */
public abstract class GenericDao<T, K> {
	protected EntityManager entityManager;
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDao(EntityManager entityManager) {
		this.entityManager = entityManager;
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void create(T obj) {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();
	}

	public void update(T obj) {
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();
	}

	public T find(K chave) {
		return entityManager.find(clazz, chave);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	public void delete(K chave) {
		T obj = find(chave);
		entityManager.getTransaction().begin();
		entityManager.remove(obj);
		entityManager.getTransaction().commit();
	}

	public long getCount() {
		return (Long) entityManager.createQuery("select count(*) from " + clazz.getName()).getSingleResult();
	}
}
