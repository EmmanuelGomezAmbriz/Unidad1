package Unidad_3_4;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String rfc;
    private String calle;
    private String colonia;
    private String ciudad;
    private String pais;
    private String telefono;
    private String celular;
    private String email;

    public Proveedor(int idProveedor, String nombre, String rfc, String calle, String colonia, String ciudad, String pais, String telefono, String celular, String email) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.rfc = rfc;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.pais = pais;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
