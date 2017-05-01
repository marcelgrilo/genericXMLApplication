package control.ui.interfaces;

import java.awt.event.ActionListener;

import view.interfaces.IView;

public interface IViewController {

	public ActionListener getActionListener();
	
	public IView getView();
	
}
