package lt.vcs.laivumusis.common;

public interface Langelis {
	
	// boolean = true/ kiekKartuSauta++
	public void sauk();
	
	// Grazinamas siame ir kituose langeliuose esantis laivas
	public Laivas getLaivas();
	
	public boolean arSauta();
	
	public int sakykKiekKartuSauta();
	
	public String getX();

	public int getY();

}
