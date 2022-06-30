package br.com.alurahotel.jdbc.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.alurahotel.jdbc.controller.HospedeController;
import br.com.alurahotel.jdbc.controller.ReservasController;
import br.com.alurahotel.jdbc.modelo.Hospede;
import br.com.alurahotel.jdbc.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class Buscar extends JFrame {

	private static final long serialVersionUID = 418986363166304048L;
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;

	private DefaultTableModel modeloHospede;
	private DefaultTableModel modeloReserva;

	private HospedeController hospedeController;
	private ReservasController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("../imagens/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		this.hospedeController = new HospedeController();
		this.reservaController = new ReservasController();

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Busca");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 212, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/encerrar-sessao-32-px.png")));
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.WHITE);
		btnSair.setBounds(815, 416, 54, 41);
		contentPane.add(btnSair);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);

		tbHospedes = new JTable();
		tbHospedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("../imagens/pessoa.png")), tbHospedes, null);
		modeloHospede = (DefaultTableModel) tbHospedes.getModel();

		modeloHospede.addColumn("Id");
		modeloHospede.addColumn("Nome");
		modeloHospede.addColumn("Sobrenome");
		modeloHospede.addColumn("data_nascimento");
		modeloHospede.addColumn("nacionalidade");
		modeloHospede.addColumn("telefone");
		modeloHospede.addColumn("Id Reserva");

		tbReservas = new JTable();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("../imagens/calendario.png")), tbReservas,
				null);

		modeloReserva = (DefaultTableModel) tbReservas.getModel();

		modeloReserva.addColumn("Id");
		modeloReserva.addColumn("data de entrada");
		modeloReserva.addColumn("data de saida");
		modeloReserva.addColumn("valor");
		modeloReserva.addColumn("forma de pagamento");
		modeloReserva.addColumn("telefone");

		preencherHospedes();
		preencherReservas();

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				if (txtBuscar.getText().length() == 0) {
					preencher(panel);
				} else {

					try {
						id = Integer.parseInt(txtBuscar.getText());
						buscar(panel, id);
					} catch (Exception e2) {
						Erro erro = new Erro("Insira um Id valido", 80);
						erro.setVisible(true);
					}
				}
			}
		});

		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/deletar.png")));
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(651, 416, 54, 41);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar(panel);
			}
		});

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alterar(panel);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("../imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);			
		setResizable(false);
	}

	private void preencher(JTabbedPane panel) {
		if (panel.getSelectedIndex() == 0)
			tbHospedes.getSelectionModel().clearSelection();
			limparTabela(tbHospedes);
			preencherHospedes();
		if (panel.getSelectedIndex() == 1)
			tbReservas.getSelectionModel().clearSelection();
			limparTabela(tbReservas);
			preencherReservas();
	}

	private void alterar(JTabbedPane panel) throws ParseException {
		if (panel.getSelectedIndex() == 0)
			alterarHospedes();
		if (panel.getSelectedIndex() == 1)
			alterarReserva();

	}

	private void deletar(JTabbedPane panel) {
		if (panel.getSelectedIndex() == 0)
			deletarHospede();
		if (panel.getSelectedIndex() == 1)
			deletarReserva();
	}

	private void buscar(JTabbedPane panel, int id) {
		if (panel.getSelectedIndex() == 0)
			buscarHospede(id);
		if (panel.getSelectedIndex() == 1)
			buscarReserva(id);
	}

	private void buscarReserva(int id) {
		limparTabela(tbReservas);
		buscarUmaReserva(id);
	}

	private void buscarHospede(int id) {
		limparTabela(tbHospedes);
		buscarUmHospede(id);

	}

	private void limparTabela(JTable tabela) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

//	private void removerLinha(JTable tabela, int linha) {
//		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
//		model.removeRow(linha);
//	}

	private void alterarReserva() throws ParseException {
		if (tbReservas.getSelectedRow() > -1) {
			Object objetoDaLinha = (Object) modeloReserva.getValueAt(tbReservas.getSelectedRow(),
					tbReservas.getSelectedColumn());
			if (objetoDaLinha instanceof Integer) {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Integer id = (Integer) objetoDaLinha;
				Date entrada = formato.parse(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1).toString());
				Date saida = formato.parse(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2).toString());
				@SuppressWarnings("deprecation")
				Double valor = (double) (saida.getDate() - entrada.getDate()) * 40;
				String pagamento = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);
				Reserva reserva = new Reserva(entrada, saida, valor, pagamento);
				reserva.setId(id);
				this.reservaController.alterar(reserva);
				preencherReservas();
				Erro erro = new Erro("Reserva alterada com sucesso", 50);
				erro.setVisible(true);
			} else {
				Erro erro = new Erro("Por favor, selecione o ID", 60);
				erro.setVisible(true);
			}
		} else {
			Erro erro = new Erro("Por favor, selecione alguma reserva", 20);
			erro.setVisible(true);
		}

	}

	private void alterarHospedes() throws ParseException {
		if (tbHospedes.getSelectedRow() > -1) {
			Object objetoDaLinha = (Object) modeloHospede.getValueAt(tbHospedes.getSelectedRow(),
					tbHospedes.getSelectedColumn());
			if (objetoDaLinha instanceof Integer) {
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Integer id = (Integer) objetoDaLinha;
				String nome = (String) modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 1);
				String sobrenome = (String) modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 2);
				Date dataN = formato.parse(modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
				String nacionalidade = (String) modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 4);
				Long telefone = Long.parseLong(modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 5).toString());
				Integer id_r = (Integer) modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 6);
				Hospede hospede = new Hospede(nome, sobrenome, dataN, nacionalidade, telefone, id_r);
				hospede.setId(id);
				this.hospedeController.alterar(hospede);
				preencherHospedes();
				Erro erro = new Erro("Hospede alterado com sucesso", 50);
				erro.setVisible(true);
			} else {
				Erro erro = new Erro("Por favor, selecione o ID", 60);
				erro.setVisible(true);
			}
		} else {
			Erro erro = new Erro("Por favor, selecione algum hospede", 20);
			erro.setVisible(true);
		}

	}

	private void deletarHospede() {
		if (tbHospedes.getSelectedRow() > -1) {
			Object objetoDaLinha = (Object) modeloHospede.getValueAt(tbHospedes.getSelectedRow(),
					tbHospedes.getSelectedColumn());
			if (objetoDaLinha instanceof Integer) {
				Integer id = (Integer) objetoDaLinha;
				Integer id_r = (Integer) modeloHospede.getValueAt(tbHospedes.getSelectedRow(), 6);	
				tbHospedes.requestFocus();
				tbHospedes.editCellAt(0,0);
				tbHospedes.changeSelection(0, 0, false, false);
				this.hospedeController.deletar(id, id_r);
				preencherHospedes();
				preencherReservas();
				Erro erro = new Erro("Hospede Deletado com sucesso!", 50);
				erro.setVisible(true);
			} else {
				Erro erro = new Erro("Por favor, selecione o ID", 60);
				erro.setVisible(true);
			}
		} else {
			Erro erro = new Erro("Por favor, selecione algum hospede", 20);
			erro.setVisible(true);
		}

	}

	private void deletarReserva() {
		if (tbReservas.getSelectedRow() > -1) {
			Object objetoDaLinha = (Object) modeloReserva.getValueAt(tbReservas.getSelectedRow(),
					tbReservas.getSelectedColumn());
			if (objetoDaLinha instanceof Integer) {
				Integer id = (Integer) objetoDaLinha;
				tbReservas.requestFocus();
				tbReservas.editCellAt(0,0);
				tbReservas.changeSelection(0, 0, false, false);
				this.reservaController.deletar(id);
				preencherHospedes();
				preencherReservas();	
				Erro erro = new Erro("Reserva deletada com sucesso!", 50);
				erro.setVisible(true);
			} else {
				Erro erro = new Erro("Por favor, selecione o ID", 60);
				erro.setVisible(true);
			}
		} else {
			Erro erro = new Erro("Por favor, selecione alguma reserva", 20);
			erro.setVisible(true);
		}

	}

	private void preencherHospedes() {
		limparTabela(tbHospedes);
		List<Hospede> hospedes = hospedeController.listarHospedes();
		try {
			for (Hospede hospede : hospedes) {
				modeloHospede.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
						hospede.getData_nascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
						hospede.getId_r() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void buscarUmHospede(int id) {
		limparTabela(tbHospedes);
		List<Hospede> hospedes = hospedeController.listarHospedes();
		try {
			for (Hospede hospede : hospedes) {
				if (hospede.getId() == id) {
					modeloHospede.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
							hospede.getData_nascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
							hospede.getId_r() });
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void preencherReservas() {
		limparTabela(tbReservas);
		List<Reserva> reservas = reservaController.listarReservas();
		try {
			for (Reserva reserva : reservas) {
				modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getData_entrada(), reserva.getData_saida(),
						reserva.getValor(), reserva.getForma_pagamento() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void buscarUmaReserva(int id) {
		limparTabela(tbReservas);
		List<Reserva> reservas = reservaController.listarReservas();
		try {
			for (Reserva reserva : reservas) {
				if (reserva.getId() == id) {
					modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getData_entrada(),
							reserva.getData_saida(), reserva.getValor(), reserva.getForma_pagamento() });
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
