package controlador.cliente;

import modelo.cliente.Cliente;
import modelo.tarifa.Tarifa;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ClienteController {
    HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();


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
        return clientes.get(nif);

    }

    //Devuelve todos los clientes
    public List<Cliente> listarClientes (){
        return new LinkedList<Cliente>( clientes.values() );

    }

}
