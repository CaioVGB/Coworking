package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField passwordField;
	private JPasswordField inputSenha;

	public Funcionarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		setTitle("Funcionarios");
		setBounds(0, 0, 500, 400);
		getContentPane().setLayout(null);

		JLabel nomeFunc = new JLabel("Nome");
		nomeFunc.setBounds(10, 14, 46, 14);
		getContentPane().add(nomeFunc);

		JLabel loginFunc = new JLabel("Login");
		loginFunc.setBounds(10, 154, 46, 14);
		getContentPane().add(loginFunc);

		JLabel senhaFunc = new JLabel("Senha");
		senhaFunc.setBounds(231, 154, 46, 14);
		getContentPane().add(senhaFunc);

		JLabel emailFunc = new JLabel("Email");
		emailFunc.setBounds(231, 205, 39, 14);
		getContentPane().add(emailFunc);

		JLabel perfilFunc = new JLabel("Perfil");
		perfilFunc.setBounds(10, 205, 46, 14);
		getContentPane().add(perfilFunc);

		inputNome = new JTextField();
		inputNome.setBounds(50, 11, 367, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);

		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				buscarFuncionarioNaTabela();
			}
		});

		inputEmail = new JTextField();
		inputEmail.setBounds(280, 202, 137, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);

		inputLogin = new JTextField();
		inputLogin.setBounds(50, 151, 158, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(280, 151, 137, 20);
		getContentPane().add(inputSenha);

		inputPerfil = new JComboBox();
		inputPerfil.setModel(
				new DefaultComboBoxModel(new String[] { "", "Adminstrador", "Gerência", "Atendimento", "Suporte" }));
		inputPerfil.setBounds(50, 201, 158, 22);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 27, 367, 97);
		getContentPane().add(scrollPane);

		tblFuncionario = new JTable();
		scrollPane.setViewportView(tblFuncionario);

	}

	// Criar um objeto da classe DAO para estabelecer conexão com o banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	private JTable tblFuncionario;

	public void adicionarFuncionarios() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";

		try {
			// Estabelecer a conexao
			Connection conexaoBanco = dao.conectar();

			PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);

			// Substituir os pontos de interrogação pelo conteudo das caixas de texto
			// (inputs)
			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getText());
			executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
			executarSQL.setString(5, inputEmail.getText());

			// Executar os comandos SQL e inserir o funcionario no banco de dados
			executarSQL.executeUpdate();

			JOptionPane.showMessageDialog(null, "Usuario cadstrado com sucesso");

			limparCampos();
			conexaoBanco.close();
		}

		catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário");
			limparCampos();

		}

		catch (Exception e) {
			System.out.println(e);

		}

	}

	private void buscarFuncionarioNaTabela() {

		String readTabela = "Select idFuncionario as ID, nomeFunc as Nome, email as Email from funcionario"
				+ " where nomeFunc like ?; ";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);

			// Substituir o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputNome.getText() + "%");

			// Executar o comando SQL e exibir o resultado na tabela

			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Exibir o resultado na tabela, utilização da biblioteca rs2xm1 para "popular"
			// a tabela
			tblFuncionario.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void setarCaixasTexto() {

		// Criar uma variável para receber a linha da tabela
		// int setarLinha = tblFuncionarios.getSelectedRow(executarSQL);

	}

	private void limparCampos() {

		inputNome.setText(null);
		inputLogin.setText(null);
		inputSenha.setText(null);
		// inputPerfil.setSelectedItem(null);
		inputPerfil.setSelectedIndex(-1);
		inputEmail.setText(null);

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
