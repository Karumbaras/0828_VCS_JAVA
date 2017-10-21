package lt.vcs.laivumusis.andrius;

import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class LaivuMusioZaidimas implements Zaidimas {

	@Override
	public void run() {
		throw new UnsupportedOperationException("Method is not implemented");
	}

	@Override
	public List<ZaidimoLenta> getLentos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void skaiciuokStatistika() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Busena tikrinkBusena(String zaidejoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registruokZaideja(String zaidejoId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pridekLaiva(Laivas laivas, String zaidejoId) throws LaivuPridejimoKlaida {
		if (laivas == null || laivas.getLaivoKoordinates().size() <= 0) {
			System.out.println("Grybiskos laivo koordinates");
			return;
		}

		Langelis buvesLangelis = null;
		for (Langelis langelis : laivas.getLaivoKoordinates()) {
			buvesLangelis = langelis;
			if (buvesLangelis == null)
				continue;

			// TODO needs improvements
			if (buvesLangelis.getX().equals(langelis.getX()) || buvesLangelis.getY() == buvesLangelis.getY()) {

			}

		}

	}

	@Override
	public boolean sauk(String x, int y, String zaidejoId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Laivas> duokLaivus(String zaidejoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZaidimoLenta duokZaidimoLenta(String zaidejoId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
