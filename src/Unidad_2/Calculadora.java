package Unidad_2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Calculadora extends Application {
    private double numero1;
    private String operacion;

    @FXML
    private TextField Display;

    @FXML
    void LimpiarTodo(ActionEvent event){
        Display.setText("0");
    }

    @FXML
    private Button btnCE;

    @FXML
    private Button btnDiv;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btnMulti;

    @FXML
    private Button btnResta;

    @FXML
    private Button btnPunto;

    @FXML
    void accionIgual(ActionEvent event){
        double num2 = Double.valueOf(Display.getText());
        double resultado = 0;

        switch (operacion){
            case "+":
                resultado = numero1 + num2;
                break;
            case "-":
                resultado = numero1 - num2;
                break;
            case "*":
                resultado = numero1 * num2;
                break;
            case "/":
                resultado = numero1 / num2;
                break;
        }
        Display.setText(String.valueOf(resultado));
    }

    @FXML
    void accionBasica(ActionEvent event) {
        numero1 = Double.valueOf(Display.getText());
        Display.setText("0");
        operacion = ((Button)event.getSource()).getText();
    }

    @FXML
    void agrgarDigito(ActionEvent event) {
            Button boton = (Button)event.getSource();
            String digito = boton.getText();

            if(Display.getText().equalsIgnoreCase("0")){
                Display.setText(digito);
            }else {
                Display.setText(Display.getText()+digito);
            }
    }
    @FXML
    void agregarPunto(ActionEvent event) {
        if(Display.getText().contains(".") == false){
            Display.setText(Display.getText()+".");
        }else {
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent layout =FXMLLoader.load(getClass().getResource("Ejemplo_vista.fxml"));

        Scene escena = new Scene(layout);
        primaryStage.setTitle("Calculadora");
        primaryStage.setScene(escena);
        primaryStage.show();
    }
}
