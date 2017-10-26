package lt.vcs.laivumusis.piratai;

import java.util.Scanner;

import lt.vcs.laivumusis.common.Zaidejas;
import lt.vcs.laivumusis.piratai.grafika.Grafika;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasArnas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasKestutis;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasLina;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasManvydas;
import lt.vcs.laivumusis.piratai.zaidejas.ZaidejasOdeta;

public class MainArnas {
	static lt.vcs.laivumusis.common.Zaidimas zaidimas;
	static lt.vcs.laivumusis.common.Zaidejas zaidejas1;
	static lt.vcs.laivumusis.common.Zaidejas zaidejas2;

	static boolean zaidejoPasirinkimas1 = false;
	static boolean zaidejoPasirinkimas2 = false;
	static boolean pasirinktasZaidimas = false;

	public static void pasirinkZaideja1() {
		while (!zaidejoPasirinkimas1) {
			System.out.println("Pasirinkite 1 zaidejo numeri");
			System.out.println("1. Lina");
			System.out.println("2. Manvydas");
			System.out.println("3. Odeta");
			System.out.println("4. Kestutis");
			System.out.println("5. Arnas");

			Scanner input = new Scanner(System.in);
			int zaidejoSk1 = input.nextInt();
			switch (zaidejoSk1) {
			case 1:
				zaidejas1 = new ZaidejasLina(zaidimas, "Lina");
				System.out.println("Pasirinkta Lina");
				zaidejoPasirinkimas1 = true;
				break;
			case 2:

				zaidejas1 = new ZaidejasManvydas(zaidimas, "Manvydas");
				System.out.println("Pasirinkta Mavydas");
				zaidejoPasirinkimas1 = true;
				break;

			case 3:
				zaidejas1 = new ZaidejasOdeta(zaidimas, "Odeta");
				System.out.println("Pasirinkta Odeta");
				zaidejoPasirinkimas1 = true;
				break;

			case 4:
				zaidejas1 = new ZaidejasKestutis(zaidimas, "Kestutis");
				System.out.println("Pasirinkta Kestutis");
				zaidejoPasirinkimas1 = true;
				break;
			case 5:
				zaidejas1 = new ZaidejasArnas(zaidimas, "Arnas");
				System.out.println("Pasirinkta Arnas");
				zaidejoPasirinkimas1 = true;
				break;
			default:
				System.out.println("neteisingai pasirinkot, bandykit dar");

			}
		}
	}

	public static void pasirinkZaideja2() {
		while (!zaidejoPasirinkimas2) {
			System.out.println("Pasirinkite 2 zaidejo numeri");
			System.out.println("1. Lina");
			System.out.println("2. Manvydas");
			System.out.println("3. Odeta");
			System.out.println("4. Kestutis");
			System.out.println("5. Arnas");
			Scanner input = new Scanner(System.in);
			int zaidejoSk2 = input.nextInt();
			switch (zaidejoSk2) {
			case 1:
				zaidejas2 = new ZaidejasLina(zaidimas, "Lina");
				System.out.println("Pasirinkta Lina");
				zaidejoPasirinkimas2 = true;
				break;
			case 2:

				zaidejas2 = new ZaidejasManvydas(zaidimas, "Manvydas");
				System.out.println("Pasirinkta Mavydas");
				zaidejoPasirinkimas2 = true;
				break;

			case 3:
				zaidejas2 = new ZaidejasOdeta(zaidimas, "Odeta");
				System.out.println("Pasirinkta Odeta");
				zaidejoPasirinkimas2 = true;
				break;

			case 4:
				zaidejas2 = new ZaidejasKestutis(zaidimas, "Kestutis");
				System.out.println("Pasirinkta Kestutis");
				zaidejoPasirinkimas2 = true;
				break;
			case 5:
				zaidejas2 = new ZaidejasArnas(zaidimas, "Arnas");
				System.out.println("Pasirinkta Arnas");
				zaidejoPasirinkimas2 = true;
				break;
			default:
				System.out.println("neteisingai pasirinkot, bandykit dar");

			}
		}
	}

	public static void pasirinkZaidima() {
		while (!pasirinktasZaidimas) {
			System.out.println("Pasirinkite skaiciu zaidimo kuri norite zaisti:");
			System.out.println("1. Piratai");
			System.out.println("2. UssEnterprise");
			Scanner input = new Scanner(System.in);
			int zaidimoPasirinkimas = input.nextInt();

			switch (zaidimoPasirinkimas) {
			case 1:
				zaidimas = new lt.vcs.laivumusis.piratai.Zaidimas();
				System.out.println("Pasirinkta Piratai");
				pasirinktasZaidimas = true;
				break;
			case 2:
				// Neveikia:(
				zaidimas = new lt.vcs.laivumusis.USSENTERPRISE.zaidimas.Zaidimas();
				System.out.println("Pasirinkta USSENTERPRISE");
				pasirinktasZaidimas = true;
				break;
			default:
				System.out.println("neteisingai pasirinktas zaidejas");
				break;
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("Sveiki atvyke i laivu Musi");
		pasirinkZaidima();
		pasirinkZaideja1();
		pasirinkZaideja2();

		Thread pirmasZaidejas = new Thread(zaidejas1);
		Thread antrasZaidejas = new Thread(zaidejas2);

		Grafika grafika = new Grafika();
		Thread grafikaThread = new Thread(grafika);

		grafika.zaidimoLenta1 = (ZaidimoLenta) zaidimas.getLentos().get(0);
		grafika.zaidimoLenta2 = (ZaidimoLenta) zaidimas.getLentos().get(1);

		grafikaThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pirmasZaidejas.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		antrasZaidejas.start();

	}

}
