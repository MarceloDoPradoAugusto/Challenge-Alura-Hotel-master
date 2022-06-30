package br.com.alurahotel.jdbc.modelo;

import java.util.Date;

public class Hospede {

	private Integer id;
	private String nome;
	private String sobrenome;
	private Date data_nascimento;
	private String nacionalidade;
	private long telefone;
	private Integer id_r;
	
	
	
	public Hospede(String nome, String sobrenome, Date date, String nacionalidade, long telefone,
			Integer id_r) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.data_nascimento = date;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.id_r = id_r;
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public Integer getId_r() {
		return id_r;
	}

	public void setId_r(Integer id_r) {
		this.id_r = id_r;
	}

	
	
}
