package Unidad_3_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AcercaDe {

    @FXML
    private AnchorPane contenedor;

    @FXML
    void volver(ActionEvent event) {
        Pane p = (Pane) contenedor.getParent();
        p.getChildren().remove(0);
    }

}
