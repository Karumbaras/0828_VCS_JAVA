package lt.vcs.laivumusis.USSENTERPRISE.laivas;

import java.util.ArrayList;
import java.util.List;

import lt.vcs.laivumusis.common.Langelis;

public class Laivas implements lt.vcs.laivumusis.common.Laivas {
	private String[] masyvasX;
	private int[] masyvasY;
	private int laivoIlgis;
	List<Laivas> laivuListas = new ArrayList<Laivas>();

	public Laivas(int laivoIlgis) { // konstruktorius kad nustatyti laivo dydi ir mes ji naudosime
									// switch metode kuris pasakys laivo varda ir sukurs to dydzio
		this.laivoIlgis = laivoIlgis; // laiva
		this.masyvasX = new String[laivoIlgis];
		this.masyvasY = new int[laivoIlgis];



	}

<<<<<<< Updated upstream
	@Override
	public void setKordinates(List<Langelis> langeliai) {// reikia i laivu obijekta priskyrti kordinates

	}

	@Override
	public int getLaivoIlgis() {
		return laivoIlgis;
	}


	@Override
	public boolean arNusautas() {// turi grazinti laivo busena liste ar jo visos kordinates true

		return false;
	}

	@Override
	public List<Langelis> getLaivoKoordinates() {

		return null;
	}

=======
	private int ilgisLaivo;
	
	
	
	public Laivas() {
		
	}
	
	@Override
	public int getLaivoIlgis() {
		return ilgisLaivo;
	}

	@Override
	public boolean arNusautas() {
		
		
		return false;
	}

	@Override
	public List<Langelis> getLaivoKoordinates() {
		return null;
	}

	@Override
	public void setKordinates(List<Langelis> langeliai) {
		langeliai
		
	}
	
	
	/*
	// laivo kintamieji
	private int eilute;
	private int stulpelis;
	private int ilgis;
	private int kryptis;

	// krypties konstantos

	public static final int NENUSTATYTA = -1;
	public static final int HORIZONTALI = 0;
	public static final int VERTIKALI = 1;

	// konstruktorius
	public Laivas(int ilgis) {
		this.ilgis = ilgis;
		this.eilute = -1;
		this.stulpelis = -1;
		this.kryptis = NENUSTATYTA;
	}
	
	
	
	

	// lokacija

	public boolean arNustatytaVieta() {
		if (eilute == -1 || stulpelis == -1)
			return false;
		else
			return true;
	}

	public boolean arNustatytaKryptis() {
		if (kryptis == NENUSTATYTA)
			return false;
		else
			return true;
	}

	// laivo vietos nustatymas

	public void setLokacija(int eilute, int stulpelis) {
		this.eilute = eilute;
		this.stulpelis = stulpelis;
	}

	// laivo krypties nustatymas
	public void setKryptis(int kryptis) {
		if (kryptis != NENUSTATYTA && kryptis != HORIZONTALI && kryptis != VERTIKALI)
			throw new IllegalArgumentException("Netinkama kryptis. turi buti -1, 0 arba 1");
		this.kryptis = kryptis;

	}

	// eiles getteris

	public int getEilute() {
		return eilute;
	}
	// stulpelio getteris

	public int getStulpelis() {
		return stulpelis;
	}

	// laivo ilgio geteris

	public int getIlgis() {
		return ilgis;
	}

	// krypties getteris
	public int getKryptis() {
		return kryptis;
	}

	// metodas gauti string reiksme is krypties
	private String kryptisToString() {
		if (kryptis == NENUSTATYTA)
			return "NENUSTATYTA";
		else if (kryptis == HORIZONTALI)
			return "HORIZONTALI";
		else
			return "VERTIKALI";
	}

	// iStringa laivo suma
	public String toString() {
		return "Laivas: " + getEilute() + ", " + getStulpelis() + "ilgio" + getIlgis() + "kryptimi "
				+ kryptisToString();
	}
*/
	
>>>>>>> Stashed changes
}
