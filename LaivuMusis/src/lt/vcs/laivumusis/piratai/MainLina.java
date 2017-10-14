package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;

public class MainLina {
	public static void main(String[] args) {
		int [][] laivai = {{1,4}};
		Zaidimas zaidimas = new Zaidimas();
		Zaidejas zaid = new Zaidejas(zaidimas);

		Thread zaidejas1 = new Thread(new Zaidejas(zaidimas));
		Thread zaidejas2 = new Thread(new Zaidejas(zaidimas));
		
		zaidejas1.start();
		zaidejas2.start();
		
		
	
		//ZaidimoLenta a = new ZaidimoLenta(10,20);
		//String x ="A";
		//int y = 5;
		//System.out.println(a.getLangeliai().get(x).get(y - 1));
		//System.out.println(a.getLangeliai().get(x).get(y - 1).getX());
		//System.out.println(a.getLangeliai().get(x).get(y - 1).getY());
		//Vaizdas vaizdas = new Vaizdas(a);
		//vaizdas.pieskVaizda();
		//vaizdas.atnaujinkVaizda();
	}
}