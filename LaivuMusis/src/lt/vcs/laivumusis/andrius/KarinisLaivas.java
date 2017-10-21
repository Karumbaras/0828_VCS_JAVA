package lt.vcs.laivumusis.andrius;

import java.util.ArrayList;
import java.util.List;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;

public class KarinisLaivas implements Laivas {
	
	private int ilgis = 0;
	
	private List<Langelis> langeliai = new ArrayList<Langelis>();
	
	public KarinisLaivas(int ilgis) {
		this.ilgis = ilgis;
	}

	@Override
	public int getLaivoIlgis() {
		return ilgis;
	}

	@Override
	public boolean arNusautas() {
		for(Langelis langelis : langeliai){
			if(!langelis.arSauta()){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Langelis> getLaivoKoordinates() {
		return langeliai;
	}

	@Override
	public void setKordinates(List<Langelis> langeliai) {
		this.langeliai = langeliai;
	}

}
