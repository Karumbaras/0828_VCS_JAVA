package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.grafika.Grafika;
import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasKestutis;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasOdeta;
import org.apache.log4j.Logger;
import lt.vcs.laivumusis.piratai.grafika.Grafika;

public class MainOdeta {

	public static void main(String[] args) {

		Zaidimas zaidimasOdetos = new Zaidimas();
		Grafika grafikaOdetos = new Grafika();
		 
		grafikaOdetos.zaidimoLenta1 = (ZaidimoLenta) zaidimasOdetos.getLentos().get(0);
		grafikaOdetos.zaidimoLenta2 = (ZaidimoLenta) zaidimasOdetos.getLentos().get(1);

		
		Thread zaidejasOdetosPirmas = new Thread(new ZaidejasOdeta(zaidimasOdetos, "Test1"));
		Thread zaidejasOdetosAntras = new Thread(new ZaidejasOdeta(zaidimasOdetos, "Tes2"));
		Thread grafika = new Thread (grafikaOdetos);
		grafika.start();
		
		

		zaidejasOdetosPirmas.start();
		zaidejasOdetosAntras.start();



	}

}
