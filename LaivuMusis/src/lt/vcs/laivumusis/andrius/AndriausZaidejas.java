package lt.vcs.laivumusis.andrius;

import java.util.List;

import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.common.Zaidejas;
import lt.vcs.laivumusis.common.Zaidimas;

public class AndriausZaidejas implements Zaidejas {
	
	private String zaidejoId = "Andriaus zaidejas";
	
	private Zaidimas zaidimas;
	
	
	public AndriausZaidejas(Zaidimas zaidimas){
		//TODO insert into db?
		if(!zaidimas.registruokZaideja(zaidejoId)){
			throw new RuntimeException("Neimanom prisiregistruoti");
		}
		
		this.zaidimas = zaidimas;
	}	

	@Override
	public void run() {
		
		while(true){
			 List<Laivas> laivai = zaidimas.duokLaivus(zaidejoId);
		}
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Zaidimas getZaidimas() {
		// TODO Auto-generated method stub
		return null;
	}

}
