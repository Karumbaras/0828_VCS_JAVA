package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class Zaidimas implements lt.vcs.laivumusis.common.Zaidimas {
	// Zaidejas
	private static int zaidejuSkaicius;
	private String zaidejoId1;
	private String zaidejoId2;
	private int soveKartu1;
	private int soveKartu2;
	private int taiklusSuviai1;
	private int taiklusSuviai2;

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

	@Override
	public void run() {
		// Nebereikalingas metodas

	}

	public Zaidimas() {

		this.zaidimoLenta1 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);
		this.zaidimoLenta2 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis, lentosPlotis);

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
		Langelis soveId1 = this.zaidimoLenta2.getLangeliai().get(x).get(y);
		Langelis soveId2 = this.zaidimoLenta1.getLangeliai().get(x).get(y);
		// zaidejo ID tas kuris sauna
		if (zaidejoId == this.zaidejoId1) {
			saukPagalba(soveId1, laivai2, taiklusSuviai1, soveKartu1);
			return true;
			}

		 else if (zaidejoId == this.zaidejoId2) {
			 saukPagalba(soveId2, laivai1, taiklusSuviai2, soveKartu2);
			 return true;
		 }
		System.err.println("Tokio zaidejo nera");

		return false;
	}
	
	private void saukPagalba(Langelis soveID, List<Laivas> laivai,
			 int taiklusSuviai, int soveKartu) {
	
	soveKartu++;
	// einam per langeliu masyva tikrindami ar sutampa koordinates
	// kur sauta su esasnciu laivu langelyje
	for (Laivas a : laivai) {
		for (Langelis b : a.getLaivoKoordinates()) {
			if (b == soveID) {
				System.out.println("Laivas pasautas");
				taiklusSuviai++;
			}
			}
		}
	}
	@Override
	public synchronized List<Laivas> duokLaivus(String zaidejoId) {
		// Kolas nera kopija, o nurodo i tuos paciu objektus - reikia pakeisti!

		if (zaidejoId != this.zaidejoId1 & zaidejoId != this.zaidejoId2) {
			System.err.println("Toks zaidejas nedalyvauja zaidime!");
			return null;
		}

		System.out.println("Zaidimas paduoda laivus zaidejui " + zaidejoId);

		List<Laivas> laivai = new ArrayList<Laivas>();
		if (zaidejoId == this.zaidejoId1) {
			laivai = sukurkLaivuSarasa(laivuMasyvas);
			arGavoLaivusId1 = true;
		}

		if (zaidejoId == this.zaidejoId2) {
			laivai = sukurkLaivuSarasa(laivuMasyvas);
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
	public synchronized void pridekLaiva(lt.vcs.laivumusis.common.Laivas laivas, String zaidejoId) {

		//ar neturetu sis metodas grazinti boolean?

		
		/*StringBuilder sb = new StringBuilder();
		List<lt.vcs.laivumusis.piratai.Langelis> pilniLangeliai = new ArrayList<lt.vcs.laivumusis.piratai.Langelis> ();
		String abecele = "ABCDEFGHIJ";
		Random generator = new Random();
		
		sb.append(abecele.charAt(generator.nextInt(abecele.length())));
		String x = sb.toString();
		int y = generator.nextInt(10);
		
		
		
		
		for (lt.vcs.laivumusis.common.Laivas l : laivai1) {
			if (l.getLaivoIlgis() == 1) {
				String ixas = (this.zaidimoLenta1.getLangeliai().get(x).get(y).getX());
				int ykas = this.zaidimoLenta1.getLangeliai().get(x).get(y).getY();
				lt.vcs.laivumusis.piratai.Langelis pilnas = new lt.vcs.laivumusis.piratai.Langelis(ixas, ykas);
				pilniLangeliai.add(pilnas);
				
			}
		}*/
		
		LaivuValidatorius validatorius = new LaivuValidatorius((lt.vcs.laivumusis.piratai.Laivas) laivas);

		//validatorius.arPerduotosKoordinatesGeros();

		if (zaidejoId == zaidejoId1) {
			validatorius.tikrinkArLieciasi(zaidimoLenta2);
		} else
			validatorius.tikrinkArLieciasi(zaidimoLenta1);

		// Kai zaidejas paduoda laiva, patikriname kokio ilgio, ir is esamo saraso
		// padedame zaidejo paduoto laivo kopija.

		if (zaidejoId == zaidejoId1) {
			if (padekZaidejoLaiva(laivas, zaidimoLenta1, laivai1)) {
				padetaLaivuId1++;
			}
		}

		if (zaidejoId == zaidejoId2) {
			if (padekZaidejoLaiva(laivas, zaidimoLenta2, laivai2)) {
				padetaLaivuId2++;
			}
		}
		//TODO sutvarkyti
		if(padetaLaivuId1==laivuKiekis & padetaLaivuId2 == laivuKiekis) {
			zaidimoBusena = Busena.TavoEile;
		}
		System.out.println();
		new Vaizdas(zaidimoLenta1).pieskVaizda();
		new Vaizdas(zaidimoLenta2).pieskVaizda();

	}

	private boolean padekZaidejoLaiva(lt.vcs.laivumusis.common.Laivas laivas, ZaidimoLenta zaidimoLenta,
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

}
