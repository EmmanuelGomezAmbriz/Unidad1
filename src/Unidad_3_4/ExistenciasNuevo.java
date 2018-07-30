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

public class ExistenciasNuevo implements Initializable {

    private ArrayList<Producto> productos;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField cantidad;

    @FXML
    private JFXComboBox<String> producto;

    @FXML
    private JFXTextField costo;

    @FXML
    void cancelar(ActionEvent event) {
        Pane p = (Pane)contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void guardar(ActionEvent event) throws SQLException {
        int indiceProductoSeleccionado = producto.getSelectionModel().getSelectedIndex();
        int idProducto = productos.get(indiceProductoSeleccionado).getIdProducto();

        int cantidadExistencia = Integer.valueOf(cantidad.getText());
        double costoExistencia = Double.valueOf(costo.getText());

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "INSERT INTO existencias (idProducto, cantidad, costo) VALUES(" +
                ""+idProducto+","+
                ""+cantidadExistencia+","+
                ""+costoExistencia+")";
        statement.execute(sql);
        statement.close();
        connection.close();

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
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
