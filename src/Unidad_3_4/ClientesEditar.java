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

public class ClientesEditar implements Initializable {
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
    private JFXComboBox<String> cliente;

    private ArrayList<Cliente> clientes;

    @FXML
    void actualizar(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "UPDATE clientes SET "+
                "nombre= '"+nombre.getText()+"',"+
                "rfc= '"+rfc.getText()+"',"+
                "calle= '"+calle.getText()+"',"+
                "colonia= '"+colonia.getText()+"',"+
                "ciudad= '"+ciudad.getText()+"',"+
                "pais= '"+pais.getText()+"',"+
                "telefono= '"+telefono.getText()+"',"+
                "celular= '"+celular.getText()+"',"+
                "email= '"+email.getText()+"'"+
                "WHERE idCliente="+clientes.get(indice).getIdCliente();
        statement.execute(sql);
        clientes.get(indice).setNombre(nombre.getText());
        clientes.get(indice).setRfc(rfc.getText());
        clientes.get(indice).setCalle(calle.getText());
        clientes.get(indice).setColonia(colonia.getText());
        clientes.get(indice).setCiudad(ciudad.getText());
        clientes.get(indice).setPais(pais.getText());
        clientes.get(indice).setTelefono(telefono.getText());
        clientes.get(indice).setCelular(celular.getText());
        clientes.get(indice).setEmail(email.getText());

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

        String sql = "DELETE FROM clientes WHERE idCliente = "+clientes.get(indice).getIdCliente();

        statement.execute(sql);

        statement.close();
        connection.close();

        cliente.getItems().remove(indice);
        clientes.remove(indice);

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

            String sql = "SELECT * FROM clientes";
            ResultSet resultset = statement.executeQuery(sql);

            clientes = new ArrayList<Cliente>();
            while (resultset.next()){
                clientes.add(new Cliente(
                        resultset.getInt("idCliente"),
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
                cliente.getItems().add(resultset.getString("nombre"));
            }
            cliente.setOnAction(event -> {
                indice = cliente.getSelectionModel().getSelectedIndex();

                nombre.setText(clientes.get(indice).getNombre());
                rfc.setText(clientes.get(indice).getRfc());
                calle.setText(clientes.get(indice).getCalle());
                colonia.setText(clientes.get(indice).getColonia());
                ciudad.setText(clientes.get(indice).getCiudad());
                pais.setText(clientes.get(indice).getPais());
                telefono.setText(clientes.get(indice).getTelefono());
                celular.setText(clientes.get(indice).getCelular());
                email.setText(clientes.get(indice).getEmail());
            });
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
