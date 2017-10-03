package lt.vcs.kavosaparatas.odeta.main;

import lt.vcs.kavosaparatas.common.exceptions.TrukstaProduktu;
import lt.vcs.kavosaparatas.odeta.aparatai.KavosAparatasDu;
import lt.vcs.kavosaparatas.odeta.kavospuodeliai.JuodosKavosPuodelis;
import lt.vcs.kavosaparatas.odeta.kavospuodeliai.LatteKavosPuodelis;
import lt.vcs.kavosaparatas.odeta.produktai.Produktai;
import lt.vcs.kavosaparatas.odeta.puodeliai.KavosPuodelis;
import lt.vcs.kavosaparatas.odeta.puodeliai.Puodelis;
import lt.vcs.kavosaparatas.odeta.servisai.KavosAparatuServisas;

public class MainKavosDu {

	public static void main(String[] args) {

		Produktai p = new Produktai(5, 5, 5);
		
		KavosAparatuServisas kac = new KavosAparatuServisas();
		
		KavosAparatasDu aparatas =kac.sukurVienaKavosAparata();
		
		aparatas.setProduktai(p);
	
		
		
		///// KODEL META KLAIDA:   aparatas.gaminkKava(LatteKavosPuodelis);
		
		
		//System.out.println(p.getCukrausKiekis());
		//System.out.println(p.getKavosPupeliuKiekis());
		//System.out.println(p.getVandensKiekis());
		
		kac.sakykKiekBuvoSukurtaAparatu();
		
		KavosAparatasDu aparatai[] = kac.sukurkKavosAparatus(8);
		
		aparatai[1].setProduktai(p);
		aparatai[1].sakykProduktuBusena();
		aparatai[1].isvalykProduktus();
		
		aparatai[1].sakykProduktuBusena();

	kac.sakykKiekBuvoSukurtaAparatu();
		
	while(true) {
		try {
			aparatas.gaminkKava();
		}catch(TrukstaProduktu e) {
			System.out.println("Truksta produktu, kavos pagaminti negalime" + e.getStackTrace());
		}
	}
	
	//aparatas.gaminkKavaKitaip(kavosTipas);
	
	/*while(true) {
		try {
			aparatas.gaminkKavaKitaip("juoda");
		}catch(TrukstaProduktu e) {
			System.out.println("Truksta produktu, kavos pagaminti negalime" + e.getStackTrace());
		}
	}*/
	


	/*while(true) {
		try {
			aparatas.gaminkKava(puodelis);
		}catch(TrukstaProduktu e) {
			System.out.println("Truksta produktu, kavos pagaminti negalime" + e.getStackTrace());
		}
	}*/
	
		
	
		//kavosAparatasDu.gaminkKava("juoda");
		
		//KavosAparatuServisas delKavosAparatu = new KavosAparatuServisas();
	
		//delKavosAparatu.sukurkKavosAparatus(5);
			
		//Produktai produktaiMusu = new Produktai(5, 10, 10);
		
		//produktaiMusu.getCukrausKiekis();
	
	
		
	}

}
