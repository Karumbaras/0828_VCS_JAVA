package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasArnas;

public class MainArnas {

	public static void main(String[] args) {
		
			//int [][] laivai = {{1,3},{1,4}};
			//Zaidimas zaidimas = new Zaidimas(10,10,laivai);
			Zaidimas zaidimas = new Zaidimas();

			Thread zaidejas1 = new Thread(new ZaidejasArnas(zaidimas, "Jolanta1"));
			Thread zaidejas2 = new Thread(new ZaidejasArnas(zaidimas, "Paulius1"));
			
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


