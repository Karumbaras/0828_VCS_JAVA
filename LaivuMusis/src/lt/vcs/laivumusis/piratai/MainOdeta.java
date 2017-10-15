package lt.vcs.laivumusis.piratai;

import lt.vcs.laivumusis.piratai.zaidejas.Zaidejas;

public class MainOdeta {

	public static void main(String[] args) {
		
		Zaidimas zaidimasOdetos = new Zaidimas();
		
		//ZaidimoLenta zaidimoLentaOdetos = new ZaidimoLenta(10, 10);
		
		Zaidejas zaidejasOdetosPirmas = new Zaidejas(zaidimasOdetos);
		Zaidejas zaidejasOdetosAntras = new Zaidejas(zaidimasOdetos);

		zaidejasOdetosPirmas.getZaidimas();
		
		zaidimasOdetos.registruokZaideja();
		
		int [][] laivai = {{1,2}};
		
		//zaidejasOdetosPirmas.getZaidimas().duokLaivus(zaidejoId);
		
		
		//Thread zaidejasOdetosPirmas = new Thread(new Zaidejas(zaidimasOdetos));
		//Thread zaidejasOdetosAntras = new Thread(new Zaidejas(zaidimasOdetos));

		//zaidejasOdetosPirmas.start();
		//zaidejasOdetosAntras.start();
	
		
		//Vaizdas vaizdasOdetos = new Vaizdas(zaidimoLentaOdetos);
		
		//vaizdasOdetos.pieskVaizda();
		
		
	}

}
