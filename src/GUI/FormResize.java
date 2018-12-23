package GUI;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class FormResize extends JFrame{
	
	public FormResize() {
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentresized(ComponentEvent e) {
				Component c = (Component)e.getSource();
			}
		});
	}

}
