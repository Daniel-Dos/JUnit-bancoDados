/**
 * 
 */
package br.com.ufrj.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.ufrj.jdbc.ConnectionFactory;
import br.com.ufrj.jdbc.dao.ContatoDao;
import br.com.ufrj.jdbc.modelo.Contato;

/**
 * @author daniel
 *
 */
class TestBanco {

	static Connection connection;
	static ContatoDao contatoDAO;
	Contato contato;

	@BeforeAll  // È executado antes de todos os testes
	static void testConnection() throws SQLException {
		connection = new ConnectionFactory().getConnection();
		contatoDAO = new ContatoDao();
	}

	@AfterEach  // Executa DEPOIS da CADA teste, a cada teste executado ele deleta a tabela.
	void testDropTable() throws SQLException {
		String sqlDrop = "drop table contato";
		PreparedStatement ps = connection.prepareStatement(sqlDrop);
		ps.executeQuery();
	}

	@BeforeEach  // Executa ANTES da CADA teste, a cada teste executado ele cria a tabela.
	 void testCreateTable() throws SQLException {
		String sqlCreate = "create table contato "
				+ "(\n" + "id INTEGER NOT NULL,\n" 
				+ "nome VARCHAR(255),\n"
				+ "email VARCHAR(255),\n" 
				+ "endereco VARCHAR(255),\n" 
				+ "dataNascimento DATE,\n" + 
				"primary key (id)\n" + ")";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlCreate);
		preparedStatement.executeQuery();

	}

	@Test
	void testInsert() {

		contato = new Contato();
		assertNotNull(contato); // verifica se nao é NULO

		contato.setId(new Random().nextInt(10));
		contato.setDataNascimento(LocalDate.now());
		contato.setEmail("abc@gmail.com");
		contato.setNome("Daniel");

		assertEquals(contatoDAO != null, true);
		contatoDAO.adiciona(contato);

		List<Contato> listContato = contatoDAO.getLista();
		Contato newContato = listContato.get(0);	
		
		 // Verifica se o conteudo original é igual do atual
		assertEquals(contato.getNome(), newContato.getNome());

	}

	@Test
	void testFindAll() {
		
		contato = new Contato();
		assertNotNull(contato);

		contato.setId(new Random().nextInt(10));
		contato.setDataNascimento(LocalDate.now());
		contato.setEmail("abc@gmail.com");
		contato.setNome("Daniel");
		
		contatoDAO.adiciona(contato);
		
		// Recupera todos da Lista 
		List<Contato> listContato = contatoDAO.getLista();
		
		assertNotNull(listContato);
		assertEquals(1, listContato.size());
		
		// Pego o primeiro elemento da minha lista
		Contato newContato = listContato.get(0);
		
		assertAll("newContato",
				() -> assertEquals("Daniel", newContato.getNome()),
				() -> assertEquals("abc@gmail.com", newContato.getEmail()),
				() -> assertEquals(contato.getDataNascimento(), newContato.getDataNascimento())
			);
	}
	
	@Test
	void testDeleteContato() {
		
		contato = new Contato();
		assertNotNull(contato);

		contato.setId(new Random().nextInt(10));
		contato.setDataNascimento(LocalDate.now());
		contato.setEmail("abc@gmail.com");
		contato.setNome("Daniel");
		
		contatoDAO.adiciona(contato);
		
		
		List<Contato> listContato = contatoDAO.getLista();
		Contato newContato = listContato.get(0);

		assertEquals(contato.getId(), newContato.getId());
		
		contatoDAO.delete(newContato);
		
		List<Contato> listaContatoNovo = contatoDAO.getLista();
		assertEquals(0, listaContatoNovo.size());
	}
}