package vista;

import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;

import java.util.Scanner;

public class VistaFactura {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    Scanner sc;
    FacturaController facturaController;

    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================

    public VistaFactura (Scanner sc , LlamadaController llamadas) {
        this.sc = sc;
        facturaController = new FacturaController(llamadas);
    }

    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    //Se muestran las operaciones que se pueden hacer
    public String muestraOpciones(){

        String menu =
                "E - Emitir Factura para un cliente\n" +
                "F - Obtener datos de una Factura\n" +
                "A -  Ver Facturas de un Cliente\n" +
                "Q - Salir";
        return menu;
    }
    //En funcion de lo introducido, se va a ejecutar un método u otro
    //Capturamos la accion
    public String recogeRespuesta() {
        System.out.printf("Opción: ");
        String resp  = sc.nextLine().toUpperCase();
        switch ( resp ) {
            case "E":
                break;
            case "F":
                break;
            case "A":
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



    public void ejecuta() {

    }

    //==================================================
    //--------------------END METHODS-------------------
    //==================================================

}
