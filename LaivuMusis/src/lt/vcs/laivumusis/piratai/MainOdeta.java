package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasOdeta;

public class MainOdeta {

	public static void main(String[] args) {

		Zaidimas zaidimasOdetos = new Zaidimas();
		
		// ZaidimoLenta zaidimoLentaOdetos = new ZaidimoLenta(10, 10);


		// Zaidejas zaidejasOdetosAntras = new Zaidejas(zaidimasOdetos);

		// zaidejasOdetosPirmas.getZaidimas();

		// String registruotasPirmasId = zaidimasOdetos.registruokZaideja();
		// System.out.println(registruotasPirmasId);

		Thread zaidejasOdetosPirmas = new Thread(new ZaidejasOdeta(zaidimasOdetos, "Odeta"));
		Thread zaidejasOdetosAntras = new Thread(new ZaidejasOdeta(zaidimasOdetos, "Jonas"));

		zaidejasOdetosPirmas.start();
		zaidejasOdetosAntras.start();

		// zaidejasOdetosPirmas.getZaidimas().duokLaivus(registruotasPirmasId);

		// String registruotasPirmasId = zaidimasOdetos.registruokZaideja();
		// System.out.println(registruotasPirmasId);

		// Vaizdas vaizdasOdetos = new Vaizdas(zaidimoLenta);

		// vaizdasOdetos.pieskVaizda();

		// zaidejasOdetosPirmas.getZaidimas().duokZaidimoLenta(registruotasPirmasId);

		// Laivas laivasPirmas = new Laivas(2);

		// kaip prisideda laivas, kaip priskiriamos koordinates
		// zaidejasOdetosPirmas.getZaidimas().pridekLaiva, registruotasPirmasId);

	}

}
