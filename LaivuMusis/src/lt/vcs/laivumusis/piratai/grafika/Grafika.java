package lt.vcs.laivumusis.piratai.grafika;

import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lt.vcs.laivumusis.piratai.Zaidimas;
//import lt.vcs.laivumusis.piratai.ZaidimoLenta;

import lt.vcs.laivumusis.common.Langelis;
import lt.vcs.laivumusis.piratai.ZaidimoLenta;

public class Grafika  extends Application implements Runnable {
	

	

	public static ZaidimoLenta zaidimoLenta1;
	public static ZaidimoLenta zaidimoLenta2;
	
	
	@Override
	public void run() {
		Grafika.launch(Grafika.class,new String[0]);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Piratu laivas");
		
		
		HBox hbox = new HBox(); // spacing = 8
	    hbox.setSpacing(10);
		GridPane gridPane1 = new GridPane();
		gridPane1.setHgap(1);
		gridPane1.setVgap(1);
	    GridPane gridPane2 = new GridPane();
	    gridPane2.setHgap(1);
		gridPane2.setVgap(1);
	    
	    hbox.getChildren().addAll(gridPane1, gridPane2);

		// Idedu langelius, nustatau dydzius ir priskiriu pradine spalva
		// Langeliai talpinami pagal koordinates
	    
	    
	    priskirkLangelius(zaidimoLenta1, gridPane1);
	    priskirkLangelius(zaidimoLenta2, gridPane2);
		

		Scene scene = new Scene(hbox, 800, 800);

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
			kvadratas.setHeight(10);
			kvadratas.setWidth(10);
			kvadratas.setFill(Color.AQUA);
			gridPane.add(kvadratas, eilute, i, 1, 1);
		}
		eilute++;
	}
	
}



}
