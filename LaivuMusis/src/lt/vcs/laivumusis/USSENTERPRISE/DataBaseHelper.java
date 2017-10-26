package lt.vcs.laivumusis.USSENTERPRISE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Laivas;

public class DataBaseHelper {
	//zaidejui
	private String zaidejas;
	private String vardas;
	private int konfiguracijosID;
	
	//konfiguracija
	private int konfigId ;
	private int zemelapioId ;
	private int lavyno_id;
	private String x_koordinate;
	private long y_koordinate;
	private int laivo_id;
	private long skaiciusLaivu;
	private long laivoIlgis;
	//laivu listas
	private List<Laivas> laivuListas = new ArrayList<Laivas>();
	//zaidimo lenta
	private Map<String, List<Langelis>> zaidimoLentele = new HashMap<String, List<Langelis>>();
	private List<List<Laivas>> visiLaivai = new ArrayList<List<Laivas>>();
	
	
	public DataBaseHelper(String zaidejoId) {
		this.zaidejas = zaidejoId;
	}
	public DataBaseHelper(int kofig) {
		this.konfiguracijosID = kofig;
	}
	public DataBaseHelper(String zaidejoId, int kofig) {
		this(zaidejoId);
		this.konfiguracijosID = kofig;
	}
	
	private Connection CreateConnection() throws SQLException, ClassNotFoundException{
		
		Connection dbconnection = null;
		Class.forName("org.sqlite.JDBC");
		
		dbconnection = DriverManager.getConnection("jdbc:sqlite:C/Users/User/Desktop/Programavimas/Java mokymai/Git/0828_VCS_JAVA/LaivuMusis/LaivuMusis.db");
		return dbconnection;

	}
	
	//insertinam ziadjea
	public void insertZaidejas () throws SQLException, ClassNotFoundException  {
		
		Connection dbConnection = null;
		PreparedStatement prepared = null;
		String sql = "INSERT INTO zaidejai (vardas,paskutine_data) VALUES (?, datetime())";
		try {
			
			dbConnection = CreateConnection();
			if(!selectZaidejas(dbConnection))
				return;
			prepared = dbConnection.prepareStatement(sql);
			prepared.setString(1, zaidejas);
			prepared.executeUpdate();
			System.out.println("Zaidejas irasytas!");
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
		finally {
			if(prepared != null)
				prepared.close();

			if(dbConnection != null)
				dbConnection.close();	
		}
	
	}
	
	//pasirenkam zaideja
	private boolean selectZaidejas (Connection dbConnection) throws SQLException {
			String sql = "select * from zaidejai where vardas = ?";
			PreparedStatement prepared = dbConnection.prepareStatement(sql);
			prepared.setString(1, zaidejas);
			ResultSet rs = prepared.executeQuery();
			while(rs.next()) {
				this.vardas = rs.getString("vardas");
				if(this.vardas.equals(zaidejas)) {
					String updateSql = "update zaidejai set paskutine_data = datetime() where vardas = ?";
					PreparedStatement preparedUpdate = dbConnection.prepareStatement(updateSql);
					preparedUpdate.setString(1, zaidejas);
					preparedUpdate .executeUpdate();
					return false;
				}
			}
		return true;
	}
	
	//Laivu sukurimas
	public void selectLaivusFromKonfig() throws SQLException, ClassNotFoundException{
		Connection dbConnection = null;
		PreparedStatement prepared = null;
		String sql = "SELECT kofiguracijos.id, kofiguracijos.lavyno_id, laivynai.laivo_id,"
				+ "laivynai.skaicius, laivai.ilgis "
				+ "FROM kofiguracijos "
				 + "Inner join laivynai on kofig.lavyno_id = laivynai.id "
				  + "Inner join laivai "
				  + "on laivai.id = laivynai.laivo_id where kofiguracijos.id =?";
		try {
			dbConnection = CreateConnection();
			prepared = dbConnection.prepareStatement(sql);
			prepared.setInt(1, konfiguracijosID);
			ResultSet rs = prepared.executeQuery();
			while(rs.next()) {
				 konfigId = rs.getInt("id");
				 lavyno_id = rs.getInt("lavyno_id");
				 laivo_id = rs.getInt("laivo_id");
				 skaiciusLaivu = rs.getLong("skaicius");
				 laivoIlgis = rs.getLong("ilgis");
				 
				 //TODO sukurti laivu lista
				 LaivuKurimas();	 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} 
		finally {
			if(prepared != null)
				prepared.close();
			
			if(dbConnection != null)
				dbConnection.close();
		}
	
	}
	//laivams
	public void LaivuKurimas() {
		List<Laivas> laivuSarasas = new ArrayList<Laivas>();
		
			for (int k = 1; k <= skaiciusLaivu; k++) 
				laivuSarasas.add(new lt.vcs.laivumusis.USSENTERPRISE.laivas.Laivas((int)laivoIlgis));
		
			visiLaivai.add(laivuSarasas);
	}
	//grazina laivu lista
	public List<List<Laivas>> getVisiLaivai() {
		return visiLaivai;
	}

	//Lentos kurimas
	public void selectZaidimoLenFromKonfig() throws SQLException, ClassNotFoundException{
		
		Connection dbConnection = null;
		PreparedStatement prepared = null;
		String sql = "SELECT kofiguracijos.id,kofiguracijos.zemelapio_id, " + 
				"kofiguracijos.lavyno_id," + 
				"zemelapiai.x_koordinate,zemelapiai.y_koordinate " + 
				"FROM kofiguracijos " + 
				"Inner Join zemelapiai ON kofiguracijos.zemelapio_id = zemelapiai.id " + 
				"where kofiguracijos.id =?";
		
		try {
			dbConnection = CreateConnection();
		    prepared = dbConnection.prepareStatement(sql);
			prepared.setInt(1, konfiguracijosID);
			ResultSet rs = prepared.executeQuery();
			while(rs.next()) {
				 konfigId = rs.getInt(1);
				 zemelapioId = rs.getInt("zemelapio_id");
				 lavyno_id = rs.getInt("lavyno_id");
				 x_koordinate = rs.getString("x_koordinate");
				 y_koordinate = rs.getLong("y_koordinate");
				 
				 lentosKurimas();
 		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {

			if(prepared != null)
				prepared.close();
			
			if(dbConnection != null)
			    dbConnection.close();
		}
		
	}
	
	private void lentosKurimas() {
		
		String x = x_koordinate;
		 x = x.replaceAll("\\s+","");
		 String[] abecele = x.split(","); 
		 
		 for(String a : abecele) {
			 List<Langelis> langas = new ArrayList<Langelis>(); 
			 for (int ilg = 1; ilg <= this.y_koordinate; ilg++) {
				 langas.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis(a, ilg));
				}
			 zaidimoLentele.put(a, langas);	
		 }

	}
//Pasiimame lenta
	public Map<String, List<Langelis>> getZaidimoLentele() {
		return zaidimoLentele;
	}

}
