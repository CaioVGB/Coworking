package view;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class  Ajuda extends JDialog {
	public Ajuda() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(30, 77, 46, 14);
		getContentPane().add(lblNewLabel);
	}

	public static void main(String[] args) {
		

	}
}
