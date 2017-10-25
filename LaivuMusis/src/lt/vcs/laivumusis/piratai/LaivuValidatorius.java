package lt.vcs.laivumusis.piratai;

import org.apache.log4j.Logger;

import lt.vcs.laivumusis.common.ZaidimoLenta;

public class LaivuValidatorius {

	Laivas laivas;
	private boolean arSkaiciaiLygus = true;
	private boolean arSkaiciaiIsEiles = true;;
	private boolean arRaidesLygios = true;;
	private boolean arRaidesIsEiles = true;
	private boolean arLieciasi = false;

	private int lentosIlgis;
	private int lentosPlotis;

	static Logger log = Logger.getLogger(LaivuValidatorius.class.getName());

	public LaivuValidatorius(Laivas laivas, int lentosIlgis, int lentosPlotis) {
		this.laivas = laivas;
		this.lentosIlgis = lentosIlgis;
		this.lentosPlotis = lentosPlotis;
	}

	public boolean arPerduotosKoordinatesGeros() {
		log.info("labas vakaras");
		arSkaiciaiLygus();
		arSkaiciaiIsEiles();
		arRaidesLygios();
		arRaidesIsEiles();

		if ((arPaduotaVienaRaide() && arTeisingasLaivoIlgis() && arLaivuFormaGera()
				&& tikrinkArNeUzLentosRibu(laivas, lentosIlgis, lentosPlotis))) {
			return true;
		}
		return false;
	}

	private boolean arPaduotaVienaRaide() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			if (laivas.getLaivoKoordinates().get(i).getX().length() != 1) {
				System.err.println("Paduota daugiau nei viena raide");
				return false;
			}
		}
		return true;
	}

	private boolean arTeisingasLaivoIlgis() {
		if (laivas.laivoIlgis != laivas.getLaivoKoordinates().size()) {
			System.err.println("Laivas susideda is " + laivas.laivoIlgis + " langeliu");
			return false;
		}
		return true;
	}

	private void arSkaiciaiLygus() {
		if (laivas.laivoIlgis == 1) {
			return;
		}

		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			if (laivas.getLaivoKoordinates().get(i).getY() == laivas.getLaivoKoordinates().get(i + 1).getY()) {
				continue;

			} else {
				arSkaiciaiLygus = false;
			}

		}
	}

	private void arSkaiciaiIsEiles() {
		if (laivas.laivoIlgis == 1) {
			return;
		}

		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			if (Math.abs(
					laivas.getLaivoKoordinates().get(i).getY() - laivas.getLaivoKoordinates().get(i + 1).getY()) == 1) {
				continue;

			} else {
				arSkaiciaiIsEiles = false;
			}

		}
	}

	private void arRaidesLygios() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
			int b = laivas.getLaivoKoordinates().get(i + 1).getX().charAt(0);
			if (a == b) {
				continue;

			} else {
				arRaidesLygios = false;
			}

		}
	}

	public void arRaidesIsEiles() {
		if (laivas.laivoIlgis == 1) {
			return;
		}

		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
			int b = laivas.getLaivoKoordinates().get(i + 1).getX().charAt(0);
			if (Math.abs(a - b) == 1) {
				continue;

			} else {
				arRaidesIsEiles = false;
			}

		}
	}

	private boolean arLaivuFormaGera() {

		if (laivas.laivoIlgis == 1) {
			return true;
		}
		
		if ((arSkaiciaiLygus == arSkaiciaiIsEiles) || (arRaidesLygios == arRaidesIsEiles)) {
			System.err.println("Laivas lenktas");
			return false;
		}

		if ((arSkaiciaiLygus == arRaidesLygios) || (arSkaiciaiIsEiles == arRaidesIsEiles)) {
			System.err.println("Laivas lenktas 2");
			return false;
		}
		return true;

	}

	public boolean tikrinkArNeUzLentosRibu(Laivas laivas, int lentosIlgis, int lentosPlotis) {

		boolean arNeUzRibos = true;
		for (int i = 0; i < laivas.getLaivoIlgis(); i++) {

			if (laivas.getLaivoKoordinates().get(i).getY() < 1
					|| laivas.getLaivoKoordinates().get(i).getY() > lentosIlgis) {
				System.err.println("Laivo Y koordinate uz lentos ribu");
				arNeUzRibos = false;
			}

			if (laivas.getLaivoKoordinates().get(i).getX().charAt(0) < 1
					|| laivas.getLaivoKoordinates().get(i).getX().charAt(0) > lentosPlotis + 64) {
				System.err.println("Laivo X koordinate uz lentos ribu");
				arNeUzRibos = false;
			}

			if (!arNeUzRibos) {
				return false;
			}

		}
		return arNeUzRibos;
	}

	public boolean tikrinkArLieciasi(ZaidimoLenta zaidimoLenta) {
		for (int i = 0; i < laivas.laivoIlgis; i++) {
			String raidineKoordinate = ((lt.vcs.laivumusis.piratai.Langelis) laivas.getLaivoKoordinates().get(i))
					.getX();
			int skaitineKoordinate = ((lt.vcs.laivumusis.piratai.Langelis) laivas.getLaivoKoordinates().get(i)).getY();

			if (((lt.vcs.laivumusis.piratai.Langelis) zaidimoLenta.getLangeliai().get(raidineKoordinate)
					.get(skaitineKoordinate - 1)).getArGalimaDetiLaiva()) {
				arLieciasi = false;
			} else {
				arLieciasi = true;
				return arLieciasi;
			}
		}

		return arLieciasi;
	}

	public void neleiskLaivamLiestis(ZaidimoLenta zaidimoLenta) {
		for (int i = 0; i < laivas.laivoIlgis; i++) {

			((lt.vcs.laivumusis.piratai.Langelis) laivas.getLaivoKoordinates().get(i)).setArGalimaDetiLaiva();

			String raidineKoordinate = ((lt.vcs.laivumusis.piratai.Langelis) laivas.getLaivoKoordinates().get(i))
					.getX();
			int skaitineKoordinate = ((lt.vcs.laivumusis.piratai.Langelis) laivas.getLaivoKoordinates().get(i)).getY();

			for (int sk = -1; sk <= 1; sk++) {
				for (int sk2 = -1; sk2 <= 1; sk2++) {
					if (((raidineKoordinate.charAt(0) + sk) >= 65)
							&& ((raidineKoordinate.charAt(0) + sk) <= zaidimoLenta.getLangeliai().size() + 64)) {

						if (((skaitineKoordinate + sk2) >= 1) && ((skaitineKoordinate + sk2) <= zaidimoLenta
								.getLangeliai().get(raidineKoordinate).size())) {

							((lt.vcs.laivumusis.piratai.Langelis) zaidimoLenta.getLangeliai()
									.get("" + (char) (raidineKoordinate.charAt(0) + sk))
									.get((skaitineKoordinate + sk2) - 1)).setArGalimaDetiLaiva();
						}
					}
				}

			}
		}
	}
}