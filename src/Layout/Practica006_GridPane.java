package Layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Practica006_GridPane extends Application {
    public static  void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setHgap(15);
        layout.setVgap(20);
        layout.add(new Button("0,3"),0,3);
        layout.add(new Button("1,0"),1,0);
        layout.add(new Button("2,1"),2,1);
        layout.add(new Button("2,2"),2,2);

        Scene escena = new Scene(layout);

        primaryStage.setScene(escena);
        primaryStage.show();
    }
}
