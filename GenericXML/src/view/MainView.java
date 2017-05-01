package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import control.ui.interfaces.IViewController;
import view.interfaces.IView;

public class MainView extends JPanel implements IView {

	private static final long serialVersionUID = 1L;

	private JTextArea log;
	
	private JButton startService;
	private JButton stopService;
	
	private JLabel labelCoresInfo;
	private JLabel labelThreadsInfo;
	private JLabel labelMemoryInfo;
	private JLabel labelFilesProcessedInfo;
	private JLabel labelFilesFailedInfo;


	
	private IViewController controller;
	
	public MainView(IViewController controller)
	{
		this.controller = controller;
		
		this.log = new JTextArea();
		log.setEditable(false);
		log.setLineWrap(true);		
		JScrollPane scroll = new JScrollPane(log);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		startService = new JButton("Start");
		startService.setActionCommand("START");
		startService.addActionListener(this.controller.getActionListener());
		
		stopService = new JButton("Stop");
		stopService.setActionCommand("STOP");
		stopService.addActionListener(this.controller.getActionListener());
		
		labelCoresInfo =  new JLabel(" ");
		labelThreadsInfo = new JLabel(" ");
		labelMemoryInfo = new JLabel(" ");
		labelFilesProcessedInfo = new JLabel(" ");
		labelFilesFailedInfo = new JLabel(" ");		
		
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));		
		info.setBackground(Color.LIGHT_GRAY);
		info.add(labelCoresInfo);
		info.add(labelThreadsInfo);
		info.add(labelMemoryInfo);
		info.add(labelFilesProcessedInfo);
		info.add(labelFilesFailedInfo);

		JPanel head = new JPanel(new FlowLayout());
		head.add(startService);
		head.add(stopService);
		
		
		JPanel center = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.10;
		c.fill = GridBagConstraints.BOTH;
		center.add(info,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = c.weighty = 0.90;
		c.fill = GridBagConstraints.BOTH;
		center.add(scroll,c);		
		
		this.setLayout(new BorderLayout());
		this.add(head, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
	}	
	
	@Override
	public Component getViewComponent() {
		return this;
	}
	
	public void SetThreadsText(String text)
	{
		labelThreadsInfo.setText(text);
	}
	
	public void SetMemoryText(String text)
	{
		labelMemoryInfo.setText(text);
	}
	
	public void SetFilesProcessedText(String text)
	{
		labelFilesProcessedInfo.setText(text);
	}
	
	public void SetFilesFailedText(String text)
	{
		labelFilesFailedInfo.setText(text);
	}
	
	public void SetCoresText(String text) {
		labelCoresInfo.setText(text);
	}
	
	public void Log(String text)
	{
		log.append(text);
		log.append("\n");
	}
	public void ClearLog()
	{
		log.setText("");
	}
	
	public void Alert(String text)
	{
		JOptionPane.showMessageDialog(this, text, "Warning!", JOptionPane.WARNING_MESSAGE);
	}

}
