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

public class ProductosEditar implements Initializable {

    private ArrayList<Proveedor> proveedores;
    private ArrayList<Producto> productos;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    private JFXComboBox<String> idproveeedor;

    @FXML
    private JFXComboBox<String> idproducto;

    @FXML
    void cancelar(ActionEvent event) {
        Pane p = (Pane)contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void eliminar(ActionEvent event) throws SQLException {
        int indice = idproducto.getSelectionModel().getSelectedIndex();
        int idProducto = productos.get(indice).getIdProducto();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "DELETE FROM productos WHERE idProducto="+idProducto;
        statement.execute(sql);

        idproducto.getSelectionModel().clearSelection();
        idproveeedor.getSelectionModel().clearSelection();
        nombre.setText("");
        descripcion.setText("");

        productos.remove(indice);
        idproducto.getItems().remove(indice);

    }

    @FXML
    void actualizar(ActionEvent event) throws SQLException {
        String nombreProducto = nombre.getText();
        String descripcionProducto = descripcion.getText();
        int indiceSeleccionado = idproveeedor.getSelectionModel().getSelectedIndex();
        int idProveedor = proveedores.get(indiceSeleccionado).getIdProveedor();

        int indiceSeleccionadoProducto = idproducto.getSelectionModel().getSelectedIndex();
        int idProducto = productos.get(indiceSeleccionadoProducto).getIdProducto();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "UPDATE productos SET " +
                "idProveedor="+idProveedor+","+
                "nombre='"+nombreProducto+"',"+
                "descripcion='"+descripcionProducto+"' WHERE idProducto= '"+idProducto;

        statement.execute(sql);
        statement.close();
        connection.close();
        productos.get(indiceSeleccionadoProducto).setNombre(nombreProducto);
        productos.get(indiceSeleccionadoProducto).setDescripcion(descripcionProducto);
        productos.get(indiceSeleccionadoProducto).setIdProveedor(idProveedor);

        idproveeedor.getSelectionModel().clearSelection();
        idproducto.getSelectionModel().clearSelection();
        nombre.setText("");
        descripcion.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            String sql = "SELECT * FROM productos";
            ResultSet resultSet = statement.executeQuery(sql);

            productos = new ArrayList<Producto>();

            while (resultSet.next()){
                idproducto.getItems().add(resultSet.getString("nombre"));
                productos.add(new Producto(
                        resultSet.getInt("idProducto"),
                        resultSet.getInt("idProveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion")
                ));
            }
            sql = "SELECT * FROM proveedores";
            resultSet = statement.executeQuery(sql);

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

            idproducto.setOnAction(event -> {
                int indice = idproducto.getSelectionModel().getSelectedIndex();
                nombre.setText(productos.get(indice).getNombre());
                descripcion.setText(productos.get(indice).getDescripcion());

                for(int i = 0; i<proveedores.size(); i++){
                    if(productos.get(indice).getIdProveedor()==proveedores.get(i).getIdProveedor());
                    idproveeedor.getSelectionModel().select(i);
                    break;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
