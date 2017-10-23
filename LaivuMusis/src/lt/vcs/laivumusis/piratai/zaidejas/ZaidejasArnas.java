package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;

public class ZaidejasArnas implements lt.vcs.laivumusis.common.Zaidejas {

	private Zaidimas zaidimas;

	private String zaidejoId;

	List<Laivas> laivai = new ArrayList<Laivas>();
	ZaidimoLenta zaidimoLenta;
	List<String> pasautiLangeliai = new ArrayList<String>();

	public ZaidejasArnas(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public void run() {

		boolean ArDarTestiZaidima = true;
		try {
			while (ArDarTestiZaidima) {

				switch (zaidimas.tikrinkBusena(zaidejoId)) {
				case Registracija:
					registruokis();
					zaidimas.registruokZaideja(zaidejoId);
					Thread.sleep(1000);
					break;
				case DalinamesZemelapius:
					zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					Thread.sleep(1000);
					break;
				case DalinemesLaivus:
					laivai = zaidimas.duokLaivus(zaidejoId);
					Thread.sleep(1000);
					break;
				case RikiuojamLaivus:
					for (int i = 0; i < laivai.size(); i++) {

						try {
							laivai.get(i).setKordinates(galvokKoordinates(laivai.get(i)));
							zaidimas.pridekLaiva(laivai.get(i), zaidejoId);
						} catch (LaivuPridejimoKlaida e) {
							e.printStackTrace();
							i--;
						}
					}

					break;
				case PriesininkoEile:
					Thread.sleep(500);
					break;

				case TavoEile:
					sauk();

					break;
				case TuLaimejai:
					System.out.println(this.zaidejoId + " laimejo");
					ArDarTestiZaidima = false;
					zaidimas.skaiciuokStatistika();
					break;
				case PriesasLaimejo:
					System.out.println(this.zaidejoId + " pralaimejo");
					ArDarTestiZaidima = false;
					zaidimas.skaiciuokStatistika();
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

	private void sauk() {
		boolean arILangeliSauta = false;
		while (!arILangeliSauta) {
			String x = "" + (char) (new Random().nextInt(zaidimoLenta.getLangeliai().keySet().size()) + 65);
			int y = new Random().nextInt(zaidimoLenta.getLangeliai().get("A").size()) + 1;

			if (!pasautiLangeliai.contains(x + y)) {
				pasautiLangeliai.add(x + y);
				zaidimas.sauk(x, y, zaidejoId);
				arILangeliSauta = true;
			}
		}
	}

	private List<Langelis> galvokKoordinates(Laivas laivas) {
		// 0 horizontalus, 1 vertikalus
		int laivosPadetis = new Random().nextInt(2);

		List<Langelis> langeliuListas = new ArrayList<Langelis>();

		int x = new Random().nextInt(zaidimoLenta.getLangeliai().keySet().size()) + 65;
		int y = new Random().nextInt(zaidimoLenta.getLangeliai().get("A").size()) + 1;

		if (laivosPadetis == 1) {

			for (int i = 0; i < laivas.getLaivoIlgis(); i++) {
				langeliuListas.add(new lt.vcs.laivumusis.piratai.Langelis("" + (char) x, y + i));
			}
		}

		if (laivosPadetis == 0) {

			for (int i = 0; i < laivas.getLaivoIlgis(); i++) {

				langeliuListas.add(new lt.vcs.laivumusis.piratai.Langelis("" + (char) (x + i), y));
			}
		}

		return langeliuListas;
	}

	private void registruokis() {
		DuomenuBaze duomenuBaze = new DuomenuBaze("C:/Users/Arn/Desktop/LaivuMusis Db/LaivuMusis.db");
		duomenuBaze.registruokZaideja(zaidejoId);
	}

}
