package controlador.cliente;

import modelo.cliente.Cliente;
import modelo.conjuntos.GetConjunto;
import modelo.tarifa.Tarifa;

import java.io.Serializable;
import java.util.*;

public class ClienteController implements Serializable {

    private  HashMap<String, Cliente> clientes;

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    //Constructor
    public ClienteController() {
        this.clientes = new HashMap<String, Cliente>();
    }


    //Añade un cliente
    public boolean altaCliente (Cliente nuevo){
        if (! clientes.containsKey(nuevo.getNIF())) {
            clientes.put(nuevo.getNIF(), nuevo);
            return true;
        }
        return false;
    }

    //Borra un cliente
    public boolean bajaCliente (Cliente cliente){
        if(clientes.isEmpty()){
            return false;
        }

        if (clientes.containsKey(cliente.getNIF())){
            clientes.remove(cliente.getNIF());
            return true;
        }
        return false;
    }

    //Cambia la tarifa de un cliente
    public boolean cambiarTarifa (Cliente cliente, Tarifa nueva){

        if (clientes.containsKey(cliente.getNIF())){
            clientes.get(cliente.getNIF()).setTarifa(nueva);
            return true;
        }
        return false;
    }

    //Devuelve los datos del cliente por su NIF
    public Cliente getCliente (String nif){
        if ( clientes.containsKey( nif ) )
            return clientes.get(nif);
        else
            return null;

    }

    //Devuelve todos los clientes
    public List<Cliente> listarClientes (){
        return new LinkedList<Cliente>( clientes.values() );

    }

    //Devolver listado Clientes entre dos fechas
    public List<Cliente> clientesEntreFechas( Date una, Date otra ) {

        //Recuperamos todos los clientes disponibles en el mapa
        //La devolverá vacía si no se encuentran clientes entre esas fechas
        return new GetConjunto<Cliente>().situadosEntre(
                                                    this.listarClientes(), una , otra
                                                    );

    }

}
