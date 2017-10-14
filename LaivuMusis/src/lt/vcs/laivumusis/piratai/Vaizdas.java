package lt.vcs.laivumusis.piratai;

import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Langelis;

import lt.vcs.laivumusis.common.ZaidimoLenta;

public class Vaizdas implements lt.vcs.laivumusis.common.Vaizdas {
	ZaidimoLenta zaidimoLenta;

	public Vaizdas(ZaidimoLenta zaidimoLenta) {
		this.zaidimoLenta = zaidimoLenta;

	}

	@Override
	public synchronized void pieskVaizda() {
		// Pirma eilute
		System.out.print("  ");
		for (String k : this.zaidimoLenta.getLangeliai().keySet()) {
			System.out.print(k + " ");
		}
		System.out.println("");

		for (int i = 0; i < this.zaidimoLenta.getLangeliai().keySet().size(); i++) {
			if (i >= this.zaidimoLenta.getLangeliai().get("A").size()) {
				break;
			}
			System.out.print((i + 1) + " ");
			for (List<Langelis> l : this.zaidimoLenta.getLangeliai().values()) {
				if (l.get(i).getLaivas() != null & l.get(i).arSauta()) {
					// System.out.print(l.get(i).getX() + l.get(i).getY());
					System.out.print("N ");
				} else {
					if (l.get(i).getLaivas() != null) {
						// System.out.print(l.get(i).getX() + l.get(i).getY());
						System.out.print("L ");
					} else {

						if (l.get(i).arSauta()) {
							System.out.print("x ");
						} else {
							System.out.print("o ");
						}
					}
				}
			}
			System.out.println("");
		}
	}

	@Override
	public void atnaujinkVaizda() {
		isvalyk();
		pieskVaizda();
	}

	@Override
	public void isvalyk() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(" ");
		}
	}

}
