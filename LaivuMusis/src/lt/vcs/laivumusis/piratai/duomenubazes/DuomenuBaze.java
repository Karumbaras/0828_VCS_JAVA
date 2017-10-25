package lt.vcs.laivumusis.piratai.duomenubazes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DuomenuBaze {
	private String keliasIkiBazes;
	private Connection connection = null;
	private Statement statement;

	public DuomenuBaze(String keliasIkiBazes) {
		this.keliasIkiBazes = keliasIkiBazes;
		try {
			Prisijungimas();
		} catch (ClassNotFoundException e) {
			System.out.println("Nepavyko prisijungti prie duomenu bazes!");
		}
	}

	private synchronized void Prisijungimas() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + keliasIkiBazes);
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}  
	}

	public synchronized boolean registruokZaideja(String zaidejoId) {
		try {
			Prisijungimas();
			if (tikrinkArYraToksZaidejas(zaidejoId)) {
				statement = connection.createStatement();
				String update = "UPDATE zaidejai SET paskutine_data = datetime() WHERE vardas = '" + zaidejoId + "'";
				statement.executeUpdate(update);
				return true;
			} else {
				statement = connection.createStatement();
				String insert = "INSERT INTO zaidejai(vardas,paskutine_data) values('" + zaidejoId + "',datetime())";
				statement.executeUpdate(insert);
				return true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Nepavyko uzregistruoti zaidejo!");
			e.printStackTrace();
			return false;
		} finally {
			closeDatabase();
		}

	}

	private synchronized boolean tikrinkArYraToksZaidejas(String zaidejoId) throws SQLException {

		statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM zaidejai WHERE vardas = '" + zaidejoId + "'");
		// tikrinu ar yra bent vienas irasas
		if (!result.next()) {
			return false;
		}
		return true;

	}

	private synchronized void closeDatabase() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// connection close failed.
			System.err.println(e);
		}

	}

}
