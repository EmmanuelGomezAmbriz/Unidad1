package Layout;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica010_ListView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ListView<String> listView = new ListView<String>();
        ObservableList<String> elementos = FXCollections.observableArrayList();
        elementos.add("Manzana");
        elementos.add("Pera");
        elementos.add("Sandía");
        elementos.add("Melón");
        elementos.add("Aguacate");
        listView.setItems(elementos);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOrientation(Orientation.HORIZONTAL);

        Button boton = new Button("Cuáles están seleccionados");
        boton.setOnAction(event -> {
            ObservableList<String> elementosSeleccionados =
                    listView.getSelectionModel().getSelectedItems();
            System.out.println("\nElementos seleccionados");
            for(String eSelected : elementosSeleccionados){
                System.out.println(eSelected);
            }
        });
        VBox layout = new VBox();
        layout.getChildren().add(listView);
        layout.getChildren().add(boton);

        Scene escena = new Scene(layout,400,200);

        primaryStage.setScene(escena);
        primaryStage.show();
    }
}
