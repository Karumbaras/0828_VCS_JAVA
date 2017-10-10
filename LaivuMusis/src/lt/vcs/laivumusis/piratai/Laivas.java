package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import lt.vcs.laivumusis.common.Langelis;

public class Laivas implements lt.vcs.laivumusis.common.Laivas {
	int laivoIlgis;
	boolean arNusautas;
	List<Langelis> laivoKoordinates = new ArrayList<Langelis>();
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
			break;
		}
		
		// 
		for (int i = 0; i < langeliai.size(); i++) {
			
		}
		
		// pakeiciam kiekvieno langelio kintamaji arTalpinaLaiva i true
		for (int i = 0; i < langeliai.size(); i++) {
			langeliai.get(i).setArTalpinaLaiva();
		}
		
		this.laivoKoordinates = langeliai;
	}
}