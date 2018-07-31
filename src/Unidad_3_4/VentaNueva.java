package Unidad_3_4;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentaNueva implements Initializable {

    private ArrayList<Cliente> clientes;
    private ArrayList<VentaProductoCosto> ventaProductoCostos;
    private ObservableList<VentaElementos> ventaElementos;
    private double calcularTotal(){
        double total = 0;
        for(VentaElementos v :ventaElementos){
            total = total + v.getCostoT();
        }
        return total;
    }

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField producto;

    @FXML
    private Label granTotal;

    @FXML
    private JFXComboBox<String> cliente;

    @FXML
    private TableView<VentaElementos> tabla;

    @FXML
    private JFXListView<String> listaProductos;

    @FXML
    private JFXTextField cantidad;

    @FXML
    void agregar(ActionEvent event) {
        int indiceLista = listaProductos.getSelectionModel().getSelectedIndex();
        if(indiceLista < 0)
            return;
        int cantidadProductoExistente = ventaProductoCostos.get(indiceLista).getCantidad();
        int cantidadProducto = Integer.valueOf(cantidad.getText().isEmpty()?"1":cantidad.getText());
        if(cantidadProducto>cantidadProductoExistente)
            return;
        int idProducto = ventaProductoCostos.get(indiceLista).getIdProducto();
        int idExistencia = ventaProductoCostos.get(indiceLista).getIdExistencia();
        String nombreProducto = ventaProductoCostos.get(indiceLista).getNombre();
        double costoUnitario = ventaProductoCostos.get(indiceLista).getCosto();

        ventaElementos.add(new VentaElementos(idProducto,idExistencia,cantidadProducto, nombreProducto,costoUnitario));
        granTotal.setText("$ "+String.valueOf(calcularTotal()));
    }

    @FXML
    void cancelar(ActionEvent event) {
        Pane p = (Pane)contenedor.getParent();
        p.getChildren().remove(0);
    }

    @FXML
    void guardar(ActionEvent event) throws SQLException {
        if(ventaElementos.size()==0)
            return;
        int idClienteCombo = cliente.getSelectionModel().getSelectedIndex();
        int idCliente = clientes.get(idClienteCombo).getIdCliente();
        double total = calcularTotal();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "INSERT INTO ventas (idCliente, fecha, total) " +
                "VALUES ("+idCliente+", date('now'), "+total+")";

        statement.execute(sql);
        sql = "SELECT last_insert_rowid()";
        ResultSet  resultSet = statement.executeQuery(sql);
        int idVenta = -1;
        if(resultSet.next()){
            idVenta = resultSet.getInt(1);
        }
        for (VentaElementos v: ventaElementos){
            sql = "INSERT INTO ventasDetalle (idVenta, idProducto, costo) " +
                    "VALUES ("+idVenta+", "+v.getIdProducto()+", "+v.getCostoT()+")";
            statement.execute(sql);
        }
        ventaElementos.clear();
        granTotal.setText(String.valueOf(calcularTotal()));
    }

    @FXML
    void quitar(ActionEvent event) {
        int indice = tabla.getSelectionModel().getSelectedIndex();
        if(indice < 0)
            return;
        ventaElementos.remove(indice);

        granTotal.setText("$ "+String.valueOf(calcularTotal()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            String sql = "SELECT * FROM clientes";
            ResultSet resultSet = statement.executeQuery(sql);

            clientes = new ArrayList<Cliente>();
            cliente.getItems().clear();

            while(resultSet.next()){
                cliente.getItems().add(resultSet.getString("nombre"));
                clientes.add(new Cliente(
                        resultSet.getInt("idCliente"),
                        resultSet.getString("nombre"),
                        resultSet.getString("rfc"),
                        resultSet.getString("calle"),
                        resultSet.getString("colonia"),
                        resultSet.getString("ciudad"),
                        resultSet.getString("pais"),
                        resultSet.getString("telefono"),
                        resultSet.getString("celular"),
                        resultSet.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        producto.setOnKeyTyped(event -> {
            String patron = producto.getText();
            if(patron.length()<3)
                return;
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:pVenta.db");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(60);

                String sql = "SELECT * FROM productos, existencias WHERE productos.idProducto=existencias.idProducto " +
                        "AND nombre LIKE '%" + patron + "%' AND cantidad > 0";

                ResultSet resultSet = statement.executeQuery(sql);
                ventaProductoCostos = new ArrayList<VentaProductoCosto>();
                listaProductos.getItems().clear();

                while (resultSet.next()){
                    listaProductos.getItems().add(resultSet.getString("nombre"));
                    ventaProductoCostos.add(new VentaProductoCosto(
                            resultSet.getInt("idProducto"),
                            resultSet.getInt("idExistencia"),
                            resultSet.getString("nombre"),
                            resultSet.getString("descripcion"),
                            resultSet.getInt("cantidad"),
                            resultSet.getDouble("costo")
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        TableColumn<VentaElementos, String> columnaCantidad = new TableColumn<>("Cantidad:");
        columnaCantidad.setMinWidth(15);
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<VentaElementos, String> columnaNombre = new TableColumn<>("Nombre Producto:");
        columnaNombre.setMinWidth(265);
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<VentaElementos, String> columnaCostoU = new TableColumn<>("C. U.");
        columnaCostoU.setMinWidth(5);
        columnaCostoU.setCellValueFactory(new PropertyValueFactory<>("costoU"));

        TableColumn<VentaElementos, String> columnaCostoT = new TableColumn<>("C. T.");
        columnaCostoT.setMinWidth(5);
        columnaCostoT.setCellValueFactory(new PropertyValueFactory<>("costoT"));

        tabla.getColumns().clear();
        tabla.getColumns().addAll(columnaCantidad, columnaNombre, columnaCostoU, columnaCostoT);

        ventaElementos = FXCollections.observableArrayList();
        tabla.setItems(ventaElementos);

    }
}
