package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.Color;

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
		loginFunc.setBounds(10, 205, 39, 14);
		getContentPane().add(loginFunc);

		JLabel senhaFunc = new JLabel("Senha");
		senhaFunc.setBounds(235, 205, 46, 14);
		getContentPane().add(senhaFunc);

		JLabel emailFunc = new JLabel("Email");
		emailFunc.setBounds(235, 247, 39, 14);
		getContentPane().add(emailFunc);

		JLabel perfilFunc = new JLabel("Perfil");
		perfilFunc.setBounds(10, 247, 32, 14);
		getContentPane().add(perfilFunc);

		inputNome = new JTextField();
		inputNome.setBounds(50, 11, 314, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);

		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				buscarFuncionarioNaTabela();
			}
		});

		inputEmail = new JTextField();
		inputEmail.setBounds(280, 244, 137, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);

		inputLogin = new JTextField();
		inputLogin.setBounds(50, 202, 158, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(280, 202, 137, 20);
		getContentPane().add(inputSenha);

		inputPerfil = new JComboBox();
		inputPerfil.setModel(
				new DefaultComboBoxModel(new String[] { "", "Adminstrador", "Gerência", "Atendimento", "Suporte" }));
		inputPerfil.setBounds(50, 243, 158, 22);
		getContentPane().add(inputPerfil);

		JButton btnCreate = new JButton("");
		btnCreate.setBackground(new Color(240, 240, 240));
		btnCreate.setBorderPainted(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		btnCreate.setBounds(181, 290, 89, 60);
		getContentPane().add(btnCreate);

		getContentPane().add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionarios();
			}
		});

		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarFuncionarios();
			}
		});
		btnUpdate.setBackground(new Color(240, 240, 240));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		btnUpdate.setBounds(280, 290, 89, 60);
		getContentPane().add(btnUpdate);

		JButton btnDelete;
		btnDelete = new JButton("");
		btnDelete.setBackground(new Color(240, 240, 240));
		btnDelete.setBorderPainted(false);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		btnDelete.setBounds(374, 290, 89, 60);
		getContentPane().add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarFuncionario();
				
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 27, 314, 97);
		getContentPane().add(scrollPane);

		tblFuncionario = new JTable();
		scrollPane.setViewportView(tblFuncionario);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(240, 240, 240));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(374, 11, 89, 33);
		getContentPane().add(btnPesquisar);
		
		btnPesquisar.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				btnBuscarFuncionario();
			}
		});
		
		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(50, 156, 86, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);
		
		JLabel idFunc = new JLabel("ID");
		idFunc.setBounds(10, 159, 32, 14);
		getContentPane().add(idFunc);
		tblFuncionario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});

	}

	// Criar um objeto da classe DAO para estabelecer conexão com o banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	private JTable tblFuncionario;
	private JTextField inputID;
	public JButton btnDelete;


	public void adicionarFuncionarios() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";
		
		if(inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuario obrigatorio!");
			inputLogin.requestFocus();
		}
		
		else if(inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha ou usuario obrigatoria!");
			inputSenha.requestFocus();
		}
		
		else if(inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do usuario obrigatorio!");
			inputNome.requestFocus();
			
		}
		
		else if(inputEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuario obrigatorio!");
			inputEmail.requestFocus();
			
		}
		
		else {
		
		
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
		
		int setarLinha = tblFuncionario.getSelectedRow();		
		inputNome.setText(tblFuncionario.getModel().getValueAt(setarLinha, 1).toString());
		inputID.setText(tblFuncionario.getModel().getValueAt(setarLinha, 0).toString());


	}
	
	
	//Criar método para buscar funcionário pelo botão Pesquisar
	
	private void btnBuscarFuncionario() {
		String readBtn = "select * from funcionario where nomeFunc = ?;";
				
		try {
			//Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);
			
			//Substituir o ponto de interrogação pelo conteúdo da caixa de texto (nome)
			executarSQL.setString(1, inputNome.getText());
			
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			if  (resultadoExecucao.next()) {
				//Preencher os campos do formulário
				inputLogin.setText(resultadoExecucao.getString(3));
				inputSenha.setText(resultadoExecucao.getString(4));
				inputPerfil.setSelectedItem(resultadoExecucao.getString(5));
				inputEmail.setText(resultadoExecucao.getString(6));
				
				
			}
			
		}
	
		
		catch (Exception e) {
			System.out.println(e);
		}
	
	}

	private void limparCampos() {

		inputNome.setText(null);
		inputLogin.setText(null);
		inputSenha.setText(null);
		// inputPerfil.setSelectedItem(null);
		inputPerfil.setSelectedIndex(-1);
		inputEmail.setText(null);

	}
	
	public void atualizarFuncionarios() {
		
		String updateBtn = "update funcionario set nomeFunc = ?, login = ?, senha = md5(?), perfil = ?, email = ? where idFuncionario = ?;";
		
		if(inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuario obrigatorio!");
			inputLogin.requestFocus();
		}
		
		else if(inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha ou usuario obrigatoria!");
			inputSenha.requestFocus();
		}
		
		else if(inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do usuario obrigatorio!");
			inputNome.requestFocus();
			
		}
		
		else if(inputEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuario obrigatorio!");
			inputEmail.requestFocus();
			
		}
		
		else {
		
		 
				try {
		 
					Connection conexaoBanco = dao.conectar();
		 
					PreparedStatement executarSQL = conexaoBanco.prepareStatement(updateBtn);
		 
					executarSQL.setString(1, inputNome.getText());
					executarSQL.setString(2, inputLogin.getText());
					executarSQL.setString(3, inputSenha.getText());
					executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
					executarSQL.setString(5, inputEmail.getText());
					executarSQL.setString(6, inputID.getText());
		 
					executarSQL.executeUpdate();
		 
					JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso");
		 
					limparCampos();
		 
					conexaoBanco.close();
		 
				} catch (SQLIntegrityConstraintViolationException error) {
					JOptionPane.showMessageDialog(null, "Login e/ou email em uso. \nEscolha novos dados.");
					limparCampos();
		 
				}
		 
				catch (Exception e) {
					System.out.println(e);
		 
				}
		}
		 
			}

	private void deletarFuncionario() {
		String delete = "delete from funcionario where idFuncionario = ?;";
 
		try {
			Connection conexaoBanco = dao.conectar();
 
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(delete);
 
			executarSQL.setString(1, inputID.getText());
 
			executarSQL.executeUpdate();
 
			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso.");
 
			limparCampos();
 
			conexaoBanco.close();
		}
 
		catch (Exception e) {
			System.out.print(e);
 
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
