package Unidad_2.Practica016_JFoenix_Formulario;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Formulario extends Application {

    @FXML
    private AnchorPane paneUsuario;

    @FXML
    private AnchorPane paneMapa;

    @FXML
    private AnchorPane paneEscuela;

    @FXML
    private AnchorPane paneSalir;


    @FXML
    void mostrarEscuela(MouseEvent event) {
        mostrarPane("Escuela");
    }

    @FXML
    void mostrarMapa(MouseEvent event) {
        mostrarPane("Mapa");
    }

    @FXML
    void mostrarUser(MouseEvent event) {
        mostrarPane("Usuario");
    }

    @FXML
    void salir(MouseEvent event) {
        Platform.exit();
    }
    @FXML
    private ImageView UserUp;

    @FXML
    private ImageView MapaUp;

    @FXML
    private ImageView EscuelaUp;


    private void mostrarPane(String op){
        switch (op){
            case "Usuario":
                if(paneUsuario.isVisible()){
                paneUsuario.setVisible(false);
                return;
            }
            break;
            case "Mapa":
                if(paneMapa.isVisible()){
                    paneMapa.setVisible(false);
                    return;
                }
                break;
            case "Escuela":
                if(paneEscuela.isVisible()){
                    paneEscuela.setVisible(false);
                    return;
                }
                break;
        }
        paneUsuario.setVisible(false);
        paneMapa.setVisible(false);
        paneEscuela.setVisible(false);
        paneSalir.setVisible(false);
        MapaUp.setVisible(false);
        EscuelaUp.setVisible(false);
        UserUp.setVisible(false);
        switch(op){
            case "Usuario":
                paneUsuario.setVisible(true);
                UserUp.setVisible(true);
                break;
            case "Mapa":
                paneMapa.setVisible(true);
                MapaUp.setVisible(true);
                break;
            case "Escuela":
                paneEscuela.setVisible(true);
                EscuelaUp.setVisible(true);
                break;
        }
    }
    private double posX, posY;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent layout = FXMLLoader.load(getClass().getResource("Formulario.fxml"));
        layout.setOnMousePressed(event -> {
            posX = primaryStage.getX()-event.getX();
            posY = primaryStage.getY()-event.getY();
            //System.out.println("0: "+posX+", "+posY);
            //System.out.println("1: "+primaryStage.getX()+", "+primaryStage.getY());
        });
        layout.setOnMouseDragged(event -> {
            primaryStage.setX(event.getX()+posX);
            primaryStage.setY(event.getY()+posY);
        });

        Scene escena = new Scene(layout);
        escena.setFill(Color.TRANSPARENT);
        primaryStage.setScene(escena);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
