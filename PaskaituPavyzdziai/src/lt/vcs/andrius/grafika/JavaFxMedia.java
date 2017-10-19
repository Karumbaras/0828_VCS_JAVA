package lt.vcs.andrius.grafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class JavaFxMedia extends Application {

	public void start(Stage primaryStage) {
		
		HBox root = new HBox();
		final String mediaUrl = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
		
		Media media = new Media(mediaUrl);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		
		MediaView mediaView = new MediaView(mediaPlayer);
		root.getChildren().add(mediaView);
		primaryStage.setScene(new Scene(root, 600, 500));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
