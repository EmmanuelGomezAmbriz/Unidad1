package ProyectoFinal;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.awt.*;

import static ProyectoFinal.LogIn.stage;

public class InterfazPrincipal {

    @FXML
    private AnchorPane contenedorP;

    @FXML
    private Pane interfaz;

    @FXML
    private Pane menu;

    @FXML
    private TilePane contenedor;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    void agregar(MouseEvent event) {
        mostrarPane("Pane");
    }

    @FXML
    void cerrar(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void limpiar(MouseEvent event) {

    }

    @FXML
    void hecho(ActionEvent event) {

    }

    @FXML
    void minimizar(MouseEvent event) {
        stage.setIconified(true);
        stage.setResizable(false);
    }

    @FXML
    void nuevoEditar(ActionEvent event) {
        contenedor.setVgap(10);
        contenedor.setHgap(10);
        GridPane layout = new GridPane();
        layout.setPrefSize(120, 135);
        layout.setStyle("-fx-background-color: #e8ff1e");
        layout.setOpacity(0.85);
        layout.setPadding(new Insets(10));
        layout.add(new Label("" + code.getText() + "\n\n" + descripcion.getText()), 0, 0);
        contenedor.getChildren().add(layout);
    }

    private void mostrarPane(String op) {
        switch (op) {
            case "Pane":
                if (interfaz.isVisible()) {
                    interfaz.setVisible(false);
                    contenedor.setVisible(false);
                    return;
                }
                break;
        }
                interfaz.setVisible(false);
                contenedor.setVisible(false);
            switch (op) {
                case "Pane":
                    interfaz.setVisible(true);
                    contenedor.setVisible(true);
                    break;
            }
        }
}