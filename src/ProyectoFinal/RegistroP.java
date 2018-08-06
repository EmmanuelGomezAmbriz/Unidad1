package ProyectoFinal;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroP {
    public static Stage stage;

    @FXML
    private JFXTextField correo;

    @FXML
    private AnchorPane contenedorA;

    @FXML
    private JFXTextField contraseña1;

    @FXML
    private JFXTextField Rusuario;

    @FXML
    private JFXTextField contraseña2;

    @FXML
    void Cancelar(ActionEvent event) throws IOException {

        Parent layout = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene escena = new Scene(layout);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(escena);
        stage.show();
    }

    @FXML
    void Registrarse(ActionEvent event) throws SQLException {
        if(correo.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Por favor ingresa un correo valido");
        if(Rusuario.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Por favor ingresa un nombre de usuario");
        if (contraseña2.getText().equals(""))
            JOptionPane.showMessageDialog(null,"No dejar campos vacios");
        if (contraseña1.getText().equals("") )
            JOptionPane.showMessageDialog(null,"No dejar campos vacios");
        if (contraseña1.getText().equals(contraseña2.getText()))
        {
            Connection coneccion=DriverManager.getConnection("jdbc:sqlite:basedatos1.db");
            Statement statement=coneccion.createStatement();
            statement.setQueryTimeout(30);

            String sql="INSERT INTO usuarios(email,password,nombre) VALUES ('"+correo.getText()+"','"+contraseña2.getText()+
                    "','"+Rusuario.getText()+"')";
            statement.execute(sql);

            correo.setText("");
            Rusuario.setText("");
            contraseña1.setText("");
            contraseña2.setText("");
        }
        else
            JOptionPane.showMessageDialog(null,"Verifica la contraseña");
    }

}
