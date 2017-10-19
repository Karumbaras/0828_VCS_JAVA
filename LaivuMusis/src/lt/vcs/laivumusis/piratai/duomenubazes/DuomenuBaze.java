package lt.vcs.laivumusis.piratai.duomenubazes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DuomenuBaze {
	private String keliasIkiBazes;
	private Connection connection = null;
	private PreparedStatement statement;

	public DuomenuBaze(String keliasIkiBazes) {
		this.keliasIkiBazes = keliasIkiBazes;
		System.out.println("aaa");
		try {
		Prisijungimas();
		System.out.println("aaa");
		} catch (ClassNotFoundException e) {
			System.out.println("Nepavyko prisijungti prie duomenu bazes!");
		}
	}

	private void Prisijungimas() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		System.out.println("a");
		try {
			System.out.println("b");
			connection = DriverManager.getConnection("jdbc:sqlite:" + keliasIkiBazes);
			//connection = DriverManager.getConnection("jdbc:sqlite:C:/LaivuMusisLina.db");
			RegistruokZaideja("Lina");


		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
	}
	
	public boolean RegistruokZaideja(String zaidejoId) {
		try {
		if(tikrinkArYraToksZaidejas(zaidejoId)) {		
			statement = connection.prepareStatement("UPDATE zaidejai SET paskutine_data = datetime() WHERE vardas = ?");		
			statement.setString(1, zaidejoId);
			statement.executeQuery();
			return true;
		} else {
			statement = connection.prepareStatement("INSERT INTO zaidejai(vardas,paskutine_data) values(?,datetime())");		
			statement.setString(1, zaidejoId);
			statement.executeQuery();
			return true;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Nepavyko uzregistruoti zaidejo!");
			e.printStackTrace();
			return false;
		}
		
	}
	private boolean tikrinkArYraToksZaidejas(String zaidejoId) throws SQLException{
	
		 statement = connection.prepareStatement("SELECT * FROM zaidejai WHERE vardas = ?");
		 statement.setString(1, zaidejoId);
		 System.out.println("viduje");
		 ResultSet result = statement.executeQuery();
		 //tikrinu ar yra bent vienas irasas
		if (result.next() == false) {
			System.out.println("tikrinu");
			return false;
		}
		return true;

	}
	
	
}
