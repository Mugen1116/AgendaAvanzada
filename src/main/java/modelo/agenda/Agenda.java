package modelo.agenda;

import modelo.cliente.Cliente;
import modelo.factura.Factura;
import modelo.llamada.Llamada;

import java.util.List;

public class Agenda {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private List<Cliente> clientes;
    private List<Factura> facturas;
    private List<Llamada> llamadas;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================

    public Agenda (){
        super();
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================

    public List<Cliente> getClientes() { return clientes; }
    public void setClientes(List<Cliente> clientes) { this.clientes = clientes; }
    public List<Factura> getFacturas() { return facturas; }
    public void setFacturas(List<Factura> facturas) { this.facturas = facturas; }
    public List<Llamada> getLlamadas() { return llamadas; }
    public void setLlamadas(List<Llamada> llamadas) { this.llamadas = llamadas; }
    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================
}
