package lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lt.vcs.laivumusis.common.*;
import lt.vcs.laivumusis.USSENTERPRISE.DataBaseHelper;

import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;

public class ZaidimoLenta implements lt.vcs.laivumusis.common.ZaidimoLenta {
	
	private int konfiguracijosID;
	static Logger logas = Logger.getLogger(ZaidimoLenta.class.getName());
	// mapas
	//public Map<String, List<Langelis>> zaidimoLentele = new TreeMap<String, List<Langelis>>();
	public Map<String, List<Langelis>> zaidimoLentele = new HashMap<String, List<Langelis>>();
	private List<Laivas> laivuListas = new ArrayList<Laivas>();
	//String[] abecele = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	//nzn kas cia 
	int bendrosGyvyes = 15;
	DataBaseHelper isDB;

	// konstruktorius
	public ZaidimoLenta() throws ClassNotFoundException, SQLException {
		lentosKurimas();
	}
	//Povilo konstruktorius
	public ZaidimoLenta(int konfiguracijosID) throws ClassNotFoundException, SQLException {
		this.konfiguracijosID = konfiguracijosID;
		lentosKurimas();
	}
	
	public ZaidimoLenta(String testas) {}
	
//	public void lentosKurimas() {
//		for (String a : abecele) {
//			zaidimoLentele.put(a, kopijosAtidavimas());
//		}
//	}
	
	public void lentosKurimas() throws ClassNotFoundException, SQLException {
		isDB = new DataBaseHelper(konfiguracijosID);
		isDB.selectZaidimoLenFromKonfig();
		zaidimoLentele = isDB.getZaidimoLentele();

	}

	/*private List<Langelis> kopijosAtidavimas() {
		List<Langelis> langas = new ArrayList<Langelis>();
		for (int kordinateY = 1; kordinateY <= 10; kordinateY++) {
			for (String a : abecele) {
				langas.add(new Langelis(a, kordinateY));
			}

		}
		return langas;
	}*/

	

	public int getLentosGyvybes() {
		return bendrosGyvyes;
	}

	public void laivoPridejimasILista(Laivas laivas) {
		laivuListas.add(laivas);
	}

	@Override
	public synchronized List<Laivas> getLaivai() {
		return laivuListas;
	}

	@Override
	public synchronized boolean sauk(String x, int y) {
		zaidimoLentele.get(x).get(y-1).sauk();
		boolean arPataike = ((lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis) zaidimoLentele.get(x).get(y))
				.arLaivoDalisPasauta();
		if (arPataike == true) {
			bendrosGyvyes--;
		}
		return arPataike;
	}

	@Override
	public synchronized Map<String, List<Langelis>> getLangeliai() {
		return this.zaidimoLentele;
	}
	
	
	public int getKonfiguracijosID() {
		return konfiguracijosID;
	}

}
