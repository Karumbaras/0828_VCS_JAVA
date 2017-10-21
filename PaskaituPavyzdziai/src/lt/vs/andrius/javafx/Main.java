package lt.vs.andrius.javafx;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lt.vcs.andrius.grafika.JavaFxGridPane;

public class Main {

	public static List<Langelis> sukurkLangeliuSarasa(int n) {
		List<Langelis> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new Langelis());
		}
		return list;
	}

	public static void main(String[] args) {

		Map<String, List<Langelis>> langeliai = new HashMap<>();

		langeliai.put("A", sukurkLangeliuSarasa(5));
		langeliai.put("B", sukurkLangeliuSarasa(5));
		langeliai.put("C", sukurkLangeliuSarasa(5));
		langeliai.put("D", sukurkLangeliuSarasa(5));

		// Statiskai saugau ir perduodu langelius nes kaip per DemoGUI konstruktoriu perduoti neradau
		DemoGUI.langeliai = langeliai;

		new Thread(new DemoGUI()).start();

		// Sleep kad GUI spetu inicijuotis
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		// Toliau atsitiktinai keiciu spalvas
		Random r = new Random();
		List<Color> colours = allColors();

		while (true) {
			for (Map.Entry<String, List<Langelis>> e : langeliai.entrySet()) {
				for (Langelis l : e.getValue()) {
					
					// Galima spalva settinti tiesiog, o cia random darau
					// l.setFill(Color.RED);
					l.setFill(colours.get(r.nextInt(colours.size())));
				}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	// Random metodas is interneto tik tam kad random spalvas gauciau
	private static List<Color> allColors() {
		List<Color> colors = new ArrayList<>();
		Class clazz = null;
		try {
			clazz = Class.forName("javafx.scene.paint.Color");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (clazz != null) {
			Field[] field = clazz.getFields();
			for (int i = 0; i < field.length; i++) {
				Field f = field[i];
				Object obj = null;
				try {
					obj = f.get(null);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (obj instanceof Color) {
					colors.add((Color) obj);
				}

			}
		}
		return colors;
	}

}
