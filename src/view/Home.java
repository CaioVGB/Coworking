package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.Color;

public class Home extends JDialog {
	public JPanel panelUsuario;
	
	public JLabel txtUsuarioLogado;
	
	public JLabel txtData;
	public JLabel txtPerfilLogado;
	
	public Home() {
		addWindowListener (new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				Date DataSistema = new Date();
				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);
				txtData.setText(formatadorData.format(DataSistema));
			}
		});
		
//WindowAdapter(), WindowEvent, Date IMPORTAR A PARTIR DO java.util.date, DateFormat		
		
		setTitle("Home");
		setBounds(0,0,500,400);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JButton btnUser = new JButton("");
		btnUser.setBackground(new Color(240, 240, 240));
		btnUser.setBorderPainted(false);
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(28, 122, 103, 105);
		getContentPane().add(btnUser);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcionarios = new Funcionarios();
				funcionarios.setVisible(true);
			}
		});
		
		JButton btnRoom = new JButton("");
		btnRoom.setBackground(new Color(240, 240, 240));
		btnRoom.setBorderPainted(false);
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(183, 122, 103, 105);
		getContentPane().add(btnRoom);
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salas salas = new Salas();
				salas.setVisible(true);
			}
		});
		
		JButton btnReserve = new JButton("");
		btnReserve.setBackground(new Color(240, 240, 240));
		btnReserve.setBorderPainted(false);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(360, 122, 103, 105);
		getContentPane().add(btnReserve);
		
		panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 313, 474, 48);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
		
		
		
		
		txtUsuarioLogado = new JLabel("");
		txtUsuarioLogado.setForeground(Color.BLACK);
		txtUsuarioLogado.setBounds(10, 0, 130, 14);
		panelUsuario.add(txtUsuarioLogado);
		
		
		
		txtData = new JLabel("");
		txtData.setBounds(319, 11, 145, 26);
		panelUsuario.add(txtData);
		
		txtPerfilLogado = new JLabel("");
		txtPerfilLogado.setBounds(10, 25, 117, 14);
		panelUsuario.add(txtPerfilLogado);
		
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservas reservas = new Reservas();
				reservas.setVisible(true);
			}
		});
		
	}
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Home dialog = new Home();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	


