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
import javax.swing.SwingConstants;

public class Salas extends JDialog {
	private JTextField inputOcup;
	private JPasswordField passwordField;

	public Salas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salas.class.getResource("/img/logo.png")));
		setTitle("Salas");
		setBounds(0, 0, 500, 400);
		getContentPane().setLayout(null);

		JLabel tipoSala = new JLabel("Categoria:");
		tipoSala.setHorizontalAlignment(SwingConstants.CENTER);
		tipoSala.setBounds(10, 14, 73, 14);
		getContentPane().add(tipoSala);

		JLabel codSala = new JLabel("Código");
		codSala.setBounds(10, 205, 39, 14);
		getContentPane().add(codSala);

		JLabel andarSala = new JLabel("Andar");
		andarSala.setBounds(235, 205, 46, 14);
		getContentPane().add(andarSala);

		JLabel ocupSala = new JLabel("Ocupação:");
		ocupSala.setBounds(235, 247, 39, 14);
		getContentPane().add(ocupSala);

		JLabel numSala = new JLabel("Número");
		numSala.setBounds(10, 247, 39, 14);
		getContentPane().add(numSala);

		inputOcup = new JTextField();
		inputOcup.setBounds(280, 244, 137, 20);
		getContentPane().add(inputOcup);
		inputOcup.setColumns(10);

		JButton btnCreate = new JButton("");
		btnCreate.setBackground(new Color(240, 240, 240));
		btnCreate.setBorderPainted(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));
		btnCreate.setBounds(181, 290, 89, 60);
		getContentPane().add(btnCreate);

		getContentPane().add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adicionarSalas();
			}
		});

		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//atualizarSalas();
			}
		});
		btnUpdate.setBackground(new Color(240, 240, 240));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		btnUpdate.setBounds(280, 290, 89, 60);
		getContentPane().add(btnUpdate);

		JButton btnDelete;
		btnDelete = new JButton("");
		btnDelete.setBackground(new Color(240, 240, 240));
		btnDelete.setBorderPainted(false);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		btnDelete.setBounds(374, 290, 89, 60);
		getContentPane().add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deletarFuncionario();
				
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 39, 291, 97);
		getContentPane().add(scrollPane);

		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(240, 240, 240));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(374, 11, 89, 33);
		getContentPane().add(btnPesquisar);
		
		btnPesquisar.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				//btnBuscarFuncionario();
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
		
		JComboBox inputCategoria = new JComboBox();
		inputCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Sala de Reunião", "Sala de Conferencia", "Espaço de Eventos", "Escritório Privado"}));
		inputCategoria.setBounds(78, 10, 263, 22);
		getContentPane().add(inputCategoria);
		
		JComboBox inputCod = new JComboBox();
		inputCod.setModel(new DefaultComboBoxModel(new String[] {"", "REU", "CONF", "EVENT", "PRIV"}));
		inputCod.setBounds(50, 201, 158, 22);
		getContentPane().add(inputCod);
		
		JComboBox inputAndar = new JComboBox();
		inputAndar.setModel(new DefaultComboBoxModel(new String[] {"", "Subsolo", "Térreo", "1° andar", "2° andar", "3° andar"}));
		inputAndar.setBounds(280, 201, 137, 22);
		getContentPane().add(inputAndar);
		
		inputNum = new JTextField();
		inputNum.setBounds(50, 244, 158, 20);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);
		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//setarCaixasTexto();
			}
		});

	}

	// Criar um objeto da classe DAO para estabelecer conexão com o banco
	DAO dao = new DAO();
	private JTable tblSalas;
	private JTextField inputID;
	public JButton btnDelete;
	private JTextField inputNum;


	/*public void adicionarSalas() {
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
		
		else if(inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuario obrigatorio!");
			inputOcup.requestFocus();
			
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
			executarSQL.setString(4, input.getSelectedItem().toString());
			executarSQL.setString(5, inputOcup.getText());

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

	private void setarCaixasTexto() {

		// Criar uma variável para receber a linha da tabela
		
		int setarLinha = tblSalas.getSelectedRow();		
		inputNome.setText(tblSalas.getModel().getValueAt(setarLinha, 1).toString());
		inputID.setText(tblSalas.getModel().getValueAt(setarLinha, 0).toString());


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
				input.setSelectedItem(resultadoExecucao.getString(5));
				inputOcup.setText(resultadoExecucao.getString(6));
				
				
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
		input.setSelectedIndex(-1);
		inputOcup.setText(null);

	}
	
	public void atualizarSalas() {
		
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
		
		else if(inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuario obrigatorio!");
			inputOcup.requestFocus();
			
		}
		
		else {
		
		 
				try {
		 
					Connection conexaoBanco = dao.conectar();
		 
					PreparedStatement executarSQL = conexaoBanco.prepareStatement(updateBtn);
		 
					executarSQL.setString(1, inputNome.getText());
					executarSQL.setString(2, inputLogin.getText());
					executarSQL.setString(3, inputSenha.getText());
					executarSQL.setString(4, input.getSelectedItem().toString());
					executarSQL.setString(5, inputOcup.getText());
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
	*/
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
