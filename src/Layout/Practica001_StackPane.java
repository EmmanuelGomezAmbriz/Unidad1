package Layout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Practica001_StackPane extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button boton = new Button("Click aquí");
        Label etiqueta = new Label("Buen día");
        Rectangle rectangulo = new Rectangle(100,200,Color.DARKBLUE);

        StackPane layout = new StackPane();

        layout.getChildren().add(rectangulo);
        layout.getChildren().add(boton);
        layout.getChildren().add(etiqueta);

        layout.setAlignment(rectangulo, Pos.TOP_LEFT);
        layout.setAlignment(etiqueta, Pos.BOTTOM_RIGHT);

        Scene escena = new Scene(layout, 300, 200);

        primaryStage.setScene(escena);
        primaryStage.show();

    }
}
