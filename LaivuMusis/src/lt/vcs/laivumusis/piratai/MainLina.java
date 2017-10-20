package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;

public class MainLina {
	public static void main(String[] args) {
		//int [][] laivai = {{1,3},{1,4}};
		//Zaidimas zaidimas = new Zaidimas(10,10,laivai);
		Zaidimas zaidimas = new Zaidimas();

		Thread zaidejas1 = new Thread(new ZaidejasLina(zaidimas, "Jolanta1"));
		Thread zaidejas2 = new Thread(new ZaidejasLina(zaidimas, "Paulius1"));
		
		zaidejas1.start();
		zaidejas2.start();
		
		//DuomenuBaze db = new DuomenuBaze("D:/sarunas/Linos/LaivuMusisLina.db");
		//db.registruokZaideja("Arnas");
		//db.registruokZaideja("Linute");
		
		
	}
}