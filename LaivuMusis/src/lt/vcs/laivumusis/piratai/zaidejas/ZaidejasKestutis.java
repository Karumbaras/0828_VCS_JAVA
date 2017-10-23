package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class ZaidejasKestutis implements lt.vcs.laivumusis.common.Zaidejas{

	private Zaidimas zaidimas;
	private String zaidejoId;
	ZaidimoLenta zaidimoLenta;
	private List<Laivas> laivuListas = new ArrayList<Laivas>();
	private String abecele = "ABCDEFGHIJ";
	Random generator = new Random();
	boolean tikrinimui = true;
		
	
	public ZaidejasKestutis(Zaidimas zaidimas, String zaidejoId) {
		this.zaidimas = zaidimas;
		this.zaidejoId = zaidejoId;
	}
	
	@Override
	public void run() {
		zaidimas.registruokZaideja(this.zaidejoId);
		System.out.println(this.zaidejoId);
		
		try {
			while (tikrinimui) {
				
			
				Thread.sleep(10);
				
				Busena zaidimoBusena = zaidimas.tikrinkBusena(zaidejoId);
			
				
				
				if (zaidimoBusena == Busena.DalinamesZemelapius) {
					this.zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
					Thread.sleep(1000);
				}
			

				if (zaidimoBusena == Busena.DalinemesLaivus) {
					this.laivuListas = zaidimas.duokLaivus(zaidejoId);
				    Thread.sleep(1000);	
				}
				
				if (zaidimoBusena == Busena.RikiuojamLaivus) {
					Thread.sleep(1000);	
					
					for (Laivas k : this.laivuListas) {
						try {
						int laivoKryptis = generator.nextInt(2);
						
						List<Langelis> laivoLangeliai = new ArrayList<Langelis>();
						
						if (laivoKryptis == 0) { //horizontalus
							int ixas = generator.nextInt(abecele.length() - k.getLaivoIlgis());
							int y = generator.nextInt(10) + 1;
							for (int i = 0; i < k.getLaivoIlgis(); i++) {
								String x = "" + abecele.charAt(ixas + i);
								laivoLangeliai.add(new lt.vcs.laivumusis.piratai.Langelis(x, y));
								}}
							else { //vertikalus
								String x = "" + abecele.charAt(generator.nextInt(abecele.length()));
								int y = generator.nextInt(10-k.getLaivoIlgis()) + 1;
								for (int i = 0; i < k.getLaivoIlgis(); i++) {
									laivoLangeliai.add(new lt.vcs.laivumusis.piratai.Langelis(x, y+i));
								}
							}
						k.setKordinates(laivoLangeliai);
						zaidimas.pridekLaiva(k, this.zaidejoId);
						 }catch (Exception e) {}
					}
				}
				
				if (zaidimoBusena == Busena.TavoEile) {
					String x = "" + abecele.charAt(generator.nextInt(abecele.length()));
					int y = generator.nextInt(10) + 1;
					zaidimas.sauk(x, y, this.zaidejoId);
					 
					}
				
				if (zaidimoBusena == Busena.TuLaimejai) {
					System.out.println(this.zaidejoId +" Laimejo");
					zaidimas.skaiciuokStatistika();
					tikrinimui = false;
				}
				
				if (zaidimoBusena == Busena.PriesasLaimejo) {
					System.out.println(this.zaidejoId +" Pralaimejo");
					tikrinimui = false;
				}
				
			}} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public Zaidimas getZaidimas() {
		// TODO Auto-generated method stub
		return null;
	}
}