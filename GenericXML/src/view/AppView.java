package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.interfaces.IView;

public class AppView implements IView {

	private JFrame frame;

	private JPanel basePanel;

	public AppView(IView menuPanel, IView mainPanel) {
		frame = new JFrame();
		frame.setTitle("genericXMLapp");
		frame.setSize(800, 600);

		basePanel = new JPanel();

		basePanel.setLayout(new BorderLayout());
		basePanel.add(menuPanel.getViewComponent(), BorderLayout.PAGE_START);
		basePanel.add(mainPanel.getViewComponent(), BorderLayout.CENTER);

		frame.add(basePanel);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {                
                e.getWindow().dispose();
                System.exit(0);
            }
        });
	}

	@Override
	public Component getViewComponent() {
		return this.frame;
	}
}