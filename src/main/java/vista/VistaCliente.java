package vista;

import com.sun.deploy.util.SessionState;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;
import modelo.direccion.Direccion;
import modelo.tarifa.Tarifa;
import modelo.utils.DateUtils;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VistaCliente extends VistaMadre {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    Scanner sc;
    ClienteController clienteController;

    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================
    VistaCliente( Scanner sc ){
        this.sc = sc;
        clienteController = new ClienteController();
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
                        "N - Nuevo Cliente\n" +
                        "B - Borrar un Cliente\n" +
                        "T - Cambiar Tarifa Cliente\n" +
                        "C - Obtener datos de un Cliente\n" +
                        "A - Obtener datos de todos los Clientes\n" +
                        "L - Listar los clientes dados de alta entre dos fechas\n" +
                        "Q - Salir";
        return menu;
    }

    //En funcion de lo introducido, se va a ejecutar un método u otro
    //Capturamos la accion
    @Override
    String recogeRespuesta() {
        System.out.printf("Opcion: ");
        String resp = sc.nextLine().toUpperCase();
        switch ( resp ) {
            case "N":
                //Nuevo
                this.anyadirClienteVista();
                break;
            case "B":
                //Borrar
                this.borrarClienteVista();
                break;
            case "T":
                //Cambiar Tarifa
                this.cambiarTarifaVista();
                break;
            case "C":
                //Mostrar cliente
                this.mostrarClienteVista();
                break;

            case "A":
                this.mostrarClientesVista();
                //Mostrar todos los clientes
                break;

            case "L":
                this.listarClientesFechasVista();
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

    private void listarClientesFechasVista() {
        System.out.println("Seleccione las fechas entre las que se quiere filtrar");
        System.out.println("Fecha de Inicio (Desde cuándo)");
        Date inicio = getFecha( sc );
        System.out.println("Fecha de Fin (Hasta cuándo)");
        Date fin = getFecha( sc );
        System.out.println("------------------------------");
        for ( Cliente cliente : clienteController.clientesEntreFechas( inicio, fin ) ) {
            System.out.println(cliente);
            System.out.println("------------------------------");
        }
    }

    private void cambiarTarifaVista() {
        System.out.printf("NIF/DNI del cliente del que se quiere cambiar la tarifa: ");
        String dni = sc.nextLine();
        Cliente cliente = clienteController.getCliente( dni );
        if ( cliente != null ) {
            System.out.println("Tarifa actual del cliente: " + cliente.getTarifa() );
            System.out.printf("Introduzca nueva tarifa (centimos/minuto) Ej: 0.10 : ");
            float precio = Float.parseFloat( sc.nextLine() );
            clienteController.cambiarTarifa(
                                cliente, new Tarifa( precio )
                                );
        }
        else
            System.out.println("Error! No se encuentra el cliente introducido");
    }

    private void borrarClienteVista() {
        System.out.printf("NIF/DNI del cliente que se quiere borrar: ");
        String dni = sc.nextLine();
        Cliente cliente = clienteController.getCliente( dni );
        if ( cliente != null ) {
           if ( clienteController.bajaCliente( cliente ) )
               System.out.println("Cliente borrado correctamente");
           else
               System.out.println("Error al intentar borrar el cliente");
        }
        else {
            System.out.println("Error! No se encuentra el cliente introducido.");
        }
    }

    private void mostrarClienteVista() {
        System.out.printf("NIF/DNI del cliente que se quiere mostrar: ");
        String dni = sc.nextLine();
        Cliente cliente = clienteController.getCliente( dni );

        if ( cliente != null ){
            System.out.println("Cliente: ");
            System.out.println(cliente);
        }
        else{
            System.out.println("Error! No se encuentra el cliente introducido.");
        }

    }

    private void mostrarClientesVista() {
        System.out.println("Clientes Registrados: ");
        List<Cliente> clientes = clienteController.listarClientes();

        if ( clientes.isEmpty() )
            System.out.println( "No hay clientes registrados");
        else
            System.out.println( clientes );
    }

    private void anyadirClienteVista(){
        Cliente nuevo = new Cliente();
        System.out.println("Introduce los datos del nuevo cliente");
        System.out.printf(  "¿Es Empresa o Particular?" +
                            "\nE - Empresa" +
                            "\nP - Partidular\n");
        System.out.printf("Respuesta: ");
        String resp = sc.nextLine().toUpperCase();
        if( !resp.equals("P")  && !resp.equals("E") ){
                System.out.println("Error en la elección, saliendo...");
                return;
        }
        String apell = null;
        System.out.printf("Nombre (Solo nombre): ");
        nuevo.setNombre( sc.nextLine() );
        if (resp.equals("P")){
            System.out.printf("Apellidos: ");
            apell = sc.nextLine();
        }
        System.out.printf("NIF: ");
        nuevo.setNIF( sc.nextLine() );
        System.out.printf("Email: ");
        nuevo.setEmail( sc.nextLine() );
        //Dirección
        System.out.println("Dirección");
        Direccion dir = new Direccion();
        System.out.printf("Población: ");
        dir.setPoblacion( sc.nextLine() );
        System.out.printf("Provincia: ");
        dir.setProvincia( sc.nextLine() );
        System.out.printf("Codigo Postal: ");
        dir.setCodigoPostal(Integer.parseInt(sc.nextLine()));
        //Setear Direccion
        nuevo.setDireccion( dir );
        //Setear apellidos
        if ( resp.equals("P") ) {
            Particular part = new Particular( nuevo );
            part.setApellidos( apell );
            clienteController.altaCliente( part );
        }
        else {
            Empresa empresa = new Empresa( nuevo );
            clienteController.altaCliente( empresa );
        }
        System.out.println("Cliente insertado correctamente");
    }

    public ClienteController getClienteController() {
        return clienteController;
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
