package ProyectoFinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import proyectoFinalP.Actividad;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static ProyectoFinal.LogIn.stage;

public class InterfazPrincipal implements Initializable{

    private ArrayList<Notas> notas;
    private boolean editandoNota;
    private int indice;
    private int id;
    private ResultSet resultSet;

    @FXML
    private AnchorPane contenedorP;

    @FXML
    private Pane interfaz;

    @FXML
    private Pane menu;

    @FXML
    private JFXButton nuevoEditar;

    @FXML
    private JFXCheckBox hecho;

    @FXML
    private TilePane contenedor;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    void agregar(MouseEvent event) {
        mostrarPane("Pane");
        code.setText("");
        descripcion.setText("");
        hecho.setSelected(false);
        editandoNota = false;
        nuevoEditar.setText("Guardar Nuevo");
    }

    @FXML
    void cerrar(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void limpiar(MouseEvent event) {
        try {
            EjecutarSQL("DELETE FROM Actividades WHERE hecho = 1");
            for(int i = notas.size()-1; i>=0; i--) {
                if(notas.get(i).isHecho()) {
                    notas.remove(i);
                    contenedor.getChildren().remove(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void hecho(ActionEvent event) {

    }

    @FXML
    void minimizar(MouseEvent event) {
        stage.setIconified(true);
        stage.setResizable(false);
    }

    @FXML
    void nuevoEditar(ActionEvent event) throws SQLException {

        GridPane nuevaN = crearNota(code.getText(), descripcion.getText(), hecho.isSelected());
        if( editandoNota) {
            notas.get(indice).setTitulo(code.getText());
            notas.get(indice).setDescripcion(descripcion.getText());
            notas.get(indice).setHecho(hecho.isSelected());
            contenedor.getChildren().set(indice, nuevaN);
            EjecutarSQL("UPDATE Actividades SET " +
                    "titulo='"+ code.getText() +"', " +
                    "descripcion='" + descripcion.getText()+"', " +
                    "hecho="+hecho.isSelected()+
                    " WHERE idActividad="+ notas.get(indice).getId()
            );


        }
        else {
            notas.add(new Notas(id, code.getText(), descripcion.getText(), hecho.isSelected()));
            contenedor.getChildren().add(nuevaN);
            EjecutarSQL("INSERT INTO Actividades (titulo, descripcion, hecho) VALUES " +
                    "('"+ code.getText() +"', " +
                    "'" + descripcion.getText()+"', " +
                    hecho.isSelected()+")");
        }

        code.setText("");
        descripcion.setText("");
        hecho.setSelected(false);
        editandoNota =false;
        nuevoEditar.setText("Guardar Nuevo");

    }

    private void mostrarPane(String op) {
        switch (op) {
            case "Pane":
                if (interfaz.isVisible()) {
                    interfaz.setVisible(false);
                    contenedor.setVisible(false);
                    return;
                }
                break;
        }
                interfaz.setVisible(false);
                contenedor.setVisible(false);
            switch (op) {
                case "Pane":
                    interfaz.setVisible(true);
                    contenedor.setVisible(true);
                    break;
            }
        }
    private GridPane crearNota(String titulo, String descripcion, boolean activa) {
        GridPane nuevo = new GridPane();

        nuevo.setMaxWidth(250);
        nuevo.setMinWidth(250);
        nuevo.setPrefHeight(180);

        nuevo.setVgap(10);
        nuevo.setHgap(10);
        nuevo.setPadding(new Insets(10));


        Label tittle =new Label(titulo);
        Reflection r = new Reflection();
        r.setFraction(0.5f);
        tittle.setEffect(r);

        nuevo.add( tittle, 0, 0);


        nuevo.add(new Label(descripcion), 0, 1);

        if(activa)
            nuevo.setStyle("-fx-background-color:#e8ff1e; -fx-opacity:0.5;");
        else
            nuevo.setStyle("-fx-background-color:#e8ff1e; -fx-opacity:0.85;");

        nuevo.setOnMouseClicked(e -> {
            indice = buscarN((GridPane) e.getSource());
            this.code.setText(notas.get(indice).getTitulo());
            this.descripcion.setText(notas.get(indice).getDescripcion());
            this.hecho.setSelected(notas.get(indice).isHecho());
            this.editandoNota = true;
            this.nuevoEditar.setText("Actualizar");

        });
        return nuevo;
    }


    private int buscarN(GridPane gridPane) {
        int i=0;
        for(Node g : contenedor.getChildren()) {
            if( gridPane== g) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contenedor.setHgap(10);
        contenedor.setVgap(10);
        notas = new ArrayList<>();
        editandoNota = false;

        try {
            resultSet = ConsultarSQL("SELECT * FROM Actividades");
            notas.clear();
            while(resultSet.next()) {
                notas.add(new Notas(
                        resultSet.getInt("idActividad"),
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        resultSet.getBoolean("hecho")
                ));

                contenedor.getChildren().add(crearNota(
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        resultSet.getBoolean("hecho")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet ConsultarSQL(String sql) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:basedatos1.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);
        return statement.executeQuery(sql);

    }

    private void EjecutarSQL(String sql) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:sqlite:basedatos1.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);
        statement.execute(sql);
        statement.close();
        connection.close();
    }
}