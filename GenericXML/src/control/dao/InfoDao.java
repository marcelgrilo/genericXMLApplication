package control.dao;

import javax.persistence.EntityManager;

import model.xml.persist.Info;

public class InfoDao extends GenericDao<Info, Integer> {

    public InfoDao(EntityManager entityManager) {
        super(entityManager);
    }
    
}
