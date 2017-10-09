package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;

public class MockLangelis implements Langelis{
	
	// kometaras
	
	String x;
	int y;
	
	int sautaKartu ;
	boolean arPasautasLangelis;
	boolean arTalpinaLaiva;
	
	public MockLangelis(String x , int y ) {
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
		
		// Parodo kurio laivo dalis yra sitas langelis, grazina visa laiva, nesvarbu i kuria dali sauni
		System.out.println("Laivas langelyje");
		return new MockLaivas(4);
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

}
