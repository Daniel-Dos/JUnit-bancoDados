/**
 * 
 */
package br.com.ufrj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Daniel Dias
 *
 */
public class ConnectionFactory {
	
	public Connection getConnection() {	
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","akatsuki");
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}