package br.com.ufrj.jdbc.modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;

/**
 * @author Daniel Dias
 *
 */
public class Contato {

	private int id;
	private String nome;
	private String email;
	private String endereco;
	private LocalDate dataNascimento;

	// m�todos get e set para id, nome, email, endere�o e dataNascimento
	public String getNome() {
		return this.nome;
	}

	public void setNome(String novo) {
		this.nome = novo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String novo) {
		this.email = novo;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String novo) {
		this.endereco = novo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int novo) {
		this.id = novo;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String fomatarData(LocalDate da) {
		String fo = da.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return fo;
		
	}
}
