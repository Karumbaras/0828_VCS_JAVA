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
	private String abecele = "";
	private int lentosIlgis;
	private int lentosPlotis;

	private List<Laivas> laivai = new ArrayList<Laivas>();
	private List<String> suviai = new ArrayList<String>();

	public ZaidejasManvydas(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public void run() {

		boolean arUzregistravo = true;
		boolean arZaidimasTesiasi = true;

		System.out.println(this.zaidejoId);
		try {
			while (arZaidimasTesiasi) {
				switch (zaidimas.tikrinkBusena(zaidejoId)) {

				case Registracija:

					while (arUzregistravo) {
						registruokis();
						Thread.sleep(new Random().nextInt(1000));
						if (zaidimas.registruokZaideja(this.zaidejoId)) {
							arUzregistravo = false;
						}
					}
					break;

				case DalinamesZemelapius:

					this.zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);

					for (String k : zaidimoLenta.getLangeliai().keySet()) {
						this.abecele = this.abecele + k;
					}

					this.lentosPlotis = zaidimoLenta.getLangeliai().keySet().size();
					this.lentosIlgis = zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size();

					Thread.sleep(new Random().nextInt(2000));
					break;

				case DalinemesLaivus:
					this.laivai = zaidimas.duokLaivus(zaidejoId);
					break;

				case RikiuojamLaivus:
					rikiuokLaivus();

				case PriesininkoEile:
					break;

				case TavoEile:
					Thread.sleep(new Random().nextInt(100));
					sauk();
					break;

				case TuLaimejai:
					System.out.println(this.zaidejoId + " Laimejo!!!");
					zaidimas.skaiciuokStatistika();
					arZaidimasTesiasi = false;
					return;

				case PriesasLaimejo:
					System.out.println(this.zaidejoId + " Pralose...");
					zaidimas.skaiciuokStatistika();
					arZaidimasTesiasi = false;
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

	public void rikiuokLaivus() {
		int stulpelisSkaicius;
		int eilute;
		boolean arNeleidoPadeti = true;

		for (Laivas laivas : laivai) {
			
			double kryptis = Math.random();

			do {
				
				List<Langelis> laivoKoordinates = new ArrayList<Langelis>();
				
				try {

					if (kryptis < 0.5) {
						
						stulpelisSkaicius = new Random().nextInt(lentosPlotis - laivas.getLaivoIlgis());
						eilute = new Random().nextInt(lentosIlgis) + 1;
						
						for (int i = 0; i < laivas.getLaivoIlgis(); i++) {

							
							String stulpelis = "" + abecele.charAt(stulpelisSkaicius + i);

							laivoKoordinates.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute));
							laivas.setKordinates(laivoKoordinates);
						}
					}

					else {
						String stulpelis = "" + abecele.charAt(new Random().nextInt(lentosPlotis));
						eilute = new Random().nextInt(lentosIlgis - laivas.getLaivoIlgis()) + 1;
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

	private void sauk() {
		while (true) {
			String stulpelis = "" + abecele.charAt(new Random().nextInt(lentosPlotis));
			int eilute = new Random().nextInt(lentosIlgis) + 1;
			if (suviai.contains(stulpelis + eilute) == false) {
				suviai.add(stulpelis + eilute);
				if (zaidimas.sauk(stulpelis, eilute, this.zaidejoId)) {
					ieskokLaivo(stulpelis, eilute);
				}

				break;
			}
		}
	}

	private void ieskokLaivo(String stulpelis, int eilute) {
		if (!suviai.contains(stulpelis + (eilute + 1)) && eilute + 1 < lentosIlgis) {
			suviai.add(stulpelis + (eilute + 1));
			zaidimas.sauk(stulpelis, (eilute + 1), this.zaidejoId);
		} else if (!suviai.contains(stulpelis + (eilute - 1)) && eilute - 1 > lentosIlgis) {
			suviai.add(stulpelis + (eilute - 1));
			zaidimas.sauk(stulpelis, (eilute - 1), this.zaidejoId);
		}
	}

	private void registruokis() {
		DuomenuBaze duomenuBaze = new DuomenuBaze("C:/Users/Manvydas/Desktop/VCS/Java/LaivuMusis.db");
		duomenuBaze.registruokZaideja(zaidejoId);
	}
}
