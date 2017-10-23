package lt.vcs.laivumusis.USSENTERPRISE.zaidimas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.LaivuPridejimoKlaida;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.USSENTERPRISE.DataBaseHelper;
import lt.vcs.laivumusis.common.Vaizdas;

public class Zaidimas implements lt.vcs.laivumusis.common.Zaidimas {

	private List<lt.vcs.laivumusis.common.Laivas> laivuListas = new ArrayList<lt.vcs.laivumusis.common.Laivas>();
	private List<ZaidimoLenta> zaidejuLentos = new ArrayList<ZaidimoLenta>();
	// sita reikia kazkaip perdaryti
	String[] abecele = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

	private int konfiguracijosID;
	static Logger logas = Logger.getLogger(Zaidimas.class.getName());
	Laivas laivas;
	boolean ret1 = false;
	boolean ret2 = false;
	boolean duotiLentaZaid_1 = false;
	boolean duotiLentaZaid_2 = false;

	private String zaidejoId1;
	private String zaidejoId2;
	static private int zaidejuSkaicius;

	private List<Laivas> laivai1 = new ArrayList<Laivas>();
	private List<Laivas> laivai2 = new ArrayList<Laivas>();

	// private ZaidimoLenta lenta12 = new ZaidimoLenta();
	// private ZaidimoLenta lenta2 = new ZaidimoLenta();
	private lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta lenta1 = null;
	private lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta lenta2 = null;

	// laivai
	private int laivuKiekis;
	private int padetaLaivuId1;
	private int padetaLaivuId2;

	// Vaizdas
	private Vaizdas pirmoZaidejoVaizdas = null; /// ??????????????????
	private Vaizdas antrojoZaidejoVaizdas = null;

	// Vaizdas pirmoZaidejoVaizdas = new Vaizdas(lenta1.zaidimoLentele);
	// Vaizdas antrojoZaidejoVaizdas = new Vaizdas(lenta2.zaidimoLentele);

	Busena busenos = Busena.Registracija;
	Busena zaidejas1_B = Busena.TavoEile;
	Busena zaidejas2_B = Busena.PriesininkoEile;

	private Laivas laivasVienas = new lt.vcs.laivumusis.USSENTERPRISE.laivas.Laivas(1, "laivasVienas");
	private Laivas laivasDu = new lt.vcs.laivumusis.USSENTERPRISE.laivas.Laivas(2, "laivasDu");
	private Laivas laivasTrys = new lt.vcs.laivumusis.USSENTERPRISE.laivas.Laivas(3, "laivasTrys");
	private Laivas LaivasKeturi = new lt.vcs.laivumusis.USSENTERPRISE.laivas.Laivas(4, "LaivasKeturi");
	private Laivas LaivasPenki = new lt.vcs.laivumusis.USSENTERPRISE.laivas.Laivas(5, "LaivasPenki");

	// nenaudojamas ir kazkodel neistrinam
	@Override
	public void run() {
	}

	// ///
	/*
	 * public Zaidimas() { laivai1.add(laivasVienas); laivai1.add(laivasDu);
	 * laivai1.add(laivasTrys); laivai1.add(LaivasKeturi); laivai1.add(LaivasPenki);
	 * 
	 * laivai2.add(laivasVienas); laivai2.add(laivasDu); laivai2.add(laivasTrys);
	 * laivai2.add(LaivasKeturi); laivai2.add(LaivasPenki);
	 * 
	 * }
	 */
	public Zaidimas() {
	}

	public Zaidimas(int konfiguracijosID) throws ClassNotFoundException, SQLException {
		this.konfiguracijosID = konfiguracijosID;
		lentuPadavimasZaidimui();
		laivuKurimas();

	}

	private void lentuPadavimasZaidimui() throws ClassNotFoundException, SQLException {
		this.lenta1 = new lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta(konfiguracijosID);
		this.lenta2 = new lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta(konfiguracijosID);
		this.pirmoZaidejoVaizdas = new lt.vcs.laivumusis.USSENTERPRISE.vaizdas.Vaizdas(this.lenta1);
		this.antrojoZaidejoVaizdas = new lt.vcs.laivumusis.USSENTERPRISE.vaizdas.Vaizdas(this.lenta2);
	}

	private void laivuKurimas() {
		this.laivai1 = sukurkLaivuSarasa();
		this.laivai2 = sukurkLaivuSarasa();
		this.laivuKiekis = this.laivai1.size();
	}

	// TODO perdaryti kad daugiau laivu paimtu, nes db po viena.
	private synchronized List<Laivas> sukurkLaivuSarasa() {
		List<Laivas> laivuSarasas = new ArrayList<Laivas>();
		laivuSarasas.add(laivasVienas);
		laivuSarasas.add(laivasDu);
		laivuSarasas.add(laivasTrys);
		laivuSarasas.add(LaivasKeturi);
		laivuSarasas.add(LaivasPenki);
		return laivuSarasas;
	}

	@Override
	public List<ZaidimoLenta> getLentos() {
		zaidejuLentos.add(lenta1);
		zaidejuLentos.add(lenta2);
		return zaidejuLentos;
	}

	@Override
	public void skaiciuokStatistika() {
		// TODO is db paimti ir irasyti

	}

	@Override
	public synchronized Busena tikrinkBusena(String zaidejoId) {// patikrink kieno eile
		if (busenos == Busena.TavoEile || busenos == Busena.PriesininkoEile) {
			if (zaidejoId == zaidejoId1) {
				return zaidejas1_B;
			}
			if (zaidejoId == zaidejoId2)
				return zaidejas2_B;

		}
		return busenos;

	}

	@Override
	public synchronized boolean registruokZaideja(String zaidejoId) {// +

		if (zaidejoId.isEmpty())
			return false;

		if (zaidejuSkaicius >= 2) {
			System.out.println("zaidejai uzregistruoti");
			return false;
		}
		if (zaidejoId1 == null) {
			try {
				new DataBaseHelper(zaidejoId).insertZaidejas();
			} catch (ClassNotFoundException | SQLException e) {
				e.getMessage();
			}
			this.zaidejoId1 = zaidejoId;
			System.out.println("Pirmas zaidejas uzregistruotas");
			zaidejuSkaicius++;
			pirmoZaidejoVaizdas.pieskVaizda();
			return true;
		} else {
			try {
				new DataBaseHelper(zaidejoId).insertZaidejas();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Antras zaidejas uzregistruotas");
			zaidejoId2 = zaidejoId;
			zaidejuSkaicius++;
			busenos = Busena.DalinamesZemelapius;
			antrojoZaidejoVaizdas.pieskVaizda();
			return true;

		}

	}

	// po kiekvieno zaidejo ejimo pieseme nauja vaizda
	// truksta vieno tikrinam ar laivai dedami gerai
	@Override
	public synchronized void pridekLaiva(Laivas laivas, String zaidejoId) throws LaivuPridejimoKlaida {
		if (zaidejoId != zaidejoId1 || zaidejoId != zaidejoId2)
			throw new LaivuPridejimoKlaida("Nera tokio zaidejo");

		if (laivas.getLaivoKoordinates() == null)
			throw new LaivuPridejimoKlaida("Laivui nepriskirtos kordinates!");
		this.laivas = laivas;
		
		boolean arlaivasiseiles = arPerduotosKoordinatesGeros();
		if(arlaivasiseiles != true ) {
			throw new LaivuPridejimoKlaida("Laivas kreivas");
		}

		boolean arLaivasGaliButPridetas = true;
		Laivas Laivukas = laivas;
		arLaivasGaliButPridetas = tikrinaArGalimaIdetiLaiva(Laivukas, zaidejoId);

		if (arLaivasGaliButPridetas == false)
			throw new LaivuPridejimoKlaida("Laivo su siomis kordinatemis negalima prideti, bandykite is naujo");

		// jau dedame laivus
		Laivas laivelis;
		
		int laivoIlgis = laivas.getLaivoIlgis();
		// 1 zaidejas
		System.out.println("1 ZAIDEJAS");
		if (zaidejoId1 == zaidejoId) {
			laivelis = laivai1.get(laivoIlgis - 1);
			List<Langelis> laivoLangeliukopija = laivas.getLaivoKoordinates();
			for (int a = 0; a < laivoLangeliukopija.size(); a++) {
				String x = laivoLangeliukopija.get(a).getX();
				int y = laivoLangeliukopija.get(a).getY();
				((lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis) lenta1.getLangeliai().get(x).get(y - 1))
						.setLaivaLangeliui(laivelis);
			}
			System.out.println("laivas pridetas");
			pirmoZaidejoVaizdas.atnaujinkVaizda();
			laivai1.get(laivoIlgis - 1).setKordinates(laivoLangeliukopija);

		}

		// 2 zaidejas
		System.out.println("2 ZAIDEJAS");
		if (zaidejoId2 == zaidejoId) {
			laivelis = laivai2.get(laivoIlgis - 1);
			List<Langelis> laivoLangeliukopija = laivas.getLaivoKoordinates();
			for (int a = 0; a < laivoLangeliukopija.size(); a++) {
				String x = laivoLangeliukopija.get(a).getX();
				int y = laivoLangeliukopija.get(a).getY();
				((lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis) lenta2.getLangeliai().get(x).get(y - 1))
						.setLaivaLangeliui(laivelis);

				
			}
			System.out.println("laivas pridetas");
			antrojoZaidejoVaizdas.atnaujinkVaizda();
			laivai2.get(laivoIlgis - 1).setKordinates(laivoLangeliukopija);
		}
	}

	@Override
	public synchronized boolean sauk(String x, int y, String zaidejoId) {// +
		if (zaidejoId != zaidejoId1 & zaidejoId != zaidejoId2) {
			System.out.println("Nera tokio zaidejo");
			return false;
		}
		if (zaidejoId == zaidejoId1) {
			System.out.println("saunam");
			boolean arPataike = lenta2.sauk(x, y);

			pirmoZaidejoVaizdas.atnaujinkVaizda();
			if (arPataike = true) {
				if (lenta2.getLentosGyvybes() == 0) {
					zaidejas1_B = Busena.TuLaimejai;
					System.out.println("tu laimejai");
				}
			}
			return arPataike;
		} else {
			System.out.println("saunam");
			boolean arPataike = lenta1.sauk(x, y);

			antrojoZaidejoVaizdas.atnaujinkVaizda();
			if (arPataike = true) {
				if (lenta1.getLentosGyvybes() == 0) {
					zaidejas2_B = Busena.TuLaimejai;
					System.out.println("tu laimejai");
				}
			}
			return arPataike;
		}
	}

	@Override
	public synchronized List<Laivas> duokLaivus(String zaidejoId) {// +

		List<Laivas> laivukopija = new ArrayList<>();
		if (zaidejoId != this.zaidejoId1 || zaidejoId != this.zaidejoId2) {
			System.out.println("Zaidejas nezaidzia!!!");
			return null;
		}
		if (zaidejoId == zaidejoId1) {
			laivukopija = sukurkLaivuSarasa();// laivai1;
			ret1 = true;
		}
		if (zaidejoId == zaidejoId2) {
			laivukopija = sukurkLaivuSarasa();// laivai2;
			ret2 = true;
		}

		if (ret1 && ret2)
			busenos = Busena.RikiuojamLaivus;

		return laivukopija;
	}

	@Override
	public synchronized ZaidimoLenta duokZaidimoLenta(String zaidejoId) {// klaida
		ZaidimoLenta lentosKopija = new lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta("int lentosIlgis = zaidejoZaidimoLenta.getLangeliai().size();");

		// if (zaidejoId != zaidejoId1 || zaidejoId != zaidejoId2) {
		// System.out.println("Nera tokio zaidejo lenta");
		// return lentosKopija;
		// }

		if (zaidejoId == zaidejoId2) {
			// lentosKopija = lenta2;
			duotiLentaZaid_2 = true;
			lentosKopija = lenta1;
		}
		if (zaidejoId == zaidejoId1) {
			duotiLentaZaid_1 = true;
			lentosKopija = lenta2;
		}

		if (duotiLentaZaid_2 & duotiLentaZaid_1) {
			busenos = Busena.DalinemesLaivus;
		}

		return lentosKopija;
	}

	private synchronized boolean tikrinaArGalimaIdetiLaiva(Laivas laivas, String zaidejoId) {

		// 1 zaidejas
		if (zaidejoId == zaidejoId1) {
			List<Langelis> tikrinamosKordinates = laivas.getLaivoKoordinates();
			// begam per zaidejo mapa patikrindami ar galima toje vietoje deti laiva, o tai
			// reiskia patikrinam tuo paciu ar nera
			// aplinkui tas vietas kitu laivu
			for (int i = 0; i < tikrinamosKordinates.size(); i++) {
				String x = tikrinamosKordinates.get(i).getX();
				int y = tikrinamosKordinates.get(i).getY();
				int raidesVieta = 0;
				// ieskojau raides vietos masyve stringo
				for (int j = 0; j < abecele.length; j++) {
					if (abecele[j].equals(x)) {
						raidesVieta = j;
					}
				}
				// pradedu tikrinti langelius if sakiniai patikrina ar langeliai nera prie
				// krastu
				if (raidesVieta > 0 && raidesVieta < 9) {
					// raide ne prie krasto
					for (int k = raidesVieta - 1; k <= raidesVieta + 1; k++) {
						String raide = abecele[k];
						// skaicius ne prie krasto
						if (y > 1 && y < 10) {
							for (int o = y - 1; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raide).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;

								}
							}
						}
						// skaicius prie kairio krasto
						if (y == 1) {
							for (int o = y; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raide).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
						// skaicius prie desinio krasto
						if (y == 10) {
							for (int o = y - 1; o <= y; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raide).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
					}
				}
				// raide prie kairio krasto
				if (raidesVieta == 0) {
					for (int n = raidesVieta; n <= raidesVieta + 1; n++) {
						String raidelis = abecele[n];
						// skaicius ne prie krasto
						if (y > 1 && y < 10) {
							for (int o = y - 1; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raidelis).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;

								}
							}
						}
						// skaicius prie kairio krasto
						if (y == 1) {
							for (int o = y; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raidelis).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
						// skaicius prie desinio krasto
						if (y == 10) {
							for (int o = y - 1; o <= y; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raidelis).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
					}
				}
				// raide prie desinio krasto
				if (raidesVieta == 9) {
					for (int m = raidesVieta - 1; m <= raidesVieta; m++) {
						String raideliukas = abecele[m];
						// skaicius ne prie krasto
						if (y > 1 && y < 10) {
							for (int o = y - 1; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raideliukas).get(o - 1)
										.getLaivas();// nepamirsi
														// -1
								if (arLangelisTuriLaiva != null) {
									return false;

								}
							}
						}
						// skaicius prie kairio krasto
						if (y == 1) {
							for (int o = y; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raideliukas).get(o - 1)
										.getLaivas();// nepamirsi
														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
						// skaicius prie desinio krasto
						if (y == 10) {
							for (int o = y - 1; o <= y; o++) {
								Laivas arLangelisTuriLaiva = lenta1.zaidimoLentele.get(raideliukas).get(o - 1)
										.getLaivas();// nepamirsi
														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}

						}
					}
				}

			}
			return true;
		}
		// 2 zaidejas
		if (zaidejoId == zaidejoId2) {
			List<Langelis> tikrinamosKordinates = laivas.getLaivoKoordinates();
			// begam per zaidejo mapa patikrindami ar galima toje vietoje deti laiva, o tai
			// reiskia patikrinam tuo paciu ar nera
			// aplinkui tas vietas kitu laivu
			for (int i = 0; i < tikrinamosKordinates.size(); i++) {
				String x = tikrinamosKordinates.get(i).getX();
				int y = tikrinamosKordinates.get(i).getY();
				int raidesVieta = 0;
				// ieskojau raides vietos masyve
				// zaidimoLentele.keySet().size() panaudoti gal
				for (int j = 0; j < abecele.length; j++) {
					if (abecele[j].equals(x)) {
						raidesVieta = j;
					}
				}
				// pradedu tikrinti langelius if sakiniai patikrina ar langeliai nera prie
				// krastu
				if (raidesVieta > 0 && raidesVieta < 9) {
					// raide ne prie krasto
					for (int k = raidesVieta - 1; k <= raidesVieta + 1; k++) {
						String raide = abecele[k];
						// skaicius ne prie krasto
						if (y > 1 && y < 10) {
							for (int o = y - 1; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raide).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;

								}
							}
						}
						// skaicius prie kairio krasto
						if (y == 1) {
							for (int o = y; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raide).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
						// skaicius prie desinio krasto
						if (y == 10) {
							for (int o = y - 1; o <= y; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raide).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
					}
				}
				// raide prie kairio krasto
				if (raidesVieta == 0) {
					for (int n = raidesVieta; n <= raidesVieta + 1; n++) {
						String raidelis = abecele[n];
						// skaicius ne prie krasto
						if (y > 1 && y < 10) {
							for (int o = y - 1; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raidelis).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;

								}
							}
						}
						// skaicius prie kairio krasto
						if (y == 1) {
							for (int o = y; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raidelis).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
						// skaicius prie desinio krasto
						if (y == 10) {
							for (int o = y - 1; o <= y; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raidelis).get(o - 1).getLaivas();// nepamirsi
																														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
					}
				}
				// raide prie desinio krasto
				if (raidesVieta == 9) {
					for (int m = raidesVieta - 1; m <= raidesVieta; m++) {
						String raideliukas = abecele[m];
						// skaicius ne prie krasto
						if (y > 1 && y < 10) {
							for (int o = y - 1; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raideliukas).get(o - 1)
										.getLaivas();// nepamirsi
														// -1
								if (arLangelisTuriLaiva != null) {
									return false;

								}
							}
						}
						// skaicius prie kairio krasto
						if (y == 1) {
							for (int o = y; o <= y + 1; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raideliukas).get(o - 1)
										.getLaivas();// nepamirsi
														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}
						}
						// skaicius prie desinio krasto
						if (y == 10) {
							for (int o = y - 1; o <= y; o++) {
								Laivas arLangelisTuriLaiva = lenta2.zaidimoLentele.get(raideliukas).get(o - 1)
										.getLaivas();// nepamirsi
														// -1
								if (arLangelisTuriLaiva != null) {
									return false;
								}
							}

						}
					}
				}

			}
			return true;
		} else
			return false;
	}
	
	//////////////////////////////////////////////////////////////////////////
	private boolean arSkaiciaiLygus = true;
	private boolean arSkaiciaiIsEiles = true;
	private boolean arRaidesLygios = true;
	private boolean arRaidesIsEiles = true;
	public boolean arPerduotosKoordinatesGeros() {
	
		arSkaiciaiLygus();
		arSkaiciaiIsEiles();
		arRaidesLygios();
		arRaidesIsEiles();

		if (arLaivuFormaGera()) {
			return true;
		}
		return false;
	}

	private void arSkaiciaiLygus() {
		if (laivas.getLaivoIlgis() == 1) {
			return;
		}

		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			if (laivas.getLaivoKoordinates().get(i).getY() == laivas.getLaivoKoordinates().get(i + 1).getY()) {
				continue;

			} else {
				arSkaiciaiLygus = false;
			}

		}
	}

	private void arSkaiciaiIsEiles() {
		if (laivas.getLaivoIlgis() == 1) {
			return;
		}

		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			if (Math.abs(
					laivas.getLaivoKoordinates().get(i).getY() - laivas.getLaivoKoordinates().get(i + 1).getY()) == 1) {
				continue;

			} else {
				arSkaiciaiIsEiles = false;
			}

		}
	}

	private void arRaidesLygios() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
			int b = laivas.getLaivoKoordinates().get(i + 1).getX().charAt(0);
			if (a == b) {
				continue;

			} else {
				arRaidesLygios = false;
			}

		}
	}

	public void arRaidesIsEiles() {
		if (laivas.getLaivoIlgis() == 1) {
			return;
		}

		for (int i = 0; i < laivas.getLaivoKoordinates().size() - 1; i++) {
			int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
			int b = laivas.getLaivoKoordinates().get(i + 1).getX().charAt(0);
			if (Math.abs(a - b) == 1) {
				continue;

			} else {
				arRaidesIsEiles = false;
			}

		}
	}
	
	private boolean arLaivuFormaGera() {

		if (laivas.getLaivoIlgis() == 1) {
			return true;
		}
		
		if ((arSkaiciaiLygus == arSkaiciaiIsEiles) || (arRaidesLygios == arRaidesIsEiles)) {
			System.err.println("Laivas lenktas");
			return false;
		}

		if ((arSkaiciaiLygus == arRaidesLygios) || (arSkaiciaiIsEiles == arRaidesIsEiles)) {
			System.err.println("Laivas lenktas 2");
			return false;
		}
		return true;

	}
}
