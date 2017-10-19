package lt.vcs.andrius.grafika;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class JavaFxHello extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        
        Button btn2 = new Button();
        btn2.setText("Hello");
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        HBox root = new HBox();
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        
        
        VBox box = new VBox();
        Button btn3 = new Button();
        btn3.setText("3");
        
        Button btn4 = new Button();
        btn4.setText("4");
        
        root.getChildren().add(box);
        
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }
}
