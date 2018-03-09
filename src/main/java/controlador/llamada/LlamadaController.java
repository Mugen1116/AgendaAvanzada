package controlador.llamada;

import modelo.cliente.Cliente;
import modelo.llamada.Llamada;

import java.util.HashMap;
import java.util.LinkedList;

public class LlamadaController {

    HashMap<Cliente, LinkedList<Llamada>> llamadas = new HashMap<Cliente, LinkedList<Llamada>>();

    //Alta de una llamada
    public boolean altaLlamada (Cliente cliente, Llamada nueva){
        //Nunca se van a poder dar de alta con esta comprobación
        if (llamadas.containsKey(cliente)){
            llamadas.get(cliente).add(nueva);
        }
        //Ahora, si no hay cliente registrado en el mapa, se registra y se añade la llamada nueva
        else {
            LinkedList<Llamada> llamadasNuevo = new LinkedList<>();
            llamadasNuevo.add(nueva);
            llamadas.put(cliente, llamadasNuevo);
        }
        return true;

    }

    //Listar las llamadas de un cliente
    public LinkedList<Llamada> listaLlamadas (Cliente cliente){

        return llamadas.get(cliente);

    }

}
