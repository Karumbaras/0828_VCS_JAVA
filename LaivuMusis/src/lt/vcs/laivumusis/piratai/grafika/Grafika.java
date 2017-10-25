package lt.vcs.laivumusis.piratai.grafika;

//import java.awt.Button;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lt.vcs.laivumusis.piratai.Zaidimas;
import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.piratai.ZaidimoLenta;

public class Grafika extends Application implements Runnable {

	public static ZaidimoLenta zaidimoLenta1;
	public static ZaidimoLenta zaidimoLenta2;
	public static String zaidejoID1;
	public static String zaidejoID2;


	@Override
	public void run() {
		Grafika.launch(Grafika.class, new String[0]);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Piratu laivas");

		// Pagrindinis Grid'as
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(5);

		// Text zaidejoId1 = new Text(zaidimas.getZaidejoId1());
		// Text zaidejoId2 = new Text(zaidimas.getZaidejoId2());

		// Zaideju ID
		Text zaidejoId1 = new Text("Zaidejas 1");
		zaidejoId1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		Text zaidejoId2 = new Text("Zaidejas 2");
		zaidejoId2.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		gridPane.add(zaidejoId1, 1, 1, this.zaidimoLenta1.getPlotis(), 1);
		gridPane.add(zaidejoId2, 2, 1, this.zaidimoLenta2.getPlotis(), 1);

		// Zaidimo lentu grid'ai
		GridPane gridPane1 = new GridPane();
		gridPane1.setHgap(1);
		gridPane1.setVgap(1);

		GridPane gridPane2 = new GridPane();
		gridPane2.setHgap(1);
		gridPane2.setVgap(1);

		gridPane.add(gridPane1, 1, 4);
		gridPane.add(gridPane2, 2, 4);

		// Idedu langelius, nustatau dydzius ir priskiriu pradine spalva
		// Langeliai talpinami pagal koordinates

		priskirkLangelius(zaidimoLenta1, gridPane1);
		priskirkLangelius(zaidimoLenta2, gridPane2);

		Scene scene = new Scene(gridPane, 700, 400);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void priskirkLangelius(ZaidimoLenta zaidimoLenta, GridPane gridPane) {
		Map<String, List<Langelis>> langeliai = zaidimoLenta.getLangeliai();

		int eilute = 0;
		for (Map.Entry<String, List<Langelis>> e : langeliai.entrySet()) {

			for (int i = 0; i < e.getValue().size(); i++) {

				lt.vcs.laivumusis.piratai.Langelis langelis = (lt.vcs.laivumusis.piratai.Langelis) e.getValue().get(i);
				Rectangle kvadratas = langelis.getKvadratas();
				kvadratas.setHeight(30);
				kvadratas.setWidth(30);
				kvadratas.setFill(Color.AQUA);
				gridPane.add(kvadratas, eilute, i, 1, 1);
			}
			eilute++;
		}

	}

}
