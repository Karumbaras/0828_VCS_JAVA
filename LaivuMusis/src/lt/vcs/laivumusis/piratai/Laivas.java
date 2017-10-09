package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import lt.vcs.laivumusis.common.Langelis;

public class Laivas implements lt.vcs.laivumusis.common.Laivas {
	int laivoIlgis;
	boolean arNusautas;
	List<Langelis> laivoKoordinates = new ArrayList<Langelis>();

	
	public Laivas(int laivoIlgis) {
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
		//TODO patikrinti, kad koordinates eitu is eiles ir nebutu uz zaidimo lentos ribu
		this.laivoKoordinates = langeliai;
	}
}