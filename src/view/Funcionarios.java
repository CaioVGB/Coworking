package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Funcionarios extends JDialog{
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField passwordField;
	private JPasswordField inputSenha;
	private JTextField inputPerfil;
	public Funcionarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		setTitle("Funcionarios");
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome");
		nomeFunc.setBounds(10, 75, 46, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login");
		loginFunc.setBounds(10, 14, 46, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha");
		senhaFunc.setBounds(231, 75, 46, 14);
		getContentPane().add(senhaFunc);
		
		JLabel emailFunc = new JLabel("Email");
		emailFunc.setBounds(231, 150, 39, 14);
		getContentPane().add(emailFunc);
		
		JLabel perfilFunc = new JLabel("Perfil");
		perfilFunc.setBounds(10, 150, 46, 14);
		getContentPane().add(perfilFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(50, 72, 158, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(280, 147, 137, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(50, 11, 367, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(280, 72, 137, 20);
		getContentPane().add(inputSenha);
		
		JLabel imgCreate = new JLabel("");
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(188, 195, 69, 55);
		getContentPane().add(imgCreate);
		
		JLabel imgUpdate = new JLabel("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(267, 195, 64, 55);
		getContentPane().add(imgUpdate);
		
		JLabel imgDelete = new JLabel("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(353, 195, 64, 55);
		getContentPane().add(imgDelete);
		
		inputPerfil = new JTextField();
		inputPerfil.setBounds(50, 147, 158, 20);
		getContentPane().add(inputPerfil);
		inputPerfil.setColumns(10);
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
