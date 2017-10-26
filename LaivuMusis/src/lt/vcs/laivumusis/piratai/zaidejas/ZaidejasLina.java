package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;

public class ZaidejasLina implements lt.vcs.laivumusis.common.Zaidejas {

	private String zaidejoId;

	private Zaidimas zaidimas;

	private ZaidimoLenta zaidimoLenta;
	private String abecele = "";
	private int lentosIlgis;
	private int lentosPlotis;

	private List<Laivas> laivuListas = new ArrayList<Laivas>();

	private List<String> suviai = new ArrayList<String>();
	private String nusautas;
	private Map<String, Integer> galimiSuviai = new HashMap<String, Integer>();

	// Konstruktoriai
	public ZaidejasLina(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public void run() {

		boolean arUzregistravo = true;
		boolean arTiesa = true;

		System.out.println(this.zaidejoId);
		try {
			while (arTiesa) {
				// System.out.println(zaidimas.tikrinkBusena(zaidejoId));
				switch (zaidimas.tikrinkBusena(zaidejoId)) {
				case Registracija:
					registruokis();
					while (arUzregistravo) {
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
					this.laivuListas = zaidimas.duokLaivus(zaidejoId);
					break;

				case RikiuojamLaivus:

					for (int k = 0; k < this.laivuListas.size(); k++) {
						try {
							// Thread.sleep(new Random().nextInt(2000));
							int laivoPadetis = new Random().nextInt(50);
							int laivoIlgis = this.laivuListas.get(k).getLaivoIlgis();
							this.laivuListas.get(k)
									.setKordinates(sugalvokLaivoKoordinates(laivoIlgis, laivoPadetis, zaidimoLenta));

							zaidimas.pridekLaiva(this.laivuListas.get(k), this.zaidejoId);
						} catch (Exception e) {
							k--;
						}
					}

				case PriesininkoEile:
					break;
				case TavoEile:
					Thread.sleep(new Random().nextInt(100));
					sauk();
					break;
				case TuLaimejai:
					System.out.println(this.zaidejoId + " dziaugiasi!!;)))");
					zaidimas.skaiciuokStatistika();
					arTiesa = false;
					break;
				case PriesasLaimejo:
					System.out.println(this.zaidejoId + " liudi!!;(((");
					zaidimas.skaiciuokStatistika();
					arTiesa = false;
					break;
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}

	private List<Langelis> sugalvokLaivoKoordinates(int laivoIlgis, int laivoPadetis, ZaidimoLenta zaidimoLenta) {
		List<Langelis> langeliai = new ArrayList<Langelis>();

		// Vienu atveju bus vertikalus, kitu atveju bus horizontalus
		if (laivoPadetis < 25) {
			// horizontalus
			int stulpelisInt = new Random().nextInt(lentosPlotis - laivoIlgis);
			int eilute = new Random().nextInt(lentosIlgis) + 1;
			for (int i = 0; i < laivoIlgis; i++) {
				String stulpelis = "" + abecele.charAt(stulpelisInt + i);
				// System.out.println(stulpelis + eilute);
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute));
			}
		} else {
			// vertikalus
			String stulpelis = "" + abecele.charAt(new Random().nextInt(lentosPlotis));
			int eilute = new Random().nextInt(lentosIlgis - laivoIlgis) + 1;
			for (int i = 0; i < laivoIlgis; i++) {
				// System.out.println(stulpelis + (eilute + i));
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute + i));
			}
		}
		return langeliai;
	}

	private void sauk() {
		if (!galimiSuviai.isEmpty()) {
			String stulpeliai = "";
			for (String k : galimiSuviai.keySet()) {
				stulpeliai = stulpeliai + k;
			}
			String stulpelis = "" + stulpeliai.charAt(0);
			int eilute = galimiSuviai.get(stulpelis);
			suviai.add(stulpelis + eilute);
			boolean pataike = zaidimas.sauk(stulpelis, eilute, this.zaidejoId);
			if (pataike) {
				this.nusautas = stulpelis + eilute;
				this.galimiSuviai = new HashMap<String, Integer>();
				sukurkGalimybiuSarasa(stulpelis, eilute);
			} else {
				galimiSuviai.remove(stulpelis);
			}
		} else {

			while (true) {
				String stulpelis = "" + abecele.charAt(new Random().nextInt(lentosPlotis));
				int eilute = new Random().nextInt(lentosIlgis) + 1;
				if (suviai.contains(stulpelis + eilute) == false) {
					suviai.add(stulpelis + eilute);
					boolean pataike = zaidimas.sauk(stulpelis, eilute, this.zaidejoId);
					if (pataike) {
						this.nusautas = stulpelis + eilute;
						sukurkGalimybiuSarasa(stulpelis, eilute);
					}
					break;
				}
			}
		}
	}

	private void sukurkGalimybiuSarasa(String stulpelis, int eilute) {
		if (eilute < lentosIlgis) {
			this.galimiSuviai.put(stulpelis, eilute + 1);
		}
		if (eilute > 1) {
			this.galimiSuviai.put(stulpelis, eilute - 1);
		}

		int stulpelioVieta = abecele.indexOf(stulpelis);
		if (stulpelioVieta < abecele.length() - 1) {
			String stulpelis1 = "" + abecele.charAt(stulpelioVieta + 1);
			this.galimiSuviai.put(stulpelis1, eilute);
		}

		if (stulpelioVieta > 0) {
			String stulpelis0 = "" + abecele.charAt(stulpelioVieta - 1);
			this.galimiSuviai.put(stulpelis0, eilute);
		}
		if (!galimiSuviai.isEmpty()) {
			tikrinkArSauta();
		}
	}

	private void tikrinkArSauta() {
		String trinti = "";
		for (String k : galimiSuviai.keySet()) {
			String suvis = k + galimiSuviai.get(k);
			if (suviai.contains(suvis)) {
				trinti = trinti + k;
			}
		}
		
		for (int i=0; i<trinti.length();i++) {
			galimiSuviai.remove(""+trinti.charAt(i));
		}
		
	}

	private void registruokis() {
		//DuomenuBaze duomenuBaze = new DuomenuBaze("D:/sarunas/Linos/LaivuMusisLina.db");
		//duomenuBaze.registruokZaideja(zaidejoId);
	}

}
