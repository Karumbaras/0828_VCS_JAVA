package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasKestutis;

public class MainKestutis {

	public static void main(String[] args) {
		Zaidimas zaidimas = new Zaidimas();
		
		
		
		Thread zaidejas1 = new Thread(new ZaidejasKestutis(zaidimas, "Zaidejas1"));
		Thread zaidejas2 = new Thread(new ZaidejasKestutis(zaidimas, "Zaidejas2"));
		
		zaidejas1.start();
		zaidejas2.start();

	}

}
