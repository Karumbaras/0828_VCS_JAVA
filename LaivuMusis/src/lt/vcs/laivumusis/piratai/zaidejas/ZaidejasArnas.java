package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class ZaidejasArnas {
	public class Zaidejas implements lt.vcs.laivumusis.common.Zaidejas {
		private Zaidimas zaidimas;
		private String zaidejoId;
		List<Laivas> laivai = new ArrayList<Laivas>();
		ZaidimoLenta zaidimoLenta;

		public Zaidejas(Zaidimas zaidimas, String zaidejoId) {
			this.zaidimas = zaidimas;
			this.zaidejoId = zaidejoId;
		}

		@Override
		public void run() {
			if (zaidimas.registruokZaideja(zaidejoId)) {
				;
				try {
					while (true) {
						Busena zaidimoBusena = zaidimas.tikrinkBusena(zaidejoId);
						switch (zaidimoBusena) {
						case DalinamesZemelapius:
							zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
							Thread.sleep(2000);
							continue;
						case DalinemesLaivus:
							laivai = zaidimas.duokLaivus(zaidejoId);
							Thread.sleep(2000);
							continue;
						case RikiuojamLaivus:
						case PriesasLaimejo:
							break;
						case PriesininkoEile:
							break;
						case Registracija:
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
		}

		@Override
		public Zaidimas getZaidimas() {
			return this.zaidimas;
		}

		private void priskirkKoodinatesLaivui() {
			for (int i = 0; i < laivai.size(); i++) {
				for (int sk = 0; i < laivai.get(i).getLaivoIlgis(); sk++) {
					laivai.get(i).setKordinates(galvokKoordinates(laivai.get(i).getLaivoIlgis()));
				}

			}
		}

		private List<Langelis> galvokKoordinates(int laivoIlgis) {
			int laivosPadetis = new Random().nextInt(1);
			List<Langelis> langeliuListas = new ArrayList<Langelis>();
			Map<String, List<Langelis>> lentosLangeliai = zaidimoLenta.getLangeliai();
			int ilgis = lentosLangeliai.keySet().size();
			int plotis = lentosLangeliai.get(0).size();

			return langeliuListas;
		}
	}
}