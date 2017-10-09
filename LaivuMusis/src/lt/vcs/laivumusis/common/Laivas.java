package lt.vcs.laivumusis.common;

import java.util.List;

public interface Laivas {
	
	// Naudojama nusakyti zaidimui laivo kordinates pagal x y/ String int
	public void setKordinates(List<Langelis> langeliai);

	// Zaidimas pasakys koks laivo ilgis, kiek langeliu
    public int getLaivoIlgis();

    // 100% nusautas
    public boolean arNusautas();

    public List<Langelis> getLaivoKoordinates();

}
