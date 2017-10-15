package lt.vcs.laivumusis.piratai.zaidejas;

import java.util.ArrayList;
import java.util.List;

import lt.vcs.laivumusis.common.Busena;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class ZaidejasOdeta implements lt.vcs.laivumusis.common.Zaidejas {

		Zaidimas zaidimas;
		private String zaidejoId;

		public ZaidejasOdeta(Zaidimas zaidimas) {
			this.zaidimas = zaidimas;
		}

		@Override
		public void run() {
			this.zaidejoId = zaidimas.registruokZaideja();
			System.out.println(this.zaidejoId);
			try {
				while (true) {
					
					Thread.sleep(1000);
					Busena zaidimoBusena = zaidimas.tikrinkBusena(zaidejoId);
					System.out.println(zaidimoBusena);
					if (zaidimoBusena == Busena.DalinamesZemelapius) {
						ZaidimoLenta zaidimoLenta = zaidimas.duokZaidimoLenta(zaidejoId);
						Thread.sleep(1000);
					}

					if (zaidimoBusena == Busena.DalinemesLaivus) {
						List<Laivas> laivuListas = zaidimas.duokLaivus(zaidejoId);
			
						List<Langelis> langeliai = new ArrayList<Langelis>();
						for (int i = 0; i < laivuListas.get(0).getLaivoIlgis(); i++) {
							langeliai.add(new lt.vcs.laivumusis.piratai.Langelis("A", i+1));
						}
		
						laivuListas.get(0).setKordinates(langeliai);

						zaidimas.pridekLaiva(laivuListas.get(0), this.zaidejoId);
						//}
					}

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public Zaidimas getZaidimas() {
			return this.zaidimas;
		}

		public void padekLaivusAntLentos() {
			for (int i = 0; i < zaidimas.duokLaivus("1").size(); i++)
				zaidimas.pridekLaiva(zaidimas.duokLaivus("1").get(i), "1");
		}

	}

