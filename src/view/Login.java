package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	public Login() {
		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(0, 0, 449, 294));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel txtLogin = new JLabel("Login");
		txtLogin.setBounds(188, 60, 86, 14);
		getContentPane().add(txtLogin);
		
		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setBounds(188, 122, 99, 14);
		getContentPane().add(txtSenha);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(118, 85, 169, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(118, 147, 169, 20);
		getContentPane().add(inputSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(160, 193, 89, 23);
		getContentPane().add(btnLogin);
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setBounds(166, 23, 108, 14);
		getContentPane().add(tituloLogin);
		
		JLabel imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(21, 175, 54, 57);
		getContentPane().add(imgDatabase);
	}


		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Login dialog = new Login();
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
