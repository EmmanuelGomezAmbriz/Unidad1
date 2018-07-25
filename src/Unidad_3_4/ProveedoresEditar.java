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

public class ProveedoresEditar implements Initializable {
    private int indice;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField rfc;

    @FXML
    private JFXTextField calle;

    @FXML
    private JFXTextField colonia;

    @FXML
    private JFXTextField ciudad;

    @FXML
    private JFXTextField pais;

    @FXML
    private JFXTextField telefono;

    @FXML
    private JFXTextField celular;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXComboBox<String> proveedor;

    private ArrayList<Proveedor> proveedores;

    @FXML
    void actualizar(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "UPDATE proveedores SET "+
                "nombre= '"+nombre.getText()+"',"+
                "rfc= '"+rfc.getText()+"',"+
                "calle= '"+calle.getText()+"',"+
                "colonia= '"+colonia.getText()+"',"+
                "ciudad= '"+ciudad.getText()+"',"+
                "pais= '"+pais.getText()+"',"+
                "telefono= '"+telefono.getText()+"',"+
                "celular= '"+celular.getText()+"',"+
                "email= '"+email.getText()+"'"+
                "WHERE idProveedor="+proveedores.get(indice).getIdProveedor();
        statement.execute(sql);
        proveedores.get(indice).setNombre(nombre.getText());
        proveedores.get(indice).setRfc(rfc.getText());
        proveedores.get(indice).setCalle(calle.getText());
        proveedores.get(indice).setColonia(colonia.getText());
        proveedores.get(indice).setCiudad(ciudad.getText());
        proveedores.get(indice).setPais(pais.getText());
        proveedores.get(indice).setTelefono(telefono.getText());
        proveedores.get(indice).setCelular(celular.getText());
        proveedores.get(indice).setEmail(email.getText());

    }

    @FXML
    void cancelar(ActionEvent event) {
        Pane p = (Pane) contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void eliminar(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "DELETE FROM proveedores WHERE idProveedor = "+proveedores.get(indice).getIdProveedor();

        statement.execute(sql);

        statement.close();
        connection.close();

        proveedor.getItems().remove(indice);
        proveedores.remove(indice);

        nombre.setText("");
        rfc.setText("");
        calle.setText("");
        colonia.setText("");
        ciudad.setText("");
        pais.setText("");
        telefono.setText("");
        celular.setText("");
        email.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            String sql = "SELECT * FROM proveedores";
            ResultSet resultset = statement.executeQuery(sql);

            proveedores = new ArrayList<Proveedor>();
            while (resultset.next()){
                proveedores.add(new Proveedor(
                        resultset.getInt("idProveedor"),
                        resultset.getString("nombre"),
                        resultset.getString("rfc"),
                        resultset.getString("calle"),
                        resultset.getString("colonia"),
                        resultset.getString("ciudad"),
                        resultset.getString("pais"),
                        resultset.getString("telefono"),
                        resultset.getString("celular"),
                        resultset.getString("email")
                ));
                proveedor.getItems().add(resultset.getString("nombre"));
            }
            proveedor.setOnAction(event -> {
                indice = proveedor.getSelectionModel().getSelectedIndex();

                nombre.setText(proveedores.get(indice).getNombre());
                rfc.setText(proveedores.get(indice).getRfc());
                calle.setText(proveedores.get(indice).getCalle());
                colonia.setText(proveedores.get(indice).getColonia());
                ciudad.setText(proveedores.get(indice).getCiudad());
                pais.setText(proveedores.get(indice).getPais());
                telefono.setText(proveedores.get(indice).getTelefono());
                celular.setText(proveedores.get(indice).getCelular());
                email.setText(proveedores.get(indice).getEmail());
            });
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
