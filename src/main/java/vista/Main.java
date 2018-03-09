package vista;

import controlador.cliente.ClienteController;
import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;
import es.uji.belfern.generador.GeneradorDatosINE;
import modelo.cliente.Cliente;

import java.util.Scanner;




public class Main {

    static Scanner sc;

    static VistaCliente vc;
    static VistaFactura vf;



    public static void ControladorMenu(){
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
                    break;
                case "F":
                    System.out.println("Facturas");
                    vf.ejecuta();
                    break;
                case "Q":
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


    //=================================================
    //---------------------MAIN------------------------
    //=================================================
    public static void main(String[] args){
        sc = new Scanner(System.in);
        vc = new VistaCliente( sc );
        ControladorMenu();


    }
}
