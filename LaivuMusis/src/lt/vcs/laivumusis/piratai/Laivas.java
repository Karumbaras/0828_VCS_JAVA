package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import lt.vcs.laivumusis.common.Langelis;

public class Laivas implements lt.vcs.laivumusis.common.Laivas {
	int laivoIlgis;
	boolean arNusautas;
	public List<Langelis> laivoKoordinates = new ArrayList<Langelis>();
	private Zaidimas zaidimas;
	
	public Laivas(int laivoIlgis, Zaidimas zaidimas) {
		this.zaidimas = zaidimas;
		this.laivoIlgis = laivoIlgis;
	}

	@Override
	public int getLaivoIlgis() {
		return laivoIlgis;
	}

	@Override
	public boolean arNusautas() {
		return arNusautas;
	}

	@Override
	// ar sitas tikrai parodo laivo koordinates? Ar tik grazina langeliu lista?
	public List<Langelis> getLaivoKoordinates() {
		return laivoKoordinates;
	}

	@Override
	public void setKordinates(List<Langelis> langeliai) {
		// Naudojama nusakyti zaidimui laivo koordinates pagal x y/ String int
		//TODO patikrinti, kad koordinates eitu is eiles ir nebutu uz zaidimo lentos ribu
		
		// patikrinam, ar paduotos koordinates atitinka laivo ilgi
		if (laivoIlgis != langeliai.size()) {
			System.out.println("Error: Laivas susideda is " + laivoIlgis + " langeliu");
			
		}
		
		// Patikrinti ar nelankstomi laivai
		for (int i = 0; i < langeliai.size(); i++) {
			
		}
		
		// kiekvienam langeliui esanciui koordinates perduodam laivo objekta
		for (int i = 0; i < langeliai.size(); i++) {
			((lt.vcs.laivumusis.piratai.Langelis) langeliai.get(i)).setLangelyjeEsantisLaivas(this);
		}
		
		this.laivoKoordinates = langeliai;
	}
}