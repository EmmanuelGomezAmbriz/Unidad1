package Unidad_3_4;

public class VentaElementos {
    private int idProducto;
    private int idExistencia;
    private int cantidad;
    private String nombre;
    private double costoU;
    private double costoT;

    public VentaElementos(int idProducto, int idExistencia, int cantidad, String nombre, double costoU) {
        this.idProducto = idProducto;
        this.idExistencia = idExistencia;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.costoU = costoU;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(int idExistencia) {
        this.idExistencia = idExistencia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoU() {
        return costoU;
    }

    public void setCostoU(double costoU) {
        this.costoU = costoU;
    }

    public double getCostoT() {
        return costoT=cantidad*costoU;
    }

    public void setCostoT(double costoT) {
        this.costoT = costoT;
    }
}
