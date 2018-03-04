package vista;

import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;
import modelo.direccion.Direccion;

import java.util.Scanner;

public class VistaCliente {

    Scanner sc;
    ClienteController clienteController;

    public VistaCliente( Scanner sc ){
        this.sc = sc;
        clienteController = new ClienteController();
    }

    public String MuestraOpciones(){

        String menu =   "N - Nuevo Cliente\n" +
                        "B - Borrar un Cliente\n" +
                        "T - Cambiar Tarifa Cliente\n" +
                        "C - Obtener datos de un Cliente\n" +
                        "A - Obtener datos de todos los Clientes\n" +
                        "Q - Salir";
        return menu;
    }

    public String RecogeRespuesta() {
        System.out.printf("Opcion: ");
        String resp = sc.nextLine().toUpperCase();
        switch ( resp ) {
            case "N":
                //Nuevo
                this.AnyadirClienteVista();
                break;
            case "B":
                //Borrar
                break;
            case "T":
                //Cambiar Tarifa
                break;
            case "C":
                //Mostrar cliente
                break;

            case "A":
                this.MostrarClientesVista();
                //Mostrar todos los clientes
                break;

            case "Q":
                System.out.println("Cancelando");
                System.out.println("--------------------------------");
                break;

            default:
                System.out.println("Entrada no válida");
                break;
        }

        return resp;
    }

    private void MostrarClientesVista() {
        System.out.println("Clientes: ");
        System.out.println(clienteController.listarClientes());
    }

    public void AnyadirClienteVista(){
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

    public void Ejecuta() {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println( this.MuestraOpciones() );
        this.RecogeRespuesta();
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
    }

}
