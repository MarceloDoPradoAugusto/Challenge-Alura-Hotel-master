package br.com.alurahotel.jdbc.modelo;

import java.util.Date;

public class Reserva {

	private Integer id;
	private Date data_entrada;
	private Date data_saida;
	private Double valor;
	private String forma_pagamento;
	
	public Reserva(Date date, Date date2, double valor, String f_pagamento) {	
		this.data_entrada = date;
		this.data_saida = date2;
		this.valor = valor;
		this.forma_pagamento = f_pagamento;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getData_entrada() {
		return data_entrada;
	}


	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}


	public Date getData_saida() {
		return data_saida;
	}


	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public String getForma_pagamento() {
		return forma_pagamento;
	}


	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}


	@Override
	public String toString() {
		return this.id.toString();
	}
}



