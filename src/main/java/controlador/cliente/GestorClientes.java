package controlador.cliente;

import modelo.cliente.Cliente;
import modelo.conjuntos.GetConjunto;
import modelo.excepciones.*;
import modelo.factoria.FactoriaTarifas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class GestorClientes implements Serializable {

    private  HashMap<String, Cliente> clientes;

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    //Constructor
    public GestorClientes() {
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

    public Cliente bajaClienteNIF( String NIF )throws NoHayClientes, ClienteNoExiste {
        Cliente cliente = getCliente( NIF );
        if (clientes.isEmpty()) {
            throw new NoHayClientes();
        }
        if (clientes.containsKey(cliente.getNIF())) {
            clientes.remove(cliente.getNIF());
            return cliente;
        }
        throw new ClienteNoExiste();
    }

    //Cambia la tarifa de un cliente
    public boolean cambiarTarifa (Cliente cliente, int tipoTarifa ){
        if (clientes.containsKey(cliente.getNIF())){
            Cliente clienteRegistrado = clientes.get(cliente.getNIF() );
            //Switch
            FactoriaTarifas fabrica = new FactoriaTarifas();
            clienteRegistrado.setTarifa( fabrica.creaTarifa(tipoTarifa) );
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
    public List<Cliente> clientesEntreFechas(LocalDateTime una, LocalDateTime otra ) throws FechaInvalida, NoHayClientes, NoHayClientesEntreFechas {

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
