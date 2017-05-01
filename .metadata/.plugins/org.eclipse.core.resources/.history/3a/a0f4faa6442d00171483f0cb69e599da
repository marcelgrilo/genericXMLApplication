package control.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSource {

	 private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("genericXML-PU");

	    private DataSource(){}

	    public static EntityManager createEntityManager(){
	        return emf.createEntityManager();
	    }
}
