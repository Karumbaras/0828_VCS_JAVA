package lt.vcs.andrius.geometrija;

public class Taskas {

	public static int skaitliukas = 0;

	private int x;
	private int y;

	private String spalva;

	public Taskas(int x, int y, String spalva) {
		this.x = x;
		this.y = y;
		this.spalva = spalva;
		skaitliukas++;
	}

	public Taskas(int x, int y) {
		this.x = x;
		this.y = y;
		skaitliukas++;
	}

	public Taskas() {
		skaitliukas++;
	}

	public void spausdinkKoordinates() {
		System.out.println(x + "->" + y);
	}

	public void sakykSpalva() {
		System.out.println(this.spalva);
	}

	public boolean palygink(Taskas kitasTaskas) {
		return x == kitasTaskas.x && y == kitasTaskas.y;
	}

	public void keiskKordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void nustatykViska(int x, int y, String spalva) {
		this.x = x;
		this.y = y;
		this.spalva = spalva;
	}

	public void keiskSpalva(String spalva) {
		this.spalva = spalva;
	}

	public void setSpalva(String spalva) {
		this.spalva = spalva;
	}

	public String getSpalva() {
		return spalva;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
