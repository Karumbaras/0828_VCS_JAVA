package lt.vcs.andrius.grafika;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFxButton extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        Button btn = new Button();
        btn.setText("Quit");
        btn.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        
        Button btn2 = new Button();
        btn2.setText("Lambda");
        // Lambda expression
        btn2.setOnAction((ActionEvent event) -> {
            System.out.println("Lambda expression!");
        });
        
        Button btn3 = new Button();
        // Anonymous class
        btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {	
				System.out.println("Anonymous!");
			}
		});
        
        
        Button btn4 = new Button();
        btn4.setOnAction(new MyHandler());

        HBox root = new HBox();
        root.setPadding(new Insets(25));
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        root.getChildren().add(btn4);

        Scene scene = new Scene(root, 280, 200);

        stage.setTitle("Quit button");
        stage.setScene(scene);
        stage.show();
        
 
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class MyHandler implements EventHandler{

	@Override
	public void handle(Event event) {
		System.out.println("My handler");
	}
	
}


