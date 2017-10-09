package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.MockZaidejas;

public class Main {

	public static void main(String[] args) {
		MockZaidimas zaidimas = new MockZaidimas();
		MockZaidejas zaid = new MockZaidejas(zaidimas);
		
		//System.out.println(zaid.getZaidimas().registruokZaideja());
		//System.out.println(zaid.zaidejoID);
		
		


		MockZaidimoLenta a = new MockZaidimoLenta(10, 10);
		MockVaizdas vaizdas = new MockVaizdas(a);
		vaizdas.pieskVaizda();

	}

}