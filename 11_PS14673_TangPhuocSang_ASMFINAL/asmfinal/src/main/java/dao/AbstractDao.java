package dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import utils.JpaUtils;

public class AbstractDao<T> {
	public static final EntityManager entityManager = JpaUtils.getEntityManager();
	
	@Override  //go tu finalize -> enter
	protected void finalize() throws Throwable {
		entityManager.close();
		super.finalize();
	}
	public T findById(Class<T> clazz, Integer id) {
		return entityManager.find(clazz, id);
	}
	public List<T> findAll(Class<T> clazz, boolean existIsActive) {
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("select o from ").append(entityName).append(" o ");
		if(existIsActive == true) {
			sql.append(" where isActive = 1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
		return query.getResultList();
		//select
	}
	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber,int pageSize) {
		String entityName = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("select o from ").append(entityName).append(" o ");
		if(existIsActive == true) {
			sql.append(" where isActive = 1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
		
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		
		return query.getResultList();
		
	}
	public T findOne(Class<T> clazz, String sql,Object...params) {
		TypedQuery<T> query = entityManager.createQuery(sql,clazz);
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<T> result = query.getResultList();
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	public List<T> findMany(Class<T> clazz, String sql,Object...params) {
		TypedQuery<T> query = entityManager.createQuery(sql,clazz);
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		
		return query.getResultList();
	}
	
	public List<Object[]> findManyNativeQuery(String sql,Object...params) {
		Query query = entityManager.createNativeQuery(sql);
		
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		
		return query.getResultList();
	}
	public T create(T entity) {
		try {
			
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot insert entity "+entity.getClass().getSimpleName()+" to DB");
			throw new RuntimeException(e);
		}
	}
	public T update(T entity) {
		try {
			
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot update entity "+entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		}
	}
	public T detete(T entity) {
		try {
			
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot delete entity "+entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		}
	}
	public List<T> callStored(String namedStored,Map<String,Object> paramas) {
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(namedStored);
		paramas.forEach((key,value)-> query.setParameter(key, value));
		return (List<T>) query.getResultList();
	}
}
