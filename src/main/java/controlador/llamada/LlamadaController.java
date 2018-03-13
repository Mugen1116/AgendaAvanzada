package controlador.llamada;

import modelo.cliente.Cliente;
import modelo.llamada.Llamada;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LlamadaController {

    private HashMap<Cliente, LinkedList<Llamada>> llamadas;

    public LlamadaController() {
        this.llamadas = new HashMap<>();
    }

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
        if ( llamadas.containsKey(cliente) )
            return llamadas.get(cliente);
        else
            return null;

    }

    //Devuelve las Llamadas situadas entre dos fechas
    public List<Llamada> situadosEntre(List<Llamada> lista, Date fecha1, Date fecha2){

        LinkedList<Llamada> sublista = new LinkedList<Llamada>();
        Date fecha0;

        for (Llamada aux : lista){

            fecha0 = aux.getFecha();

            if (fecha0.compareTo(fecha1)>=0  && fecha0.compareTo(fecha2)<=0){

                sublista.add(aux);

            }

        }

        return sublista;
    }
}
