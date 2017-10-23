package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.piratai.Vaizdas;
import sun.security.action.GetLongAction;

public class ZaidejasOdeta implements lt.vcs.laivumusis.common.Zaidejas {

	private Zaidimas zaidimas;
	private String zaidejoId;
	private Langelis paskutinisTikslusSuvis = new lt.vcs.laivumusis.piratai.Langelis("A", 0);
	boolean zaidimoTikrinimoSalyga = true;

	private ZaidimoLenta zaidimoLenta;
	private List<Laivas> laivuListas = new ArrayList();

	private String abecele = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

		zaidimas.registruokZaideja(this.zaidejoId);
		System.out.println(
				"Sveiki, Jus prisiregistravote i zaidima. Jusu zaidejo ID yra: " + this.zaidejoId + "Pradedame!");
		try {
			while (zaidimoTikrinimoSalyga) {

				Thread.sleep(50);
				Busena zaidimoBusena = zaidimas.tikrinkBusena(zaidejoId);
				System.out.println("Zaidejo " + zaidejoId + " busena: " + zaidimoBusena);
				Thread.sleep(50);

				if (zaidimoBusena == Busena.DalinamesZemelapius) {
					this.zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					Thread.sleep(50);
				}

				if (zaidimoBusena == Busena.DalinemesLaivus) {
					this.laivuListas = zaidimas.duokLaivus(zaidejoId);
					Thread.sleep(50);
				}

				if (zaidimoBusena == Busena.RikiuojamLaivus) {
					Thread.sleep(50);
					for (int k = 0; k < this.laivuListas.size(); k++) {
						Laivas laivelis = this.laivuListas.get(k);
						int horizontaliaiArVertikaliai = new Random().nextInt(2);
						int pozicijaX = new Random().nextInt(zaidimoLenta.getLangeliai().keySet().size());	
						int pozicijaY = new Random()
								.nextInt(zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size()) + 1;
						List<Langelis> vienoLaivoLangeliai = new ArrayList<Langelis>();

						// horizontaliai reiksme: 0;
						if (horizontaliaiArVertikaliai == 0) {
							for (int i = pozicijaX; i < pozicijaX + laivelis.getLaivoIlgis(); i++) {
								String x = "" + abecele.charAt(pozicijaX + i);
								Langelis langelis = new lt.vcs.laivumusis.piratai.Langelis(x, pozicijaY);
								vienoLaivoLangeliai.add(langelis);
							}

							// vertikaliai reiksme: 1;
						} else {
							for (int i = pozicijaY; i < pozicijaY + laivelis.getLaivoIlgis(); i++) {
								String x = "" + abecele.charAt(pozicijaX);
								Langelis langelis = new lt.vcs.laivumusis.piratai.Langelis(x, i);
								vienoLaivoLangeliai.add(langelis);
							}
						}

						laivelis.setKordinates(vienoLaivoLangeliai);
						try {
							zaidimas.pridekLaiva(laivelis, this.zaidejoId);
							System.out.println("Laivas nr. : " + k + " sukurtas");
						} catch (Exception e) {
							k--;
						}
					}
				}
				if (zaidimoBusena == Busena.TavoEile) {

					//if (this.paskutinisTikslusSuvis.getY() == 0) {

						String xKoordinateSaunu = ""
								+ abecele.charAt(new Random().nextInt(zaidimoLenta.getLangeliai().keySet().size()));
						int ySovimui = new Random()
								.nextInt(zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size()) + 1;
						int yKoordinateSaunu = ySovimui;
						Sovimas(xKoordinateSaunu, yKoordinateSaunu);
						
					/*} else {
						String xPaskutinisPataikytas = "" + this.paskutinisTikslusSuvis.getX();
						int yPaskutinisPataikytas = this.paskutinisTikslusSuvis.getY() + 1;
						if(yPaskutinisPataikytas <= zaidimoLenta.getLangeliai().get("" + abecele.charAt(0)).size()) {
							Sovimas(xPaskutinisPataikytas, yPaskutinisPataikytas);
						}else {
							Sovimas(xPaskutinisPataikytas, yPaskutinisPataikytas - 1);
						}
					}*/

				}

				if (zaidimoBusena == Busena.PriesininkoEile) {
				}

				if (zaidimoBusena == Busena.TuLaimejai) {
					System.out.println("Sveikiname, zaidima laimejo zaidejas: " + zaidejoId);
					zaidimoTikrinimoSalyga = false;
					zaidimas.skaiciuokStatistika();
					break;
				}

				if (zaidimoBusena == Busena.PriesasLaimejo) {
					System.out.println("Deja, zaidejas " + zaidejoId + " pralose. Bandykite zaisti dar karta");
					zaidimoTikrinimoSalyga = false;
					zaidimas.skaiciuokStatistika();
					break;
				}
			}

		} catch (

		InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized void Sovimas(String xKoordinateSaunu, int yKoordinateSaunu) {
		String abiKoordinates = xKoordinateSaunu + yKoordinateSaunu;
		if (sautuKoordinaciuListas.contains(abiKoordinates) == false) {
			sautuKoordinaciuListas.add(abiKoordinates);
			if (zaidimas.sauk(xKoordinateSaunu, yKoordinateSaunu, zaidejoId)) {
				//this.paskutinisTikslusSuvis = new lt.vcs.laivumusis.piratai.Langelis(xKoordinateSaunu,
				//		yKoordinateSaunu);
			} else {
				//this.paskutinisTikslusSuvis = new lt.vcs.laivumusis.piratai.Langelis("A", 0);
			}
		}
	}

}
