package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;
import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;

public class MainLina {
	public static void main(String[] args) {
		//int [][] laivai = {{1,3},{1,4}};
		//Zaidimas zaidimas = new Zaidimas(10,10,laivai);
		//Zaidimas zaidimas = new Zaidimas();

		//Thread zaidejas1 = new Thread(new ZaidejasLina(zaidimas, "Lina"));
		//Thread zaidejas2 = new Thread(new ZaidejasLina(zaidimas, "Kestutis"));
		
		//zaidejas1.start();
		//zaidejas2.start();
		
		DuomenuBaze db = new DuomenuBaze("C:/LaivuMusisLina.db");
		//db.tikrinkArYraToksZaidejas("Lina");
		
		
	}
}