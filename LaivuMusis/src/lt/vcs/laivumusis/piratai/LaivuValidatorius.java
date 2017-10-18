package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.common.ZaidimoLenta;

public class LaivuValidatorius {

	Laivas laivas;
	private boolean arSkaiciaiLygus = false;
	private boolean arSkaiciaiIsEiles = false;
	private boolean arRaidesLygios = false;
	private boolean arRaidesIsEiles = false;
	
	private int lentosIlgis;
	private int lentosPlotis;

	public LaivuValidatorius(Laivas laivas, int lentosIlgis, int lentosPlotis) {
		this.laivas = laivas;
		this.lentosIlgis = lentosIlgis;
		this.lentosPlotis = lentosPlotis;
	}

	public boolean arPerduotosKoordinatesGeros() {
		
		arTeisingasLaivoIlgis();
		arPaduotaVienaRaide();
		arSkaiciaiLygus();
		arSkaiciaiIsEiles();
		arRaidesLygios();
		arRaidesIsEiles();
		
		if ((arPaduotaVienaRaide() && arTeisingasLaivoIlgis() && arLaivuFormaGera() && tikrinkArNeUzLentosRibu(laivas, lentosIlgis, lentosPlotis))) {
			return true;
		}
		return false;
	}

	private boolean arPaduotaVienaRaide() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			if (laivas.getLaivoKoordinates().get(i).getX().length() != 1) {
				System.out.println("blogos koordinates");
				return false;
			}
		}
		return true;
	}

	private boolean arTeisingasLaivoIlgis() {
		if (laivas.laivoIlgis != laivas.getLaivoKoordinates().size()) {
			System.out.println("Error: Laivas susideda is " + laivas.laivoIlgis + " langeliu");
			return false;
		}
		return true;
	}

	private boolean arSkaiciaiLygus() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				if (laivas.getLaivoKoordinates().get(i).getY() == laivas.getLaivoKoordinates().get(i + sk).getY()) {
					arSkaiciaiLygus = true;

				}
			}
		}
		return false;
	}

	private boolean arSkaiciaiIsEiles() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				if (Math.abs(laivas.getLaivoKoordinates().get(i).getY()
						- laivas.getLaivoKoordinates().get(i + sk).getY()) == 1) {
					arSkaiciaiIsEiles = true;
				}
			}
		}
		return false;
	}

	private boolean arRaidesLygios() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
				int b = laivas.getLaivoKoordinates().get(i + sk).getX().charAt(0);
				if (a == b) {
					arRaidesLygios = true;
				}
			}
		}
		return false;
	}

	private boolean arRaidesIsEiles() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
				int b = laivas.getLaivoKoordinates().get(i + sk).getX().charAt(0);
				if (Math.abs(a - b) == 1) {
					arRaidesIsEiles = true;
				}
			}
		}
		return false;
	}

	private boolean arLaivuFormaGera() {

		if ((arSkaiciaiLygus == arSkaiciaiIsEiles) || (arRaidesLygios == arRaidesIsEiles)) {
			System.out.println("Laivas lenktas");
			return false;
		}

		if ((arSkaiciaiLygus == arRaidesLygios) || (arSkaiciaiIsEiles == arRaidesIsEiles)) {
			System.out.println("Laivas lenktas 2");
			return false;
		}
		return true;

	}

	private boolean tikrinkArNeUzLentosRibu(Laivas laivas, int lentosIlgis, int lentosPlotis) {

		for (int i = 0; i < laivas.getLaivoIlgis(); i++) {

			if (laivas.getLaivoKoordinates().get(i).getY() < 1
					|| laivas.getLaivoKoordinates().get(i).getY() > lentosIlgis) {
				System.out.println("Laivo Y koordinate uz lentos ribu");
				return true;
			}

			int laivoXKoordinate = laivas.getLaivoKoordinates().get(i).getX().charAt(0) - 64;

			if (laivoXKoordinate < 1 || laivoXKoordinate > 26) {
				System.out.println("Laivo X koordinate uz lentos ribu");
				return false;
			}

		}
		return true;
	}

	public boolean tikrinkArLieciasi(ZaidimoLenta zaidimoLenta) {
		for (int i = 0; i < laivas.laivoIlgis; i++) {
			String raidineKoordinate = laivas.getLaivoKoordinates().get(i).getX();
			int skaitineKoordinate = laivas.getLaivoKoordinates().get(i).getY();

			if (((lt.vcs.laivumusis.piratai.Langelis) zaidimoLenta.getLangeliai().get(raidineKoordinate).get(skaitineKoordinate-1))
					.getArGalimaDetiLaiva()) {
				return false;
			}
		}
		return true;
	}
	
	public void neleiskLaivamLiestis(ZaidimoLenta zaidimoLenta) {
		for (int i = 0; i < laivas.laivoIlgis; i++) {

			((lt.vcs.laivumusis.piratai.Langelis) laivas.getLaivoKoordinates().get(i)).setArGalimaDetiLaiva();

			String raidineKoordinate = laivas.getLaivoKoordinates().get(i).getX();
			int skaitineKoordinate = laivas.getLaivoKoordinates().get(i).getY();
			
			for (int sk = -1; sk <= 1; sk++) {
				for (int sk2 = -1; sk2 <= 1; sk2++) {
					if (((raidineKoordinate.charAt(0) + sk) >= 65)
							&& ((raidineKoordinate.charAt(0) + sk) <= zaidimoLenta.getLangeliai().size()+64)) {

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