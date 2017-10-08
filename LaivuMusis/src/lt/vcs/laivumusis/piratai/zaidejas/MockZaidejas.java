package lt.vcs.laivumusis.piratai.zaidejas;

import lt.vcs.laivumusis.common.Zaidejas;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.piratai.MockZaidimas;

public class MockZaidejas implements Zaidejas {
	MockZaidimas zaidimas;
	
	public MockZaidejas(MockZaidimas zaidimas) {
		this.zaidimas = zaidimas;
	}

	@Override
	public void run() {
		System.out.println("kazakass");
	}

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}
}
