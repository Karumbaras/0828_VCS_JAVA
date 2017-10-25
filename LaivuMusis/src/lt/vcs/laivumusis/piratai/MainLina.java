package lt.vcs.laivumusis.piratai;

import java.util.Random;

import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasArnas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasKestutis;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class MainLina {
	public static void main(String[] args) {
		//int [][] laivai = {{1,3},{1,4}};
		//Zaidimas zaidimas = new Zaidimas(10,10,laivai);
		Zaidimas zaidimas = new Zaidimas();

		Thread zaidejas1 = new Thread(new ZaidejasLina(zaidimas, "Lina"));
		Thread zaidejas2 = new Thread(new ZaidejasLina(zaidimas, "Kestutis"));
		
		zaidejas1.start();
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zaidejas2.start();
		
		//DuomenuBaze db = new DuomenuBaze("D:/sarunas/Linos/LaivuMusisLina.db");
		//db.registruokZaideja("Arnas");
		//db.registruokZaideja("Linute");
		
		
	}
}