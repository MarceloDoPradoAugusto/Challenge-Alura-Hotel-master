package br.com.alurahotel.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alurahotel.jdbc.dao.ReservaDAO;
import br.com.alurahotel.jdbc.factory.ConnectionFactory;
import br.com.alurahotel.jdbc.modelo.Reserva;

public class ReservasController {
	
	private ReservaDAO reservaDAO;
	
	public ReservasController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	
	public List<Reserva> listarReservas() {
		return this.reservaDAO.listar();
	}
	
	public void inserir(Reserva reserva) {
		this.reservaDAO.inserir(reserva);
	}
	
	
	public void alterar(Reserva reserva) {
		this.reservaDAO.alterar(reserva);
	}
	
	public void deletar(int id) {
		this.reservaDAO.deletar(id);
	}
	
	public Reserva buscarUm(int id) {
		return this.reservaDAO.buscarUm(id);
	}
}

