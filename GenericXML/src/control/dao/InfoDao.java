package control.dao;

import javax.persistence.EntityManager;

public class InfoDao extends GenericDao<InfoDao, Integer> {

    public InfoDao(EntityManager entityManager) {
        super(entityManager);
    }
    
}
