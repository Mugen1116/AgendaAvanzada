package vista;

import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.utils.DateUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public abstract class VistaMadre {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    Scanner sc;
    ClienteController clienteController;

    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    void ejecuta() {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println( muestraOpciones() );
        recogeRespuesta();
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
    }

    Date getFecha( Scanner sc ) {
        System.out.printf("Día: ");
        int dia = Integer.parseInt( sc.nextLine() );
        System.out.printf("Número de Mes(1-12): ");
        int mes = Integer.parseInt( sc.nextLine() );
        System.out.printf("Año (EJ: 2018): ");
        int anyo = Integer.parseInt( sc.nextLine() );
        return  DateUtils.asDate( LocalDate.of(anyo, mes, dia) );
    }

    Cliente getCliente(Scanner sc ) {
        System.out.printf("NIF del cliente: ");
        Cliente cliente = clienteController.getCliente( sc.nextLine() );
        if ( cliente == null ){
            System.out.println("No existe el cliente introduciendo, vuelva a intentarlo");
        }
        return cliente;
    }
    abstract String muestraOpciones() ;

    abstract String recogeRespuesta();
}
