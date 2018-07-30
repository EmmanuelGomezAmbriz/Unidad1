package Unidad_3_4;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientesNuevo {
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
    void cancelar(ActionEvent event) {
        Pane p = (Pane) contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void guardar(ActionEvent event) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "INSERT INTO clientes (nombre,rfc,calle,colonia,ciudad,pais,telefono,celular,email) VALUES ("+
                "'"+nombre.getText()+"',"+
                "'"+rfc.getText()+"',"+
                "'"+calle.getText()+"',"+
                "'"+colonia.getText()+"',"+
                "'"+ciudad.getText()+"',"+
                "'"+pais.getText()+"',"+
                "'"+telefono.getText()+"',"+
                "'"+celular.getText()+"',"+
                "'"+email.getText()+"')";
        statement.execute(sql);
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

}