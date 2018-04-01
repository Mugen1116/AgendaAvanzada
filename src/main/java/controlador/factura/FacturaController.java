package controlador.factura;

import modelo.conjuntos.GetConjunto;
import modelo.utils.Pair;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.factura.Factura;
import modelo.llamada.Llamada;
import modelo.utils.Periodo;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.*;

public class FacturaController implements Serializable{

    /*
     * * Utilizamos Pair a modo de tupla para almacenar la factura y el cliente
     * * fst Factura
     * * snd Cliente
     */




    private HashMap<String, Pair< Factura, Cliente> > facturas;
    private LlamadaController llamadasController;

    public HashMap<String, Pair<Factura, Cliente>> getFacturas() { return facturas; }
    public void setFacturas(HashMap<String, Pair<Factura, Cliente>> facturas) { this.facturas = facturas; }

    public FacturaController( LlamadaController llamadasController ) {
        this.facturas = new HashMap<>();
        this.llamadasController = llamadasController;
        //Esto ultimo lo necesitamos para poder recuperar el listado de llamadas
        //De un cliente que se de, para generar la Factura
    }

    //Emitir Factura para un clienteen funcion a sus Llamadas
    //Devuelve el importe de la factura
    public Factura emitirFactura(Periodo periodo, Cliente cliente){
        Factura factura = new Factura();
        factura.setPeriodo( periodo );
        factura.setTarifa( cliente.getTarifa() );
        factura.setFechaEmision( new Date());

        //Ahora es necesario que recuperemos las llamadas del Cliente
        float importe = 0f;
        List<Llamada> llamadas = llamadasController.listaLlamadas(cliente);
        if ( llamadas != null ) {
            for (Llamada llamada : llamadas) {
                Date inicioPeriodo = Date.from(periodo.getInicio().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date finPeriodo = Date.from(periodo.getFin().atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (llamada.getFecha().compareTo(inicioPeriodo) >= 1
                        && llamada.getFecha().compareTo(finPeriodo) <= -1) {
                    importe += llamada.getDuracion() * cliente.getTarifa().getPrecio();
                }
                //Sumamos al importe la duración de la llamada multiplicado por la tarifa que tiene activa
                //Esto lo hacemos para todas las llamadas que estén dentro del periodo dado
                //Si no está en el rango, no hacemos nada
            }
        }
        factura.setImporte( importe );
        facturas.put( factura.getUniqueID(), new Pair<>(factura, cliente) );

        return factura;
    }

    //Datos de una factura segun su codigo
    public Factura getFactura ( String codigo ){
        if (facturas.containsKey( codigo) )
            return facturas.get( codigo ).fst;
        else
            return null;
    }

    //
    /*
     * * Ahora se va a hacer una implementación muy ineficiente
     * * Pero para la siguiente entrega se va a revisar para mejorar la busqueda
     * * De clientes en una estructura de datos utilizando una mejor
     * * O en el mismo mapa con expresiones regulares y que el identificador de la factura
     * * Contenga el NIF del cliente para poder buscar más eficientemente
     * *
     * *
     * * En este momento el método hará una busqueda secuencial
     */

    public List<Factura> getFacturasCliente ( Cliente cliente ) {
        List<Factura> listafacturas = new LinkedList<>();
        for (Map.Entry<String, Pair<Factura, Cliente>> entry : facturas.entrySet() ){
            if ( entry.getValue().snd.equals(cliente) ) {
                listafacturas.add( entry.getValue().fst);
            }
        }

        return listafacturas;
    }

    public List<Factura> facturasEntreFechas( Cliente cliente, Date una, Date otra ){

        return new GetConjunto<Factura>().situadosEntre(
                                        this.getFacturasCliente(cliente), una, otra
                                        );
    }

}
