package lt.vcs.laivumusis.piratai;

import java.util.List;

import lt.vcs.laivumusis.common.Langelis;

public class LaivuValidatorius {
	
	Laivas laivas;
	private boolean arSkaiciaiLygus = false;
	private boolean arSkaiciaiIsEiles = false;
	private boolean arRaidesLygios = false;
	private boolean arRaidesIsEiles = false;
	
	public LaivuValidatorius(Laivas laivas) {
		this.laivas = laivas;
		
	}
	
	//TODO prideti zaidimo lentos ribu validacijos metoda
	public boolean arPerduotosKoordinatesGeros() {
		if((arPaduotaVienaRaide()&&arTeisingasLaivoIlgis()&&arLaivuFormaGera())==true) {
			return true;
		}
		return false;
	}
	
	private boolean arPaduotaVienaRaide() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			if (laivas.getLaivoKoordinates().get(i).getX().length() != 1) {
				System.out.println("blogos koordinates");
				return false;
			}
		}
		return true;
	}
	
	private boolean arTeisingasLaivoIlgis() {
		if (laivas.laivoIlgis != laivas.getLaivoKoordinates().size()) {
			System.out.println("Error: Laivas susideda is " + laivas.laivoIlgis + " langeliu");
			return false;
		}
		return true;
	}
	
	private boolean arSkaiciaiLygus() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				if (laivas.getLaivoKoordinates().get(i).getY() == laivas.getLaivoKoordinates().get(i + sk).getY()) {
					arSkaiciaiLygus = true;

				}
			}
		}
		return false;
	}
	
	private boolean arSkaiciaiIsEiles() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				if (Math.abs(laivas.getLaivoKoordinates().get(i).getY() - laivas.getLaivoKoordinates().get(i + sk).getY()) == 1) {
					arSkaiciaiIsEiles = true;
				}
			}
		}
		return false;
	}
	
	private boolean arRaidesLygios() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
				int b = laivas.getLaivoKoordinates().get(i + sk).getX().charAt(0);
				if (a == b) {
					arRaidesLygios = true;
				}
			}
		}
		return false;
	}
	
	
	private boolean arRaidesIsEiles() {
		for (int i = 0; i < laivas.getLaivoKoordinates().size(); i++) {
			for (int sk = i + 1; sk < i; sk++) {
				int a = laivas.getLaivoKoordinates().get(i).getX().charAt(0);
				int b = laivas.getLaivoKoordinates().get(i + sk).getX().charAt(0);
				if (Math.abs(a - b) == 1) {
					arRaidesIsEiles = true;
				}
			}
		}
		return false;
	}
	
	private boolean arLaivuFormaGera(){
		
		if ((arSkaiciaiLygus == arSkaiciaiIsEiles) || (arRaidesLygios == arRaidesIsEiles)) {
			System.out.println("Blogai priskirtos koordinates");
			return false;
		}

		if ((arSkaiciaiLygus == arRaidesLygios) || (arSkaiciaiIsEiles == arRaidesIsEiles)) {
			System.out.println("Blogai priskirtos koordinates");
			return false;
		}
		return true;
		
	}
	
}	
		

		

		

		

		

	