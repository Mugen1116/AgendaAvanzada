package vista;

import controlador.cliente.ClienteController;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.llamada.Llamada;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class VistaLlamada extends VistaMadre {


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    Scanner sc;
    LlamadaController llamadaController;
    ClienteController clienteController;
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
            case "Q":
                System.out.println("Cancelando");
                break;
            default:
                System.out.println("Entrada no válida");
                break;

        }
        return resp;
    }

    private void altaLlamadaVista() {
        System.out.println("Dar de Alta una nueva llamada");
        System.out.printf("NIF del cliente: ");
        Cliente cliente = clienteController.getCliente( sc.nextLine() );
        if ( cliente == null ){
            System.out.println("No existe el cliente introduciendo, vuelva a intentarlo");

        }
        else {
            System.out.println("Informacion de la llamada");
            System.out.printf("Teléfono al que se ha llamado: ");
            int telefono = Integer.parseInt( sc.nextLine() );
            System.out.println("Cuando se ha hecho la llamada");
            System.out.printf("Dia: ");
            int dia = Integer.parseInt( sc.nextLine() );
            System.out.printf("Mes: ");
            int mes = Integer.parseInt( sc.nextLine() );
            System.out.printf("Año: ");
            int anyo = Integer.parseInt( sc.nextLine() );

            LocalDate local = LocalDate.of(anyo, mes, dia);
            Date fecha = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());

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
        System.out.printf("NIF del cliente: ");
        Cliente cliente = clienteController.getCliente( sc.nextLine() );
        if ( cliente == null ){
            System.out.println("No existe el cliente introduciendo, vuelva a intentarlo");
        }
        else {
            List<Llamada> llamadas = llamadaController.listaLlamadas( cliente);

            if ( llamadas == null )
                System.out.println("Este cliente no tiene llamadas hechas");
            else {
                System.out.println("Llamadas realizadas: ");
                for (Llamada llamada : llamadas ){
                    System.out.println("-------------------------------");
                    System.out.println(llamada);
                    System.out.println("-------------------------------");
                }

            }
        }

    }


    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
