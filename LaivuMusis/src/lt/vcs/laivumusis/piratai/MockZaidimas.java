package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidejas;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.zaidejas.MockZaidejas;

public class MockZaidimas implements Zaidimas {
	
	private static int zaidejuSkaicius;
	private String zaidejoId1;
	private String zaidejoId2;
	private MockZaidimoLenta zaidimoLenta1;
	private MockZaidimoLenta zaidimoLenta2;
	private List<Laivas> laivai1;
	private List<Laivas> laivai2;
	private int lentosIlgis = 10;
	private int lentosPlotis = 10;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public MockZaidimas() {
		//TODO Pakoreguoti, kad grazesnis kodas butu
		this.zaidimoLenta1 = new MockZaidimoLenta(lentosIlgis,lentosPlotis);
		this.zaidimoLenta2 = new MockZaidimoLenta(lentosIlgis,lentosPlotis);
		this.laivai1 = new ArrayList<Laivas>();
		this.laivai1.add(new MockLaivas(4));
		this.laivai1.add(new MockLaivas(3));
		this.laivai1.add(new MockLaivas(3));
		this.laivai1.add(new MockLaivas(2));
		this.laivai1.add(new MockLaivas(2));
		this.laivai1.add(new MockLaivas(2));
		this.laivai1.add(new MockLaivas(1));
		this.laivai1.add(new MockLaivas(1));
		this.laivai1.add(new MockLaivas(1));
		this.laivai1.add(new MockLaivas(1));
		
		this.laivai2 = new ArrayList<Laivas>();
		this.laivai2.add(new MockLaivas(4));
		this.laivai2.add(new MockLaivas(3));
		this.laivai2.add(new MockLaivas(3));
		this.laivai2.add(new MockLaivas(2));
		this.laivai2.add(new MockLaivas(2));
		this.laivai2.add(new MockLaivas(2));
		this.laivai2.add(new MockLaivas(1));
		this.laivai2.add(new MockLaivas(1));
		this.laivai2.add(new MockLaivas(1));
		this.laivai2.add(new MockLaivas(1));
	}
	
	public MockZaidimas(int lentosIlgis, int lentosPlotis ) {
		this.lentosIlgis = lentosIlgis ;
		this.lentosPlotis = lentosPlotis ;
		//TODO - parasyti konstruktoriu, kuris priima laivu kieki ir ilgius ir dar patikrina ar tilps
	}

	@Override
	public List<ZaidimoLenta> getLentos() {
		List <ZaidimoLenta> lenta = new ArrayList<ZaidimoLenta>();
		return lenta;
	}

	@Override
	public void skaiciuokStatistika() {
		System.out.println("Rodau statistika");
		
	}

	@Override
	public Busena tikrinkBusena(String zaidejoId) {
		Busena busena = Busena.DalinamesZemelapius;
		return busena;
	}

	@Override
	public String registruokZaideja() {
		
		if (zaidejuSkaicius >= 2) {
			System.out.println("Jau uzregistruoti 2 zaidejai");
			return null;
		}
		if (zaidejoId1 != null) {
			this.zaidejoId2 = "zaidejoId2"+new Random().nextInt(100);
			zaidejuSkaicius++;
			return this.zaidejoId2;
		} zaidejuSkaicius++;
		return this.zaidejoId1 = "zaidejoId1"+new Random().nextInt(100);
	}
	
	
	@Override
	public boolean sauk(String x, int y, String zaidejoId) {
		//zaidejo ID tas kuris sauna
		if (zaidejoId == this.zaidejoId1) {

		//	return this.zaidimoLenta2.getLangeliai().get(x).get(y-1).sauk();

			this.zaidimoLenta2.getLangeliai().get(x).get(y-1).sauk();
			return this.zaidimoLenta2.getLangeliai().get(x).get(y-1).arSauta();

		}
		return true;
	}

	@Override
	public List<Laivas> duokLaivus(String zaidejoId) {
		//zaidimas tikrina kad nelankstyti laivu
		
	
		if (zaidejoId == this.zaidejoId1) {
			return this.laivai1;
		}
		if (zaidejoId == this.zaidejoId2) {
			return this.laivai2;
		}
		System.out.println("Toks zaidejas nedalyvauja zaidime!");
		return null;
	}

	@Override
	public ZaidimoLenta duokZaidimoLenta(String zaidejoId) {
		
		//atiduoti lentos kopija zaidejui
		return new MockZaidimoLenta(lentosIlgis,lentosPlotis);
	}

	@Override
	public void pridekLaiva(Laivas laivas, String zaidejoId) {
		/*if (zaidejoId = this.zaidejoId1) {
			for (int i = 0; i < laivai1.size();i++) {
				int laivoIlgis = laivai1.get(i).getLaivoIlgis() ;
				List<Langelis> langeliai = new ArrayList(laivoIlgis);
				
		//TODO ste koordinates kiekvienam laivui, t.y. list koordinaciu
				
			}
		
		}*/
		
	}

}
