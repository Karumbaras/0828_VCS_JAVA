package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class Main {

	public static void main(String[] args) {
		Zaidimas zaidimas = new Zaidimas();
		Zaidimas.paleiskZaidima(new ZaidejasLina(zaidimas, "Lina"), new ZaidejasManvydas(zaidimas, "Manvydas!!!"),new Zaidimas());

	}

}