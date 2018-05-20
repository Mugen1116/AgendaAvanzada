package vista;

import controlador.cliente.ClienteController;
import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;
import modelo.GuardarCargar.Guardar;
import modelo.agenda.Agenda;
import modelo.cliente.Cliente;
import modelo.excepciones.*;
import modelo.factura.Factura;
import modelo.llamada.Llamada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class VistaGraficaMadre {



    ClienteController clienteController;
    FacturaController facturaController;
    LlamadaController llamadaController;


    public VistaGraficaMadre( ){

        clienteController = new ClienteController();
        llamadaController = new LlamadaController();
        facturaController = new FacturaController( llamadaController );

    }

    /*
     * * Coloca los paneles al segundo atributo
     */

    public static void accionPaneles( JPanel[] paneles, boolean accion ){
        for ( JPanel panel : paneles) {
            panel.setVisible( accion );
        }
    }


    //==================================================================
    //---------------FUNCIONES PARA LOS CONTROLADORES-------------------
    //==================================================================

    //------------------------------------------------------------------
    //1.- Llenar todos los clientes
    //------------------------------------------------------------------
    public String[][] llenaClientes () {

        HashMap<String, Cliente> mapa = clienteController.getClientes();
        Set claves = mapa.keySet();
        String[][] lista = new String[mapa.size()][6];
        //Iterador
        Iterator<String> iterador = claves.iterator();
        int index = 0;
        while ( iterador.hasNext() ){
            String clave =  iterador.next();
            Cliente valor =  mapa.get(clave);
            lista[index][0] = valor.getNombre();
            lista[index][1] = valor.getNIF();
            lista[index][2] = valor.getDireccion().toString();
            lista[index][3] = valor.getEmail();
            lista[index][4] = valor.getFechaAlta().toString();
            lista[index][5] = valor.getTarifa().toString();
            index++;
        }
        return lista;

    }

    //------------------------------------------------------------------
    //2.- Añadir Cliente
    //------------------------------------------------------------------

    public void addClient ( DefaultTableModel modelo, Cliente client )  {
        Object[] nuevoCliente  =   {
                                client.getNombre(),
                                client.getNIF(),
                                client.getDireccion().toString(),
                                client.getEmail(),
                                client.getFechaAlta().toString(),
                                client.getTarifa().toString(),
                                };
        modelo.addRow( nuevoCliente );

    }
    //------------------------------------------------------------------
    //3.- Eliminar Cliente
    //------------------------------------------------------------------
    public void removeClient ( DefaultTableModel modelo, Cliente client ){
        //TODO Mejorar eficiencia, es Lenta hacer un bucle si hay miles de usuarios

    }
    //------------------------------------------------------------------
    //4.- Actualizar Tabla  Clientes
    //------------------------------------------------------------------
    public  void updateTableClientes(DefaultTableModel modelo ) {
        HashMap<String, Cliente> mapa = clienteController.getClientes();
        Set claves = mapa.keySet();
        String[][] lista = new String[mapa.size()][6];
        //Iterador
        Iterator<String> iterador = claves.iterator();

        flushModel( modelo );

        while ( iterador.hasNext() ){
            String clave =  iterador.next();
            Cliente client =  mapa.get(clave);
            Object[] nuevoCliente  =   {
                    client.getNombre(),
                    client.getNIF(),
                    client.getDireccion().toString(),
                    client.getEmail(),
                    client.getFechaAlta().toString(),
                    client.getTarifa().toString(),
            };
            modelo.addRow( nuevoCliente );

        }
    }
    //------------------------------------------------------------------
    //5.- Buscar Cliente
    //------------------------------------------------------------------
    public void buscarCliente( DefaultTableModel modelo, String nif ) throws ClienteNoExiste {
        flushModel( modelo );

        //Cuando tengas el cliente añadirlo
        Cliente client = clienteController.getCliente( nif );
        Object[] nuevoCliente  =   {
                client.getNombre(),
                client.getNIF(),
                client.getDireccion().toString(),
                client.getEmail(),
                client.getFechaAlta().toString(),
                client.getTarifa().toString(),
        };
        modelo.addRow( nuevoCliente );

    }
    //------------------------------------------------------------------
    //6.- Clientes Entre Fechas
    //------------------------------------------------------------------
    public void clientesFechas ( DefaultTableModel modelo, LocalDateTime inicio, LocalDateTime fin ) throws NoHayClientesEntreFechas, FechaInvalida, NoHayClientes {
        flushModel( modelo );
        List<Cliente> clientes = clienteController.clientesEntreFechas(inicio, fin );
        for ( Cliente client : clientes ){
            Object[] nuevoCliente  =   {
                    client.getNombre(),
                    client.getNIF(),
                    client.getDireccion().toString(),
                    client.getEmail(),
                    client.getFechaAlta().toString(),
                    client.getTarifa().toString(),
            };
            modelo.addRow( nuevoCliente );
        }
    }
    //------------------------------------------------------------------
    //7.- Actualiza Tabla Llamadas
    //------------------------------------------------------------------
    public void llamadasCliente (DefaultTableModel modelo, Cliente cliente ) throws NoHayLlamadasCliente, ClienteNoExiste {
        flushModel( modelo );
        List<Llamada> llamadas = llamadaController.listaLlamadas( cliente );
        fillTableLlamadas( modelo, llamadas);
    }
    //------------------------------------------------------------------
    //8.- Llamadas entre Fechas
    //------------------------------------------------------------------
    public void llamadasFechas ( DefaultTableModel modelo, LocalDateTime inicio, LocalDateTime fin, Cliente cliente  ) throws NoHayLlamadasCliente, FechaInvalida, NoHayLlamadasEntreFechas {
        flushModel( modelo );
        List<Llamada> llamadas = llamadaController.llamadasEntreFechas(cliente, inicio, fin);
        fillTableLlamadas( modelo, llamadas);
    }
    //------------------------------------------------------------------
    //9.- Facturas entre Fechas
    //------------------------------------------------------------------
    public void facturasFechas ( DefaultTableModel modelo, LocalDateTime inicio, LocalDateTime fin, Cliente cliente ) throws NoExistenFacturasDeCliente {
        flushModel( modelo );
        List<Factura> facturas = facturaController.facturasEntreFechas(cliente, inicio, fin );
        fillTableFacturas( modelo, facturas );
    }
    //10. Factura con codigo de factura
    public void facturaCodigo ( DefaultTableModel modelo, String codigo ) throws NoExisteFactura {
        flushModel( modelo);
        Factura factura = facturaController.getFactura( codigo );
        Object[] nuevaFactura = {
                factura.getUniqueID(),
                factura.getTarifa().toString(),
                factura.getImporte(),
                factura.getFechaEmision().toString(),
                factura.getPeriodo().getInicio().toString(),
                factura.getPeriodo().getFin().toString()
        };
        modelo.addRow( nuevaFactura );
    }
    //------------------------------------------------------------------
    //AUXILIARES
    //------------------------------------------------------------------
    //AUX TO FLUSH TABLES
    public void flushModel ( DefaultTableModel modelo ){
        if (modelo.getRowCount() > 0) {
            for (int i = modelo.getRowCount() - 1; i > -1; i--) {
                modelo.removeRow(i);
            }
        }
    }
    //AUX Generate LocalDateTime
    public LocalDateTime dameFecha( String[] diaMesAnyo ){
        LocalDate date = LocalDate.of(
                //Anyo mes dia
                Integer.parseInt(diaMesAnyo[2] ),
                Integer.parseInt(diaMesAnyo[1] ),
                Integer.parseInt(diaMesAnyo[0] )
        );
        LocalTime time = LocalTime.of( 0,0);
        return LocalDateTime.of( date, time );
    }
    //AUX Fill tableLlamadas
    public void fillTableLlamadas ( DefaultTableModel modelo, List<Llamada> llamadas ){
        for ( Llamada llamada : llamadas) {
            Object[] nuevaLlamada  =   {
                    llamada.getTelefonoLlamado(),
                    llamada.getDiaHora().toString(),
                    llamada.getDuracion()
            };
            modelo.addRow( nuevaLlamada );
        }
    }
    //AUX Fill tableFacturas
    public void fillTableFacturas (DefaultTableModel modelo, List<Factura> facturas ){
        for ( Factura factura : facturas ){
            Object[] nuevaFactura = {
                    factura.getUniqueID(),
                    factura.getTarifa().toString(),
                    factura.getImporte(),
                    factura.getFechaEmision().toString(),
                    factura.getPeriodo().getInicio().toString(),
                    factura.getPeriodo().getFin().toString()
            };
            modelo.addRow( nuevaFactura );
        }
    }



    public void guardarDatos () {
        System.out.println("Se va a guardar la información.");
        System.out.println("Archivo actual: \"Agenda.bin\"");
        String archivo = "Agenda.bin";
        Guardar guardar = new Guardar();
        Agenda agenda = new Agenda();
        agenda.setClientes( clienteController.getClientes() );
        agenda.setFacturas( facturaController.getFacturas() );
        agenda.setLlamadas( llamadaController.getLlamadas() );
        guardar.guardar( archivo, agenda );
    }

    public abstract void ejecutar();

    //==================================================================
    //-----------------------GETTERS AND SETTERS------------------------
    //==================================================================
    public void setClienteController(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    public void setFacturaController(FacturaController facturaController) {
        this.facturaController = facturaController;
    }

    public void setLlamadaController(LlamadaController llamadaController) {
        this.llamadaController = llamadaController;
    }

    public ClienteController getClienteController() {
        return clienteController;
    }

    public FacturaController getFacturaController() {
        return facturaController;
    }

    public LlamadaController getLlamadaController() {
        return llamadaController;
    }
}
