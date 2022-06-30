package br.com.alurahotel.jdbc.modelo;

public class Adm extends Funcionario implements Autenticavel {

	private AutenticarSenha autenticador;

	public Adm(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
		this.autenticador = new AutenticarSenha();
	}

	@Override
	public void setSenha(int senha) {
		this.autenticador.setSenha(senha);
	}

	@Override
	public boolean autenticar(int senha) {
		return this.autenticador.autenticar(senha);
	}
	
	@Override
	public int getId() {
		return super.getId();
	}

}
