package ProyectoFinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class LogIn extends Application {

    public static Stage stage;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField usuario;

    @FXML
    private JFXPasswordField clave;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:basedatos1.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);//set timeout to 30 sec.

            String correo = usuario.getText();
            String pw = clave.getText();

            String consulta = "SELECT * FROM usuarios WHERE email = '"+correo+"' AND password = '"+pw+"'";
            ResultSet rs = statement.executeQuery(consulta);

            if(rs.next()){
                System.out.println("Usuario válido");
                System.out.println(rs.getString("nombre"));

                JOptionPane.showMessageDialog(null,"¡Bienvenido "+rs.getString("nombre")+"!");

                Parent layout = FXMLLoader.load(getClass().getResource("InterfazPrincipal.fxml"));
                Scene escena = new Scene(layout);
                escena.setFill(Color.TRANSPARENT);
                stage.setScene(escena);
            }
            else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos\n" +
                        "Por favor revisa los datos e intenta de nuevo.");
                System.out.println("Usuario no válido");
                usuario.setText("");
                clave.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Usuario: "+usuario.getText());
        System.out.println("Clave: "+clave.getText());
    }

    @FXML
    void registrarse(ActionEvent event) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent layout = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        stage = primaryStage;
        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Inicio de Sesiòn");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
