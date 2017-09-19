package lt.vcs.kavosaparatas.manvydas.servisai;

import lt.vcs.kavosaparatas.manvydas.aparatai.KavosAparatas;
import lt.vcs.kavosaparatas.manvydas.resursai.Produktai;

public class Aptarnavimas {

	// Sukuria kavos aparata ir ji grazina
	public KavosAparatas sukurkAparata(int cukrusAparateGramais, int kavaAparateGramais,
			int pienasAparateMililitrais) {
		KavosAparatas kavosAparatas = new KavosAparatas(cukrusAparateGramais, kavaAparateGramais,
				pienasAparateMililitrais);
		return kavosAparatas;
	}

	// sukuria kavos aparatu masyva, ir kiekvienam masyvo nariui priskiria nauja
	// kavos aparata
	public KavosAparatas[] sukurkKavosAparatus(int skaicius) {
		KavosAparatas[] kavosAparatai = new KavosAparatas[skaicius];

		for (int i = 0; i < kavosAparatai.length; i++) {
			kavosAparatai[i] = new KavosAparatas();
		}
		return kavosAparatai;
	}

	// Isplauna perduota aparata
	public KavosAparatas isplaukAparata(KavosAparatas kavosAparatas) {
		if (kavosAparatas.panaudojimuSkaicius == 0) {
			System.out.println("Aparatas jau isplautas, plauti nereikia");
		} else {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
			kavosAparatas.panaudojimuSkaicius = 0;
			System.out.println("Aparatas sekmingai isplautas!");
		}
		return kavosAparatas;
	}

	// Isplauna per masyva perduotus aparatus
	public void isplaukAparatus(KavosAparatas[] kavosAparatai) {
		for (int i = 0; i < kavosAparatai.length; i++) {

			if (kavosAparatai[i].panaudojimuSkaicius == 0) {
				System.out.println("Aparatas, kurio indeksas " + i + " jau isplautas, plauti nereikia");
			} else {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
				}
				kavosAparatai[i].panaudojimuSkaicius = 0;
				System.out.println("Aparatas, kurio indeksas " + i + " sekmingai isplautas!");
			}
		}
	}
	

}
