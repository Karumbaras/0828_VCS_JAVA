package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import sun.security.action.GetLongAction;

public class Zaidimas implements lt.vcs.laivumusis.common.Zaidimas {

	private static int zaidejuSkaicius;
	private String zaidejoId1;
	private String zaidejoId2;
	private ZaidimoLenta zaidimoLenta1;
	private ZaidimoLenta zaidimoLenta2;
	private List<Laivas> laivai1;
	private List<Laivas> laivai2;
	private int lentosIlgis = 10;
	private int lentosPlotis = 10;
	private int soveKartu1;
	private int soveKartu2;
	private int taiklusSuviai1;
	private int taiklusSuviai2;
	private static Busena zaidimoBusena = Busena.Registracija;
	private boolean arGavoLentaId1;
	private boolean arGavoLentaId2;
	private boolean arGavoLaivusId1;
	private boolean arGavoLaivusId2;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public Zaidimas() {
		// TODO Pakoreguoti, kad grazesnis kodas butu
		this.zaidimoLenta1 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.zaidimoLenta2 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.laivai1 = new ArrayList<Laivas>();
		this.laivai2 = new ArrayList<Laivas>();
		// masyvas laivu ilgiams.
		// Masyvas [laivuKiekis] [laivuIlgis]
		int[][] laivuMasyvas = { { 1, 4 }, { 2, 3 }, { 3, 3 }, { 4, 1 } };
		for (int i = 0; i < laivuMasyvas.length; i++) {
			int kiek = laivuMasyvas[i][0];
			int laivoIlgis = laivuMasyvas[i][1];
			for (int k = 0; k < kiek; k++) {
				this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis, this));
				this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis, this));
			}
		}

	}

	public Zaidimas(int lentosIlgis, int lentosPlotis, int[][] laivuMasyvas) {
		// patikrinimas, ar gerai paduotas masyvas
		for (int i = 0; i < laivuMasyvas.length; i++) {
			if (laivuMasyvas[i].length != 2) {
				System.out.println("Blogas laivu sarasas! Masyvas turi talpinti 2 elementu"
						+ " ilgio masyvus, kuriu pirmasis narys reiskia laivu kieki," + " o antrasis laivo ilgi");
				return;
			}
		}

		// TODO turetu patikrinti, ar tilps laivai i lenta
		this.lentosIlgis = lentosIlgis;
		this.lentosPlotis = lentosPlotis;
		this.laivai1 = new ArrayList<Laivas>();
		this.laivai2 = new ArrayList<Laivas>();

		for (int i = 0; i < laivuMasyvas.length; i++) {
			int kiek = laivuMasyvas[i][0];
			int laivoIlgis = laivuMasyvas[i][1];
			for (int k = 0; k < kiek; k++) {
				this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis, this));
				this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis, this));
			}
		}

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
	public void skaiciuokStatistika() {
		System.out.println("Pirmasis zaidejas sove " + this.soveKartu1 + " kartu");
		System.out.println("Antrasis zaidejas sove " + this.soveKartu2 + " kartu");
		System.out.println("Pirmo zaidejo taikliu suviu skaicius " + this.taiklusSuviai1);
		System.out.println("Pirmo zaidejo taikliu suviu skaicius " + this.taiklusSuviai2);

	}

	@Override
	public Busena tikrinkBusena(String zaidejoId) {
		return zaidimoBusena;
	}

	@Override
	public synchronized String registruokZaideja() {
		if (zaidejuSkaicius >= 2) {
			System.out.println("Jau uzregistruoti 2 zaidejai");
			return null;
		}
		if (zaidejoId1 != null) {
			this.zaidejoId2 = "zaidejoId2" + new Random().nextInt(100);
			zaidejuSkaicius++;
			zaidimoBusena = Busena.DalinamesZemelapius;
			System.out.println("Uzregistruotas 2 zaidejas!");
			return this.zaidejoId2;
		}
		zaidejuSkaicius++;
		System.out.println("Uzregistruotas 1 zaidejas!");
		return this.zaidejoId1 = "zaidejoId1" + new Random().nextInt(100);

	}

	// Grazina ar pasautas laivas
	@Override
	public synchronized boolean sauk(String x, int y, String zaidejoId) {
		Langelis soveId1 = this.zaidimoLenta2.getLangeliai().get(x).get(y - 1);
		Langelis soveId2 = this.zaidimoLenta1.getLangeliai().get(x).get(y - 1);
		// zaidejo ID tas kuris sauna
		if (zaidejoId == this.zaidejoId1) {
			this.soveKartu1++;
			// einam per langeliu masyva tikrindami ar sutampa koordinates
			// kur sauta su esasnciu laivu langelyje
			for (Laivas a : laivai2) {
				for (Langelis b : a.getLaivoKoordinates()) {
					if (b == soveId1) {
						System.out.println("Laivas pasautas");
						this.taiklusSuviai1++;
						return true;

					}
				}
			}
		} else if (zaidejoId == this.zaidejoId2) {
			this.soveKartu2++;
			for (Laivas a : laivai1) {
				for (Langelis b : a.getLaivoKoordinates()) {
					if (b == soveId2) {
						System.out.println("Laivas pasautas");
						this.taiklusSuviai2++;
						return true;
					}
				}
			}
		}
		System.out.println("Tokio zaidejo nera");
		return false;
	}

	@Override
	public synchronized List<Laivas> duokLaivus(String zaidejoId) {
		// Kolas nera kopija, o nurodo i tuos paciu objektus - reikia pakeisti!

		if (zaidejoId != this.zaidejoId1 & zaidejoId != this.zaidejoId2) {
			System.out.println("Toks zaidejas nedalyvauja zaidime!");
			return null;
		}

		System.out.println("Zaidimas paduoda laivus zaidejui " + zaidejoId);
		List<Laivas> laivai = new ArrayList<Laivas>();
		if (zaidejoId == this.zaidejoId1) {
			laivai = ((List) ((ArrayList) this.laivai1).clone());
		}

		if (zaidejoId == this.zaidejoId2) {
			laivai = ((List) ((ArrayList) this.laivai2).clone());
		}

		if (zaidejoId == this.zaidejoId1) {
			arGavoLaivusId1 = true;
		}
		if (zaidejoId == this.zaidejoId2) {
			arGavoLaivusId2 = true;
		}

		if (arGavoLaivusId1 || arGavoLaivusId2) {
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
			System.out.println("Toks zaidejas nedalyvauja zaidime, lentos duoti negalime");
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
	public synchronized void pridekLaiva(lt.vcs.laivumusis.common.Laivas laivas, String zaidejoId) {

		// Kai zaidejas paduoda laiva, patikriname kokio ilgio, ir is esamo saraso
		// padedame zaidejo paduoto laivo kopija.

		if (zaidejoId == zaidejoId1) {
			padekZaidejoLaiva(laivas, zaidimoLenta1, laivai1);
		}

		if (zaidejoId == zaidejoId2) {
			padekZaidejoLaiva(laivas, zaidimoLenta2, laivai2);
		}
		System.out.println();
		new Vaizdas(zaidimoLenta1).pieskVaizda();
		new Vaizdas(zaidimoLenta2).pieskVaizda();

	}

	private void padekZaidejoLaiva(lt.vcs.laivumusis.common.Laivas laivas, ZaidimoLenta zaidimoLenta,
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
				lt.vcs.laivumusis.piratai.Laivas naujasLaivas = new lt.vcs.laivumusis.piratai.Laivas(laivoIlgis, this);
				naujasLaivas.setKordinates(koordinates);
				laivuSarasas.add(naujasLaivas);
				priskirkLangeliuiLaiva(koordinates, naujasLaivas);
				break;
			}
		}

	}

	private void priskirkLangeliuiLaiva(List<Langelis> langelis, lt.vcs.laivumusis.piratai.Laivas laivas) {

		for (int i = 0; i < langelis.size(); i++) {
			lt.vcs.laivumusis.piratai.Langelis langas = (lt.vcs.laivumusis.piratai.Langelis)langelis.get(i);
			langas.setLangelyjeEsantisLaivas(laivas);
		}
	}
}
