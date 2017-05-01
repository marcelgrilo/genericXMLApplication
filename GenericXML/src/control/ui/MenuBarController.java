package control.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import control.ui.interfaces.INotifyable;
import control.ui.interfaces.IViewController;
import view.MenuView;
import view.interfaces.IView;

public class MenuBarController implements ActionListener, IViewController, INotifyable  {
	
	private IView menuBarView;
	
	private INotifyable parent;
	
	public MenuBarController(INotifyable parent)
	{
		this.parent = parent;
		this.menuBarView = new MenuView(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Exit"))
		{
			System.exit( 0 );
		}
		else if(e.getActionCommand().equals("Main"))
		{
			parent.notifyController(this, "MAIN_VIEW");
		}
		else if(e.getActionCommand().equals("Config"))
		{
			parent.notifyController(this, "CONFIG_VIEW");
		}
		else if(e.getActionCommand().equals("About"))
		{
			JOptionPane.showMessageDialog(null, "Aplicação GenericXML \n\n Criado por Marcel Mendonca Grilo\nComo uma avaliacao tecnica ");
		}
	}

	@Override
	public ActionListener getActionListener() {
		return this;
	}

	@Override
	public IView getView() {
		return this.menuBarView;
	}

	@Override
	public INotifyable getParent() {
		return parent;
	}

	@Override
	public void notifyController(Object source, String message) {
				
			//dosomething here.
		
	}

}
