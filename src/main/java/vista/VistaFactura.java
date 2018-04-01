package vista;

import controlador.cliente.ClienteController;
import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.factura.Factura;
import modelo.utils.DateUtils;
import modelo.utils.Periodo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VistaFactura extends  VistaMadre {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================

    //Super
    FacturaController facturaController;


    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================

    VistaFactura (Scanner sc , LlamadaController llamadas,
                         ClienteController clientes) {
        this.sc = sc;
        this.facturaController = new FacturaController(llamadas);
        this.clienteController = clientes;
    }

    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    //Se muestran las operaciones que se pueden hacer
    @Override
    String muestraOpciones(){

        String menu =
                "E - Emitir Factura para un cliente\n" +
                "F - Obtener datos de una Factura\n" +
                "A - Ver Facturas de un Cliente\n" +
                "L - Listar Facturas de un Cliente emitidas entre dos fechas\n" +
                "Q - Salir";
        return menu;
    }
    //En funcion de lo introducido, se va a ejecutar un método u otro
    //Capturamos la accion
    @Override
    String recogeRespuesta() {
        System.out.printf("Opción: ");
        String resp  = sc.nextLine().toUpperCase();
        switch ( resp ) {
            case "E":
                this.emitirFacturaVista();
                break;
            case "F":
                this.datosFacturaVista();
                break;
            case "A":
                this.facturasClienteVista();
                break;
            case "L":
                this.listaFacturasEntreFechasVista();
                break;
            case "Q":
                System.out.println("Cancelando");
                break;
            default:
                System.out.println("Entrada no válida");
                break;
        }
        System.out.printf("\n\n");
        return resp;
    }

    private void listaFacturasEntreFechasVista() {
        System.out.println("Listar las facturas de un cliente emitidas entre dos fechas");
        Cliente cliente = getCliente(sc);
        System.out.println("Fecha inicio (Desde cuándo)");
        Date inicio = getFecha( sc );
        System.out.println("Fecha fin (Hasta cuándo)");
        Date fin = getFecha( sc );
        List<Factura> facturas = facturaController.facturasEntreFechas(cliente, inicio, fin);
        listaFacturas( facturas );
    }

    private void facturasClienteVista() {
        System.out.println("Listar todas las facturas de un cliente");
        List<Factura> facturas = facturaController.getFacturasCliente( getCliente(sc) );
        listaFacturas( facturas );

    }
    private void listaFacturas( List<Factura> facturas ) {
        if ( facturas == null || facturas.size() == 0){
            System.out.println("No existen facturas asociadas con los datos introducidos");
        }
        else {
            for (Factura factura : facturas ){
                System.out.println("--------------------------------");
                System.out.println( factura );
                System.out.println("--------------------------------");
            }
        }

    }

    private void datosFacturaVista() {
        System.out.println("Obtener información de una Factura");
        System.out.printf("Introduzca el identificador de la factura a consultar: ");
        Factura factura = facturaController.getFactura( sc.nextLine() );
        System.out.println(factura);
    }

    private void emitirFacturaVista() {
        System.out.println("Emitir nueva factura");
        System.out.println("Introduzca la fecha de inicio de facturación");
        LocalDate fechainicio  = DateUtils.asLocalDate( getFecha( sc ) );
        System.out.println("Introduzca la fecha de fin de facturación");
        LocalDate fechafin = DateUtils.asLocalDate( getFecha( sc ) );

        Periodo periodo = new Periodo( fechainicio , fechafin );
        System.out.println("Información del client");
        System.out.printf("NIF del cliente para facturar: ");
        Cliente cliente = clienteController.getCliente( sc.nextLine() );
        System.out.println("Emitiendo factura...");
        Factura exito = facturaController.emitirFactura( periodo, cliente );
        if (exito != null )
            System.out.println("Factura emitida con éxito, ID: " + exito.getUniqueID() );
        else System.out.println("Error al emitir la factura, intentelo de nuevo");
    }

    //==================================================
    //--------------------END METHODS-------------------
    //==================================================


}
