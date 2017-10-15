package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;

public class Langelis implements lt.vcs.laivumusis.common.Langelis {
	
	private String x;
	private int y;
	
	private int sautaKartu ;
	private boolean arPasautasLangelis;
	
	private Laivas laivas;
	private boolean arGalimaDetiLaiva = true;
	
	
	public Langelis(String x , int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void sauk() {
		sautaKartu++;
		if (laivas!=null) {
		arPasautasLangelis = true;
		}
	}

	@Override
	public Laivas getLaivas() {	
		if(laivas!=null) { 
		return laivas;
		}
		return null;
	}

	@Override
	public boolean arSauta() {
		// Ar buvo sauta i si langeli
		if (sautaKartu>0) {
			return true;	
		}
		return false;
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
	
		
	public void setLangelyjeEsantisLaivas(lt.vcs.laivumusis.piratai.Laivas laivas) {
		this.laivas = laivas;
		arGalimaDetiLaiva = false;
	}

}
