package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class ZaidejasManvydas {
	Zaidimas zaidimas;
	private String zaidejoId;
	List<Laivas> laivai;

	public ZaidejasManvydas(Zaidimas zaidimas) {
		this.zaidimas = zaidimas;
	}

	//@Override
	public void run() {
		this.zaidejoId = zaidimas.registruokZaideja();
		System.out.println(this.zaidejoId);
		try {
			while (true) {
				
				Thread.sleep(3000);
				Busena zaidimoBusena = zaidimas.tikrinkBusena(zaidejoId);
				System.out.println(zaidimoBusena);
				if (zaidimoBusena == Busena.DalinamesZemelapius) {
					ZaidimoLenta zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					Thread.sleep(3000);
				}

				if (zaidimoBusena == Busena.DalinemesLaivus) {
					List<Laivas> laivuListas = zaidimas.duokLaivus(zaidejoId);
					for (Laivas l : laivuListas) {
						List<Langelis> langeliai = new ArrayList<Langelis>(l.getLaivoIlgis());
						for (int i = 0; i < langeliai.size(); i++) {
						
						}
						l.setKordinates(langeliai);
					}
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}

	public void pasiimkLaivus() {
		this.laivai = zaidimas.duokLaivus(this.zaidejoId);
	}
	
	public void priskirkLaivamsKoordinates() {
		for(int i = 0; i < this.laivai.size(); i++) {
			
			List<Langelis> langeliai = null;
			
			for (int k = 0; k < this.laivai.get(i).getLaivoIlgis(); k++) {
				langeliai.add(null);
			}
			
			this.laivai.get(i).setKordinates(langeliai);
		}
	}
	
	public List<Langelis> parinkKoordinates(List<Langelis> langeliai) {
		
		
		
		return langeliai;
		
	}
	
	public void padekLaivusAntLentos() {
		for (int i = 0; i < zaidimas.duokLaivus("1").size(); i++)
			zaidimas.pridekLaiva(zaidimas.duokLaivus("1").get(i), "1");
	}
}
















