package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;

public class ZaidejasManvydas implements lt.vcs.laivumusis.common.Zaidejas {

	private String zaidejoId;

	private Zaidimas zaidimas;

	private ZaidimoLenta zaidimoLenta;
	private int lentosIlgis;
	private int lentosPlotis;

	private boolean arZaidimasTesiasi = true;

	private String abecele = "";

	Random random = new Random();

	private List<Laivas> laivai = new ArrayList<Laivas>();
	private List<String> suviai = new ArrayList<String>();

	public ZaidejasManvydas(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public void run() {
		boolean arUzregistravo = false;

		try {
			while (arZaidimasTesiasi) {
				switch (zaidimas.tikrinkBusena(zaidejoId)) {

				case Registracija:
					while (!arUzregistravo) {
						registruokis();
						Thread.sleep(10);
						if (zaidimas.registruokZaideja(this.zaidejoId)) {
							arUzregistravo = true;
						}
					}
					break;

				case DalinamesZemelapius:
					pasiimkLenta();
					Thread.sleep(10);
					break;

				case DalinemesLaivus:
					pasiimkLaivus();
					Thread.sleep(10);
					break;

				case RikiuojamLaivus:
					rikiuokLaivus();
					Thread.sleep(10);
					break;

				case PriesininkoEile:
					Thread.sleep(10);
					break;

				case TavoEile:
					sauk();
					break;

				case TuLaimejai:
					baikZaidima("laimejo");
					return;

				case PriesasLaimejo:
					baikZaidima("pralaimejo");
					return;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}

	private void registruokis() {
		DuomenuBaze duomenuBaze = new DuomenuBaze("C:/Users/Manvydas/Desktop/VCS/Java/LaivuMusis.db");
		duomenuBaze.registruokZaideja(zaidejoId);
	}

	private void pasiimkLenta() {
		this.zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
		for (String k : zaidimoLenta.getLangeliai().keySet()) {
			this.abecele = this.abecele + k;
		}
		this.lentosPlotis = zaidimoLenta.getLangeliai().keySet().size();
		this.lentosIlgis = zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size();
	}

	private void pasiimkLaivus() {
		this.laivai = zaidimas.duokLaivus(zaidejoId);
	}

	private void rikiuokLaivus() {
		int stulpelisSkaicius;
		int eilute;
		boolean arNeleidoPadeti = true;

		for (Laivas laivas : laivai) {

			double kryptis = Math.random();

			do {

				List<Langelis> laivoKoordinates = new ArrayList<Langelis>();

				try {

					if (kryptis < 0.5) {
						stulpelisSkaicius = random.nextInt(lentosPlotis - laivas.getLaivoIlgis());
						eilute = random.nextInt(lentosIlgis) + 1;

						for (int i = 0; i < laivas.getLaivoIlgis(); i++) {
							String stulpelis = "" + abecele.charAt(stulpelisSkaicius + i);

							laivoKoordinates.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute));
							laivas.setKordinates(laivoKoordinates);
						}
					} else {
						String stulpelis = "" + abecele.charAt(random.nextInt(lentosPlotis));
						eilute = random.nextInt(lentosIlgis - laivas.getLaivoIlgis()) + 1;

						for (int i = 0; i < laivas.getLaivoIlgis(); i++) {
							laivoKoordinates.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute + i));
							laivas.setKordinates(laivoKoordinates);
						}
					}

					arNeleidoPadeti = false;
					zaidimas.pridekLaiva(laivas, zaidejoId);

				} catch (Exception e) {
					arNeleidoPadeti = true;
				}
			} while (arNeleidoPadeti);
		}
	}

	private void sauk() throws InterruptedException {
		while (true) {
			String stulpelis = "" + abecele.charAt(random.nextInt(lentosPlotis));
			int eilute = random.nextInt(lentosIlgis) + 1;
			if (suviai.contains(stulpelis + eilute) == false) {
				suviai.add(stulpelis + eilute);
				if (zaidimas.sauk(stulpelis, eilute, this.zaidejoId)) {
					ieskokLaivo(stulpelis, eilute);
				}

				break;
			}
		}
	}

	/*
	 * private void sauk() {
	 * 
	 * while (true) {
	 * 
	 * String stulpelis = "" + abecele.charAt(random.nextInt(lentosPlotis)); int
	 * eilute = random.nextInt(lentosIlgis) + 1;
	 * 
	 * if (!suviai.contains(stulpelis + eilute)) { suviai.add(stulpelis + eilute);
	 * if (zaidimas.sauk(stulpelis, eilute, this.zaidejoId)) {
	 * 
	 * boolean arLaivasNusautas = false;
	 * 
	 * while (!arLaivasNusautas) { if (tikrinkArNusautas(stulpelis, eilute)) {
	 * arLaivasNusautas = true; } }
	 * 
	 * ieskokLaivo(stulpelis, eilute); } } break;
	 * 
	 * } }
	 */

	private boolean tikrinkArNusautas(String stulpelis, int eilute) {
		
		int stulp = stulpelis.charAt(0);
		
		if ((suviai.contains(stulpelis + (eilute + 1)) && eilute + 1 < lentosIlgis)
				&& (suviai.contains(stulpelis + (eilute - 1)) && eilute - 1 > lentosIlgis)
				

		) {
			return true;
		}
		return false;
	}

	private void ieskokLaivo(String stulpelis, int eilute) throws InterruptedException {
		if (!suviai.contains(stulpelis + (eilute + 1)) && eilute + 1 < lentosIlgis) {
			suviai.add(stulpelis + (eilute + 1));
			Thread.sleep(200);
			zaidimas.sauk(stulpelis, (eilute + 1), this.zaidejoId);
		} else if (!suviai.contains(stulpelis + (eilute - 1)) && eilute - 1 > lentosIlgis) {
			suviai.add(stulpelis + (eilute - 1));
			Thread.sleep(200);
			zaidimas.sauk(stulpelis, (eilute - 1), this.zaidejoId);
		}
	}

	private void baikZaidima(String rezultatas) {
		if (rezultatas == "laimejo") {
			System.out.println(this.zaidejoId + " Laimejo!!!");
		} else {
			System.out.println(this.zaidejoId + " Pralose...");
		}
		zaidimas.skaiciuokStatistika();
		arZaidimasTesiasi = false;
	}
}
