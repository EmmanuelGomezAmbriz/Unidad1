package Unidad_3_4;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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

public class ProductosNuevo implements Initializable {

    private ArrayList<Proveedor> proveedores;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    private JFXComboBox<String> idproveeedor;

    @FXML
    void cancelar(ActionEvent event) {
        Pane p = (Pane)contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void guardar(ActionEvent event) throws SQLException {
        String nombreProducto = nombre.getText();
        String descripcionProducto = descripcion.getText();
        int indiceSeleccionado = idproveeedor.getSelectionModel().getSelectedIndex();
        int idProveedor = proveedores.get(indiceSeleccionado).getIdProveedor();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");

        Statement statement = connection.createStatement();

        String sql = "INSERT INTO productos (idProveedor,nombre,descripcion) VALUES(" +
                "'"+idProveedor+"',"+
                "'"+nombreProducto+"',"+
                "'"+descripcionProducto+"')";

        statement.execute(sql);
        statement.close();
        connection.close();
        idproveeedor.getSelectionModel().clearSelection();
        nombre.setText("");
        descripcion.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            String sql = "SELECT * FROM proveedores";
            ResultSet resultSet = statement.executeQuery(sql);

            proveedores = new ArrayList<Proveedor>();

            while (resultSet.next()){
                idproveeedor.getItems().add(resultSet.getString("nombre"));
                proveedores.add(new Proveedor(
                        resultSet.getInt("idProveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("rfc"),
                        resultSet.getString("calle"),
                        resultSet.getString("colonia"),
                        resultSet.getString("ciudad"),
                        resultSet.getString("pais"),
                        resultSet.getString("telefono"),
                        resultSet.getString("celular"),
                        resultSet.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
