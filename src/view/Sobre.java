package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {
	public Sobre() {
		setTitle("Sobre");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel titulo = new JLabel("Sobre o Software");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		titulo.setBounds(143, 33, 154, 42);
		getContentPane().add(titulo);

		JLabel descricao1 = new JLabel("O software CoWorking trata-se de um protótipo cujo objetivo");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setBounds(61, 86, 306, 14);
		getContentPane().add(descricao1);

		JLabel descricao2 = new JLabel("é possibilitar o gerenciamento de reserva de salas em um espaço colaborativo.");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setBounds(28, 132, 376, 14);
		getContentPane().add(descricao2);

		JLabel vesao = new JLabel("Versão 1.0.0");
		vesao.setHorizontalAlignment(SwingConstants.CENTER);
		vesao.setBounds(121, 170, 176, 14);
		getContentPane().add(vesao);

		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setBounds(129, 214, 154, 14);
		getContentPane().add(atualizacao);

		JLabel imgMIT = new JLabel("New label");
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(353, 193, 51, 56);
		getContentPane().add(imgMIT);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
