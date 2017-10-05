package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class MockZaidimoLenta implements ZaidimoLenta {

	int ilgis;
	int plotis;
	private static final String abecele = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Map<String, List<Langelis>> zaidimoLenta = new HashMap<String, List<Langelis>>();
	List<Laivas> laivai = new ArrayList<Laivas>();

	public MockZaidimoLenta(int ilgis, int plotis) {

		for (int i = 0; i < plotis; i++) {
			String stulpelis = "" + abecele.charAt(i);
			List<Langelis> listas = new ArrayList<Langelis>();
			for (int sk = 1; sk <= ilgis; sk++) {
				listas.add(new MockLangelis(stulpelis, sk));
				
			}
			zaidimoLenta.put(stulpelis, listas);
		}
			this.ilgis = ilgis;
			this.plotis = plotis;
	}

	@Override
	public Map<String, List<Langelis>> getLangeliai() {
		return zaidimoLenta;
	}

	@Override
	public List<Laivas> getLaivai() {
		
		MockZaidimas.duokLaivus("zaidejoId");
		
		return this.laivai;
	}

	@Override
	public void pridekLaiva(Laivas laivas) {
		System.out.println("Laivas pridetas: " + laivas);

	}

	@Override
	public boolean sauk(String x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
