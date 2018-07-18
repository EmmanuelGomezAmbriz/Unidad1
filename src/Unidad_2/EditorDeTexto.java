package Unidad_2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditorDeTexto extends Application {
    private static Stage stage;
    private String nombreArchivo = "";
    private Boolean modificado;

    @FXML
    void menuAbrir(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(stage);
        if(f != null) {
            this.nombreArchivo = f.getAbsolutePath();
            try {
                txtArea.setText(new String(
                        Files.readAllBytes(Paths.get(this.nombreArchivo))
                )
                );
                this.stage.setTitle("Editor de texto " + this.nombreArchivo);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private TextArea txtArea;

    @FXML
    void menuCerrar(ActionEvent event) {
        this.nombreArchivo = "";
        txtArea.setText("");
        this.stage.setTitle("Editor de texto " + this.nombreArchivo);
    }

    @FXML
    void menuGuardar(ActionEvent event) {
        if(this.nombreArchivo.isEmpty()){
            FileChooser fc = new FileChooser();
            File f = fc.showSaveDialog(this.stage);
            if(f != null){
                this.nombreArchivo = f.getAbsolutePath();
            }
        }
        try {
            Files.write(
              Paths.get(this.nombreArchivo),txtArea.getText().getBytes()
            );
            this.stage.setTitle("Editor de texto " + this.nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuGuardarComo(ActionEvent event) {

            FileChooser fc = new FileChooser();
            File f = fc.showSaveDialog(this.stage);
            if(f != null){
                this.nombreArchivo = f.getAbsolutePath();
                try {
                    Files.write(
                            Paths.get(this.nombreArchivo),txtArea.getText().getBytes()
                    );
                    this.stage.setTitle("Editor de texto " + this.nombreArchivo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    @FXML
    void menuNuevo(ActionEvent event) {
        this.nombreArchivo = "";
        txtArea.setText("");
        this.stage.setTitle("Editor de texto " + this.nombreArchivo);
    }

    @FXML
    void menuSalir(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent layout = FXMLLoader.load(getClass().getResource("EditorDeTexto.fxml"));

        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.show();
        this.stage = primaryStage;
        this.stage.setTitle("Editor de Texto");
    }
}
