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
	Zaidimas zaidimas;
	private String zaidejoId;

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
					ZaidimoLenta zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					Thread.sleep(1000);
					break;
				case DalinemesLaivus:
					List<Laivas> laivuListas = zaidimas.duokLaivus(zaidejoId);

					for (int k = 0; k < laivuListas.size(); k++) {
						// Bandom kurti laiva
						int laivoPadetis = new Random().nextInt(50);
						int laivoIlgis = laivuListas.get(k).getLaivoIlgis();
						laivuListas.get(k).setKordinates(sugalvokLaivoKoordinates(laivoIlgis, laivoPadetis));
						zaidimas.pridekLaiva(laivuListas.get(k), this.zaidejoId);
						break;
					}
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

	private List<Langelis> sugalvokLaivoKoordinates(int laivoIlgis, int laivoPadetis) {
		List<Langelis> langeliai = new ArrayList<Langelis>();
		//Vienu atveju bus vertikalus, kitu atveju bus horizontalus
		if (laivoPadetis < 25) {
			//vertikalus
			
		} else {
			for (int i = 0; i < laivoIlgis; i++) {
				langeliai.add(new lt.vcs.laivumusis.piratai.Langelis("A", i + 1));
			}
		}
		return langeliai;
	}
}
