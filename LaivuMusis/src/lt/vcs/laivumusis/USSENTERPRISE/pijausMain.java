package lt.vcs.laivumusis.USSENTERPRISE;

import java.sql.SQLException;

import lt.vcs.laivumusis.USSENTERPRISE.zaidejas.pijausZaidejas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimas.Zaidimas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;

public class pijausMain {

	public static void main(String[] args) {

	//	lt.vcs.laivumusis.piratai.Zaidimas zaidimas = new lt.vcs.laivumusis.piratai.Zaidimas(); // pijausZaidejas zaidejas = new
		Zaidimas zaidimas1;
		try {
			zaidimas1 = new Zaidimas(1);
			Thread x = new Thread(new pijausZaidejas(zaidimas1, "Pijus"));
			Thread y = new Thread(new ZaidejasLina(zaidimas1, "Lina"));
			
			x.start();
			y.start();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
