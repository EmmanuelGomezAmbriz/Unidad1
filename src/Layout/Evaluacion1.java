package Layout;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Evaluacion1 extends Application implements EventHandler{
    public static double n1;
    public static double n2;
    public static double r;
    public static double r1;
    public static char ope;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField display = new TextField("0");
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setEditable(false);

        Button btnLimpiarTodo = new Button("Limpiar todo");
        Button btnLimpiar = new Button("Limpiar");
        Button btn0 = new Button("0");
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");
        Button btn5 = new Button("5");
        Button btn6 = new Button("6");
        Button btn7 = new Button("7");
        Button btn8 = new Button("8");
        Button btn9 = new Button("9");
        Button btnSuma = new Button("+");
        Button btnResta = new Button("-");
        Button btnMultiplicacion = new Button("*");
        Button btnDivision = new Button("/");
        Button btnPunto = new Button(".");
        Button btnResultado = new Button("=");

        display.setPrefSize(300,30);
        btnLimpiarTodo.setPrefSize(150,40);
        btnLimpiar.setPrefSize(70,40);
        btnDivision.setPrefSize(70,40);
        btn0.setPrefSize(150,40);
        btn1.setPrefSize(70,40);
        btn2.setPrefSize(70,40);
        btn3.setPrefSize(70,40);
        btn4.setPrefSize(70,40);
        btn5.setPrefSize(70,40);
        btn6.setPrefSize(70,40);
        btn7.setPrefSize(70,40);
        btn8.setPrefSize(70,40);
        btn9.setPrefSize(70,40);
        btnSuma.setPrefSize(70,40);
        btnResta.setPrefSize(70,40);
        btnMultiplicacion.setPrefSize(70,40);
        btnPunto.setPrefSize(70,40);
        btnResultado.setPrefSize(70,40);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(display,0,0,4,1);
        layout.add(btnLimpiarTodo,0,1,2,1);
        layout.add(btnLimpiar,2,1);
        layout.add(btnDivision,3,1);
        layout.add(btn7,0,2);
        layout.add(btn8,1,2);
        layout.add(btn9,2,2);
        layout.add(btnMultiplicacion,3,2);
        layout.add(btn4,0,3);
        layout.add(btn5,1,3);
        layout.add(btn6,2,3);
        layout.add(btnResta,3,3);
        layout.add(btn1,0,4);
        layout.add(btn2,1,4);
        layout.add(btn3,2,4);
        layout.add(btnSuma,3,4);
        layout.add(btn0,0,5,2,1);
        layout.add(btnPunto,2,5);
        layout.add(btnResultado,3,5);

        btn0.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("0");
            }else {
                display.setText(display.getText()+"0");
            }
        });
        btn1.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("1");
            }else {
                display.setText(display.getText()+"1");
            }
        });
        btn2.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("2");
            }else {
                display.setText(display.getText()+"2");
            }
        });
        btn3.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("3");
            }else {
                display.setText(display.getText()+"3");
            }
        });
        btn4.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("4");
            }else {
                display.setText(display.getText()+"4");
            }
        });
        btn5.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("5");
            }else {
                display.setText(display.getText()+"5");
            }
        });
        btn6.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("6");
            }else {
                display.setText(display.getText()+"6");
            }
        });
        btn7.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("7");
            }else {
                display.setText(display.getText() + "7");
            }
        });
        btn8.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("8");
            }else {
                display.setText(display.getText()+"8");
            }
        });
        btn9.setOnAction(event ->
        {
            if (Double.parseDouble(display.getText())==0)
            {
                display.setText("9");
            }else {
                display.setText(display.getText()+"9");
            }
        });
        btnPunto.setOnAction(event ->
        {
            if (display.getText().contains(".")==false)
            {
                display.setText(display.getText()+".");
            }
        });
        btnLimpiar.setOnAction(event ->
        {
            display.setText("0");
        });
        btnLimpiarTodo.setOnAction(event -> {
            display.setText("0");
            n1 = Double.valueOf(0);
        });
        btnSuma.setOnAction(event -> {
            ope = '+';
            n1 = Double.parseDouble(display.getText());
            display.setText("0");
        });
        btnResta.setOnAction(event -> {
            ope = '-';
            n1 = Double.parseDouble(display.getText());
            display.setText("0");
        });
        btnMultiplicacion.setOnAction(event -> {
            ope = '*';
            n1 = Double.parseDouble(display.getText());
            display.setText("0");
        });
        btnDivision.setOnAction(event -> {
            ope = '/';
            n1 = Double.parseDouble(display.getText());
            display.setText("0");
        });
        btnResultado.setOnAction(event -> {
            switch (ope){
                case '+':
                    n2 = Double.valueOf(display.getText());
                    r = n1 + n2;
                    r1 = (int) r;
                    if(r==r1)
                        display.setText(""+(int)r1);
                    else display.setText(""+r);
                    break;
                case '-':
                    n2 = Double.valueOf(display.getText());
                    r = n1 - n2;
                    r1 = (int) r;
                    if(r==r1)
                        display.setText(""+(int)r1);
                    else display.setText(""+r);
                    break;
                case '*':
                    n2 = Double.valueOf(display.getText());
                    r = n1 * n2;
                    r1 = (int) r;
                    if(r==r1)
                        display.setText(""+(int)r1);
                    else display.setText(""+r);
                    break;
                case '/':
                    n2 = Double.valueOf(display.getText());
                    r = n1 / n2;
                    r1 = (int) r;
                    if(r==r1)
                    display.setText(""+(int)r1);
                    else display.setText(""+r);
                    break;
            }
        });

        Scene escena = new Scene(layout);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Calculadora Básica");
        primaryStage.show();

    }

    @Override
    public void handle(Event event) {

    }
}
