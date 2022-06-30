package br.com.alurahotel.jdbc.modelo;

public abstract class Funcionario {	
	
	protected String nome;
	protected String cpf;
	protected String senha;
	private int id;
	
	
	public String getNome() {
		return this.nome;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
		

}
