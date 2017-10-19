package lt.vcs.andrius.grafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class JavaFxGridPane extends Application {
	
	static Rectangle r = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("GridPane Experiment");

		r = new Rectangle(20, 20);
		r.setFill(Color.RED);

		Image image = new Image("https://cdn1.iconfinder.com/data/icons/toys-filled-line/614/2001_-_Toy_Boat-128.png",
				100, 0, false, false);

		Button button1 = new Button("Button 1");
		Button button2 = new Button("Button 2");
		Button button3 = new Button("Button 3");
		Button button4 = new Button("Button 4");

		GridPane gridPane = new GridPane();

		gridPane.add(button1, 0, 0, 1, 1);
		gridPane.add(button2, 1, 0, 1, 1);
		gridPane.add(button3, 2, 0, 1, 1);
		gridPane.add(button4, 0, 1, 1, 1);
		gridPane.add(new ImageView(image), 1, 1, 1, 1);
		gridPane.add(r, 2, 1, 1, 1);

		Scene scene = new Scene(gridPane, 240, 100);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);

		while (true) {

			r.setFill(Color.ALICEBLUE);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			r.setFill(Color.GREEN);
			
		}
	}
}