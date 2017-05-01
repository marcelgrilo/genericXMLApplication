package control.dao;

import javax.persistence.EntityManager;

import model.app.Server;

public class ServerDao  extends GenericDao<Server, Integer> {

    public ServerDao(EntityManager entityManager) {
        super(entityManager);
    }
    
}
