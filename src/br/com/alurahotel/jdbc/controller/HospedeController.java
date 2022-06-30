package br.com.alurahotel.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alurahotel.jdbc.dao.HospedeDAO;
import br.com.alurahotel.jdbc.factory.ConnectionFactory;
import br.com.alurahotel.jdbc.modelo.Hospede;

public class HospedeController {

private HospedeDAO hospedeDAO;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedeDAO = new HospedeDAO(connection);
	}
	
		
	public void inserir(Hospede hospede) {
		this.hospedeDAO.inserir(hospede);
	}


	public List<Hospede> listarHospedes() {
		return this.hospedeDAO.listar();
	}
	
	public void alterar(Hospede hospede) {
		this.hospedeDAO.alterar(hospede);
	}
	
	public void deletar(int id, int idReserva) {
		this.hospedeDAO.deletar(id, idReserva);
	}
	
	public void deletarUm(int id) {
		this.hospedeDAO.deletarUm(id);
	}
	
	
	public Hospede buscarUm(int id) {
		return this.hospedeDAO.buscarUm(id);
	}
	
}
