package controlador.llamada;

import modelo.cliente.Cliente;
import modelo.conjuntos.GetConjunto;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.FechaInvalida;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.excepciones.NoHayLlamadasEntreFechas;
import modelo.llamada.Llamada;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LlamadaController implements Serializable{


    private static final long serialVersionUID = -6928292017310305627L;
    private HashMap<Cliente, LinkedList<Llamada>> llamadas;

    public LlamadaController() {
        this.llamadas = new HashMap<>();
    }

    //Getter and Setter
    public HashMap<Cliente, LinkedList<Llamada>> getLlamadas() {  return llamadas; }
    public void setLlamadas(HashMap<Cliente, LinkedList<Llamada>> llamadas) { this.llamadas = llamadas; }

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
    public List<Llamada> listaLlamadas (Cliente cliente) throws NoHayLlamadasCliente, ClienteNoExiste {
        if ( llamadas.containsKey(cliente) ) {
            List<Llamada> listaLlamadas = llamadas.get(cliente);
            if ( listaLlamadas.isEmpty() )
                throw new NoHayLlamadasCliente();
            return listaLlamadas;
        }
        throw new ClienteNoExiste();



    }

    public List<Llamada> llamadasEntreFechas( Cliente cliente, Date una, Date otra) throws NoHayLlamadasCliente, FechaInvalida, NoHayLlamadasEntreFechas {
        if( una.compareTo(otra) >= 1 ){
            throw new FechaInvalida();
        }
        List<Llamada> listaLlamadas = llamadas.get(cliente);
        if ( listaLlamadas.isEmpty() )
            throw new NoHayLlamadasCliente();
        List<Llamada> listaDevolver = new GetConjunto<Llamada>().situadosEntre( listaLlamadas, una, otra);
        if ( listaDevolver.isEmpty() )
            throw new NoHayLlamadasEntreFechas();
        return listaDevolver;
    }

}
