package Layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Pregunton extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label pregunta = new Label("Pregunta 1: En una pecera había 10 " +
                "peces. 2 se ahogaron, 3 se fueron nadando ¿Cuántos peces quedarón?");
        ComboBox<String> respuestas = new ComboBox<String>();
        respuestas.getItems().add("7");
        respuestas.getItems().add("8");
        respuestas.getItems().add("10");

        Label resultado = new Label("Correcto / Incorrecto");
        Button anterior = new Button("Anterior");
        Button siguiente = new Button("Siguiente");
        CheckBox mostrarRespuesta = new CheckBox("Mostrar respuesta");
        Label respuestaCorrecta = new Label("La respuesta correcta es:");

        pregunta.setPrefHeight(100);
        pregunta.setPrefWidth(200);
        pregunta.setWrapText(true);

        respuestas.setPromptText("Selecciona una respuesta");

        resultado.setAlignment(Pos.CENTER);
        resultado.setWrapText(true);

        respuestaCorrecta.setWrapText(true);
        respuestaCorrecta.setPrefHeight(30);

        respuestaCorrecta.setVisible(false);
        mostrarRespuesta.setOnAction(event -> {
            if(mostrarRespuesta.isSelected()){
                respuestaCorrecta.setVisible(true);
            }else{
                respuestaCorrecta.setVisible(false);
            }
        });

        cargarPreguntas();;
        pregunta.setText(preguntas.get(indicePreguntaActual).getPregunta());
        respuestas.getItems().clear();
        for(String r : preguntas.get(indicePreguntaActual).getRespuestas()){
            respuestas.getItems().add(r);
        }
        respuestaCorrecta.setText(preguntas.get(indicePreguntaActual).getRespuestas()[preguntas.get(indicePreguntaActual).getIndiceCorrecto()]);
        respuestas.setOnAction(event -> {
            if(respuestas.getSelectionModel().getSelectedIndex()==preguntas.get(indicePreguntaActual).getIndiceCorrecto()){
                resultado.setText("Correcto");
            }else{
                resultado.setText("Incorrecto");
            }
        });

        siguiente.setOnAction(event -> {
            if(indicePreguntaActual==preguntas.size()-1)
            return;
            indicePreguntaActual++;
            pregunta.setText(preguntas.get(indicePreguntaActual).getPregunta());
            respuestas.getItems().clear();
            for(String r : preguntas.get(indicePreguntaActual).getRespuestas()){
                respuestas.getItems().add(r);
            }respuestaCorrecta.setText(preguntas.get(indicePreguntaActual).getRespuestas()[preguntas.get(indicePreguntaActual).getIndiceCorrecto()]);
        });
        anterior.setOnAction(event -> {
            if(indicePreguntaActual==preguntas.size()-1)
                return;
            indicePreguntaActual--;
            pregunta.setText(preguntas.get(indicePreguntaActual).getPregunta());
            respuestas.getItems().clear();
            for(String r : preguntas.get(indicePreguntaActual).getRespuestas()){
                respuestas.getItems().add(r);
            }respuestaCorrecta.setText(preguntas.get(indicePreguntaActual).getRespuestas()[preguntas.get(indicePreguntaActual).getIndiceCorrecto()]);
        });

        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        HBox layoutBotones = new HBox();
        layoutBotones.getChildren().addAll(anterior,siguiente);
        layoutBotones.setPadding(new Insets(10));
        layoutBotones.setSpacing(10);
        layoutBotones.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().add(pregunta);
        layout.getChildren().add(respuestas);
        layout.getChildren().add(resultado);
        layout.getChildren().add(layoutBotones);
        layout.getChildren().add(mostrarRespuesta);
        layout.getChildren().add(respuestaCorrecta);

        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Preguntón");
        primaryStage.show();
    }
    private ArrayList<Pregunta> preguntas;
    private int indicePreguntaActual;
    private void cargarPreguntas(){
        indicePreguntaActual = 0;
        preguntas = new ArrayList<Pregunta>();
        preguntas.add(new Pregunta("¿Cuánto es 1 + 1?",new String[]{"1","2","3"},1));
        preguntas.add(new Pregunta("¿Cuántos planetas hay?",new String[]{"6","8","7"},2));
        preguntas.add(new Pregunta("¿Quién ganará el mundial?",new String[]{"Francia","Inglaterra","Bélgica"},0));
        preguntas.add(new Pregunta("¿Selecciona la respuesta correcta?",new String[]{"1","2","3"},2));
    }
}
