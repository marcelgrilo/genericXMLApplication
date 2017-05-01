package testing;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import control.dao.DataSource;
import control.dao.InfoDao;
import control.dao.ServerDao;
import model.xml.persist.Info;

public class InfoPersistence {

	@Test
	public void test() {
		
		EntityManager em = DataSource.createEntityManager();
		InfoDao infoDao = new InfoDao(em);

		// remove todos os elementos da lista
		for (Info info : infoDao.findAll()) {
			infoDao.delete(info.getId());
		}
		
		
		Info i1 = new Info();
		i1.setIp("242.157.159.46");
		i1.setPort("2581");
		i1.setData("lorem");

		Info i2 = new Info();
		i2.setIp("159.214.165.254");
		i2.setPort("5246");
		
		infoDao.create(i1);		
		assertTrue(infoDao.findAll().size() == 1);
		infoDao.create(i2);
		assertTrue(infoDao.findAll().size() == 2);
		 		
		fail("Not yet implemented");
	}

}