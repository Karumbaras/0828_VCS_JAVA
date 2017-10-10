package lt.vcs.laivumusis.piratai.zaidejas;

import lt.vcs.laivumusis.common.Zaidimas;

public class ZaidejasArnas {
	public class Zaidejas implements lt.vcs.laivumusis.common.Zaidejas {
		Zaidimas zaidimas;
		private String zaidejoId;
		
		public Zaidejas(Zaidimas zaidimas) {
			this.zaidimas = zaidimas;
			zaidejoId = this.zaidimas.registruokZaideja();
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
}
