package controlador.cliente;

import modelo.cliente.Cliente;
import modelo.tarifa.Tarifa;

import java.io.Serializable;
import java.util.*;

public class ClienteController implements Serializable{

    private  HashMap<String, Cliente> clientes;

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

    //Devuelve los clientes dados de alta entre dos fechas
    public List<Cliente> situadosEntre(List<Cliente> lista, Date fecha1, Date fecha2){

        LinkedList<Cliente> sublista = new LinkedList<Cliente>();
        Date fecha0;

            for (Cliente aux : lista){

                fecha0 = aux.getFecha();

                if (fecha0.compareTo(fecha1)>=0  && fecha0.compareTo(fecha2)<=0){

                    sublista.add(aux);

                }

            }

        return sublista;
    }
}
