package Layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Practica007_TilePane extends Application {
    public  static  void main(String[] args){

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        TilePane layout = new TilePane();
        layout.setPrefColumns(3);
        layout.setPrefRows(2);
        for (int i=0;i<9;i++) {
            layout.getChildren().add(new Button("Botón " + i));
            layout.setPadding(new Insets(10));
            layout.setHgap(10);
            layout.setVgap(10);
        }
        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.show();
    }
}