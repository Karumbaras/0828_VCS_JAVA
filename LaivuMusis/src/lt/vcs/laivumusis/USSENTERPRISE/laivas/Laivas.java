package lt.vcs.laivumusis.USSENTERPRISE.laivas;

import java.util.ArrayList;
import java.util.List;

import lt.vcs.laivumusis.USSENTERPRISE.zaidimas.Zaidimas;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta;
import lt.vcs.laivumusis.common.Langelis;

public class Laivas implements lt.vcs.laivumusis.common.Laivas {
	private int laivoIlgis;
	private int laivogyvybes;
	private String laivoPavadinimas;

	public List<Langelis> laivoKordinates = new ArrayList<Langelis>();

	public Laivas() {

	}

	public Laivas(int laivoIlgis) {
		this.laivoIlgis = laivoIlgis;
	}

	public Laivas(int laivoIlgis, String laivoPavadinimas) {
		this(laivoIlgis);
		this.laivogyvybes = laivoIlgis;
		this.laivoPavadinimas = laivoPavadinimas;
	}

	@Override
	public void setKordinates(List<Langelis> langeliai) {
		if (langeliai.size() != laivoIlgis)
			System.out.println("Laivas per ilgas arba per trumpas");

		laivoKordinates = langeliai;
	}

	@Override
	public int getLaivoIlgis() {
		return laivoIlgis;
	}

	@Override
	public boolean arNusautas() {
		if (laivogyvybes == 0)
			return true;
		return false;
	}

	@Override
	public List<Langelis> getLaivoKoordinates() {
		return laivoKordinates;
	}

}
