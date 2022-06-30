package br.com.alurahotel.jdbc.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import br.com.alurahotel.jdbc.controller.ReservasController;
import br.com.alurahotel.jdbc.modelo.Reserva;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Reservas extends JFrame {

	private static final long serialVersionUID = 7767045887526242900L;
	private JPanel contentPane;
	private JTextField Valor;
	private Double valorFinal;
	
	private ReservasController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("../imagens/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		this.reservaController = new ReservasController();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		JDateChooser FechaE = new JDateChooser();
		FechaE.setBounds(88, 166, 235, 33);
		panel.add(FechaE);

		JLabel lblNewLabel_1 = new JLabel("Data de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Data de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		JDateChooser FechaS = new JDateChooser();
		FechaS.setBounds(88, 234, 235, 33);
		FechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(FechaS);
		
		FechaS.getDateEditor().addPropertyChangeListener(
			    (PropertyChangeListener) new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	
			            	if(FechaE.getDate() != null) calcularDiaria(FechaE, FechaS);
			            	
			            }
			        }
					
		});
		FechaE.getDateEditor().addPropertyChangeListener(
			    (PropertyChangeListener) new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	
			            	if(FechaS.getDate() != null) calcularDiaria(FechaE, FechaS);
			            	
			            }
			        }					
		});

		Valor = new JTextField();
		Valor.setBounds(88, 303, 235, 33);
		Valor.setForeground(Color.BLACK);
		Valor.setEnabled(false);
		panel.add(Valor);
		Valor.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Valor da Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);

		JComboBox FormaPago = new JComboBox();
		FormaPago.setBounds(88, 373, 235, 33);
		FormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		FormaPago.setModel(new DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Cartão de Débito", "Boleto" }));
		panel.add(FormaPago);

		JLabel fPagamento = new JLabel("Forma de pagamento");
		fPagamento.setBounds(88, 347, 151, 24);
		fPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(fPagamento);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {	
			if(Valor.getText().length() > 0) {	
				Reserva reserva = new Reserva(FechaE.getDate(), FechaS.getDate(), valorFinal, FormaPago.getSelectedItem().toString());
				salvar(reserva);
				RegistroHospede hospede = new RegistroHospede(reserva.getId());
				hospede.setVisible(true);
				dispose();
			}else {
				Erro erro = new Erro("Preencha todos os campos", 50);
				erro.setVisible(true);
			}
		}
	});
	
	btnReservar.setForeground(Color.WHITE);btnReservar.setBounds(190,436,133,33);btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("../imagens/calendario.png")));btnReservar.setBackground(new Color(65,105,225));btnReservar.setFont(new Font("Arial",Font.PLAIN,14));panel.add(btnReservar);

	JPanel panel_1 = new JPanel();panel_1.setBackground(Color.WHITE);panel_1.setBounds(399,0,491,502);panel.add(panel_1);panel_1.setLayout(null);

	JLabel lblNewLabel = new JLabel(
			"");lblNewLabel.setBounds(0,-16,500,539);panel_1.add(lblNewLabel);lblNewLabel.setBackground(Color.WHITE);lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("../imagens/reservas-img-2.png")));

	JLabel lblNewLabel_2 = new JLabel(
			"");lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("../imagens/Ha-100px.png")));lblNewLabel_2.setBounds(15,6,104,107);panel.add(lblNewLabel_2);

	}

	
	private void calcularDiaria(JDateChooser dataEntrada, JDateChooser dataSaida) {		
		SimpleDateFormat dcn = new SimpleDateFormat("yyyyMMdd");
		int entrada = Integer.parseInt(dcn.format(dataEntrada.getDate()));
		int saida = Integer.parseInt(dcn.format(dataSaida.getDate()));
		valorFinal = (saida - entrada) * 40.00;
		if(valorFinal > 0.0) {
			Valor.setText("R$ " + valorFinal.toString());			
		}else {
			Valor.setText("");
		}
	}
	
	private void salvar(Reserva reserva) {
		if (!Valor.getText().equals("")) {
			this.reservaController.inserir(reserva);
			Erro erro = new Erro("Salvo com sucesso!", 80);
			erro.setVisible(true);
		} else {
			Erro erro = new Erro("Não foi possível finalizar a reserva", 10);
			erro.setVisible(true);
		}
	}
}
