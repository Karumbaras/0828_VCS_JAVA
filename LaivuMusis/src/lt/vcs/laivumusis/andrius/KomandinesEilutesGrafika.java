package lt.vcs.laivumusis.andrius;

import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Vaizdas;
import lt.vcs.laivumusis.common.Zaidimas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class KomandinesEilutesGrafika implements Vaizdas {
	
	private Zaidimas zaidimas;
	
	public KomandinesEilutesGrafika(Zaidimas zaidimas) {
		this.zaidimas = zaidimas;
	}

	@Override
	public void pieskVaizda() {
		List<ZaidimoLenta> lentos = zaidimas.getLentos();
		
		for(ZaidimoLenta lenta : lentos){
			for(Map.Entry<String, List<Langelis>> langeliai: lenta.getLangeliai().entrySet()){
				for(Langelis langelis : langeliai.getValue()){
					printLangelis(langelis);
				}
				System.out.println();
			}
			
		}
		
	}
	
	private void printLangelis(Langelis langelis){
		String toPrint = null;
		if(langelis.getLaivas()!= null){
			toPrint = langelis.arSauta() ? "X":"8";
		} else {
			toPrint = langelis.arSauta() ? "-":"O";
		}
	}

	@Override
	public void atnaujinkVaizda() {
		pieskVaizda();
	}

	@Override
	public void isvalyk() {
		// TODO Auto-generated method stub
		
	}

}
