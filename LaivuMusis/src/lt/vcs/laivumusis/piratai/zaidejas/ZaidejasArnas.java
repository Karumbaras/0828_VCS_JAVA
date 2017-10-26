package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.LaivuValidatorius;
import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;

public class ZaidejasArnas implements lt.vcs.laivumusis.common.Zaidejas {
	
	static Logger log = Logger.getLogger(ZaidejasArnas.class.getName());

	private Zaidimas zaidimas;

	private String zaidejoId;

	private ZaidimoLenta zaidimoLenta;
	private List<String> pasautiLangeliai = new ArrayList<String>();

	private List<Laivas> laivai = new ArrayList<Laivas>();

	public ZaidejasArnas(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public void run() {

		boolean ArDarTestiZaidima = true;
		boolean arPrisiregistravo = true;
		try {
			while (ArDarTestiZaidima) {

				switch (zaidimas.tikrinkBusena(zaidejoId)) {
				case Registracija:
					if (arPrisiregistravo) {
						registruokis();
						zaidimas.registruokZaideja(zaidejoId);
						Thread.sleep(500);
						arPrisiregistravo = false;
					}
					break;
				case DalinamesZemelapius:
					zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					Thread.sleep(500);
					break;
				case DalinemesLaivus:
					laivai = zaidimas.duokLaivus(zaidejoId);
					Thread.sleep(500);
					break;
				case RikiuojamLaivus:
					for (int i = 0; i < laivai.size(); i++) {

						try {
							laivai.get(i).setKordinates(galvokKoordinates(laivai.get(i)));
							zaidimas.pridekLaiva(laivai.get(i), zaidejoId);
						} catch (LaivuPridejimoKlaida e) {
							log.error("Nepavyko prideti laivo", e);
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
			log.error("InterruptedException", e);
		}
	}

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}

	private void sauk() {
		boolean arILangeliSauta = false;

		while (!arILangeliSauta) {

			int x = (new Random().nextInt(zaidimoLenta.getLangeliai().keySet().size()) + 65);
			int y = new Random().nextInt(zaidimoLenta.getLangeliai().get("A").size()) + 1;

			if (!pasautiLangeliai.contains("" + (char) x + y)) {
				pasautiLangeliai.add("" + (char) x + y);

				if (zaidimas.sauk("" + (char) x, y, zaidejoId)) {
					darykKadNesautuIstrizai(x, y);

				}
				arILangeliSauta = true;

			}
		}
	}

	private void darykKadNesautuIstrizai(int x, int y) {
		for (int sk = -1; sk <= 1; sk++) {
			for (int sk2 = -1; sk2 <= 1; sk2++) {
				if (sk == 0 || sk2 == 0) {
					continue;
				}
				if (((x + sk) >= 65) && (x + sk) <= zaidimoLenta.getLangeliai().size() + 64) {

					if (((y + sk2) >= 1) && ((y + sk2) <= zaidimoLenta.getLangeliai().get("" + (char) x).size())) {

						pasautiLangeliai.add("" + (char) (x + sk) + (y + sk2));

					}
				}
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
