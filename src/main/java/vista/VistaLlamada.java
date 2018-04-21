package vista;

import controlador.cliente.ClienteController;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.FechaInvalida;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.excepciones.NoHayLlamadasEntreFechas;
import modelo.llamada.Llamada;
import modelo.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class VistaLlamada extends VistaMadre implements Serializable {
    private static final long serialVersionUID = 681701765107216920L;


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================

    //Super
    LlamadaController llamadaController;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================
    VistaLlamada( Scanner sc, ClienteController clientes){
        this.sc = sc;
        this.llamadaController = new LlamadaController();
        this.clienteController = clientes;
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    LlamadaController getLlamadaController() {
        return llamadaController;
    }

    @Override
    String muestraOpciones() {
        String menu =
                "A - Alta nueva llamada a un cliente\n" +
                "L - Listar todas las llamadas de un cliente\n" +
                "F - Listar las llamadas de un cliente entre dos fechas\n" +
                "Q - Salir";
        return menu;
    }

    @Override
    String recogeRespuesta() {
        System.out.printf("Opción: ");
        String resp  = sc.nextLine().toUpperCase();
        switch ( resp ) {
            case "A":
                this.altaLlamadaVista();
                break;
            case "L":
                this.listaLlamadasVista();
                break;
            case "F":
                this.listaEntreFechas();
                break;
            case "Q":
                System.out.println("Cancelando");
                break;
            default:
                System.out.println("Entrada no válida");
                break;

        }
        return resp;
    }

    private void listaEntreFechas() {
        System.out.println("Listar las lamadas de un cliente entre dos fechas");
        Cliente cliente = getCliente( sc );
        if ( cliente != null ) {
            System.out.println("Fecha inicio (Desde cuándo)");
            LocalDateTime inicio = getFecha( sc );
            System.out.println("Fecha fin (Hasta cuándo)");
            LocalDateTime fin = getFecha( sc );
            try {
                List<Llamada> llamadas = llamadaController.llamadasEntreFechas(cliente, inicio, fin);
                this.listaLlamadas(llamadas);
            }
            catch( NoHayLlamadasEntreFechas e) {
                System.err.println( e.getMessage() );
            } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
                System.err.println( noHayLlamadasCliente.getMessage());
            } catch (FechaInvalida fechaInvalida) {
                System.err.println( fechaInvalida.getMessage() );
            }
        }
    }


    private void altaLlamadaVista() {
        System.out.println("Dar de Alta una nueva llamada");
        Cliente cliente = getCliente(sc);
        if ( cliente != null ) {
            System.out.println("Informacion de la llamada");
            System.out.printf("Teléfono al que se ha llamado: ");
            int telefono = Integer.parseInt( sc.nextLine() );
            System.out.println("Cuando se ha hecho la llamada");
            LocalDateTime fecha = getFechaDiaHora( sc );
            System.out.printf("¿Cuánto duró la llamada? (En Segundos)");
            float duracion = Float.parseFloat( sc.nextLine() );
            Llamada llamada = new Llamada( telefono, fecha, duracion);
            llamadaController.altaLlamada( cliente, llamada);
            if ( llamada == null )
                System.out.println("Ha ocurrido un error, vuelva a intentarlo");
            else
                System.out.println("Llamada creada con éxito");
        }
    }
    private void listaLlamadasVista(){
        System.out.println("Listado de llamadas de un cliente");
        Cliente cliente = getCliente( sc );
        List<Llamada> llamadas = null;
        try {
            llamadas = llamadaController.listaLlamadas( cliente);
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            System.err.println( noHayLlamadasCliente.getMessage() );
        } catch (ClienteNoExiste e) {
            System.err.println( e.getMessage() );
        }
        this.listaLlamadas( llamadas );


    }
    private void listaLlamadas ( List<Llamada> llamadas){

        System.out.println("Llamadas realizadas: ");
        System.out.println("-------------------------------");
        for (Llamada llamada : llamadas ){
            System.out.println(llamada);
            System.out.println("-------------------------------");
        }

    }


    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
