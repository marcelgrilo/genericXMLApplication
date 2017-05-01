package control.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.ui.interfaces.INotifyable;
import control.ui.interfaces.IViewController;
import view.AppView;
import view.interfaces.IView;

public class MainFrameController implements ActionListener, IViewController,  INotifyable {

	private IView mainFrame;
	
	private MenuBarController menuController;
	private CenterController centerController;
		
	public MainFrameController()
	{		
		menuController = new MenuBarController(this);
		centerController = new CenterController(this);
		mainFrame = new AppView(menuController.getView(), centerController.getView());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {		
	}

	@Override
	public ActionListener getActionListener() {
		return this;
	}

	@Override
	public IView getView() {
		return this.mainFrame;
	}

	@Override
	public INotifyable getParent() {
		// noOne is parent of root.
		return null;
	}

	@Override
	public void notifyController(Object source, String message) {
		if(source instanceof MenuBarController)
		{
			if(message.equals("MAIN_VIEW") || message.equals("CONFIG_VIEW") )
			{
				centerController.notifyController(this, message);
			}
		}
	}


	
}