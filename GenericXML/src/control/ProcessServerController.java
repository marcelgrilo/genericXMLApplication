package control;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;

import control.dao.DataSource;
import control.dao.InfoDao;
import control.ui.interfaces.INotifyable;
import model.app.Server;
import model.xml.persist.Info;
import services.InfoFactory;
import services.ServerFileReader;
import services.UnzipService;
import services.Interfaces.IUnpack;
import services.exceptions.UnpackException;

public class ProcessServerController implements Runnable {

	private Server server;
	private IUnpack unpackService;
	private InfoFactory factory;

	private INotifyable controller;
	
	private EntityManager em = DataSource.createEntityManager();
	private InfoDao infoDao = new InfoDao(em);

	public ProcessServerController(Server server, INotifyable controller) {
		this.server = server;
		this.unpackService = new UnzipService();
		this.factory = new InfoFactory();
		this.controller = controller;
	}

	@Override
	public void run() {
		try {
			ServerFileReader sfr = new ServerFileReader(server.getUrl(), server.getPort(), server.getUser(),
				server.getPassword());

			List<File> zippedFiles = sfr.SaveCompactedXML(Runtime.getRuntime().freeMemory());
			if (sfr.getFilesFailed() > 0) {
				this.controller.notifyController("LOG","Files Failed: " + sfr.getFilesFailed() );
			}
	
			for (File file : zippedFiles) {
				for (Info info : factory.CreateModels(unpackService.Unpack(file))) {
					infoDao.create(info);
				}
			}
		} catch (UnpackException e) {
			e.printStackTrace();
			this.controller.notifyController("LOG","exception: " + e.getMessage() );
		} catch (Exception e) {
			this.controller.notifyController("LOG","exception: " + e.getMessage() );
		}
	}

}
