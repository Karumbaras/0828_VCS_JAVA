package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.USSENTERPRISE.zaidejas.pijausZaidejas;
import lt.vcs.laivumusis.piratai.grafika.Grafika;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasArnas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class MainLina {
	public static void main(String[] args) {
		//int [][] laivai = {{1,3},{1,4}};
		//Zaidimas zaidimas = new Zaidimas(10,10,laivai);
		Zaidimas zaidimas = new Zaidimas();
		Grafika grafika1 = new Grafika();
		Grafika.zaidimoLenta1 = (ZaidimoLenta) zaidimas.getLentos().get(0);
		Grafika.zaidimoLenta2 = (ZaidimoLenta) zaidimas.getLentos().get(1);
		//grafika1.zaidimas = zaidimas;
		
		Thread zaidejas1 = new Thread(new ZaidejasLina(zaidimas, "Lina"));
		Thread zaidejas2 = new Thread(new pijausZaidejas(zaidimas, "Pijus"));
		//grafika1.zaidejoID1 = "Lina";
		//grafika1.zaidejoID2 = "Manvydas";
		Thread grafika = new Thread(grafika1);
		grafika.start();

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zaidejas1.start();
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zaidejas2.start();
			
	}
}