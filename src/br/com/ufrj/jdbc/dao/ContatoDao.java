package br.com.ufrj.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ufrj.jdbc.ConnectionFactory;
import br.com.ufrj.jdbc.modelo.Contato;

/**
 * @author Daniel Dias
 *
 */
public class ContatoDao {

	// a conexa com o bd
	private Connection connection;

	public ContatoDao() throws SQLException {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void CreateTable() throws SQLException {

		String sqlCreate = "create table contato " + "(\n" + "id INTEGER NOT NULL,\n" + "nome VARCHAR(255),\n"
				+ "email VARCHAR(255),\n" + "endereco VARCHAR(255),\n" + "dataNascimento DATE,\n" + "primary key (id)\n"
				+ ")";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlCreate);
		preparedStatement.executeQuery();
	}

	public void adiciona(Contato contato) {

		String sql = "insert into contato (id,nome,email,endereco,dataNascimento) values (?,?,?,?,?)";

		try {
			// preparedstatement para inserir
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, contato.getId());
			stmt.setString(2, contato.getNome());
			stmt.setString(3, contato.getEmail());
			stmt.setString(4, contato.getEndereco());
			stmt.setObject(5, contato.getDataNascimento());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Contato contato) {

		String sql = "delete from contato where ID = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, contato.getId());

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// listar

	public List<Contato> getLista() {

		try {
			List<Contato> contatos = new ArrayList<Contato>();

			PreparedStatement stmt = this.connection.prepareStatement("select * from contato");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("Endereco"));

				// monta data
				contato.setDataNascimento(rs.getObject("dataNascimento", LocalDate.class));
				contatos.add(contato);
			}

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}