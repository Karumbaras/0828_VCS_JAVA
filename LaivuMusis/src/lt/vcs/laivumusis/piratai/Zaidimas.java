package lt.vcs.laivumusis.piratai;

import java.util.ArrayList;
import java.util.List;
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public Zaidimas() {
		//TODO Pakoreguoti, kad grazesnis kodas butu
		this.zaidimoLenta1 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis,lentosPlotis);
		this.zaidimoLenta2 = new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis,lentosPlotis);
		this.laivai1 = new ArrayList<Laivas>();
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(4, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(3, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(3, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(2, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(2, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(2, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		this.laivai1.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		
		this.laivai2 = new ArrayList<Laivas>();
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(4, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(3, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(3, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(2, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(2, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(2, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
		this.laivai2.add(new lt.vcs.laivumusis.piratai.Laivas(1, this));
	}
	
	public Zaidimas(int lentosIlgis, int lentosPlotis ) {
		this.lentosIlgis = lentosIlgis ;
		this.lentosPlotis = lentosPlotis ;
		//TODO - parasyti konstruktoriu, kuris priima laivu kieki ir ilgius ir dar patikrina ar tilps
	}

	@Override
	public List<ZaidimoLenta> getLentos() {
		List <ZaidimoLenta> lenta = new ArrayList<ZaidimoLenta>();
		lenta.add(zaidimoLenta1);
		lenta.add(zaidimoLenta2);
		return lenta;
	}

	@Override
	public void skaiciuokStatistika() {
		System.out.println("Pirmasis zaidejas sove "+this.soveKartu1+" kartu");
		System.out.println("Antrasis zaidejas sove "+this.soveKartu2+" kartu");
		System.out.println("Pirmo zaidejo taikliu suviu skaicius "+this.taiklusSuviai1);
		System.out.println("Pirmo zaidejo taikliu suviu skaicius "+this.taiklusSuviai2);
		
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
	
	//Grazina ar pasautas laivas
	@Override
	public boolean sauk(String x, int y, String zaidejoId) {
		 Langelis soveId1 = this.zaidimoLenta2.getLangeliai().get(x).get(y-1);
		 Langelis soveId2 = this.zaidimoLenta1.getLangeliai().get(x).get(y-1);
		//zaidejo ID tas kuris sauna
		if (zaidejoId == this.zaidejoId1) {
			this.soveKartu1++;
			//einam per langeliu masyva tikrindami ar sutampa koordinates
			//kur sauta su esasnciu laivu langelyje
			for (Laivas a : laivai2) {
				for (Langelis b : a.getLaivoKoordinates()) {
					if (b == soveId1)	{		
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
						if (b == soveId2)	{
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
	public List<Laivas> duokLaivus(String zaidejoId) {
		// TODO zaidimas tikrina kad nelankstyti laivu
		
	
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
		return new lt.vcs.laivumusis.piratai.ZaidimoLenta(lentosIlgis,lentosPlotis);
	}

	@Override
	public void pridekLaiva(lt.vcs.laivumusis.common.Laivas laivas, String zaidejoId) {
		// ar zaidimas pats sugalvoja kur padeti laiva?
		// ar cia prasome user input'o ir ji kaip langeliu lista perduodame laivo setKoordinatem?
		if (zaidejoId == zaidejoId1) {
			for(int i =1;i<= laivas.getLaivoIlgis();i++ ) {
				
			}
		}
		
	}
}
