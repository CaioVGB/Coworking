package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	private JLabel imgDatabase;

	public Login() {

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});

		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(0, 0, 441, 305));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel txtLogin = new JLabel("Login");
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(0, 80, 146, 14);
		getContentPane().add(txtLogin);

		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setBounds(0, 130, 146, 14);
		getContentPane().add(txtSenha);

		inputLogin = new JTextField();
		inputLogin.setBounds(132, 77, 195, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(132, 127, 195, 20);
		getContentPane().add(inputSenha);

		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(181, 186, 89, 23);
		getContentPane().add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});

		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setBounds(0, 27, 424, 20);
		getContentPane().add(tituloLogin);

		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 171, 46, 95);
		getContentPane().add(imgDatabase);
		
		//Acessar o botão "Entrar" com a tecla "Enter"
		getRootPane().setDefaultButton(btnLogin);
		
		
		//Validação dos campos utilizando a biblioteca Atxy2k
		
		//Validação do campo inputLogin
		RestrictedTextField validarLogin = new RestrictedTextField(inputLogin, "abcdefghijklmnopqrstuvwxyz0123456789_-.");
		
		
		//Determinar o uso de alguns caracteres especiais (_-.) e alfanuméricos
		validarLogin.setOnlyCustomCharacters(true);
		
		
		//Limitar a somente 20 caracteres no campo login
		validarLogin.setLimit(20);
		
		//Validação do campo InputSenha
		RestrictedTextField validarSenha = new RestrictedTextField(inputSenha);
		
	
		//limitar a somente 15 caracteres no campo senha
		validarSenha.setLimit(15);
		
		//Desativar a tecla espaço no campo senha
	
	}


	DAO dao = new DAO();

	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();

			if (conexaoBanco == null) {
				// Escolher a imagem para quando não há conexão
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
			}

			else {
				// Trocar a imagem se houver conexão
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOn.png")));
			}
			conexaoBanco.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void logar() {
		String read = "select * from funcionario where login=? and senha=md5(?)";
		// Validação do login do usuario
		if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuario obrigatorio!");
			inputLogin.requestFocus();
		}

		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha ou usuario obrigatoria!");
			inputSenha.requestFocus();
		}

		else {

			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// preparar a execução do script SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);

				// Atribuir valore de login e senha
				// Substituir as interrogações ? ? pelo conteudo da caixa de texto (input)
				executarSQL.setString(1, inputLogin.getText());
				executarSQL.setString(2, inputSenha.getText());

				// Executar os comandos SQL e de acordo com o resultado liberar os recursos na
				// tela
				ResultSet resultadoExecucao = executarSQL.executeQuery();

				// Validação do funcionario (autenticação)
				// resultadoExecucao.next() significa que o login e a senha existem, ou seja,
				// correspodem

				if (resultadoExecucao.next()) {
					Home home = new Home();
					
					
					home.txtUsuarioLogado.setText("Usuario: " + resultadoExecucao.getString(2));
					home.txtPerfilLogado.setText("Perfil: " + resultadoExecucao.getString(5));
					
					
					home.setVisible(true);
					
					dispose();

				}

				else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha invalido(s)!");
					inputLogin.setText(null);
					inputSenha.setText(null);
					inputLogin.requestFocus();

				}

				conexaoBanco.close();

			}

			catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}