package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import control.ui.interfaces.IViewController;
import model.app.Server;
import view.interfaces.IView;

public class ConfigView extends JPanel implements IView {

	private static final long serialVersionUID = 1L;
	
	private JButton btnAddServer;
	private JButton btnRemoveServer;

	private JTextField serverURL;
	private JTextField serverPort;
	private JTextField serverUser;
	private JTextField serverPassword;
	
	private JList<String> serverList;
	
	private IViewController controller;
	
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	public ConfigView(IViewController controller)
	{
		this.controller = controller;
		
		btnAddServer = new JButton("Adicionar Servidor na lsita");
		btnAddServer.setActionCommand("ADD");
		btnAddServer.addActionListener(this.controller.getActionListener());
		
		btnRemoveServer = new JButton("Remover Servidor da lsita");
		btnRemoveServer.setActionCommand("DEL");
		btnRemoveServer.addActionListener(this.controller.getActionListener());
		
		serverURL = new JTextField(100);
		serverPort = new JTextField(5);
		serverUser = new JTextField(20);
		serverPassword = new JTextField(20);
		
		JPanel dataPanel = new JPanel(new SpringLayout());
		dataPanel.add(new JLabel("URL: "));
		dataPanel.add(serverURL);

		dataPanel.add(new JLabel("Port: "));
		dataPanel.add(serverPort);

		dataPanel.add(new JLabel("User: "));
		dataPanel.add(serverUser);

		dataPanel.add(new JLabel("Password: "));
		dataPanel.add(serverPassword);
		SpringUtilities.makeCompactGrid(dataPanel,
                4, 2, //rows, cols
                5, 5, //initialX, initialY
                2, 2);//xPad, yPad
		serverList = new JList<String>(listModel);
		serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new BorderLayout());
		top.add(dataPanel, BorderLayout.CENTER);
		top.add(btnAddServer, BorderLayout.LINE_END);		
		this.add(top,BorderLayout.PAGE_START);
		
		this.add(serverList,BorderLayout.CENTER);
		
		JPanel bot = new JPanel(new FlowLayout());
		bot.add(btnRemoveServer);
		this.add(bot, BorderLayout.PAGE_END);			
	}
	
	public void UpdateServerList(List<Server> list)
	{
		listModel.clear();
		for (Server server : list) {
			listModel.addElement(String.format("%1s:%2 User: %3", server.getUrl(), server.getPort(),server.getUser()));		
		}
		serverList.clearSelection();	        
	}
	
	public String GetListSelectedValue()
	{
		return serverList.getSelectedValue();	
	}
	
	public String GetServerName()
	{
		return serverURL.getText();	
	}
	
	public String GetServerPort() {
		return serverPort.getText();	
	}	
	
	public String GetServerUser() {
		return serverUser.getText();	
	}	
	
	public String GetServerPass() {
		return serverPassword.getText();	
	}
	
	@Override
	public Component getViewComponent() {
		return this;
	}

	public void Clear(){
		serverURL.setText("");
		serverPort.setText("");
		serverUser.setText("");
		serverPassword.setText("");
	}
}