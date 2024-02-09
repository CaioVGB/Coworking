package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Reservas extends JDialog {
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/img/logo.png")));
		setTitle("Reservas");
		getContentPane().setLayout(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas dialog = new Reservas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
