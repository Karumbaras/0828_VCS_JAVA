package lt.vcs.laivumusis.andrius;

import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class LaivuMusioLenta implements ZaidimoLenta {

	private Map<String, List<Langelis>> lenta;

	public LaivuMusioLenta(Map<String, List<Langelis>> lenta) {
		this.lenta = lenta;
	}

	@Override
	public Map<String, List<Langelis>> getLangeliai() {
		return lenta;
	}

	// TODO implement
	@Override
	public List<Laivas> getLaivai() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean sauk(String x, int y) {

		return false;
	}

}
