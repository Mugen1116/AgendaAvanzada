package vista;

import modelo.utils.DateUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public abstract class VistaMadre {

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

    abstract String muestraOpciones() ;

    abstract String recogeRespuesta();
}
