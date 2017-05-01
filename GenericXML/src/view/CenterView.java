package view;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

import control.ui.interfaces.IViewController;
import view.interfaces.IChangeView;
import view.interfaces.IView;

public class CenterView  extends JPanel implements IChangeView, IView 
{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private IViewController controller; 
	
	private IView configView;
	private IView mainView;
	
	
	public CenterView(IViewController controller, IView configController, IView mainController )
	{
		this.controller = controller;
		this.configView = configController;
		this.mainView = mainController;
		
		this.setLayout(new CardLayout());
		
		this.add(this.configView.getViewComponent(), "CONFIG_VIEW");
		this.add(this.mainView.getViewComponent(), "MAIN_VIEW");
	}
	
	@Override
	public void ChangeView(String viewName) 
	{
		  CardLayout cl = (CardLayout)(this.getLayout());
		  cl.show(this, viewName);
	}

	@Override
	public Component getViewComponent() {
		return this;
	}
	
}