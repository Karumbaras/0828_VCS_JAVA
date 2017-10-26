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
	private String zaidejasId;
	private List<Laivas> zaidejoLaivuListas;
	private lt.vcs.laivumusis.common.ZaidimoLenta zaidejoZaidimoLenta;
	
	int laivuskaicius = 0;

	public pijausZaidejas(Zaidimas zaidimas, String zaidejoid) {
		this.zaidimas = zaidimas;
		zaidejasId = zaidejoid;
	}

	@Override
	public void run() {
		System.out.println(zaidejasId);
		try {
			boolean random = true;
			while (random) {
				switch (zaidimas.tikrinkBusena(zaidejasId)) {
				case Registracija:
					this.zaidimas.registruokZaideja(this.zaidejasId);
					while (true) {
						if (this.zaidimas.registruokZaideja(this.zaidejasId));
						Thread.sleep(1000);
						break;
					}
					Thread.sleep(1000);
					break;
				case DalinamesZemelapius:
					this.zaidejoZaidimoLenta = this.zaidimas.duokZaidimoLenta(zaidejasId);
					Thread.sleep(1000);
					break;
				case DalinemesLaivus:
					zaidejoLaivuListas = this.zaidimas.duokLaivus(zaidejasId);

					// Object []aa=zaidejoZaidimoLenta.getLangeliai().keySet().toArray();
					// for (Object k : aa) {
					// System.out.println((String)k);
					// }
					break;
				case RikiuojamLaivus:
					zaidejauPridekLaivus();
					

					System.out.println(laivuskaicius);
					Thread.sleep(1000);
					break;
				case TavoEile:
					try {
					zaidejauSauk();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					break;
				case PriesininkoEile:
					
					break;
				case TuLaimejai:
					System.out.println(zaidejasId + " laimejo!!!");
					Thread.sleep(1000);
					random = false;
					break;
				case PriesasLaimejo:
					System.out.println("Priesas laimejo");
					Thread.sleep(1000);
					random = false;
					break;

				}
			}

		} catch (Exception ex) {
			
		}
	}

	public synchronized List<Langelis> generuojamLaivoKordintates(Laivas laivas, boolean arLaivasvertikalus, String laivasVertikalus,
			int laivasHorizantalus) {
		List<Langelis> laivoLangeliai = new ArrayList<Langelis>();
		// String []aa=(String [])zaidejoZaidimoLenta.getLangeliai().keySet().toArray();
		int laivoIlgis = laivas.getLaivoIlgis();
		int Ykordinate = laivasHorizantalus;
		int stringovieta = xMasyvas.indexOf(laivasVertikalus);

		if (arLaivasvertikalus == true) {
			if (Ykordinate <= 10 - laivoIlgis) {
				for (int i = 0; i < laivoIlgis; i++) {
					laivoLangeliai
							.add(new lt.vcs.laivumusis.piratai.Langelis(laivasVertikalus, Ykordinate++));
				}
				return laivoLangeliai;
			} else {
				Ykordinate = Ykordinate - laivoIlgis;
				for (int i = 0; i < laivoIlgis; i++) {
					laivoLangeliai
							.add(new lt.vcs.laivumusis.piratai.Langelis(laivasVertikalus, Ykordinate++));

				}
				return laivoLangeliai;
			}
		}
		if (arLaivasvertikalus == false) {

			if (stringovieta <= 10 - laivoIlgis) {
				for (int i = 0; i < laivoIlgis; i++) {
					laivoLangeliai.add(new lt.vcs.laivumusis.piratai.Langelis(
							"" + xMasyvas.charAt(stringovieta + 1), laivasHorizantalus));
					stringovieta++;
				}
				return laivoLangeliai;
			} else {
				stringovieta = stringovieta - laivoIlgis;
				for (int i = 0; i < laivoIlgis; i++) {
					laivoLangeliai.add(new lt.vcs.laivumusis.piratai.Langelis(
							"" + xMasyvas.charAt(stringovieta + 1), laivasHorizantalus));
					stringovieta++;
				}
				return laivoLangeliai;
			}
		}
		return laivoLangeliai;
	}

	// kordinates dedam i laiva
	public synchronized Laivas kordinatesDedamILaiva(int i, boolean arLaivasvertikalus, String laivasVertikalus,
			int laivasHorizantalus) {
		Laivas laivukas = zaidejoLaivuListas.get(i);
		List<Langelis> langeliai = generuojamLaivoKordintates(laivukas, arLaivasvertikalus, laivasVertikalus,
				laivasHorizantalus);
		laivukas.setKordinates(langeliai);
		return laivukas;
	}

	// paduodam zaidimui
	public synchronized void zaidejauPridekLaivus() {
		for (int i = 0; i < zaidejoLaivuListas.size(); i++) {
			boolean arPavyko = true;
			while (arPavyko) {
				try {
					boolean arLaivasvertikalus = new Random().nextBoolean();
					String laivasVertikalus = generuokX();
					int laivasHorizantalus = generuokY();
					zaidimas.pridekLaiva(
							kordinatesDedamILaiva(i, arLaivasvertikalus, laivasVertikalus, laivasHorizantalus),
							zaidejasId);
					arPavyko = false;
					laivuskaicius++;
				} catch (Exception e) {
					System.out.println(e.getMessage());

				}

			}

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
		int lentosIlgis = zaidejoZaidimoLenta.getLangeliai().size();// 10
		return new Random().nextInt(lentosIlgis + 1) + 1;
	}

	public String getZaidejas() {
		return this.zaidejasId;
	}

	@Override
	public Zaidimas getZaidimas() {

		return zaidimas;
	}

}
