package br.com.alurahotel.jdbc.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.alurahotel.jdbc.controller.AdmController;
import br.com.alurahotel.jdbc.modelo.Adm;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 3529527505425606586L;
	private JPanel contentPane;
	private JTextField txtusuario;
	private JPasswordField txtSenha;
	
	private AdmController admController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("../imagens/perfil-do-usuario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		this.admController = new AdmController();
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("../imagens/hotel.png")));
		lblNewLabel.setBounds(-53, 0, 422, 499);
		contentPane.add(lblNewLabel);
		
		txtusuario = new JTextField();
		txtusuario.setColumns(10);
		txtusuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtusuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Usuario (CPF)");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(409, 156, 100, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(409, 261, 234, 33);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(409, 236, 133, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("../imagens/perfil-do-usuario.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				
				@SuppressWarnings("deprecation")
				Adm adm = new Adm(txtusuario.getText(), txtSenha.getText());
				boolean validar = admController.buscarFuncionario(adm);
				if(validar) {					
					usuario.setVisible(true);
					dispose();					
				}else {					
					Erro erro = new Erro("Nome de usuário ou senha inválidos", 20);
					erro.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(409, 322, 109, 33);
		contentPane.add(btnLogin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object [] opcao ={"Sim","Cancelar"};
				 int escolha = JOptionPane.showOptionDialog(rootPane,"Deseja Sair da tela de login? ","Mensagem de confirmação",
				 JOptionPane.YES_NO_OPTION,
				 JOptionPane.QUESTION_MESSAGE,null,opcao,"Aceitar");
				 if (escolha == JOptionPane.YES_OPTION)
				 {
				 System.exit(0);
				 }else{
				 }
			}
		});
		
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("../imagens/cerrar-24px.png")));
		btnCancelar.setBounds(528, 322, 115, 33);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("../imagens/Ha-100px.png")));
		lblNewLabel_1.setBounds(470, 30, 103, 94);
		contentPane.add(lblNewLabel_1);
	}
}
