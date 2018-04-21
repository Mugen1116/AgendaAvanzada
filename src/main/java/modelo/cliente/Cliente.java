package modelo.cliente;

import modelo.direccion.Direccion;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.utils.DateInterface;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Cliente implements DateInterface, Serializable {


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private static final long serialVersionUID = 7296563457500384738L;
    private String nombre;
    private String NIF;
    private Direccion direccion;
    private String email;
    private LocalDateTime fechaAlta;
    private Tarifa tarifa;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================
    public Cliente(){
        super();
        this.tarifa = new TarifaBasica();
        this.fechaAlta = LocalDateTime.now();
    }
    public Cliente(String nombre, String NIF, Direccion direccion, String email, LocalDateTime fechaAlta, Tarifa tarifa) {
        this.nombre = nombre;
        this.NIF = NIF;
        this.direccion = direccion;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.tarifa = tarifa;
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNIF() { return NIF; }
    public void setNIF(String NIF) { this.NIF = NIF; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }
    public Tarifa getTarifa() { return tarifa; }
    public void setTarifa(Tarifa tarifa) { this.tarifa = tarifa; }
    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================
    @Override
    public String toString(){
        return "Nombre: " + nombre + "\nDNI: " + NIF + "\nDirección: "
                + direccion + "\nEMail: " + email + "\nTarifa: "
                + tarifa + "\nFecha de Alta: " + fechaAlta;
    }
    public LocalDateTime getFecha() {
        return getFechaAlta();
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================

}
