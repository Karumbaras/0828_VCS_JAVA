package lt.vcs.laivumusis.USSENTERPRISE.vaizdas;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

import lt.vcs.laivumusis.USSENTERPRISE.DataBaseHelper;
import lt.vcs.laivumusis.common.ZaidimoLenta;
import lt.vcs.laivumusis.common.Langelis;

public class Vaizdas implements lt.vcs.laivumusis.common.Vaizdas {
	//String[] abecele = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	//Map<String, List<lt.vcs.laivumusis.common.Langelis>> mapasKuriPiesim = new TreeMap<String, List<lt.vcs.laivumusis.common.Langelis>>();
	private ZaidimoLenta zaidimoLenta;
	DataBaseHelper isDB;
	private Map<String, List<Langelis>> zaidimoLentele;
	private int konfiguracijosID;
	
	
	public Vaizdas(int konfiguracijosID) {
		konfiguracijosID = this.konfiguracijosID;
	}
	
	
	public Vaizdas(ZaidimoLenta lenta) {
		zaidimoLentele = lenta.getLangeliai();
	}
	
	
	public Vaizdas(Map<String, List<lt.vcs.laivumusis.common.Langelis>> mapasKuriPiesim) {
		this.zaidimoLentele = mapasKuriPiesim;
	}

	public Vaizdas() throws ClassNotFoundException, SQLException {
		isDB = new DataBaseHelper(konfiguracijosID);
		isDB.selectZaidimoLenFromKonfig();
		zaidimoLentele = isDB.getZaidimoLentele();
	}
	
/*	
	@Override
	public void pieskVaizda() {
		System.out.print("  ");
		for (char kordinateX = 'A'; kordinateX <= 'J'; kordinateX++) {
			System.out.print(" " + kordinateX);
		}
		System.out.println();
		for (int kordinateY = 1; kordinateY <= 10; kordinateY++) {
			System.out.print(" " + kordinateY);
			for (String kordinateX : abecele) {
				System.out.print(((lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis) mapasKuriPiesim.get(kordinateX)
						.get(kordinateY - 1)).getlangelioVaizdaviamasZemelapyje());
			}
			System.out.println();
		}
	}
*/
	@Override
	public void pieskVaizda() {
		Collection<List<Langelis>> values = zaidimoLentele.values();
		
		System.out.print("  ");
		for(String s : zaidimoLentele.keySet()) {
			System.out.print(" "+s);
		}
		System.out.println();
		
		for (int kordinateY = 1; kordinateY <= values.size(); kordinateY++) {
			System.out.print(" " + kordinateY);
			for (String kordinateX : zaidimoLentele.keySet()) {
				System.out.print(((lt.vcs.laivumusis.USSENTERPRISE.langelis.Langelis) zaidimoLentele.get(kordinateX)
						.get(kordinateY - 1)).getlangelioVaizdaviamasZemelapyje());
			}
			System.out.println();
		}
	}
	
	@Override
	public void atnaujinkVaizda() {
		isvalyk();
		pieskVaizda();
	}

	@Override
	public void isvalyk() {
		System.out.flush();
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(" ");
//		}
	}

}
