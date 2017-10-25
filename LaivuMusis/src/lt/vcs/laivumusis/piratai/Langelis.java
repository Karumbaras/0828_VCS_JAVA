package lt.vcs.laivumusis.piratai;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Langelis implements lt.vcs.laivumusis.common.Langelis {

	

	private String x;
	private int y;

	private int sautaKartu;
	private boolean arPasautasLangelis;

	private Laivas laivas;
	private boolean arGalimaDetiLaiva = true;
	
	private Rectangle kvadratas = new Rectangle();

	public Langelis(String x, int y) {
		this.x = x.toUpperCase();
		this.y = y;
	}

	@Override
	public void sauk() {
		sautaKartu++;
		if (laivas != null) {
			arPasautasLangelis = true;
			kvadratas.setFill(Color.RED);
		} else kvadratas.setFill(Color.GRAY);
	}

	@Override
	public Laivas getLaivas() {
		if (laivas != null) {
			return laivas;
		}
		return null;
	}

	@Override
	public boolean arSauta() {
		if (sautaKartu > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int sakykKiekKartuSauta() {
		System.out.println("I si langeli sauta kartu: " + sautaKartu);
		return sautaKartu;
	}

	@Override
	public String getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	public void setLangelyjeEsantisLaivas(lt.vcs.laivumusis.piratai.Laivas laivas) {
		this.laivas = laivas;
		arGalimaDetiLaiva = false;
	}

	public void setArGalimaDetiLaiva() {
		this.arGalimaDetiLaiva = false;
	}
	
	public boolean getArGalimaDetiLaiva() {
		return this.arGalimaDetiLaiva;
	}

	public boolean getArPasautasLangelis() {
		return this.arPasautasLangelis;
	}
	
	public Rectangle getKvadratas() {
		return kvadratas;
	}
	
	
	

}
