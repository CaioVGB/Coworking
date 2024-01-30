package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Sobre extends JDialog {
	public Sobre() {
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o Software");
		titulo.setBounds(0, 0, 46, 261);
		getContentPane().add(titulo);
	}

	public static void main(String[] args) {
		
	}

}
