package br.com.alurahotel.jdbc.modelo;

public class AutenticarSenha {

	
	private int senha;
	
	public void setSenha(int senha) {
		this.senha = senha;		
	}

	
	public boolean autenticar(int senha) {
		
		if(senha == this.senha) {
			return true;
		}			
		return false;
	}

}
