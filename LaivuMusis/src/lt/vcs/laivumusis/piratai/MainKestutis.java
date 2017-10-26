package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.grafika.Grafika;
import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasKestutis;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class MainKestutis {

	public static void main(String[] args) {
	
				Zaidimas zaidimas = new Zaidimas();
				Grafika grafika1 = new Grafika();
				grafika1.zaidimoLenta1 = (ZaidimoLenta) zaidimas.getLentos().get(0);
				grafika1.zaidimoLenta2 = (ZaidimoLenta) zaidimas.getLentos().get(1);
				
				Thread zaidejas1 = new Thread(new ZaidejasKestutis(zaidimas, "1 zaidejas"));
				Thread zaidejas2 = new Thread(new ZaidejasLina(zaidimas, "2 zaidejas"));
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