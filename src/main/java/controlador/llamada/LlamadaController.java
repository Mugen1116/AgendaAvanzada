package controlador.llamada;

import modeloTest.cliente.Cliente;
import modeloTest.llamada.Llamada;

import java.util.HashMap;
import java.util.LinkedList;

public class LlamadaController {

    HashMap<Cliente, LinkedList<Llamada>> llamadas = new HashMap<Cliente, LinkedList<Llamada>>();

    //Alta de una llamada
    public boolean altaLlamada (Cliente cliente, Llamada nueva){

        if (llamadas.containsKey(cliente)){

            llamadas.get(cliente).add(nueva);
            return true;

        }

        return false;

    }

    //Listar las llamadas de un cliente
    public LinkedList<Llamada> listaLlamadas (Cliente cliente){

        return llamadas.get(cliente);

    }

}
