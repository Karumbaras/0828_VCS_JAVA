package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.Vaizdas;

public class ZaidejasLina implements lt.vcs.laivumusis.common.Zaidejas {
	private Zaidimas zaidimas;
	private String zaidejoId;

	private ZaidimoLenta zaidimoLenta;
	private String abecele = "";
	private int lentosIlgis;
	private int lentosPlotis;
	private List<Laivas> laivuListas = new ArrayList<Laivas>();

	public ZaidejasLina(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public void run() {
		List<Laivas> laivuListas;
		boolean arUzregistravo = true;
		while (arUzregistravo) {
			if (zaidimas.registruokZaideja(this.zaidejoId)) {
				arUzregistravo = false;
			}
		}
		System.out.println(this.zaidejoId);
		try {
			while (true) {
				switch (zaidimas.tikrinkBusena(zaidejoId)) {

				case Registracija:

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
						// Bandom kurti laiva
						try {
							Thread.sleep(new Random().nextInt(2000));
							int laivoPadetis = new Random().nextInt(50);
							int laivoIlgis = this.laivuListas.get(k).getLaivoIlgis();
							this.laivuListas.get(k)
									.setKordinates(sugalvokLaivoKoordinates(laivoIlgis, laivoPadetis, zaidimoLenta));

							zaidimas.pridekLaiva(this.laivuListas.get(k), this.zaidejoId);
						} catch (Exception e) {
							k--;
						}
					}
					
					/*List<Langelis> langeliai = new ArrayList<Langelis>();
					for (int i = 0; i < this.laivuListas.get(0).getLaivoIlgis(); i++) {
						langeliai.add(new lt.vcs.laivumusis.piratai.Langelis("A", i+1));
					}
					
					this.laivuListas.get(0)
					.setKordinates(langeliai);
					try {
					zaidimas.pridekLaiva(this.laivuListas.get(0), this.zaidejoId);
					zaidimas.sauk("A", 1, this.zaidejoId);
					zaidimas.sauk("C", 3, this.zaidejoId);
					for (ZaidimoLenta zl:zaidimas.getLentos()) {
						//new Vaizdas(zl).pieskVaizda();
					}
					} catch (Exception e) {

					}
					break;*/
					
				case PriesasLaimejo:
					break;
				case PriesininkoEile:
					break;

				case TavoEile:
					break;
				case TuLaimejai:
					break;
				default:
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
				System.out.println(stulpelis + eilute);
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute));
			}
		} else {
			// vertikalus
			String stulpelis = "" + abecele.charAt(new Random().nextInt(lentosPlotis));
			int eilute = new Random().nextInt(lentosIlgis - laivoIlgis) + 1;
			for (int i = 0; i < laivoIlgis; i++) {
				System.out.println(stulpelis + (eilute + i));
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute + i));
			}
		}
		return langeliai;
	}
}
