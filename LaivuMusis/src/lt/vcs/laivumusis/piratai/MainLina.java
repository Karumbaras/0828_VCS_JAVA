package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;

public class MainLina {
	public static void main(String[] args) {
		int [][] laivai = {{1,1}};
		Zaidimas zaidimas = new Zaidimas(10,10,laivai);
		//ZaidejasLina zaid = new ZaidejasLina(zaidimas);
	
		//Zaidimas zaidimas2 = new Zaidimas(15,15,laivai);
		//Zaidimas zaidimas2 = new Zaidimas();
		//Zaidejas zaid2 = new Zaidejas(zaidimas2);
		//String id = zaidimas2.registruokZaideja();
		//System.out.println(id);
		//List<lt.vcs.laivumusis.common.Laivas> laiveliai = zaidimas2.duokLaivus(id);
		//for (int i =0;i<laiveliai.size();i++) {
		//	System.out.println(laiveliai.get(i).getLaivoIlgis());
		//}
		
		//System.out.println(new Random().nextInt(2));
		Thread zaidejas1 = new Thread(new ZaidejasLina(zaidimas, "Lina"));
		Thread zaidejas2 = new Thread(new ZaidejasLina(zaidimas, "Kestutis"));
		
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