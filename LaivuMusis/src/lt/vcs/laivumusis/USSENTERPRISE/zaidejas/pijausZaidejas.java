package lt.vcs.laivumusis.USSENTERPRISE.zaidejas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.org.apache.xml.internal.security.keys.storage.implementations.SingleCertificateResolver;

import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidejas;
import lt.vcs.laivumusis.common.Zaidimas;

public class pijausZaidejas implements Zaidejas {

	Scanner laivoScaneris = new Scanner(System.in);
	private String xMasyvas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Zaidimas zaidimas;
	private String zaidejasId = "Krabas";
	private List<Laivas> zaidejoLaivuListas;
	private lt.vcs.laivumusis.common.ZaidimoLenta zaidejoZaidimoLenta;
	static Logger logas = Logger.getLogger(pijausZaidejas.class.getName());
	int laivuskaicius =0 ;
	public pijausZaidejas(Zaidimas zaidimas) {
		this.zaidimas = zaidimas;
	}

	@Override
	public void run() {// sutvarkyti nauja isvis galbut Siwtch iskelti i metoda pagalvot kaip veikt
		System.out.println(zaidejasId);
		try {
			boolean random = true;
			while (random) {
				switch (zaidimas.tikrinkBusena(zaidejasId)) {
				case Registracija:

					while (true) {
						if (this.zaidimas.registruokZaideja(this.zaidejasId));
						break;
					}
					break;
				case DalinamesZemelapius:
					this.zaidejoZaidimoLenta = this.zaidimas.duokZaidimoLenta(zaidejasId);
					
					break;
				case DalinemesLaivus:
					zaidejoLaivuListas = this.zaidimas.duokLaivus(zaidejasId);
					
//					Object []aa=zaidejoZaidimoLenta.getLangeliai().keySet().toArray();
//					for (Object k : aa) {
//						System.out.println((String)k);
//					}
					break;
				case RikiuojamLaivus:
					//zaidejauPridekLaivus();
					pridekLaiva();
					System.out.println("tra tra");
					System.out.println(laivuskaicius);
					
					break;
				case TavoEile:
					zaidejauSauk();
					break;
				case PriesininkoEile:
					
					break;
				case TuLaimejai:

					break;
				case PriesasLaimejo:

					break;

				}
			}

		} catch (Exception ex) {
			logas.error(ex.getMessage(), ex);
		}
	}
	// public void generuokRandomLaivoKordinates

	// generuojam kordinates
	public List<Langelis> generuojamLaivoKordintates(Laivas laivas, boolean arLaivasvertikalus , String laivasVertikalus, int laivasHorizantalus) {
		List<Langelis> laivoLangeliai = new ArrayList<Langelis>();
		//String []aa=(String [])zaidejoZaidimoLenta.getLangeliai().keySet().toArray();
		int a = laivas.getLaivoIlgis();
		for (int i = 0; i < a; i++) {
			if(arLaivasvertikalus == true) {
			laivoLangeliai.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis(laivasVertikalus, generuokY()));
			}
			if(arLaivasvertikalus == false) {
				laivoLangeliai.add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis(generuokX(), laivasHorizantalus));
			}
		}
		return laivoLangeliai;
	}

	// kordinates dedam i laiva
	public Laivas kordinatesDedamILaiva(int i , boolean arLaivasvertikalus , String laivasVertikalus, int laivasHorizantalus) {
		Laivas laivukas = zaidejoLaivuListas.get(i);
		List<Langelis> langeliai = generuojamLaivoKordintates(laivukas, arLaivasvertikalus ,laivasVertikalus, laivasHorizantalus);
		laivukas.setKordinates(langeliai);
		return laivukas;
	}

	// paduodam zaidimui
	public synchronized void zaidejauPridekLaivus() {
		for (int i = 0; i < zaidejoLaivuListas.size(); i++) {
			boolean arPavyko = true;
			
			
			while(arPavyko) {
			try {
				boolean arLaivasvertikalus = new Random().nextBoolean();
				String laivasVertikalus= generuokX();
				int laivasHorizantalus = generuokY();
				zaidimas.pridekLaiva(kordinatesDedamILaiva(i, arLaivasvertikalus ,laivasVertikalus, laivasHorizantalus), zaidejasId);
				arPavyko = false;
				laivuskaicius ++;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
				
			}
			
			}
			
		}

	}
	
	public void pridekLaiva() {
		Laivas laivas = zaidejoLaivuListas.get(0);
		laivas.getLaivoKoordinates().add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("B", 2));
		try {
			zaidimas.pridekLaiva(laivas, zaidejasId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Laivas laivas1 = zaidejoLaivuListas.get(1);
		laivas1.getLaivoKoordinates().add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("H", 3));
		laivas1.getLaivoKoordinates().add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("H", 4));
		try {
			zaidimas.pridekLaiva(laivas1, zaidejasId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Laivas laivas2 = zaidejoLaivuListas.get(2);
		laivas2.getLaivoKoordinates().add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("D", 8));
		laivas2.getLaivoKoordinates().add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("D", 10));
		laivas2.getLaivoKoordinates().add(new lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis("D", 9));
		try {
			zaidimas.pridekLaiva(laivas2, zaidejasId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void zaidejauSauk() {
		zaidimas.sauk(generuokX(), generuokY(), zaidejasId);
	}

	private String generuokX() {
		int lentosIlgis = zaidejoZaidimoLenta.getLangeliai().size();
		return "" + xMasyvas.charAt(new Random().nextInt(lentosIlgis));
	}

	private int generuokY() {
		int lentosIlgis = zaidejoZaidimoLenta.getLangeliai().size();//10
		return new Random().nextInt(lentosIlgis-1)+1;
	}

	public String getZaidejas() {
		return this.zaidejasId;
	}

	@Override
	public Zaidimas getZaidimas() {

		return zaidimas;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}

}
