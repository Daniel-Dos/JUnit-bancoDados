/**
 * 
 */
package br.com.ufrj.jdbc.play;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.ufrj.jdbc.dao.ContatoDao;
import br.com.ufrj.jdbc.modelo.Contato;

/**
 * @author Daniel Dias
 *
 */
public class Principal {

	public static void main(String[] args) throws SQLException {

		
		// pronto para gravar
		Contato contato = new Contato();

		contato.setId(new Random().nextInt(10));
		contato.setNome("Daniel");
		contato.setEmail("conta@gamail");
		contato.setEndereco("AAAAA");
		contato.setDataNascimento(LocalDate.now());
		ContatoDao dao = new ContatoDao();
		
		dao.CreateTable();
		dao.adiciona(contato);

		System.out.println("Gravado");

		// listando

		List<Contato> contatos = dao.getLista();

		for (Contato contato1 : contatos) {

			System.out.println("ID :" + contato1.getId());
			System.out.println("Nome :" + contato1.getNome());
			System.out.println("email :" + contato1.getEmail());
			System.out.println("endereco :" + contato1.getEndereco());
			System.out.println("Data :" + contato1.fomatarData(contato1.getDataNascimento()));
		}

		for (int i = 0; i <= 10; i++) {
			Contato contato1 = new Contato();
			contato1.setId(i);
			dao.delete(contato1);
		}
	}
}