package lt.vcs.laivumusis.USSENTERPRISE.langelis;

import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.ZaidimoLenta;
import lt.vcs.laivumusis.common.Laivas;
import lt.vcs.laivumusis.USSENTERPRISE.laivas.*;
import lt.vcs.laivumusis.USSENTERPRISE.zaidimoLenta.*;
import java.util.ArrayList;
import java.util.List;

public class Langelis implements lt.vcs.laivumusis.common.Langelis {

	private int suviuSkaicius;
	private boolean langelioBusena;
	private String x;
	private int y;
	Laivas langelioLaivas;
	private String langelioVaizdaviamasZemelapyje = " #";
	private boolean arLaivoDalisPasauta = false;
	private boolean arLangelisTuriLaiva = false;

	public Langelis(Integer y) {
		this.y = y;
	}

	public Langelis(String x) {
		this.x = x;
	}

	public Langelis(String x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public boolean arLaivoDalisPasauta() {
		return arLaivoDalisPasauta;
	}
	public boolean arLangelisTuriLaiva() {
		return arLangelisTuriLaiva;
	}

	@Override
	public void sauk() {
		langelioBusena = true;
		suviuSkaicius++;
		if (arLangelisTuriLaiva == true) {
			langelioVaizdaviamasZemelapyje = " X";
			arLaivoDalisPasauta = true;
		} else
			langelioVaizdaviamasZemelapyje = " +";
	}

	public void setLaivoDalisPasauta() {
		arLaivoDalisPasauta = true;
	}

	public void setLaivaLangeliui(lt.vcs.laivumusis.common.Laivas laivas) {
		this.langelioLaivas = laivas;
		langelioVaizdaviamasZemelapyje = " O";
		arLangelisTuriLaiva = true;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Override
	public Laivas getLaivas() {

		return langelioLaivas;
	}

	@Override
	public boolean arSauta() {
		return langelioBusena;
	}

	@Override
	public int sakykKiekKartuSauta() {
		return suviuSkaicius;

	}

	@Override
	public String getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	public String getlangelioVaizdaviamasZemelapyje() {
		return langelioVaizdaviamasZemelapyje;
	}

}
