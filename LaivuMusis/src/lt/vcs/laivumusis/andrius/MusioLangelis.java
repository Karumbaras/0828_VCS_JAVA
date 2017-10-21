package lt.vcs.laivumusis.andrius;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;

public class MusioLangelis implements Langelis {

	private String x;
	private int y;

	public MusioLangelis(String x, int y) {
		this.x = x;
		this.y = y;
	}

	private Laivas langelioLaivas;

	private int suviuSkaitliukas = 0;

	private boolean arSauta = false;

	@Override
	public void sauk() {
		arSauta = true;
		suviuSkaitliukas++;
	}

	@Override
	public Laivas getLaivas() {
		return langelioLaivas;
	}

	@Override
	public boolean arSauta() {
		return arSauta;
	}

	@Override
	public int sakykKiekKartuSauta() {
		return suviuSkaitliukas;
	}

	public String getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
