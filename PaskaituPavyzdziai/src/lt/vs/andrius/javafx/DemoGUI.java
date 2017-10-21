package lt.vs.andrius.javafx;

import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DemoGUI extends Application implements Runnable {

	// Statiniai langeliai
	public static Map<String, List<Langelis>> langeliai;
	
	 
	// Isskiriu atskira gija grafinei sasajai
	@Override
	public void run() {
		DemoGUI.launch(new String[0]);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("GUI demo!");


		GridPane gridPane = new GridPane();

		// Idedu langelius, nustatau dydzius ir priskiriu pradine spalva
		// Langeliai talpinami pagal koordinates
		int eilute = 0;
		for (Map.Entry<String, List<Langelis>> e : langeliai.entrySet()) {
			for (int i = 0; i < e.getValue().size(); i++) {
				Langelis langelis = e.getValue().get(i);
				langelis.setHeight(100);
				langelis.setWidth(100);
				langelis.setFill(Color.AQUA);
				gridPane.add(e.getValue().get(i), eilute, i, 1, 1);
			}
			eilute++;
		}

		Scene scene = new Scene(gridPane, 800, 800);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

}