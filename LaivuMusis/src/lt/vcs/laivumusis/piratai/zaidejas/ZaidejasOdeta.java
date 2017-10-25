package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.Vaizdas;
import lt.vcs.laivumusis.piratai.duomenubazes.DuomenuBaze;
import sun.security.action.GetLongAction;
import org.apache.log4j.Logger;

public class ZaidejasOdeta implements lt.vcs.laivumusis.common.Zaidejas {

	private Zaidimas zaidimas;
	private String zaidejoId;
	private Langelis paskutinisTikslusSuvis = new lt.vcs.laivumusis.piratai.Langelis("A", 0);

	private ZaidimoLenta zaidimoLenta;
	private List<Laivas> laivuListas = new ArrayList();

	private String abecele ="";
	private int gautosLentosPlotis;
	private int gautosLentosIlgis;

	private List<String> sautuKoordinaciuListas = new ArrayList<String>();

	public ZaidejasOdeta(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}

	@Override
	public void run() {

		boolean zaidimoTikrinimoSalyga = true;
		
		try {
			while (zaidimoTikrinimoSalyga) {
				
				switch(zaidimas.tikrinkBusena(zaidejoId)) {
				
				case Registracija:
					registruokis();	
					zaidimas.registruokZaideja(this.zaidejoId);
					System.out.println(
							"Sveiki, Jus prisiregistravote i zaidima. Jusu zaidejo ID yra: " + this.zaidejoId + "Pradedame!");
					Thread.sleep(10);
				break;
					
				case DalinamesZemelapius:
					this.zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					
					for(String z : zaidimoLenta.getLangeliai().keySet()) {
						this.abecele = this.abecele + z;
				}
					
					this.gautosLentosPlotis = zaidimoLenta.getLangeliai().keySet().size();
					this.gautosLentosIlgis = zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size();
					
					Thread.sleep(100);
					break;

				case DalinemesLaivus:
					this.laivuListas = zaidimas.duokLaivus(zaidejoId);
					Thread.sleep(100);
					break;
				
				case RikiuojamLaivus:
					
					for (int k = 0; k < this.laivuListas.size(); k++) {
						
						try {
						
						Laivas laivelis = this.laivuListas.get(k);
						int laivelioIlgis = this.laivuListas.get(k).getLaivoIlgis();
						int horizontaliaiArVertikaliai = new Random().nextInt(2);
						//int pozicijaX = new Random().nextInt(gautosLentosPlotis - laivelis.getLaivoIlgis());	
						//int pozicijaY = new Random()
								//.nextInt(gautosLentosIlgis + 1 - laivelis.getLaivoIlgis());
						
						List<Langelis> vienoLaivoLangeliai = new ArrayList<Langelis>();

						// horizontaliai reiksme: 0;
						if (horizontaliaiArVertikaliai == 0) {
							int pozicijaX = new Random().nextInt(gautosLentosPlotis - laivelioIlgis);
							int pozicijaY = new Random()
									.nextInt(gautosLentosIlgis) + 1;
							for (int i = 0; i < laivelioIlgis; i++) {
								String x = "" + abecele.charAt(pozicijaX + i);
								Langelis langelisHorizontaliai = new lt.vcs.laivumusis.piratai.Langelis(x, pozicijaY);
								//System.out.println("Dedamas langelis: " + x + " "+ pozicijaY + zaidejoId);
								vienoLaivoLangeliai.add(langelisHorizontaliai);
							}

							// vertikaliai reiksme: 1;
						} else {
							String x = "" + abecele.charAt(new Random().nextInt(gautosLentosPlotis));
							int pozicijaYVertikaliai = new Random().nextInt(gautosLentosIlgis - laivelis.getLaivoIlgis())+1;
							for (int i = 0; i < laivelioIlgis; i++) {
								int dedamaY = pozicijaYVertikaliai +i;
								Langelis langelisVertikaliai = new lt.vcs.laivumusis.piratai.Langelis(x, dedamaY);
								//System.out.println("Dedamas langelis: " + x + " " + dedamaY + zaidejoId);
								vienoLaivoLangeliai.add(langelisVertikaliai);
							}
						}
						
						this.laivuListas.get(k).setKordinates(vienoLaivoLangeliai);
						//laivelis.setKordinates(vienoLaivoLangeliai);
							zaidimas.pridekLaiva(laivelis, this.zaidejoId);
							System.out.println("Laivas nr. : " + k + " sukurtas" + "zaidejo: " + zaidejoId);
						} catch (LaivuPridejimoKlaida e) {
							k--;
						}
					//Thread.sleep(1000);
				}
				
				case TavoEile:

					Thread.sleep(100);
					
					if (this.paskutinisTikslusSuvis.getY() == -1) {

						String xKoordinateSaunu = ""
								+ abecele.charAt(new Random().nextInt(gautosLentosPlotis));
						int ySovimui = new Random()
								.nextInt(zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size()) + 1;
						int yKoordinateSaunu = ySovimui;
						Sovimas(xKoordinateSaunu, yKoordinateSaunu);
						
					} else {
						String xPaskutinisPataikytas = "" + this.paskutinisTikslusSuvis.getX();
						int yPaskutinisPataikytas = this.paskutinisTikslusSuvis.getY();
						String xKoordinateSaunuRandom = ""
								+ abecele.charAt(new Random().nextInt(gautosLentosPlotis));
						if((yPaskutinisPataikytas +1) < gautosLentosIlgis) {
							Sovimas(xPaskutinisPataikytas, (yPaskutinisPataikytas + 2));
						}else if((yPaskutinisPataikytas +1) == gautosLentosIlgis) {
							Sovimas(xKoordinateSaunuRandom, yPaskutinisPataikytas);
						}else {
							Sovimas(xPaskutinisPataikytas, (yPaskutinisPataikytas - 1));
						}
					}
					
					break;
		
				case PriesininkoEile:
					break;
					
				case TuLaimejai:
					System.out.println("Sveikiname, zaidima laimejo zaidejas: " + zaidejoId);
					zaidimas.skaiciuokStatistika();
					zaidimoTikrinimoSalyga = false;
					break;

				case PriesasLaimejo:
					System.out.println("Deja, zaidejas " + zaidejoId + " pralose. Bandykite zaisti dar karta");
					zaidimas.skaiciuokStatistika();
					zaidimoTikrinimoSalyga = false;
					break;
				}
			}

	
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	private void Sovimas(String xKoordinateSaunu, int yKoordinateSaunu) {
		String abiKoordinates = xKoordinateSaunu + yKoordinateSaunu;
		if (sautuKoordinaciuListas.contains(abiKoordinates) == false) {
			sautuKoordinaciuListas.add(abiKoordinates);
			if (zaidimas.sauk(xKoordinateSaunu, yKoordinateSaunu, this.zaidejoId)) {
				this.paskutinisTikslusSuvis = new lt.vcs.laivumusis.piratai.Langelis(xKoordinateSaunu,
						yKoordinateSaunu);
				
			} else {
				this.paskutinisTikslusSuvis = new lt.vcs.laivumusis.piratai.Langelis("A", -1);
			}
		}
	}
	
	private void registruokis() {
		DuomenuBaze duomenuBazeOdetos = new DuomenuBaze("C:/Users/Ooo/Downloads/LaivuMusis.db");
		duomenuBazeOdetos.registruokZaideja(zaidejoId);
	
	}

}


