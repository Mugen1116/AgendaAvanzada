package controlador.cliente;

import modelo.cliente.Cliente;
import modelo.conjuntos.GetConjunto;
import modelo.excepciones.*;
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
    public boolean altaCliente (Cliente nuevo) throws ClienteExistente {
        if (! clientes.containsKey(nuevo.getNIF())) {
            clientes.put(nuevo.getNIF(), nuevo);
            return true;
        }
        throw new ClienteExistente();
    }

    //Borra un cliente
    public boolean bajaCliente (Cliente cliente) throws NoHayClientes, ClienteNoExiste {
        if(clientes.isEmpty()){
            throw new NoHayClientes();
        }
        if (clientes.containsKey(cliente.getNIF())){
            clientes.remove(cliente.getNIF());
            return true;
        }
        throw new ClienteNoExiste();
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
    public Cliente getCliente (String nif) throws ClienteNoExiste {
        if ( clientes.containsKey( nif ) )
            return clientes.get(nif);
        else
            throw new ClienteNoExiste();
    }

    //Devuelve todos los clientes
    public List<Cliente> listarClientes () throws NoHayClientes {
        Collection<Cliente> listaClientes = clientes.values();
        if ( listaClientes.isEmpty()){
            throw new NoHayClientes();
        }
        return new LinkedList<Cliente>( listaClientes );
    }

    //Devolver listado Clientes entre dos fechas
    public List<Cliente> clientesEntreFechas( Date una, Date otra ) throws FechaInvalida, NoHayClientes, NoHayClientesEntreFechas {

        //Recuperamos todos los clientes disponibles en el mapa
        //La devolverá vacía si no se encuentran clientes entre esas fechas
        if( una.compareTo(otra) >= 1 ){
            throw new FechaInvalida();
        }
        List<Cliente> conjuntoClientes =  new GetConjunto<Cliente>().situadosEntre( this.listarClientes(), una, otra);
        if ( conjuntoClientes.size() == 0 ){
            throw new NoHayClientesEntreFechas();
        }
        return conjuntoClientes;

    }

}
