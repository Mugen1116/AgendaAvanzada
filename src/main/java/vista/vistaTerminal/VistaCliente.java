package vista.vistaTerminal;

//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;
import modelo.direccion.Direccion;
import modelo.excepciones.*;
import modelo.factoria.FactoriaObjetos;

        import java.time.LocalDateTime;
        import java.util.List;
import java.util.Scanner;

public class VistaCliente extends VistaMadre {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================

    //Heredados

    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================
    public VistaCliente( Scanner sc ){
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
    public String muestraOpciones(){

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
    public String recogeRespuesta() {
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
        System.out.printf("\n");

        return resp;
    }

    private void listarClientesFechasVista() {
        System.out.println("Seleccione las fechas entre las que se quiere filtrar");
        System.out.println("Fecha de Inicio (Desde cuándo)");
        LocalDateTime inicio = getFecha( sc );
        System.out.println("Fecha de Fin (Hasta cuándo)");
        LocalDateTime fin = getFecha( sc );
        try {
            List<Cliente> clientes = clienteController.clientesEntreFechas(inicio, fin);
            System.out.println("------------------------------");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
                System.out.println("------------------------------");

            }
        }
        catch ( FechaInvalida e) {
            System.err.println( e.getMessage() );
        } catch (NoHayClientesEntreFechas noHayClientesEntreFechas) {
            System.err.println( noHayClientesEntreFechas.getMessage() );
        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }

    }

    private void cambiarTarifaVista() {
        System.out.printf("NIF/DNI del cliente del que se quiere cambiar la tarifa: ");
        String dni = sc.nextLine();
        try {
            Cliente cliente = clienteController.getCliente( dni );
            System.out.println("Tarifa actual del cliente: " + cliente.getTarifa() );
            System.out.println("Elija la nueva que quiere contratar: ");
            System.out.println("T: - Tarifa de Tardes, 5 céntimos/minutos en llamadas de 16:00 a 20:00 horas");
            System.out.println("D: - Tarifa de Domingos, gratis llamadas realizadas en domingo");
            System.out.println("A: - Contratar las dos tarifas anteriores al mismo tiempo");
            String opcion = sc.nextLine().toUpperCase();
            switch ( opcion ) {
                case "T":
                    clienteController.cambiarTarifa( cliente,FactoriaObjetos.TARDES );
                    break;
                case "D":
                    clienteController.cambiarTarifa( cliente,FactoriaObjetos.DOMINGOS );
                    break;
                case "A":
                    clienteController.cambiarTarifa(cliente, FactoriaObjetos.TARDES_Y_DOMINGOS) ;
                    break;
                default:
                    System.out.println("Error, opción no válida");

            }
            System.out.println("Cambiado correctamente.");
//            float precio = Float.parseFloat( sc.nextLine() );
//            clienteController.cambiarTarifa(
//                                cliente, new Tarifa( precio )
//                                );
        }
        catch(ClienteNoExiste e) {
                System.err.println( e.getMessage() );
            }
    }

    private void borrarClienteVista() {
        System.out.printf("NIF/DNI del cliente que se quiere borrar: ");
        String dni = sc.nextLine();
        try {
            Cliente cliente = clienteController.getCliente( dni );
            if ( clienteController.bajaCliente( cliente ) )
                System.out.println("Cliente borrado correctamente");
            else
                System.err.println("Error al intentar borrar el cliente");
        }
        catch( ClienteNoExiste e) {
            System.err.println( e.getMessage() );

        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }
    }

    private void mostrarClienteVista() {
        System.out.printf("NIF/DNI del cliente que se quiere mostrar: ");
        String dni = sc.nextLine();
        try {
            Cliente cliente = clienteController.getCliente( dni );
            System.out.println("Cliente: ");
            System.out.println(cliente);
        }
        catch (ClienteNoExiste e ){
            System.err.println( e.getMessage() );
        }

    }

    private void mostrarClientesVista() {
        System.out.println("Clientes Registrados: ");
        try {
            List<Cliente> clientes = clienteController.listarClientes();
            System.out.println(clientes);
        }
        catch (NoHayClientes e){
            System.err.println( e.getMessage() );
        }
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
            try {
                clienteController.altaCliente( part );
            } catch (ClienteExistente e) {
                System.err.println( e.getMessage() );
            }
        }
        else {
            Empresa empresa = new Empresa( nuevo );
            try {
                clienteController.altaCliente( empresa );
            } catch (ClienteExistente e) {
                System.err.println( e.getMessage() );
            }
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
