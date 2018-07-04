package Layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Practica005_BorderPane extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button bArriba = new Button("Botón Arriba");
        Button bDerecha = new Button("Botón Derecha");
        Button bIzquierda = new Button("Botón Izquierda");
        Button bAbajo = new Button("Botón Abajo");
        Button bCentro = new Button("Botón Centro");

        BorderPane layout = new BorderPane();

        layout.setTop(bArriba);
        layout.setBottom(bAbajo);
        layout.setCenter(bCentro);
        layout.setRight(bDerecha);
        layout.setLeft(bIzquierda);

        layout.setPadding(new Insets(20));

        Scene escena = new Scene(layout, 500,500);

        BorderPane.setAlignment(bArriba, Pos.CENTER);
        BorderPane.setAlignment(bAbajo, Pos.CENTER);
        BorderPane.setAlignment(bDerecha, Pos.CENTER);
        BorderPane.setAlignment(bIzquierda, Pos.CENTER);

        primaryStage.setScene(escena);
        primaryStage.show();

    }
}
