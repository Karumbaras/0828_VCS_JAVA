package lt.vcs.laivumusis.USSENTERPRISE;

import java.sql.SQLException;

import lt.vcs.laivumusis.USSENTERPRISE.zaidejas.pijausZaidejas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimas.Zaidimas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;

public class pijausMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		//lt.vcs.laivumusis.piratai.Zaidimas zaidimas = new lt.vcs.laivumusis.piratai.Zaidimas(); // pijausZaidejas
		Zaidimas zaidimas = new Zaidimas(1);																					// zaidejas = new

		Thread x = new Thread(new pijausZaidejas(zaidimas, "Pijus"));
		Thread y = new Thread(new pijausZaidejas(zaidimas, "Lina"));

		x.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		y.start();

	}

}
