package br.com.alurahotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alurahotel.jdbc.controller.HospedeController;
import br.com.alurahotel.jdbc.modelo.Hospede;
import br.com.alurahotel.jdbc.modelo.Reserva;

public class ReservaDAO {
	private Connection connection;
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Reserva> listar() {
		try {
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM RESERVAS";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reserva reserva = new Reserva(rst.getDate(2), rst.getDate(3),rst.getDouble(4),  rst.getString(5));
						reserva.setId(rst.getInt(1));
						reservas.add(reserva);
					}
				}
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void inserir(Reserva reserva) {
		try {
			String sql = "INSERT INTO RESERVAS (DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES (?,?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				java.sql.Date entrada = new java.sql.Date(reserva.getData_entrada().getTime() );
				java.sql.Date saida = new java.sql.Date(reserva.getData_saida().getTime() );
				
				pstm.setDate(1, entrada);
				pstm.setDate(2, saida);
				pstm.setDouble(3, reserva.getValor());
				pstm.setString(4, reserva.getForma_pagamento());
				

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void deletar(Integer id) {
		HospedeController hospedeController = new HospedeController();
		Hospede hospede = hospedeController.buscarUm(id);
		if(hospede != null) {
			hospedeController.deletarUm(id);
		}
		
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void alterar(Reserva reserva) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE RESERVAS R SET R.DATA_ENTRADA = ?, R.DATA_SAIDA = ?, R.VALOR = ?,"
						+ "R.FORMA_PAGAMENTO = ? WHERE ID = ?")) {
			java.sql.Date entrada = new java.sql.Date(reserva.getData_entrada().getTime() );
			java.sql.Date saida = new java.sql.Date(reserva.getData_saida().getTime() );
			stm.setDate(1, entrada);
			stm.setDate(2, saida);
			stm.setDouble(3, reserva.getValor());
			stm.setString(4, reserva.getForma_pagamento());
			stm.setInt(5, reserva.getId());			
			
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Reserva buscarUm(int id) {
		try {			
			String sql = "SELECT * FROM RESERVAS WHERE ID = " + id;
			Reserva reserva = null;
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						reserva = new Reserva(rst.getDate(2), rst.getDate(3),
								rst.getDouble(4),  rst.getString(5));						
						
					}
				}
			}
			return reserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
}


