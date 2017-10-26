package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.grafika.Grafika;

public class Zaidimas implements lt.vcs.laivumusis.common.Zaidimas {
	// Zaidejas
	private static int zaidejuSkaicius;
	private String zaidejoId1;
	private String zaidejoId2;
	private int soveKartu1;
	private int soveKartu2;
	private int taiklusSuviai1;
	private int taiklusSuviai2;
	private Busena zaidejoBusenaId1 = Busena.TavoEile;
	private Busena zaidejoBusenaId2 = Busena.PriesininkoEile;

	// ZaidimoLenta
	private ZaidimoLenta zaidimoLenta1;
	private ZaidimoLenta zaidimoLenta2;
	private int lentosIlgis = 10;
	private int lentosPlotis = 10;
	private boolean arGavoLentaId1;
	private boolean arGavoLentaId2;

	// Laivai
	private List<Laivas> laivai1;
	private List<Laivas> laivai2;
	private int[][] laivuMasyvas = { { 1, 4 }, { 2, 3 }, { 3, 2 }, { 4, 1 } };
	private boolean arGavoLaivusId1;
	private boolean arGavoLaivusId2;

	private int laivuKiekis;
	private int padetaLaivuId1;
	private int padetaLaivuId2;

	// Zaidimas
	private static Busena zaidimoBusena = Busena.Registracija;

	// Vaizdas
	private Vaizdas vaizdas1;
	private Vaizdas vaizdas2;


	@Override
	public void run() {
		// Nebereikalingas metodas

	}

	public Zaidimas() {

		this.zaidimoLenta1 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.zaidimoLenta2 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.vaizdas1 = new Vaizdas(this.zaidimoLenta1);
		this.vaizdas2 = new Vaizdas(this.zaidimoLenta2);

		// Masyvas laivu ilgiams [laivuKiekis] [laivuIlgis]
		this.laivai1 = sukurkLaivuSarasa(this.laivuMasyvas);
		this.laivai2 = sukurkLaivuSarasa(this.laivuMasyvas);
		this.laivuKiekis = this.laivai1.size();
	}

	public Zaidimas(int lentosIlgis, int lentosPlotis, int[][] laivuMasyvas) {
		// patikrinimas, ar gerai paduotas masyvas

		for (int i = 0; i < laivuMasyvas.length; i++) {
			if (laivuMasyvas[i].length != 2) {
				System.err.println("Blogas laivu sarasas! Masyvas turi talpinti 2 elementu"
						+ " ilgio masyvus, kuriu pirmasis narys reiskia laivu kieki," + " o antrasis laivo ilgi");
				return;
			}
		}

		// TODO turetu patikrinti, ar tilps laivai i lenta
		this.lentosIlgis = lentosIlgis;
		this.lentosPlotis = lentosPlotis;
		this.zaidimoLenta1 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.zaidimoLenta2 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.vaizdas1 = new Vaizdas(this.zaidimoLenta1);
		this.vaizdas2 = new Vaizdas(this.zaidimoLenta2);
		this.laivuMasyvas = laivuMasyvas;
		this.laivai1 = sukurkLaivuSarasa(laivuMasyvas);
		this.laivai2 = sukurkLaivuSarasa(laivuMasyvas);
		this.laivuKiekis = this.laivai1.size();

	}

	private List<Laivas> sukurkLaivuSarasa(int[][] laivuMasyvas) {
		List<Laivas> laivuSarasas = new ArrayList<Laivas>();
		for (int i = 0; i < laivuMasyvas.length; i++) {
			int kiek = laivuMasyvas[i][0];
			int laivoIlgis = laivuMasyvas[i][1];
			for (int k = 0; k < kiek; k++) {
				laivuSarasas.add(new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis));
			}
		}
		return laivuSarasas;
	}

	@Override
	public List<ZaidimoLenta> getLentos() {
		// TODO paslepti nuo zaidejo
		List<ZaidimoLenta> lenta = new ArrayList<ZaidimoLenta>();
		lenta.add(zaidimoLenta1);
		lenta.add(zaidimoLenta2);
		return lenta;
	}

	@Override
	public synchronized void skaiciuokStatistika() {
		System.out.println(this.zaidejoId1 + " sove " + this.soveKartu1 + " kartu");
		System.out.println(this.zaidejoId2 + " sove " + this.soveKartu2 + " kartu");
		System.out.println(this.zaidejoId1 + " taikliu suviu skaicius " + this.taiklusSuviai1);
		System.out.println(this.zaidejoId2 + " taikliu suviu skaicius " + this.taiklusSuviai2);
	}

	@Override
	public synchronized Busena tikrinkBusena(String zaidejoId) {
		if (zaidimoBusena == Busena.Registracija || zaidimoBusena == Busena.DalinamesZemelapius
				|| zaidimoBusena == Busena.DalinemesLaivus || zaidimoBusena == Busena.RikiuojamLaivus) {
			return zaidimoBusena;
		}
		if (zaidejoId == this.zaidejoId1) {
			return zaidejoBusenaId1;
		}
		if (zaidejoId == this.zaidejoId2) {
			return zaidejoBusenaId2;
		}
		return zaidimoBusena;
	}

	@Override
	public synchronized boolean registruokZaideja(String zaidejoId) {
		if (zaidejuSkaicius >= 2) {
			System.out.println("Jau uzregistruoti 2 zaidejai");
			return false;
		}
		if (zaidejoId1 == zaidejoId) {
			System.err.println("Toks þaidëjo ID jau egzistuoja!");
			return false;
		}

		if (zaidejoId1 != null) {
			this.zaidejoId2 = zaidejoId;
			zaidejuSkaicius++;
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Grafika.zaidejoID2.setText(zaidejoId2);
				}
			});
			
			
			zaidimoBusena = Busena.DalinamesZemelapius;
			System.out.println("Uzregistruotas 2 zaidejas!");
			return true;
		} else {
			zaidejuSkaicius++;
			this.zaidejoId1 = zaidejoId;
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Grafika.zaidejoID1.setText(zaidejoId1);
				}
			});
			
			
			System.out.println("Uzregistruotas 1 zaidejas!");
		}

		return true;

	}

	// Grazina ar pasautas laivas
	@Override
	public synchronized boolean sauk(String x, int y, String zaidejoId) {

		// zaidejo ID tas kuris sauna
		if (zaidejoId == this.zaidejoId1 && zaidejoBusenaId1 == Busena.TavoEile) {
			Langelis sove = this.zaidimoLenta2.getLangeliai().get(x).get(y - 1);
			sove.sauk();
			soveKartu1++;
			if (saukPagalba(sove, laivai2)) {
				if (arLaimejo(laivai2)) {
					zaidejoBusenaId1 = Busena.TuLaimejai;
					zaidejoBusenaId2 = Busena.PriesasLaimejo;
				}
				pieskLentas();
				taiklusSuviai1++;
				return true;
			} else {
				zaidejoBusenaId1 = Busena.PriesininkoEile;
				zaidejoBusenaId2 = Busena.TavoEile;
				pieskLentas();
			}
		}

		if (zaidejoId == this.zaidejoId2 && zaidejoBusenaId2 == Busena.TavoEile) {
			Langelis sove = this.zaidimoLenta1.getLangeliai().get(x).get(y - 1);
			sove.sauk();
			soveKartu2++;
			if (saukPagalba(sove, laivai1)) {
				if (arLaimejo(laivai1)) {
					zaidejoBusenaId2 = Busena.TuLaimejai;
					zaidejoBusenaId1 = Busena.PriesasLaimejo;
				}
				pieskLentas();
				taiklusSuviai2++;
				return true;
			} else {
				zaidejoBusenaId2 = Busena.PriesininkoEile;
				zaidejoBusenaId1 = Busena.TavoEile;
				pieskLentas();
			}
		}
		return false;
	}

	private boolean saukPagalba(Langelis soveID, List<Laivas> laivai) {
		// einam per langeliu masyva tikrindami ar sutampa koordinates
		// kur sauta su esasnciu laivu langelyje
		for (Laivas a : laivai) {
			for (Langelis b : a.getLaivoKoordinates()) {
				if (b == soveID) {
					System.out.println("Laivas pasautas");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public synchronized List<Laivas> duokLaivus(String zaidejoId) {

		if (zaidejoId != this.zaidejoId1 && zaidejoId != this.zaidejoId2) {
			System.err.println("Toks zaidejas nedalyvauja zaidime!");
			return null;
		}

		List<Laivas> laivai = new ArrayList<Laivas>();
		if (zaidejoId == this.zaidejoId1) {
			laivai = sukurkLaivuSarasa(laivuMasyvas);
			arGavoLaivusId1 = true;
		}

		if (zaidejoId == this.zaidejoId2) {
			laivai = sukurkLaivuSarasa(laivuMasyvas);
			arGavoLaivusId2 = true;
		}

		if (arGavoLaivusId1 && arGavoLaivusId2) {
			zaidimoBusena = Busena.RikiuojamLaivus;
		}

		return laivai;
	}

	@Override
	public synchronized ZaidimoLenta duokZaidimoLenta(String zaidejoId) {
		ZaidimoLenta zl;
		if (zaidejoId == this.zaidejoId1 || zaidejoId == this.zaidejoId2) {
			zl = new lt.vcs.laivumusis.piratai.ZaidimoLenta(this.lentosIlgis, this.lentosPlotis);
		} else {
			System.err.println("Toks zaidejas nedalyvauja zaidime, lentos duoti negalime");
			zl = new lt.vcs.laivumusis.piratai.ZaidimoLenta(0, 0);
		}

		if (zaidejoId == this.zaidejoId1) {
			arGavoLentaId1 = true;
		}
		if (zaidejoId == this.zaidejoId2) {
			arGavoLentaId2 = true;
		}

		if (arGavoLentaId1 & arGavoLentaId2) {
			zaidimoBusena = Busena.DalinemesLaivus;
		}
		System.out.println("Paduota zaidimo lenta!");

		return zl;
	}

	@Override
	public synchronized void pridekLaiva(lt.vcs.laivumusis.common.Laivas laivas, String zaidejoId)
			throws LaivuPridejimoKlaida {

		LaivuValidatorius validatorius = new LaivuValidatorius((lt.vcs.laivumusis.piratai.Laivas) laivas, lentosIlgis,
				lentosPlotis);

		if (!validatorius.arPerduotosKoordinatesGeros()) {
			throw new LaivuPridejimoKlaida("Perduotos blogos kordinates");
		}

		/*
		 * if (zaidejoId == zaidejoId1) { validatorius.tikrinkArLieciasi(zaidimoLenta1);
		 * } else validatorius.tikrinkArLieciasi(zaidimoLenta2);
		 */

		if (zaidejoId == zaidejoId1) {
			if (!validatorius.tikrinkArLieciasi(zaidimoLenta1)) {
				if (padekZaidejoLaiva(laivas, this.zaidimoLenta1, laivai1)) {
					padetaLaivuId1++;
					validatorius.neleiskLaivamLiestis(zaidimoLenta1);
				}
			} else {
				throw new LaivuPridejimoKlaida("Laivai lieciasi");
			}
		}

		if (zaidejoId == zaidejoId2) {
			if (!validatorius.tikrinkArLieciasi(zaidimoLenta2)) {
				if (padekZaidejoLaiva(laivas, this.zaidimoLenta2, laivai2)) {
					padetaLaivuId2++;
					validatorius.neleiskLaivamLiestis(zaidimoLenta2);
				}
			} else {
				throw new LaivuPridejimoKlaida("Laivai lieciasi");
			}
		}

		if (padetaLaivuId1 == laivuKiekis && padetaLaivuId2 == laivuKiekis) {
			System.out.println();
			pieskLentas();
			zaidimoBusena = Busena.PriesininkoEile;
		}
	}

	private synchronized boolean padekZaidejoLaiva(lt.vcs.laivumusis.common.Laivas laivas, ZaidimoLenta zaidimoLenta,
			List<Laivas> laivuSarasas) {

		int laivoIlgis = laivas.getLaivoIlgis();

		// sukuriamas naujas listas tam, kad padaryti koordinaciu kopija
		// koordinates i nauja list'a priskiraiamos is lentos
		List<Langelis> koordinates = new ArrayList<Langelis>();
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			String x = laivas.getLaivoKoordinates().get(i).getX();
			int y = laivas.getLaivoKoordinates().get(i).getY();
			koordinates.add(zaidimoLenta.getLangeliai().get(x).get(y - 1));
		}

		// zaidejo laivu list'e randamas laivas reikalingo ilgio, kuriam nepriskirtos
		// koordinates ir jis istrinamas, o vietoje jo pridedamas naujas su naujom
		// lentos koordinatemis
		for (int i = 0; i < laivuSarasas.size(); i++) {

			if (laivuSarasas.get(i).getLaivoIlgis() == laivoIlgis & laivuSarasas.get(i).getLaivoKoordinates() == null) {
				laivuSarasas.remove(i);
				lt.vcs.laivumusis.piratai.Laivas naujasLaivas = new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis);
				naujasLaivas.setKordinates(koordinates);
				laivuSarasas.add(naujasLaivas);
				return true;
			}
		}
		return false;
	}

	private boolean arLaimejo(List<Laivas> LaivuListas) {
		for (Laivas laivas : LaivuListas) {
			if (laivas.arNusautas() == false) {
				return false;
			}
		}
		return true;
	}

	private void pieskLentas() {
		// vaizdas1.isvalyk();
		vaizdas1.pieskVaizda();
		vaizdas2.pieskVaizda();
		System.out.println();
	}
	
	public String getZaidejoId1() {
		return this.zaidejoId1;
	}
	
	public String getZaidejoId2() {
		return this.zaidejoId2;
	}

}
