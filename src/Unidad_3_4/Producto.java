package Unidad_3_4;

public class Producto {
    private int idProducto;
    private int idProveedor;
    private String nombre;
    private String descripcion;

    public Producto(int idProducto, int idProveedor, String nombre, String descripcion) {
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}