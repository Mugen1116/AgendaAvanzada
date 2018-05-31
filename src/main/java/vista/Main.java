package vista;

import modelo.GuardarCargar.Cargar;
import modelo.GuardarCargar.Guardar;
import modelo.agenda.Agenda;
import modelo.excepciones.NoHayClientes;
import vista.vistaGrafica.VistaGrafica;
import vista.vistaGrafica.VistaGraficaMadre;
import vista.vistaTerminal.VistaCliente;
import vista.vistaTerminal.VistaFactura;
import vista.vistaTerminal.VistaLlamada;

import java.util.Scanner;




public class Main {

    static Scanner sc;

    static VistaCliente vc;
    static VistaLlamada vl;
    static VistaFactura vf;



    public static void ControladorMenu() throws NoHayClientes {
        boolean exit = false;

        while ( !exit ) {
            System.out.println("Elija sobre qué desea hacer una operación:");
            System.out.println( "C - Clientes\n" +
                    "L - Llamadas\n" +
                    "F - Facturas\n" +
                    "Q - Salir\n");
            System.out.printf("Elige: ");
            String sig = sc.nextLine().toUpperCase();
            switch ( sig ) {
                case "C":
                    System.out.println("Clientes");
                    vc.ejecuta();
                    break;
                case "L":
                    System.out.println("Llamadas");
                    vl.ejecuta();
                    break;
                case "F":
                    System.out.println("Facturas");
                    vf.ejecuta();
                    break;
                case "Q":
                    //Guardando
                    save();
                    System.out.println("Guardando información...");
                    exit = true;
                    break;
                default:
                    System.out.println("Entrada no válida");
                    break;
            }
        }
        System.out.println("Saliendo");
        System.out.println("==================================");
        System.out.println("==================================");
    }

    public static void save() {
        System.out.println("Se va a guardar la información.");
        System.out.println("Archivo actual: \"Agenda.bin\"");
        String archivo = "Agenda.bin";
        Guardar guardar = new Guardar();
        Agenda agenda = new Agenda();
        agenda.setClientes( vc.getClienteController().getClientes() );
        agenda.setFacturas( vf.getGestorFacturas().getFacturas() );
        agenda.setLlamadas( vl.getGestorLlamadas().getLlamadas() );
        guardar.guardar( archivo, agenda );
    }
    private static Agenda load() {
        System.out.println("Cargando datos.");
        Cargar cargar = new Cargar();
        Agenda agenda = cargar.cargar( "Agenda.bin");
        return agenda;
    }
    //=================================================
    //---------------------MAIN------------------------
    //=================================================

    //Lo comento para probar

/*
    public static void main(String[] args) throws NoHayClientes {
        Agenda agenda = load();
        sc = new Scanner(System.in);
        vc = new VistaCliente(sc);
        vl = new VistaLlamada(sc, vc.getGestorClientes());
        vf = new VistaFactura(sc, vl.getGestorLlamadas(), vc.getGestorClientes());
        if ( agenda != null ){
            vc.getGestorClientes().setClientes( agenda.getClientes() );
            vl.getGestorLlamadas().setLlamadas( agenda.getLlamadas() );
            vf.getGestorFacturas().setFacturas( agenda.getFacturas() );
        }
        ControladorMenu();
    }
*/



    public static void main(String[] args) {
        Agenda agenda = load();
        VistaGraficaMadre vista = new VistaGrafica();
        if ( agenda != null ) {

            vista.getGestorClientes().setClientes( agenda.getClientes() );
            vista.getGestorLlamadas().setLlamadas( agenda.getLlamadas() );
            vista.getGestorFacturas().setFacturas( agenda.getFacturas() );

        }
        vista.ejecutar();
    }

}
