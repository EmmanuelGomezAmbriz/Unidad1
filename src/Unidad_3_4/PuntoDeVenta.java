package Unidad_3_4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PuntoDeVenta extends Application {

    @FXML
    private Pane contenedor;

    @FXML
    void acercaDe(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("AcercaDe.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void editarCliente(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ClientesEditar.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void editarExistencia(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ExistenciasEditar.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void editarProducto(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ProductosEditar.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void editarProveedor(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ProveedoresEditar.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void editarVenta(ActionEvent event) {

    }

    @FXML
    void nuevaExistencia(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ExistenciasNuevo.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void nuevaVenta(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("VentaNueva.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void nuevoCliente(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ClientesNuevo.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void nuevoProducto(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ProductosNuevo.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void nuevoProveedor(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("ProveedoresNuevo.fxml"));
        contenedor.getChildren().add(layout);
    }

    @FXML
    void salir(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent layout = FXMLLoader.load(getClass().getResource("PuntoDeVenta.fxml"));
        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Punto de Venta 0.0.1");
        primaryStage.show();
    }
}
