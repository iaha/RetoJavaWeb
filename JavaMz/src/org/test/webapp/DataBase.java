package org.test.webapp;

import java.sql.*;
import org.mariadb.jdbc.Driver;

@SuppressWarnings("unused")
public class DataBase {
	private String url = "jdbc:mysql://localhost:3306/test";
	private String uname = "root";
	private String pass = "12345";

	private Connection openConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, uname, pass);
		return connection;
	}
	
	private String getFromDB(String sql) {
		String data = null;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = openConnection();
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			data = result.getString(2);
			System.out.println("The result data is: " + data);
			statement.close();
			connection.close();
		} catch (Exception err) {
			System.err.println("Error: " + err);
			System.out.println("Error al obtener resultado desde la BD!");
		}/* finally {
			statement.close();
			connection.close();
		}*/
		return data;
	}

	private int setToDB(String sql, Matrix matrix) {
		int count = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = openConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(2, matrix.getSize());
			statement.setString(3, matrix.getData());
			statement.setString(4, matrix.getResultado());
			statement.setString(5, matrix.getOperation());
			System.out.println("El statement seria: " + statement);
			count = statement.executeUpdate();

			statement.close();
			connection.commit();
			connection.close();
		} catch (Exception err) {
			System.err.println("Error: " + err);
			System.out.println("Error al tratar de guardar el resultado en la BD!");
		} finally {
			//statement.close();
			//connection.close();
		}
		return count;
	}

	public String getLog() {
		return getFromDB("SELECT * FROM log");
	}

	public int setLog(Matrix matrix) {
		String query = "INSERT INTO log (size, matrix_a, matrix_b, operation) VALUES (?,?,?,?)";
		return setToDB(query, matrix);
	}

}
