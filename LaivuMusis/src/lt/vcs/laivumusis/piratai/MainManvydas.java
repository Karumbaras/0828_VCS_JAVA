package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.grafika.Grafika;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class MainManvydas {
	public static void main(String[] args) {
		
		Zaidimas zaidimas = new Zaidimas();
		Grafika grafika1 = new Grafika();
		
		
		grafika1.zaidimoLenta1 = (ZaidimoLenta) zaidimas.getLentos().get(0);
		grafika1.zaidimoLenta2 = (ZaidimoLenta) zaidimas.getLentos().get(1);
		

		Thread zaidejas1 = new Thread(new ZaidejasManvydas(zaidimas, "Manvydas"));
		Thread zaidejas2 = new Thread(new ZaidejasManvydas(zaidimas, "Lina"));
		
		
		Thread grafika = new Thread(grafika1);
		grafika.start();
		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zaidejas1.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zaidejas2.start();
		
		
	}
}