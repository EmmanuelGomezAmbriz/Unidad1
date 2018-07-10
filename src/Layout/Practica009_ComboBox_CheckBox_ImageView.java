package Layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica009_ComboBox_CheckBox_ImageView extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().add("Imagen Russia 2018");
        comboBox.getItems().add("Semifinal Francia vs Bélgica");
        comboBox.getItems().add("Semifinal Inglaterra vs Croacia");
        comboBox.setPromptText("Selecciona elemento");

        comboBox.setOnAction(event -> {
            System.out.println(comboBox.getValue());
            int id = comboBox.getSelectionModel().getSelectedIndex();
            System.out.println("El indice es: "+id);
        });

        CheckBox checkBox = new CheckBox("Permitir cambiar imagen");
        checkBox.setSelected(false);
        checkBox.setOnAction(event -> {
            System.out.println(checkBox.isSelected());
        });

        Image imagenRussia = new Image("Images/imagen.jpg");
        Image imagenSemis = new Image("Images/imagenSemis.jpg");
        Image imagenSemis2 = new Image("Images/imagenSemis2.jpg");

        ImageView imageView = new ImageView(imagenRussia);
        imageView.setImage(imagenRussia);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        comboBox.setOnAction(event -> {
            if(checkBox.isSelected()){
                if(comboBox.getSelectionModel().getSelectedIndex()==0){
                    imageView.setImage(imagenRussia);
                }if(comboBox.getSelectionModel().getSelectedIndex()==1){
                    imageView.setImage(imagenSemis);
                }if(comboBox.getSelectionModel().getSelectedIndex()==2){
                    imageView.setImage(imagenSemis2);
                }
            }
        });

        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.getChildren().add(comboBox);
        layout.getChildren().add(checkBox);
        layout.getChildren().add(imageView);

        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("ComboBox");
        primaryStage.show();
    }
}
