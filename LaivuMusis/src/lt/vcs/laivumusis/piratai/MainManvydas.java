package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;

public class MainManvydas {
	public static void main(String[] args) {
		//int [][] laivai = {{1,3},{1,4}};
		//Zaidimas zaidimas = new Zaidimas(10,10,laivai);
		Zaidimas zaidimas = new Zaidimas();

		Thread zaidejas1 = new Thread(new ZaidejasManvydas(zaidimas, "Piratas"));
		Thread zaidejas2 = new Thread(new ZaidejasManvydas(zaidimas, "Jureivis"));
		
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