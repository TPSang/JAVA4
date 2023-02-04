package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	private static EntityManagerFactory factory;
	
	public  static EntityManager getEntityManager() {
		if(factory == null || factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("asmjava4");
			return factory.createEntityManager();
		}
		return null;
	}
	public static void shutDown() {
		if(factory != null) {
			factory.close();
		}
	}
}
