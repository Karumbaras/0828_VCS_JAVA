package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;

public class MainLina {
	public static void main(String[] args) {
		Zaidimas zaidimas = new Zaidimas();
		//Zaidejas zaid = new Zaidejas(zaidimas);

		Thread zaidejas1 = new Thread(new Zaidejas(zaidimas));
		Thread zaidejas2 = new Thread(new Zaidejas(zaidimas));
		
		zaidejas1.start();
		zaidejas2.start();
		
		
		//String zaidejoID = zaid.getZaidimas().registruokZaideja();
		// System.out.println(zaid.getZaidimas().registruokZaideja());

		//ZaidimoLenta a = new ZaidimoLenta(10,20);
		//Vaizdas vaizdas = new Vaizdas(a);
		//vaizdas.pieskVaizda();
	}
}