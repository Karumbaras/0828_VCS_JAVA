package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;

public class Langelis implements lt.vcs.laivumusis.common.Langelis {
	
	String x;
	int y;
	
	int sautaKartu ;
	boolean arPasautasLangelis;
	boolean arTalpinaLaiva = false;
	
	
	
	public Langelis(String x , int y ) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void sauk() {
		sautaKartu++;
		arPasautasLangelis = true;
		System.out.println("Pasautas langelis!");
	}

	@Override
	public Laivas getLaivas() {	
		
		return null;
	}

	@Override
	public boolean arSauta() {
		// Ar buvo sauta i si langeli
		return arPasautasLangelis;
	}

	@Override
	public int sakykKiekKartuSauta() {
		System.out.println("I si langeli sauta kartu: "+sautaKartu);
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
	
	public void setArTalpinaLaiva() {
		this.arTalpinaLaiva = true;
	}

}
