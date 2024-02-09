package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Salas extends JDialog {
	public Salas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salas.class.getResource("/img/logo.png")));
		setTitle("Salas");
		getContentPane().setLayout(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
