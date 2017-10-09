package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;

public class Main {

	public static void main(String[] args) {
		Zaidimas zaidimas = new Zaidimas();
		Zaidejas zaid = new Zaidejas(zaidimas);
		
		
		String zaidejoID = zaid.getZaidimas().registruokZaideja();
		System.out.println(zaid.getZaidimas().registruokZaideja());
		
		
		

		MockZaidimoLenta a = new MockZaidimoLenta(10, 10);
		MockVaizdas vaizdas = new MockVaizdas(a);

	}

}