package lt.vcs.andrius.grafika;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFxWebView extends Application {

	public void start(Stage primaryStage) {
		HBox root = new HBox();

		WebView webView = new WebView();
		webView.getEngine().load("http://delfi.lt");
		
		WebView webView2 = new WebView();
		webView2.getEngine().load("https://www.youtube.com/watch?v=lZgOhP_RMSM");
		
		
		root.getChildren().add(webView);
		root.getChildren().add(webView2);
		primaryStage.setScene(new Scene(root, 600, 800));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
