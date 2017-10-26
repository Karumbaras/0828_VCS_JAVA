package lt.vcs.laivumusis.USSENTERPRISE.zaidejas;


import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PoviloZaidejas implements lt.vcs.laivumusis.common.Zaidejas {

	static Logger logas = Logger.getLogger(PoviloZaidejas.class.getName());
	
	
	private List<Laivas> laivai = new ArrayList<Laivas>();

	private List<String> suviai = new ArrayList<String>();
	
	private ZaidimoLenta zaidimoLenta;
	private Zaidimas zaidimas;
	private String zaidejasId;
	private int y;
	private String x;

	public PoviloZaidejas() {

	}
	
	public PoviloZaidejas(Zaidimas zaidimas, String zaidejasId) {
		this.zaidimas = zaidimas;
		this.zaidejasId = zaidejasId;
	}
	
	
	@Override
	public void run() {
		System.out.println(zaidejasId);
		try {
			while (true) {
				switch (zaidimas.tikrinkBusena(zaidejasId)) {
				case Registracija:
					
					while(true) {
						if(this.zaidimas.registruokZaideja(this.zaidejasId));
							break;
					}
					break;
				case DalinamesZemelapius:
					this.zaidimoLenta = this.zaidimas.duokZaidimoLenta(zaidejasId);
					Thread.sleep(2000);
					break;
				case DalinemesLaivus:
					laivai =  this.zaidimas.duokLaivus(zaidejasId);
					Thread.sleep(1000);
					break;
				case RikiuojamLaivus:
					
					zaidejauPridekLaivus();
					Thread.sleep(1000);
					break;
				case TavoEile:
					//zaidejauSauk(zaidejasId);
					break;
				case PriesininkoEile:
					//zaidejauSauk(zaidejasId);
					break;
				case TuLaimejai:
	
					break;
				case PriesasLaimejo:
	
					break;
	
				}
			}
		} 
		catch(Exception ex) {
			logas.error(ex.getMessage(),ex);
		}
		
	}
	

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}
	
	public String getZaidejas() {
		return this.zaidejasId;
	}
	
	
	// patikrinti ar laivas gerai sudetas
		public synchronized void zaidejauPridekLaivus() {
			List<Laivas> laivuListas = this.zaidimas.duokLaivus(zaidejasId);

			// for (int i = 0; i < laivuListas.size(); i++) {
			// Laivas laivelis = laivuListas.get(i);
			// List<Langelis> laivuLangeliai = laivelis.getLaivoKoordinates();
			// for (int a = 0; a < laivelis.getLaivoIlgis(); a++) {
			// System.out.println("Iveskite laivo kordinate X");
			// String x = laivoScaneris.nextLine();
			// System.out.println("Iveskite laivo kordinate Y");
			// int y = laivoScaneris.nextInt();
			// laivuLangeliai.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis(x,
			// y));
			// }
			// laivelis.setKordinates(laivuLangeliai);
			// try {
			// this.zaidimas.pridekLaiva(laivelis, zaidejasId);
			// } catch (Exception e) {
			//
			// }
			//
			// }
			
			Laivas laivelis = laivuListas.get(0);
			List<Langelis> laivuLangeliai = laivelis.getLaivoKoordinates();
			laivuLangeliai.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("A", 1));
			//laivelis.setKordinates(laivuLangeliai);
			// try {
			// this.zaidimas.pridekLaiva(laivelis, zaidejasId);
			// } catch (Exception e) {
			// System.out.println(e.getMessage());
			// }
			try {
				this.zaidimas.pridekLaiva(laivelis, zaidejasId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("dedam antra laiva");
			Laivas laiveliukas = laivuListas.get(1);
			List<Langelis> laivuLangeliai1 = laiveliukas.getLaivoKoordinates();
			laivuLangeliai1.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("C", 4));
			laivuLangeliai1.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("D", 4));
			System.out.println("tata");
			laiveliukas.setKordinates(laivuLangeliai1);// klaida
			System.out.println("papa");
			try {
				this.zaidimas.pridekLaiva(laiveliukas, zaidejasId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

}
