package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;

public class MainLina {
	Zaidimas zaidimas = new Zaidimas();
	Zaidejas zaid = new Zaidejas(zaidimas);
	
	
	String zaidejoID = zaid.getZaidimas().registruokZaideja();
	//System.out.println(zaid.getZaidimas().registruokZaideja());
	
	
	

	ZaidimoLenta a = new ZaidimoLenta(10, 10);
	Vaizdas vaizdas = new Vaizdas(a);
}