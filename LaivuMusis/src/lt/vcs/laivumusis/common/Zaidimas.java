package lt.vcs.laivumusis.common;

import java.util.List;

public interface Zaidimas extends Runnable {
	// visos taisykles zaidime

	// TODO paslepti nuo zaideju
	public List<ZaidimoLenta> getLentos();

	// 4 fun
	public void skaiciuokStatistika();

	// Zaidejai klausineja ka jiems daryti
	public Busena tikrinkBusena(String zaidejoId);

	// Grazina unikalu zaidejo id kitu operaciju kvietimui
	public String registruokZaideja();

	// Prideda zaidejo laiva, patalpina ant zemelapio
	// ir pasidaro laivo bei langeliu kopijas
	public void pridekLaiva(Laivas laivas, String zaidejoId);

	// Grazina true if pataike i laiva
	public boolean sauk(String x, int y, String zaidejoId);

	// Duoda zaidejui laivus, duoti KOPIJAS
	public List<Laivas> duokLaivus(String zaidejoId);

	// Duoda zaidejui lenta, duoti KOPIJA
	public ZaidimoLenta duokZaidimoLenta(String zaidejoId);
}
