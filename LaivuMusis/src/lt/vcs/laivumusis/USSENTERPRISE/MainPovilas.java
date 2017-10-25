package lt.vcs.laivumusis.USSENTERPRISE;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;

import lt.vcs.laivumusis.USSENTERPRISE.zaidimas.Zaidimas;

public class MainPovilas {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Zaidimas zaidimas = new Zaidimas(1);

		//Thread zaidejas1 = new Thread(new PoviloZaidejas(zaidimas, "Povilas"));
		// Thread zaidejas2 = new Thread(new PoviloZaidejas(zaidimas, "Testas"));

		//zaidejas1.start();
		// zaidejas2.start();

	}

}
