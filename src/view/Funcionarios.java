package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Funcionarios extends JDialog{
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField passwordField;
	private JPasswordField inputSenha;
	public Funcionarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		setTitle("Funcionarios");
		setBounds(0,0,500,400);
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
		
		inputPerfil = new JComboBox();
		inputPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Adminstrador", "Gerência", "Atendimento", "Suporte"}));
		inputPerfil.setBounds(50, 146, 158, 22);
		getContentPane().add(inputPerfil);
		
		JButton btnCreate = new JButton("");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		btnCreate.setBounds(181, 257, 89, 60);
		getContentPane().add(btnCreate);
		
		getContentPane().add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionarios();	
			}
		});
		
		JButton btnUpdate = new JButton("");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		btnUpdate.setBounds(280, 257, 89, 60);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		btnDelete.setBounds(379, 257, 89, 60);
		getContentPane().add(btnDelete);
		
	}

	//Criar um objeto da classe DAO para estabelecer conexão com o banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	
	public void adicionarFuncionarios() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";
		
		try {
			// Estabelecer a conexao
			Connection conexaoBanco = dao.conectar();
			
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);
			
			//Substituir os pontos de interrogação pelo conteudo das caixas de texto (inputs)
			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getText());
			executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
			executarSQL.setString(5, inputEmail.getText());
			
			//Executar os comandos SQL e inserir o funcionario no banco de dados
			executarSQL.executeUpdate();
			
			conexaoBanco.close();
		}
		
		catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário");
			
		}
		
		catch (Exception e) {
			System.out.println(e);
		
	}

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
