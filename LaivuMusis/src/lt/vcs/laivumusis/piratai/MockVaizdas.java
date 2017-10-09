package lt.vcs.laivumusis.piratai;

import java.util.List;
import java.util.Map;

import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.common.Vaizdas;
import lt.vcs.laivumusis.common.ZaidimoLenta;

public class MockVaizdas implements Vaizdas {
	ZaidimoLenta zaidimoLenta;

	public MockVaizdas(MockZaidimoLenta zaidimoLenta) {
		this.zaidimoLenta = zaidimoLenta;

	}

	@Override
	public void pieskVaizda() {
		for (Map.Entry<String, List<Langelis>> k : this.zaidimoLenta.getLangeliai().entrySet()) {
			for (int i = 0; i < k.getValue().size(); i++) {
				if (!k.getValue().get(i).arSauta()) {
					System.out.print("o ");
					// System.out.print(k.getValue().get(i).getX()+k.getValue().get(i).getY());
				} else {
					System.out.print("x ");
				}
			}
			System.out.println("");
		}
	}

	@Override
	public void atnaujinkVaizda() {
		pieskVaizda();
	}

	@Override
	public void isvalyk() {
		for (Map.Entry<String, List<Langelis>> k : this.zaidimoLenta.getLangeliai().entrySet()) {
			for (int i = 0; i < k.getValue().size(); i++) {
				System.out.print("o ");
			}
			System.out.println("");
		}

	}

}
