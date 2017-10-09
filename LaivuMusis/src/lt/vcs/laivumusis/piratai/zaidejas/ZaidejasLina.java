package lt.vcs.laivumusis.piratai.zaidejas;

import lt.vcs.laivumusis.common.Zaidimas;

public class ZaidejasLina implements lt.vcs.laivumusis.common.Zaidejas{
	Zaidimas zaidimas;
	private String zaidejoId;
	
	public ZaidejasLina(Zaidimas zaidimas) {
		this.zaidimas = zaidimas;
	}
	
	@Override
	public void run() {
		System.out.println("kazakass");
	}

	@Override
	public Zaidimas getZaidimas() {
		return this.zaidimas;
	}

	
	public void padekLaivusAntLentos() {
		for (int i = 0; i < zaidimas.duokLaivus("1").size();i++) 
		zaidimas.pridekLaiva(zaidimas.duokLaivus("1").get(i), "1");
	}
}
