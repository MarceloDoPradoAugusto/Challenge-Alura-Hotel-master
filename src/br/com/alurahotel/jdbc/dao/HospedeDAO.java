package br.com.alurahotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alurahotel.jdbc.controller.ReservasController;
import br.com.alurahotel.jdbc.modelo.Hospede;
import br.com.alurahotel.jdbc.modelo.Reserva;

public class HospedeDAO {

	private Connection connection;
	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Hospede> listar() {
		try {
			List<Hospede> hospedes = new ArrayList<>();
			String sql = "SELECT * FROM HOSPEDES";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Hospede hospede = new Hospede(rst.getString(2), rst.getString(3), rst.getDate(4),
								rst.getString(5), rst.getLong(6), rst.getInt(7));
						hospede.setId(Integer.parseInt(rst.getString(1)));

						hospedes.add(hospede);

					}
				}
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void inserir(Hospede hospede) {
		try {
			String sql = "INSERT INTO HOSPEDES (NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, ID_R) VALUES (?,?,?,?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				java.sql.Date data_nascimento = new java.sql.Date(hospede.getData_nascimento().getTime());

				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setDate(3, data_nascimento);
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setLong(5, hospede.getTelefone());
				pstm.setInt(6, hospede.getId_r());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void deletar(int id, int idReserva) {

		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
			ReservasController reservaController = new ReservasController();
			Reserva reserva = reservaController.buscarUm(idReserva);
			if (reserva != null && idReserva != 0) {
				reservaController.deletar(idReserva);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletarUm(int id) {

		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE ID_R = ?")) {
			stm.setInt(1, id);
			stm.execute();	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Hospede hospede) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE HOSPEDES H SET H.NOME = ?, H.SOBRENOME = ?, H.DATA_NASCIMENTO = ?,"
						+ "H.NACIONALIDADE = ?, H.TELEFONE = ?, H.ID_R = ? WHERE ID = ?")) {
			java.sql.Date nascimento = new java.sql.Date(hospede.getData_nascimento().getTime());
			stm.setString(1, hospede.getNome());
			stm.setString(2, hospede.getSobrenome());
			stm.setDate(3, nascimento);
			stm.setString(4, hospede.getNacionalidade());
			stm.setLong(5, hospede.getTelefone());
			stm.setInt(6, hospede.getId_r());
			stm.setInt(7, hospede.getId());
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Hospede buscarUm(int id) {
		try {
			String sql = "SELECT * FROM HOSPEDES WHERE ID_R = " + id;
			Hospede hospede = null;
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						hospede = new Hospede(rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5),
								rst.getLong(6), rst.getInt(7));
						hospede.setId(Integer.parseInt(rst.getString(1)));

					}
				}
			}
			return hospede;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
