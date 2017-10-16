package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class ZaidejasLina implements lt.vcs.laivumusis.common.Zaidejas {
	private Zaidimas zaidimas;
	private String zaidejoId;

	private ZaidimoLenta zaidimoLenta;
	private String abecele = "";
	private int lentosIlgis;
	private int lentosPlotis;

	public ZaidejasLina(Zaidimas zaidimas) {
		this.zaidimas = zaidimas;
	}

	@Override
	public void run() {
		this.zaidejoId = zaidimas.registruokZaideja();
		System.out.println(this.zaidejoId);
		try {
			while (true) {

				switch (zaidimas.tikrinkBusena(zaidejoId)) {
				case DalinamesZemelapius:
					this.zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);

					for (String k : zaidimoLenta.getLangeliai().keySet()) {
						this.abecele = this.abecele + k;
					}

					this.lentosPlotis = zaidimoLenta.getLangeliai().keySet().size();
					this.lentosIlgis = zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size();

					Thread.sleep(1000);
					break;
					
				case DalinemesLaivus:
					List<Laivas> laivuListas = zaidimas.duokLaivus(zaidejoId);
					System.out.println("Lina gauna Laivus");
					for (int k = 0; k < laivuListas.size();k++) {
						// Bandom kurti laiva
						int laivoPadetis = new Random().nextInt(50);
						int laivoIlgis = laivuListas.get(k).getLaivoIlgis();
						laivuListas.get(k)
								.setKordinates(sugalvokLaivoKoordinates(laivoIlgis, laivoPadetis, zaidimoLenta));
						zaidimas.pridekLaiva(laivuListas.get(k), this.zaidejoId);		
					}
					
					break;
				case PriesasLaimejo:
					break;
				case PriesininkoEile:
					break;
				case Registracija:
					break;
				case RikiuojamLaivus:
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

	public void padekLaivusAntLentos() {
		for (int i = 0; i < zaidimas.duokLaivus("1").size(); i++)
			zaidimas.pridekLaiva(zaidimas.duokLaivus("1").get(i), "1");
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
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute));
			}
		} else {
			// vertikalus
			String stulpelis = "" + abecele.charAt(new Random().nextInt(lentosPlotis));
			int eilute = new Random().nextInt(lentosIlgis - laivoIlgis) + 1;
			for (int i = 0; i < laivoIlgis; i++) {
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis(stulpelis, eilute));
			}
		}
		return langeliai;
	}
}
