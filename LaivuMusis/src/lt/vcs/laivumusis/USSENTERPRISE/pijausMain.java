package lt.vcs.laivumusis.USSENTERPRISE;

import java.sql.SQLException;

import lt.vcs.laivumusis.USSENTERPRISE.zaidejas.pijausZaidejas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimas.Zaidimas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta;

public class pijausMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Zaidimas zaidimas = new Zaidimas(1); // pijausZaidejas zaidejas = new
		
		Thread x = new Thread(new pijausZaidejas(zaidimas));
		
		x.start();

	}

}
