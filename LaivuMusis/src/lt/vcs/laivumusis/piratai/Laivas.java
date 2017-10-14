package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import lt.vcs.laivumusis.common.Langelis;

public class Laivas implements lt.vcs.laivumusis.common.Laivas {
	int laivoIlgis;
	boolean arNusautas;
	public List<Langelis> laivoKoordinates = null;
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
		// kiekvienam langeliui kuriam setinamos koordinates perduodam laivo objekta
		for (int i = 0; i < langeliai.size(); i++) {
			((lt.vcs.laivumusis.piratai.Langelis) langeliai.get(i)).setLangelyjeEsantisLaivas(this);
		}

		this.laivoKoordinates = langeliai;
	}
}