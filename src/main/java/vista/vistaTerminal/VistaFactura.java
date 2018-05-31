package vista.vistaTerminal;

import controlador.cliente.GestorClientes;
import controlador.factura.GestorFacturas;
import controlador.llamada.GestorLlamadas;
import modelo.cliente.Cliente;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.NoExisteFactura;
import modelo.excepciones.NoExistenFacturasDeCliente;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.factura.Factura;
import modelo.utils.Periodo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class VistaFactura extends  VistaMadre {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================

    //Super
    GestorFacturas gestorFacturas;


    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================

   public VistaFactura (Scanner sc , GestorLlamadas llamadas,
                         GestorClientes clientes) {
        this.sc = sc;
        this.gestorFacturas = new GestorFacturas(llamadas);
        this.gestorClientes = clientes;
    }

    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    //Se muestran las operaciones que se pueden hacer
    @Override
    public String muestraOpciones(){

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
    public String recogeRespuesta() {
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
        LocalDateTime inicio = getFecha( sc );
        System.out.println("Fecha fin (Hasta cuándo)");
        LocalDateTime fin = getFecha( sc );
        List<Factura> facturas = null;
        try {
            facturas = gestorFacturas.facturasEntreFechas(cliente, inicio, fin);
        } catch (NoExistenFacturasDeCliente e) {
            System.err.println( e.getMessage() );
        }
        listaFacturas( facturas );
    }

    private void facturasClienteVista() {
        System.out.println("Listar todas las facturas de un cliente");
        try {
            List<Factura> facturas = gestorFacturas.getFacturasCliente(getCliente(sc));
            listaFacturas(facturas);
        }
        catch( NoExistenFacturasDeCliente e ){
            System.err.println( e.getMessage() );
        }

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
        try {
            Factura factura = gestorFacturas.getFactura( sc.nextLine() );
            System.out.println(factura);
        }
        catch ( NoExisteFactura e ){
            System.err.println( e.getMessage() );
        }

    }

    private void emitirFacturaVista() {
        System.out.println("Emitir nueva factura");
        System.out.println("Introduzca la fecha de inicio de facturación");
        LocalDateTime fechainicio  = getFecha( sc ) ;
        System.out.println("Introduzca la fecha de fin de facturación");
        LocalDateTime fechafin =  getFecha( sc ) ;

        Periodo periodo = new Periodo( fechainicio , fechafin );
        System.out.println("Información del client");
        System.out.printf("NIF del cliente para facturar: ");
        Cliente cliente = null;
        try {
            cliente = gestorClientes.getCliente( sc.nextLine() );
        } catch (ClienteNoExiste e) {
            System.err.println( e.getMessage() );
        }
        System.out.println("Emitiendo factura...");
        Factura exito = null;
        try {
            exito = gestorFacturas.emitirFactura( periodo, cliente );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            System.err.println( clienteNoExiste.getMessage() );
        }
        if (exito != null )
            System.out.println("Factura emitida con éxito, ID: " + exito.getUniqueID() );
        else System.out.println("Error al emitir la factura, intentelo de nuevo");
    }

    public GestorFacturas getGestorFacturas() { return gestorFacturas; }

    //==================================================
    //--------------------END METHODS-------------------
    //==================================================


}
