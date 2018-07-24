package Unidad_2.Practica015_JFoenix_InicioSesion;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.security.sasl.SaslClient;
import java.sql.*;

public class InicioSesion extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent layout = FXMLLoader.load(getClass().getResource("inicioSesion.fxml"));

        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Inicio de Sesiòn");
        primaryStage.show();
    }
        @FXML
        private JFXTextField usuario;

        @FXML
        private JFXPasswordField clave;

        @FXML
        void iniciarSesion(ActionEvent event) {
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
                }
                else{
                    System.out.println("Usuario no válido");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Usuario: "+usuario.getText());
            System.out.println("Clave: "+clave.getText());

    }
}
