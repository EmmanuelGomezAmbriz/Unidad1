package Unidad_3_4;

public class Existencia {
    private int idExistencia;
    private int idProducto;
    private int cantidad;
    private double costo;

    public Existencia(int idExistencia, int idProducto, int cantidad, double costo) {
        this.idExistencia = idExistencia;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public int getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(int idExistencia) {
        this.idExistencia = idExistencia;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}