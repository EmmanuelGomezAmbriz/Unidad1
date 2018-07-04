package Layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Practica004_FlowPane extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button boton1 = new Button("Botón 1");
        Button boton2 = new Button("Botón 2");
        Button boton3 = new Button("Botón 3");
        Button boton4 = new Button("Botón 4");
        Button boton5 = new Button("Botón 5");

        FlowPane layout = new FlowPane(Orientation.VERTICAL);

        layout.getChildren().addAll(boton1,boton2,boton3,boton4,boton5);
        layout.setPadding(new Insets(10));
        layout.setVgap(20);//Espaciado vertical
        layout.setHgap(10);//Espaciado hotizontal
        layout.setAlignment(Pos.TOP_CENTER);

        Scene escena = new Scene(layout,400,400);

        primaryStage.setScene(escena);
        primaryStage.show();

    }
}
