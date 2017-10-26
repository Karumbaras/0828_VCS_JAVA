package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.common.Zaidejas;
import lt.vcs.laivumusis.piratai.grafika.Grafika;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasArnas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class MainManvydas {
	public static void main(String[] args) {
		
		Zaidimas zaidimas = new Zaidimas();
		Zaidejas zaidejas1 = new ZaidejasManvydas(zaidimas, "Arnas");
		Zaidejas zaidejas2 = new ZaidejasManvydas(zaidimas, "Piratas");
		
		
		Zaidimas.paleiskZaidima(zaidejas1, zaidejas2, zaidimas);

		
	
		
		
		
		
	}
}