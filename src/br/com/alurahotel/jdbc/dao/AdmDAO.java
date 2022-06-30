package br.com.alurahotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alurahotel.jdbc.modelo.Adm;

public class AdmDAO {

	private Connection connection;

	public AdmDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Adm adm) {
		try {
			String sql = "INSERT INTO FUNCIONARIOS (NOME, CPF, SENHA) VALUES (?, ?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, adm.getNome());
				pstm.setString(2, adm.getCpf());
				pstm.setString(3, adm.getSenha());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						adm.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
		

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM FUNCIONARIOS WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(String nome, String senha, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE FUNCIONARIOS F SET F.NOME = ?, P.SENHA = ? WHERE ID = ?")) {
			stm.setString(1, nome);
			stm.setString(2, senha);
			stm.setInt(3, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean buscarFuncionario(Adm adm) {
		boolean exist = false;
		try {
			
			String sql = "SELECT CPF, SENHA FROM FUNCIONARIOS WHERE CPF = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {		
				pstm.setString(1, adm.getCpf());
				pstm.execute();
				
				exist = validarSenha(pstm, adm.getSenha());
			}
			return exist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean validarSenha(PreparedStatement pstm, String senha) throws SQLException {
		boolean res = false;
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {	
				boolean teste = rst.getString(2).equalsIgnoreCase(senha);	
				res = teste;
			
				
			}
			return res;
		}
	}
	
	
}
