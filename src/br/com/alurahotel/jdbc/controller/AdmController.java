package br.com.alurahotel.jdbc.controller;

import java.sql.Connection;
import br.com.alurahotel.jdbc.dao.AdmDAO;
import br.com.alurahotel.jdbc.factory.ConnectionFactory;
import br.com.alurahotel.jdbc.modelo.Adm;

public class AdmController {

	private AdmDAO admDAO;

	public AdmController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.admDAO = new AdmDAO(connection);
	}

	public void deletar(Integer id) {
		this.admDAO.deletar(id);
	}

	public void salvar(Adm adm) {
		this.admDAO.salvar(adm);
	}


	public void alterar(String nome, String senha, Integer id) {
		this.admDAO.alterar(nome, senha, id);
	}
	
	public boolean buscarFuncionario(Adm adm) {
		return this.admDAO.buscarFuncionario(adm);
	}
}
