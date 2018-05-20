package vista.vistaTerminal;

import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.NoHayClientes;
import modelo.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public void ejecuta() {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println( muestraOpciones() );
        try {
            recogeRespuesta();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
    }


    public LocalDateTime getFecha( Scanner sc ) {
        System.out.printf("Día: ");
        int dia = Integer.parseInt( sc.nextLine() );
        System.out.printf("Número de Mes(1-12): ");
        int mes = Integer.parseInt( sc.nextLine() );
        System.out.printf("Año (EJ: 2018): ");
        int anyo = Integer.parseInt( sc.nextLine() );
        LocalDate date = LocalDate.of(anyo, mes, dia);
        LocalTime time = LocalTime.of(0,0);
        return LocalDateTime.of( date, time);
        //return  DateUtils.asDate( LocalDate.of(anyo, mes, dia) );
    }

    public LocalDateTime getFechaDiaHora ( Scanner sc ) {

        LocalDate date = getFecha( sc ).toLocalDate();
        System.out.println("Hora: ");
        int hour = Integer.parseInt( sc.nextLine() );
        System.out.println("Minuto: ");
        int minut = Integer.parseInt( sc.nextLine() );
        LocalTime time = LocalTime.of( hour, minut);

        return LocalDateTime.of( date, time);



    }

    public Cliente getCliente(Scanner sc ) {
        System.out.printf("NIF del cliente: ");
        Cliente cliente = null;
        try {
            cliente = clienteController.getCliente( sc.nextLine() );
        } catch (ClienteNoExiste clienteNoExiste) {
            System.err.println( clienteNoExiste.getMessage() );
        }
        return cliente;
    }
    public abstract String muestraOpciones() ;

    public abstract String recogeRespuesta() throws NoHayClientes, ClienteNoExiste;
}
