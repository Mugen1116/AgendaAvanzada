package modelo.agenda;

import modelo.cliente.Cliente;
import modelo.factura.Factura;
import modelo.llamada.Llamada;
import modelo.utils.Pair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Agenda implements Serializable {

    private static final long serialVersionUID = -4452685954943578315L;
    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private HashMap<String, Cliente> clientes;
    private HashMap<String, Pair< Factura, Cliente>> facturas;
    private HashMap<Cliente, LinkedList<Llamada>> llamadas;

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

    public HashMap<String, Cliente> getClientes() { return clientes; }
    public void setClientes(HashMap<String, Cliente> clientes) { this.clientes = clientes; }
    public HashMap<String, Pair<Factura, Cliente>> getFacturas() { return facturas; }
    public void setFacturas(HashMap<String, Pair<Factura, Cliente>> facturas) { this.facturas = facturas; }
    public HashMap<Cliente, LinkedList<Llamada>> getLlamadas() { return llamadas; }
    public void setLlamadas(HashMap<Cliente, LinkedList<Llamada>> llamadas) { this.llamadas = llamadas; }

    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================
}
