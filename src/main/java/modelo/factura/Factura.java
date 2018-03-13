package modelo.factura;

import modelo.cliente.Cliente;
import modelo.tarifa.Tarifa;
import modelo.utils.DateInterface;
import modelo.utils.Periodo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Factura implements DateInterface, Serializable{


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private Tarifa tarifa;
    //Si se cambia la tarifa se aplica en la siguiente tarifa
    private String uniqueID;
    private Date fechaEmision;
    private Periodo periodo;
    private float importe;
    private Cliente cliente;
    //==================================================
    //-----------------END ATRIBUTOS--------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================
    public Factura(){
        super();
        this.uniqueID = UUID.randomUUID().toString();
        /*this.tarifa = new Tarifa();*/
    }
    public Factura ( Periodo periodo, float importe){
        this.uniqueID = UUID.randomUUID().toString();
        this.tarifa = new Tarifa();
        this.fechaEmision = new Date();
        this.periodo = periodo;
        this.importe = importe;

    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================
    public Tarifa getTarifa() { return tarifa; }
    public void setTarifa(Tarifa tarifa) { this.tarifa = tarifa; }
    public String getUniqueID() { return uniqueID; }
    public void setUniqueID(String uniqueID) { this.uniqueID = uniqueID; }
    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }
    public Periodo getPeriodo() { return periodo; }
    public void setPeriodo(Periodo periodo) { this.periodo = periodo; }
    public float getImporte() { return importe; }
    public void setImporte(float importe) { this.importe = importe; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================


    //==================================================
    //----------------------METHODS---------------------
    //==================================================
    @Override
    public String toString(){
        return  "Id: " + this.uniqueID
                + "\nTarifa: " + this.tarifa
                + "\nFecha Emision: " + this.fechaEmision
                + "\nPeriodo Facturacion: " + this.periodo
                + "\nImporte: " + this.importe;
    }

    public Date getFecha() {
        return this.fechaEmision;
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
