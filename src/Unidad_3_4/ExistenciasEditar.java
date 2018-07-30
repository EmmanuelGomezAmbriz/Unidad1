package Unidad_3_4;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExistenciasEditar implements Initializable {

    private ArrayList<Producto> productos;
    private ArrayList<Existencia> existencias;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXComboBox<String> existencia;


    @FXML
    private JFXTextField cantidad;

    @FXML
    private JFXComboBox<String> producto;

    @FXML
    private JFXTextField costo;

    @FXML
    void eliminar(ActionEvent event) throws SQLException {
        int idSelecExistencia = existencia.getSelectionModel().getSelectedIndex();
        int idExistencia = existencias.get(idSelecExistencia).getIdExistencia();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "DELETE FROM existencias WHERE idExistencia ="+idExistencia;
        statement.execute(sql);

        statement.close();
        connection.close();
        existencia.getItems().remove(idSelecExistencia);
        existencias.remove(idSelecExistencia);

        existencia.getSelectionModel().clearSelection();
        producto.getSelectionModel().clearSelection();
        cantidad.setText("");
        costo.setText("");

    }

    @FXML
    void cancelar(ActionEvent event) {
        Pane p = (Pane)contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void guardar(ActionEvent event) throws SQLException {
        int idSelecExistencia = existencia.getSelectionModel().getSelectedIndex();
        int idExistencia = existencias.get(idSelecExistencia).getIdExistencia();
        int indiceProductoSeleccionado = producto.getSelectionModel().getSelectedIndex();
        int idProducto = productos.get(indiceProductoSeleccionado).getIdProducto();
        int cantidadExistencia = Integer.valueOf(cantidad.getText());
        double costoExistencia = Double.valueOf(costo.getText());

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "UPDATE existencias SET idProducto ="+idProducto+", cantidad = "+cantidadExistencia+
                ", costo = "+costoExistencia+" WHERE idExistencia = "+idExistencia;
        statement.execute(sql);
        statement.close();
        connection.close();

        existencias.get(idSelecExistencia).setIdProducto(idProducto);
        existencias.get(idSelecExistencia).setCosto(costoExistencia);
        existencias.get(idSelecExistencia).setCantidad(cantidadExistencia);

        existencia.getSelectionModel().clearSelection();
        producto.getSelectionModel().clearSelection();
        cantidad.setText("");
        costo.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            String sql = "SELECT * FROM productos";

            productos = new ArrayList<Producto>();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                producto.getItems().add(resultSet.getString("nombre"));
                productos.add(new Producto(
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("idProveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion")
                ));
            }

            sql = "SELECT existencias.*, productos.nombre AS prodNombre, proveedores.nombre AS provNombre " +
                    "FROM existencias, productos, proveedores WHERE existencias.idProducto = productos.idProducto" +
                    " AND productos.idProveedor=proveedores.idProveedor";

            existencias = new ArrayList<Existencia>();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                existencia.getItems().add(resultSet.getString("prodNombre")+
                        "[" + resultSet.getString("provNombre")+ "]");
                existencias.add(new Existencia(
                        resultSet.getInt("idExistencia"),
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("costo")
                ));
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        existencia.setOnAction(event -> {
            int indiceSeleccionadoExistencia = existencia.getSelectionModel().getSelectedIndex();

            cantidad.setText(String.valueOf(existencias.get(indiceSeleccionadoExistencia).getCantidad()));
            costo.setText(String.valueOf(existencias.get(indiceSeleccionadoExistencia).getCosto()));

            for(int i = 0; i < productos.size(); i++){
                if (existencias.get(indiceSeleccionadoExistencia).getIdProducto() == productos.get(i).getIdProducto()) {
                producto.getSelectionModel().select(i);
                }
            }

        });
    }

}
