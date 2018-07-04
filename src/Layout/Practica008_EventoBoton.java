package Layout;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Practica008_EventoBoton extends Application implements EventHandler{
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblValor1 = new Label("Valor 1: ");
        Label lblValor2 = new Label("Valor 2: ");
        Label lblResultado = new Label("Resultado: ");

        TextField txtValor1 = new TextField();
        TextField txtValor2 = new TextField();
        TextField txtResultado = new TextField();

        Button botonSuma = new Button("Sumar");

        GridPane layout = new GridPane();
        layout.add(lblValor1,0,0);
        layout.add(lblValor2,0,1);
        layout.add(lblResultado,0,3);
        layout.add(txtValor1,1,0);
        layout.add(txtValor2,1,1);
        layout.add(txtResultado,1,3);
        layout.add(botonSuma,1,2, 2, 1);
        botonSuma.setAlignment(Pos.CENTER);
/*
        botonSuma.setOnAction(event ->
        {
            int v1 = Integer.valueOf(txtValor1.getText());
            int v2 = Integer.valueOf(txtValor2.getText());

            int r = v1+v2;
            txtResultado.setText(String.valueOf(r));
        }
        );
*/
        botonSuma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int v1 = Integer.valueOf(txtValor1.getText());
                int v2 = Integer.valueOf(txtValor2.getText());

                int r = v1+v2;
                txtResultado.setText(String.valueOf(r));
            }
        });

        layout.setAlignment(Pos.CENTER);

        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.setVgap(10);

        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Sumador");
        primaryStage.show();

    }

    @Override
    public void handle(Event event) {
    }
}
