package vista;

import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;
import modelo.direccion.Direccion;
import modelo.excepciones.*;
import modelo.factoria.FactoriaObjetos;
import modelo.llamada.Llamada;
import modelo.utils.Periodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


/*
TODO *
        Entrega Final
        Reorganizar codigo, para delegar en otras clases
        Para no tener 1000 lineas de codigo
        Separar en VistaGraficaCliente, vistaGraficaLlamadas...
        Validador para campos
 */

public class VistaGrafica extends VistaGraficaMadre {

    //AÑADIR LOS CONTROLADORES PARA LAS FUNCIONES
    //O IGUAL MEJOR CREAR LOS METODOS EN UNA CLASE SEPARADA Y LLAMARLA
    //Controlador
    //Modelo

    public void ejecutar() {
        //=================================================================
        //Creacion de la ventana
        //=================================================================

        JFrame ventana = new JFrame("Menu Gestor");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //=================================================================
        //Menu inicio/Principal
        //=================================================================

        JPanel panelPrincipal = new JPanel();
        JLabel foto = new JLabel();
        foto.setIcon(new ImageIcon("imagenInicio.JPG"));

        JButton guardar = new JButton("Guardar datos");
        //guardar.actioListener
        guardar.addActionListener( l -> {
            guardarDatos();
        });

        panelPrincipal.add(foto);
        panelPrincipal.add(guardar);
        ventana.getContentPane().add(panelPrincipal);

//=================================================================
//=================================================================
//*****************************************************************
//==========================CLIENTES===============================
//*****************************************************************
//=================================================================
//=================================================================

        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridBagLayout());

        JPanel panelMenuCliente = new JPanel();
        panelMenuCliente.setLayout(new GridLayout(3, 3, 10, 20));

        JButton botonCrearCliente    = new JButton("Crear cliente");
        JButton botonBorrarCliente   = new JButton("Borrar cliente");
        JButton botonMostrarCliente  = new JButton("Mostrar clientes");
        JButton botonMostrarPorDni   = new JButton("Buscar cliente");
        JButton botonCambiarTarifa   = new JButton("Cambiar tarifa");
        JButton botonClienteFecha    = new JButton("Mostrar clientes entre fechas");

        panelMenuCliente.add(botonCrearCliente);
        panelMenuCliente.add(botonBorrarCliente);
        panelMenuCliente.add(botonMostrarCliente);
        panelMenuCliente.add(botonMostrarPorDni);
        panelMenuCliente.add(botonCambiarTarifa);
        panelMenuCliente.add(botonClienteFecha);

        //---------------------------------------------------------------
        //Panel imagen
        //---------------------------------------------------------------

        final JPanel panelImagenCliente = new JPanel();
        JLabel fotoCliente = new JLabel();
        fotoCliente.setIcon(new ImageIcon("clientes.jpg"));
        panelImagenCliente.add(fotoCliente);


        //***************************************************************
        //***************************************************************


        //---------------------------------------------------------------
        //Panel Crear Cliente
        //---------------------------------------------------------------

        final JPanel panelCrearCliente = new JPanel();
        panelCrearCliente.setLayout(new GridBagLayout());

        //Botones

        //Boton selector opciones
        ButtonGroup opciones = new ButtonGroup();

        //Opcion Particular, estara seleccionado por defecto
        final JRadioButton particular = new JRadioButton("Particular");
        opciones.add(particular);
        particular.setSelected(true);

        //Opcion Empresa
        JRadioButton empresa = new JRadioButton("Empresa");
        opciones.add(empresa);

        //Añadir ambos botones al boton general
        panelCrearCliente.add(particular);
        panelCrearCliente.add(empresa);


        //Declararemos un Insets para utilizar durante el resto de margenes en la creación de Bags
        Insets insets;


        insets = new Insets(10, 7, 10, 7);

        //Declaramos tambien un GridBag
        GridBagConstraints gridBagCliente = new GridBagConstraints(
                                                        0, 1, 1, 1,
                                                        0, 0,
                                                         GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets,
                                                        0, 0
                                                        );

        //Campo Nombre

        panelCrearCliente.add(new JLabel("Nombre: "), gridBagCliente);
        final JTextField nombre = new JTextField(20);
        gridBagCliente.gridx = 1;
        gridBagCliente.weightx = 0.5;
        panelCrearCliente.add(nombre, gridBagCliente);

        //Campo Apellidos
        gridBagCliente.gridx = 2;
        gridBagCliente.weightx = 0;
        panelCrearCliente.add(new JLabel("Apellidos: "), gridBagCliente);
        final JTextField apellido = new JTextField(20);
        gridBagCliente.gridx   = 3;
        gridBagCliente.weightx = 1;
        panelCrearCliente.add(apellido, gridBagCliente);

        //Campo NIF
        gridBagCliente.gridx = (int) (gridBagCliente.weightx = 0);
        gridBagCliente.gridy = 2;
        panelCrearCliente.add(new JLabel("NIF: "), gridBagCliente);
        final JTextField nif = new JTextField(20);
        gridBagCliente.gridx   = 1;
        gridBagCliente.weightx = 0.5;
        panelCrearCliente.add(nif, gridBagCliente);

        //Campo Correo electronico
        gridBagCliente.gridx   = 2;
        gridBagCliente.weightx = 0;
        panelCrearCliente.add(new JLabel("e-mail: "), gridBagCliente);
        final JTextField correo = new JTextField(20);
        gridBagCliente.gridx   = 3;
        gridBagCliente.weightx = 1;
        panelCrearCliente.add(correo, gridBagCliente);

        //Campo Direccion
        JPanel panelDireccion = new JPanel();

        panelDireccion.setLayout(new GridLayout(1, 6));
        panelDireccion.add(new JLabel("Provincia: "));
        final JTextField dirProv = new JTextField(8);
        panelDireccion.add(dirProv);

        panelDireccion.add(new JLabel(" Poblacion: "));
        final JTextField dirPobl = new JTextField(8);
        panelDireccion.add(dirPobl);

        panelDireccion.add(new JLabel("  CP: "));
        final JTextField dirCod = new JTextField(5);
        panelDireccion.add(dirCod);

        gridBagCliente.gridx     = 0;
        gridBagCliente.gridy     = 3;
        gridBagCliente.gridwidth = 4;
        panelCrearCliente.add(panelDireccion, gridBagCliente);

        //Boton ACEPTAR
        JButton aceptarCrearCliente = new JButton("Crear");
        gridBagCliente.weightx   = 0;
        gridBagCliente.gridy     = 5;
        gridBagCliente.gridwidth = gridBagCliente.gridheight = 2;
        panelCrearCliente.add(aceptarCrearCliente, gridBagCliente);

        //Panel Ayuda
        gridBagCliente.gridx     = 3;
        gridBagCliente.gridwidth = gridBagCliente.gridheight = (int) (gridBagCliente.weightx = 1);
        String texto = "Menu ayuda \n" + "Estoy aqui para ayudarte.";
        JTextArea ayuda = new JTextArea(texto, 3, 1);
        ayuda.setEditable(false);
        panelCrearCliente.add(ayuda, gridBagCliente);

        panelCrearCliente.setVisible(false);

        //***************************************************************
        //***************************************************************


        //---------------------------------------------------------------
        //===================Panel Borrar Cliente========================
        //---------------------------------------------------------------
        final JPanel panelBorrarCliente = new JPanel();

        //Insertar NIF
        panelBorrarCliente.add(new JLabel("NIF: "), gridBagCliente);
        final JTextField nifB = new JTextField(20);
        panelBorrarCliente.add(nifB, gridBagCliente);

        //Boton ACEPTAR
        JButton aceptarBorrarCliente = new JButton("Borrar");
        panelBorrarCliente.add(aceptarBorrarCliente, gridBagCliente);



        panelBorrarCliente.setVisible(false);

        //***************************************************************
        //***************************************************************

        //---------------------------------------------------------------
        //====================Panel Mostrar Clientes=====================
        //---------------------------------------------------------------


        final JPanel panelMostrarClientes = new JPanel();
        panelMostrarClientes.setLayout(new GridBagLayout());

        //Añadimos una etiqueta no visible para llenar una celda y poder centrar el panel correctamente

        final JLabel relleno = new JLabel();
        gridBagCliente = new GridBagConstraints(
                                    0, 2, 1, 1, 0, 0,
                                     GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0
                                     );
        panelMostrarClientes.add(relleno, gridBagCliente);

        gridBagCliente.gridy = (int) (gridBagCliente.weightx = gridBagCliente.weighty = 1);
        gridBagCliente.gridwidth = gridBagCliente.gridheight = 3;
        String[] columnNames = {"Nombre", "NIF", "Direccion", "e-mail", "Fecha Alta", "Tarifa"};

        DefaultTableModel modeloClientes = new DefaultTableModel( llenaClientes(), columnNames);


        JTable tablaMostrarClientes = new JTable( modeloClientes );



        final JScrollPane scrollPane = new JScrollPane(tablaMostrarClientes);
        panelMostrarClientes.add(scrollPane, gridBagCliente);

        panelMostrarClientes.setVisible(false);


        //***************************************************************
        //***************************************************************

        //---------------------------------------------------------------
        //Panel Buscar Cliente por NIF
        //---------------------------------------------------------------

        final JPanel panelBuscarCliente = new JPanel();
        panelBuscarCliente.setLayout(new GridBagLayout());

        //Introduccion NIF
        gridBagCliente = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelBuscarCliente.add(new JLabel("NIF: "), gridBagCliente);
        final JTextField nifBuscar = new JTextField(20);
        gridBagCliente = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelBuscarCliente.add(nifBuscar, gridBagCliente);


        // BOTON BUSCAR
        final JButton aceptarBuscarCliente = new JButton("Buscar");
        gridBagCliente = new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelBuscarCliente.add(aceptarBuscarCliente, gridBagCliente);
        gridBagCliente = new GridBagConstraints(0, 1, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        String[][] contenido = new String[1][1];
        DefaultTableModel modeloBuscar = new DefaultTableModel( contenido , columnNames);
        JTable tablaBuscarClientes = new JTable( modeloBuscar);
        JScrollPane scrollPaneBuscar = new JScrollPane(tablaBuscarClientes);
        panelBuscarCliente.add(scrollPaneBuscar, gridBagCliente);
        panelBuscarCliente.setVisible(false);


        //***************************************************************
        //***************************************************************


        //Panel cambio tarifa
        final JPanel panelCambiarTarifa = new JPanel();
        panelCambiarTarifa.setLayout(new GridBagLayout());

        //INTRODUCIR NIF
        gridBagCliente = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCambiarTarifa.add(new JLabel("NIF: "), gridBagCliente);
        final JTextField niftarifa = new JTextField(20);
        gridBagCliente = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCambiarTarifa.add(niftarifa, gridBagCliente);

        //TARIFAS
        final JCheckBox tarifaDomingos = new JCheckBox("Tarifa domingos gratis");
        final JCheckBox tarifaTardes = new JCheckBox("Tarifa tardes reducidas");
        gridBagCliente = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCambiarTarifa.add(tarifaDomingos, gridBagCliente);
        gridBagCliente = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCambiarTarifa.add(tarifaTardes, gridBagCliente);
        //BOTON ACEPTAR
        JButton aceptarCambiarTarifa = new JButton("Aceptar");
        gridBagCliente = new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCambiarTarifa.add(aceptarCambiarTarifa, gridBagCliente);


        panelCambiarTarifa.setVisible(false);


        //***************************************************************
        //***************************************************************

        //Panel mostrar clientes creados entre fechas

        //CLIENTES ENTRE FECHAS
        final JPanel panelClientesFecha = new JPanel();
        panelClientesFecha.setLayout(new GridBagLayout());
        gridBagCliente = new GridBagConstraints(0, 0, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelClientesFecha.add(new JLabel("Introduce las fechas entre las cuales se desea mostrar:"), gridBagCliente);

        //FECHA A
        final JPanel fecha1 = new JPanel();
        final JTextField dia1 = new JTextField(2);
        fecha1.add(dia1);
        fecha1.add(new JLabel("/"));
        final JTextField mes1 = new JTextField(2);
        fecha1.add(mes1);
        fecha1.add(new JLabel("/"));
        final JTextField anyo1 = new JTextField(4);
        fecha1.add(anyo1);

        //FECHA B
        final JPanel fecha2 = new JPanel();
        final JTextField dia2 = new JTextField(2);
        fecha2.add(dia2);
        fecha2.add(new JLabel("/"));
        final JTextField mes2 = new JTextField(2);
        fecha2.add(mes2);
        fecha2.add(new JLabel("/"));
        final JTextField anyo2 = new JTextField(4);
        fecha2.add(anyo2);

        gridBagCliente = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelClientesFecha.add(fecha1, gridBagCliente);
        gridBagCliente = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelClientesFecha.add(new JLabel(" hasta "), gridBagCliente);
        gridBagCliente = new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelClientesFecha.add(fecha2, gridBagCliente);
        gridBagCliente = new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        final JButton aceptarClientesFechas = new JButton("Aceptar");
        panelClientesFecha.add(aceptarClientesFechas, gridBagCliente);

        DefaultTableModel modeloEntreFechas = new DefaultTableModel( contenido , columnNames);

        JTable tablaClientesFecha = new JTable( modeloEntreFechas );
        JScrollPane scrollPaneFecha = new JScrollPane(tablaClientesFecha);
        gridBagCliente = new GridBagConstraints(0, 2, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelClientesFecha.add(scrollPaneFecha, gridBagCliente);

        panelClientesFecha.setVisible(false);


        //*************
        //===============================================================
        //*************

        //Añadir los contenedores al contenedor principal
        insets = new Insets(10, 10, 10, 10);

        GridBagConstraints bagConstraints = new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 0, 0);
        panelCliente.add(panelMenuCliente, bagConstraints);
        bagConstraints = new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, insets, 0, 0);
        panelCliente.add(panelImagenCliente, bagConstraints);
        panelCliente.add(panelCrearCliente, new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 0, 0));
        panelCliente.add(panelBorrarCliente, bagConstraints);
        panelCliente.add(panelMostrarClientes, bagConstraints);
        panelCliente.add(panelBuscarCliente, bagConstraints);
        panelCliente.add(panelCambiarTarifa, new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        panelCliente.add(panelClientesFecha, bagConstraints);



        //***************************************************************
        // Metemos todos los paneles en un Array para poder mandarlo
        // a la funccion de acciones
        //***************************************************************

        JPanel[] panelesClientes = {
                panelImagenCliente, panelBorrarCliente,panelMostrarClientes,
                panelBuscarCliente, panelCambiarTarifa, panelClientesFecha,
                panelCrearCliente
        };


        //***************************************************************
        //ACCIONES BOTONES
        //***************************************************************

        //Usamos lambdas para compactar las declaraciones de funciones internas
        //Version antigua
        /*
         botonCrearCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

        */
        botonCrearCliente.addActionListener( arg0 -> {
            accionPaneles( panelesClientes, false);
            panelCrearCliente.setVisible( true );
        });


        botonBorrarCliente.addActionListener( arg0 -> {
            accionPaneles( panelesClientes, false);
            panelBorrarCliente.setVisible( true );
        });


        botonMostrarCliente.addActionListener( e -> {
            updateTableClientes( modeloClientes );
            accionPaneles( panelesClientes, false);
            panelMostrarClientes.setVisible(true);
        });

        botonMostrarPorDni.addActionListener( arg0 -> {
            flushModel( modeloBuscar );
            accionPaneles( panelesClientes, false);
            panelBuscarCliente.setVisible(true);
        });

        botonCambiarTarifa.addActionListener(arg0 -> {
            accionPaneles( panelesClientes, false );
            panelCambiarTarifa.setVisible(true);
        });

        botonClienteFecha.addActionListener(arg0 -> {
            accionPaneles( panelesClientes, false);
            panelClientesFecha.setVisible(true);
        });
        //*******************************************************
        //BOTONES DE ACEPTAR
        //*******************************************************
        aceptarCrearCliente.addActionListener( arg0 -> {
            //Accion
            Cliente nuevo = new Cliente();
            nuevo.setNombre( nombre.getText() );
            nuevo.setNIF( nif.getText() );
            nuevo.setEmail( correo.getText() );
            Direccion dir = new Direccion();
            dir.setPoblacion( dirPobl.getText() );
            dir.setProvincia( dirProv.getText() );
            dir.setCodigoPostal( Integer.parseInt( dirCod.getText() ) );
            nuevo.setDireccion( dir );
            try {
                if ( particular.isSelected() ) {
                    Particular part = new Particular( nuevo );
                    part.setApellidos( apellido.getText() );
                    clienteController.altaCliente( part );
                    addClient( modeloClientes,  part );
                }
                else{
                    Empresa empr = new Empresa( nuevo );
                    clienteController.altaCliente( empr );
                    addClient( modeloClientes, empr );

                }
            } catch (ClienteExistente clienteExistente) {
                clienteExistente.printStackTrace();
                //TIRAR LOG
            }

            //Luego de crearse, es necesario volver al panel principal
            botonMostrarCliente.doClick();
        });


        aceptarBorrarCliente.addActionListener( e-> {
            try {
                clienteController.bajaClienteNIF( nifB.getText() );
                updateTableClientes( modeloClientes );
                botonMostrarCliente.doClick();
            } catch (NoHayClientes noHayClientes) {
                noHayClientes.printStackTrace();
                //LOG
            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
                //LOG
            }
        });

        aceptarBuscarCliente.addActionListener( e-> {
            try {
                buscarCliente( modeloBuscar, nifBuscar.getText() );
            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            }
        });

        aceptarCambiarTarifa.addActionListener( e-> {

            try {
                Cliente cliente = clienteController.getCliente(niftarifa.getText());
                // SELECTOR OPCIONES
                //if ( tarifaDomingos.isSelected() )
                if (tarifaTardes.isSelected() && tarifaDomingos.isSelected()) {
                    clienteController.cambiarTarifa(cliente, FactoriaObjetos.TARDES_Y_DOMINGOS);
                }
                else if ( tarifaTardes.isSelected() ){
                    clienteController.cambiarTarifa( cliente, FactoriaObjetos.TARDES);
                }
                else if ( tarifaDomingos.isSelected() ){
                    clienteController.cambiarTarifa( cliente, FactoriaObjetos.DOMINGOS);
                }
                //else *No ejecutamos nada*
            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
                //LOG
            }
            botonMostrarCliente.doClick();

        });
        aceptarClientesFechas.addActionListener( e-> {
            String[] fechas1 = { dia1.getText(), mes1.getText(), anyo1.getText() };
            LocalDateTime inicio = dameFecha( fechas1 );
            String[] fechas2 = { dia2.getText(), mes2.getText(), anyo2.getText() };
            LocalDateTime fin = dameFecha( fechas2 );
            try {
                clientesFechas( modeloEntreFechas, inicio, fin );
            } catch (NoHayClientesEntreFechas noHayClientesEntreFechas) {
                noHayClientesEntreFechas.printStackTrace();
            } catch (FechaInvalida fechaInvalida) {
                fechaInvalida.printStackTrace();
            } catch (NoHayClientes noHayClientes) {
                noHayClientes.printStackTrace();
            }

        });
        //****
        //Botones para desactivar la entrada de apellidos si creamos una empresa
        //****

        particular.addActionListener( e -> apellido.setEnabled(true) );

        empresa.addActionListener( e-> apellido.setEnabled(false) );



//=================================================================
//=================================================================
//=================================================================
//*****************************************************************
//===========================LLAMADAS==============================
//*****************************************************************
//=================================================================
//=================================================================
//=================================================================


        // Panel de Llamadas

        JPanel panelLlamada = new JPanel();
        panelLlamada.setLayout(new GridBagLayout());

        //=========================================================================
        //Menu Superior Llamadas
        //=========================================================================


        JPanel panelMenuLlamada = new JPanel();
        panelMenuLlamada.setLayout(new GridLayout(1, 3, 15, 20));
        JButton botonAltaLlamada = new JButton("Alta Llamada");
        JButton botonLlamadasCliente = new JButton("Mostrar Llamadas Cliente");
        JButton botonLlamadasFechas = new JButton("Mostrar Llamadas Entre Fechas");
        panelMenuLlamada.add(botonAltaLlamada);
        panelMenuLlamada.add(botonLlamadasCliente);
        panelMenuLlamada.add(botonLlamadasFechas);


        //=========================================================================
        //Panel IMAGEN Llamada
        //=========================================================================

        final JPanel panelImagenLlamada = new JPanel();
        JLabel fotoLlamada = new JLabel();
        fotoLlamada.setIcon(new ImageIcon("llamadas.jpg"));
        panelImagenLlamada.add(fotoLlamada);

        GridBagConstraints gbcLlam = new GridBagConstraints(
                0, 0,
                0, 0,
                0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets,
                0, 0);

        //=========================================================================
        //Alta llamada
        //=========================================================================


        final JPanel panelAltaLlamada = new JPanel();
        panelAltaLlamada.setLayout(new GridLayout(5, 2, 10, 10));
        panelAltaLlamada.add(new JLabel("Nif del cliente: "));
        final JTextField nifAltaLlamada = new JTextField(8);
        panelAltaLlamada.add(nifAltaLlamada);
        panelAltaLlamada.add(new JLabel("Numero de telefono: "));
        final JTextField telefonoNuevo = new JTextField(8);
        panelAltaLlamada.add(telefonoNuevo);
        panelAltaLlamada.add(new JLabel("Duracion: "));
        final JTextField duracion = new JTextField(3);
        panelAltaLlamada.add(duracion);

        //Fecha
        panelAltaLlamada.add(new JLabel("Fecha Llamada: dd/mm/yyyy"));

        final JPanel fechaAlta = new JPanel();
        final JTextField diaAlta = new JTextField(2);
        fechaAlta.add(diaAlta);
        fechaAlta.add(new JLabel("/"));
        final JTextField mesAlta = new JTextField(2);
        fechaAlta.add(mesAlta);
        fechaAlta.add(new JLabel("/"));
        final JTextField anyoAlta = new JTextField(4);
        fechaAlta.add(anyoAlta);

        gbcLlam.gridy = 1;
        gbcLlam.gridwidth = gbcLlam.gridheight = 1;

        panelAltaLlamada.add(fechaAlta, gbcLlam);

        JButton aceptarAltaLlamada = new JButton("Aceptar");
        panelAltaLlamada.add(aceptarAltaLlamada);

        panelAltaLlamada.setVisible(false);


        //=========================================================================
        //Mostrar llamadas clientes
        //=========================================================================


        final JPanel panelLlamadasCliente = new JPanel();
        panelLlamadasCliente.setLayout(new GridBagLayout());

        //Inroduccion del NIF
       gbcLlam = new GridBagConstraints(
                                        0, 0,
                                        1, 1,
                                        0, 0,
                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets,
                                        0, 0
                                        );
        panelLlamadasCliente.add(new JLabel("NIF: "), gbcLlam);
        final JTextField nifCliLlam = new JTextField(20);
        gbcLlam.gridx = 1;
        panelLlamadasCliente.add(nifCliLlam, gbcLlam);
        //Boton ACEPTAR
        final JButton aceptarLlamadasCliente = new JButton("Aceptar");
        gbcLlam.gridx = 2;

        panelLlamadasCliente.add(aceptarLlamadasCliente, gbcLlam);
        gbcLlam.gridx = 0;
        gbcLlam.gridy = (int) (gbcLlam.weightx = gbcLlam.weighty = 1);
        gbcLlam.gridwidth = gbcLlam.gridheight = 3;

        String[] columnNamesLlamadas = {"Numero llamada", "Fecha", "Duracion"};
        DefaultTableModel modeloLlamadasCliente = new DefaultTableModel( contenido , columnNamesLlamadas);
        JTable tablaLlamadasCliente = new JTable( modeloLlamadasCliente );

        //Object[][] dataLlamadas = new Object[0][0];
        //JTable tablaLlamadasCliente = new JTable(dataLlamadas, columnNamesLlamadas);
        JScrollPane scrollPaneLlamadas = new JScrollPane(tablaLlamadasCliente);
        panelLlamadasCliente.add(scrollPaneLlamadas, gbcLlam);

        panelLlamadasCliente.setVisible(false);

        //=========================================================================
        //Mostrar llamadas entre Fechas
        //=========================================================================


        final JPanel panelLlamadasFecha = new JPanel();
        panelLlamadasFecha.setLayout(new GridBagLayout());
        gbcLlam = new GridBagConstraints(
                    0, 0, 4,
                    1, 0, 0,
                     GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets,
                    0, 0
                    );
        panelLlamadasFecha.add(new JLabel("Introduce las fechas entre las cuales se desea mostrar:"), gbcLlam);

        //PANEL FECHA A
        final JPanel fecha3 = new JPanel();
        final JTextField dia3 = new JTextField(2);
        fecha3.add(dia3);
        fecha3.add(new JLabel("/"));
        final JTextField mes3 = new JTextField(2);
        fecha3.add(mes3);
        fecha3.add(new JLabel("/"));
        final JTextField anyo3 = new JTextField(4);
        fecha3.add(anyo3);

        //PANEL FECHA B
        final JPanel fecha4 = new JPanel();
        final JTextField dia4 = new JTextField(2);
        fecha4.add(dia4);
        fecha4.add(new JLabel("/"));
        final JTextField mes4 = new JTextField(2);
        fecha4.add(mes4);
        fecha4.add(new JLabel("/"));
        final JTextField anyo4 = new JTextField(4);
        fecha4.add(anyo4);

        gbcLlam.gridy = 1;
        gbcLlam.gridwidth = gbcLlam.gridheight = 1;

        panelLlamadasFecha.add(fecha3, gbcLlam);
        gbcLlam.gridx = 1;

        panelLlamadasFecha.add(new JLabel(" hasta "), gbcLlam);
        gbcLlam.gridx = 2;
        panelLlamadasFecha.add(fecha4, gbcLlam);
        gbcLlam.gridx = 0;
        gbcLlam.gridy = 2;
        panelLlamadasFecha.add(new JLabel("NIF: "), gbcLlam);
        gbcLlam.gridx = 1;
        final JTextField nifLlamFechas = new JTextField(8);
        panelLlamadasFecha.add(nifLlamFechas, gbcLlam);
        gbcLlam.gridx = 3;
        gbcLlam.gridheight = 2;
        gbcLlam.gridy = 1;

        final JButton aceptarLlamadasFechas = new JButton("Aceptar");
        panelLlamadasFecha.add(aceptarLlamadasFechas, gbcLlam);

        DefaultTableModel modeloLlamadasFechas = new DefaultTableModel( contenido, columnNamesLlamadas);
        JTable tablaLlamadasFecha = new JTable( modeloLlamadasFechas);

        JScrollPane scrollPaneLlamFecha = new JScrollPane(tablaLlamadasFecha);
        gbcLlam.gridx = 0;
        gbcLlam.gridy = 3;
        gbcLlam.gridwidth = 4;
        gbcLlam.gridheight = 1;
        panelLlamadasFecha.add(scrollPaneLlamFecha, gbcLlam);

        panelLlamadasFecha.setVisible(false);


        //Añadir Paneles de LLAMADAS al panel PRINCIPAL
        insets = new Insets(10, 7, 10, 7);
        gbcLlam         = new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 0, 0);
        gridBagCliente  = new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, insets, 0, 0);


        panelLlamada.add(panelMenuLlamada, gbcLlam);
        panelLlamada.add(panelImagenLlamada, gridBagCliente);


        panelLlamada.add(panelAltaLlamada,
                            new GridBagConstraints(
                                    0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
                                    1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                     insets, 0, 0
                                    )
                        );
        panelLlamada.add(panelLlamadasCliente, gridBagCliente);
        panelLlamada.add(panelLlamadasFecha, gridBagCliente);

        //***************************************************************
        // Metemos todos los paneles en un Array para poder mandarlo
        // a la funccion de acciones
        //***************************************************************
        JPanel[] panelesLlamadas = {
                panelImagenLlamada, panelAltaLlamada,
                panelLlamadasCliente, panelLlamadasFecha
        };

        //***************************************************************
        //ACCIONES BOTONES
        //***************************************************************

        botonAltaLlamada.addActionListener(e -> {
            accionPaneles( panelesLlamadas, false );
            panelAltaLlamada.setVisible(true);
        });


        botonLlamadasCliente.addActionListener(arg0 -> {
            flushModel( modeloLlamadasCliente );
            accionPaneles( panelesLlamadas, false );
            panelLlamadasCliente.setVisible(true);
        });


        botonLlamadasFechas.addActionListener(arg0 -> {
            flushModel( modeloLlamadasFechas);
            accionPaneles( panelesLlamadas, false );
            panelLlamadasFecha.setVisible(true);
        });

        //********************************************
        //************Botones de aceptar**************
        //********************************************
        aceptarAltaLlamada.addActionListener( l -> {
            try {
                Cliente cliente = clienteController.getCliente( nifAltaLlamada.getText() );
                //Coger fecha
                LocalDateTime fecha = dameFecha(
                                            new String[] {
                                                    diaAlta.getText(),
                                                    mesAlta.getText(),
                                                    anyoAlta.getText()
                                            }
                );
                float tiempoLlamada = Float.parseFloat( duracion.getText() );
                int telefonoLlamada = Integer.parseInt( telefonoNuevo.getText() );
                Llamada llamada = new Llamada( telefonoLlamada, fecha, tiempoLlamada );
                llamadaController.altaLlamada( cliente, llamada );
                botonLlamadasCliente.doClick();

            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            }
        });

        aceptarLlamadasCliente.addActionListener( l -> {
            try{
                Cliente cliente = clienteController.getCliente( nifCliLlam.getText() );
                llamadasCliente( modeloLlamadasCliente, cliente);

            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
                noHayLlamadasCliente.printStackTrace();
            }
        });

        aceptarLlamadasFechas.addActionListener( l ->{
            try{
                Cliente cliente  = clienteController.getCliente( nifLlamFechas.getText() );
                String[] fechas3 = { dia3.getText(), mes3.getText(), anyo3.getText() };
                LocalDateTime inicioLlamada = dameFecha( fechas3 );
                String[] fechas4 = { dia4.getText(), mes4.getText(), anyo4.getText() };
                LocalDateTime finLlamada = dameFecha( fechas4 );
                llamadasFechas( modeloLlamadasFechas, inicioLlamada, finLlamada, cliente);

            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            } catch (FechaInvalida fechaInvalida) {
                fechaInvalida.printStackTrace();
            } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
                noHayLlamadasCliente.printStackTrace();
            } catch (NoHayLlamadasEntreFechas noHayLlamadasEntreFechas) {
                noHayLlamadasEntreFechas.printStackTrace();
            }
        });





//=================================================================
//=================================================================
//=================================================================
//*****************************************************************
//==========================FACTURAS===============================
//*****************************************************************
//=================================================================
//=================================================================
//=================================================================


        JPanel panelFactura = new JPanel();
        panelFactura.setLayout(new GridBagLayout());

        //MENU FACTURA
        JPanel panelMenuFactura = new JPanel();
        panelMenuFactura.setLayout(new GridLayout(1, 3, 15, 20));
        JButton botonEmitirFactura = new JButton("Emitir Factura");
        JButton botonConsultarFactura = new JButton("Consultar Factura");
        JButton botonFacturasFechas = new JButton("Mostrar Facturas Entre Fechas");
        panelMenuFactura.add(botonEmitirFactura);
        panelMenuFactura.add(botonConsultarFactura);
        panelMenuFactura.add(botonFacturasFechas);

        final JPanel panelImagenFactura = new JPanel();
        JLabel fotoFactura = new JLabel();
        fotoFactura.setIcon(new ImageIcon("facturas.jpg"));
        panelImagenFactura.add(fotoFactura);


        //EMITIR FACTURA
        final JPanel panelEmitirFactura = new JPanel();
        panelEmitirFactura.setLayout(new GridLayout(4,3,10,10));
        panelEmitirFactura.add(new JLabel("Nif del cliente: "));
        final JTextField nifEmitirFactura = new JTextField(8);
        panelEmitirFactura.add(nifEmitirFactura);
        JPanel fechaE = new JPanel();
        final JTextField diaE = new JTextField(2);
        fechaE.add(diaE);
        fechaE.add(new JLabel("/"));
        final JTextField mesE = new JTextField(2);
        fechaE.add(mesE);
        fechaE.add(new JLabel("/"));
        final JTextField anyoE = new JTextField(4);
        fechaE.add(anyoE);

        //FECHA B
        final JPanel fechaE2 = new JPanel();
        final JTextField diaE2 = new JTextField(2);
        fechaE2.add(diaE2);
        fechaE2.add(new JLabel("/"));
        final JTextField mesE2 = new JTextField(2);
        fechaE2.add(mesE2);
        fechaE2.add(new JLabel("/"));
        final JTextField anyoE2 = new JTextField(4);
        fechaE2.add(anyoE2);

        //COMO SOLAMENTE CAMBIAN PEQUEÑOS PARAMETROS, EN LUGAR DE HACER UN NEW GridBagConstraints cada vez
        //Podemos setear los parámetros que cambien, y quedará más fino y elegante

        GridBagConstraints gbcFac = new GridBagConstraints(
                                0, 1,
                                1, 1, 0, 0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                insets, 0, 0
                                );
        panelEmitirFactura.add(new JLabel("Fecha Inicio Facturacion"), gbcFac);
        gbcFac.gridx = 1;
        panelEmitirFactura.add(new JLabel("Fecha Fin Facturacion"), gbcFac);
        gbcFac.gridx = 0;
        gbcFac.gridy = 2;
        panelEmitirFactura.add(fechaE, gbcFac);
        gbcFac.gridx = 1;
        panelEmitirFactura.add(fechaE2, gbcFac);
        gbcFac.gridx = 2;
        JButton aceptarEmitirFactura = new JButton("Aceptar");
        panelEmitirFactura.add(aceptarEmitirFactura, gbcFac);

        panelEmitirFactura.setVisible(false);



        //CONSULTAR FACTURA
        final JPanel panelConsultarFactura = new JPanel();
        panelConsultarFactura.setLayout(new GridBagLayout());

        //INTRODUCIR NIF
        gbcFac = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelConsultarFactura.add(new JLabel("Codigo Factura: "), gbcFac);
        final JTextField codFac = new JTextField(20);
        gbcFac = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelConsultarFactura.add(codFac,gbcFac);

        //ACEPTAR
        final JButton aceptarConsultarFactura = new JButton("Aceptar");
        gbcFac = new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelConsultarFactura.add(aceptarConsultarFactura, gbcFac);
        gbcFac = new GridBagConstraints(0, 1, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        String[] columnNamesFacturas = {"Codigo", "Tarifa", "Importe", "Fecha Emision", "Inicio Facturacion", "Fin Facturacion"};
        DefaultTableModel modeloFacturaCliente = new DefaultTableModel( contenido, columnNamesFacturas);
        JTable tablaFacturasCliente = new JTable(modeloFacturaCliente);

        JScrollPane scrollPaneFacturas = new JScrollPane(tablaFacturasCliente);
        panelConsultarFactura.add(scrollPaneFacturas,gbcFac);

        panelConsultarFactura.setVisible(false);


        //FACTURAS ENTRE FECHAS
        final JPanel panelFacturasFecha = new JPanel();
        panelFacturasFecha.setLayout(new GridBagLayout());
        gbcFac = new GridBagConstraints(0, 0, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(new JLabel("Introduce las fechas entre las cuales se desea mostrar:"), gbcFac);

        //FECHA A
        final JPanel fechaFF = new JPanel();
        final JTextField diaFF = new JTextField(2);
        fechaFF.add(diaFF);
        fechaFF.add(new JLabel("/"));
        final JTextField mesFF = new JTextField(2);
        fechaFF.add(mesFF);
        fechaFF.add(new JLabel("/"));
        final JTextField anyoFF = new JTextField(4);
        fechaFF.add(anyoFF);

        //FECHA B
        final JPanel fechaFF2 = new JPanel();
        final JTextField diaFF2 = new JTextField(2);
        fechaFF2.add(diaFF2);
        fechaFF2.add(new JLabel("/"));
        final JTextField mesFF2 = new JTextField(2);
        fechaFF2.add(mesFF2);
        fechaFF2.add(new JLabel("/"));
        final JTextField anyoFF2 = new JTextField(4);
        fechaFF2.add(anyoFF2);

        gbcFac = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(fechaFF, gbcFac);
        gbcFac = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(new JLabel(" hasta "), gbcFac);
        gbcFac = new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(fechaFF2, gbcFac);
        gbcFac = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(new JLabel("NIF: "),gbcFac);
        gbcFac = new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        final JTextField nifFacFechas = new JTextField(8);
        panelFacturasFecha.add(nifFacFechas,gbcFac);
        gbcFac = new GridBagConstraints(3, 1, 1, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        final JButton aceptarFacturasFechas = new JButton("Aceptar");
        panelFacturasFecha.add(aceptarFacturasFechas, gbcFac);

        DefaultTableModel modeloFacturasFechas = new DefaultTableModel( contenido, columnNamesFacturas);
        JTable tablaFacturasFecha = new JTable( modeloFacturasFechas );
        JScrollPane scrollPaneFacFecha = new JScrollPane(tablaFacturasFecha);
        gbcFac = new GridBagConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(scrollPaneFacFecha,gbcFac);

        panelFacturasFecha.setVisible(false);


        //AÑADIR A FACTURA
        insets = new Insets(10, 7, 10, 7);
        gbcFac =  new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 0, 0);
        panelFactura.add(panelMenuFactura,gbcFac);

        gridBagCliente = new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, insets, 0, 0);

        panelFactura.add(panelImagenFactura, gridBagCliente );
        panelFactura.add(panelEmitirFactura, new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        panelFactura.add(panelConsultarFactura, gridBagCliente );
        panelFactura.add(panelFacturasFecha, gridBagCliente );




        //***************************************************************
        // Metemos todos los paneles en un Array para poder mandarlo
        // a la funccion de acciones
        //***************************************************************
        JPanel[] panelesFacturas = {
          panelImagenFactura, panelConsultarFactura,
          panelEmitirFactura, panelFacturasFecha
        };


        //ACCIONES BOTONES
        botonEmitirFactura.addActionListener(e -> {
            accionPaneles( panelesFacturas, false );
            panelEmitirFactura.setVisible(true);

        });
        botonConsultarFactura.addActionListener(arg0 -> {
            accionPaneles( panelesFacturas, false );
            panelConsultarFactura.setVisible(true);
        });
        botonFacturasFechas.addActionListener(arg0 -> {
            flushModel( modeloFacturasFechas );
            accionPaneles( panelesFacturas, false );
            panelFacturasFecha.setVisible(true);
        });
        //********************************************
        //Acciones Aceptar
        //********************************************
        aceptarFacturasFechas.addActionListener( l -> {
            flushModel( modeloFacturasFechas );
            try {
                Cliente cliente = clienteController.getCliente( nifFacFechas.getText() );
                String[] fechasFF = { diaFF.getText(), mesFF.getText(), anyoFF.getText() };
                LocalDateTime inicioFactura = dameFecha( fechasFF );
                String[] fechasFF2 = { diaFF2.getText(), mesFF2.getText(), anyoFF2.getText() };
                LocalDateTime finFactura = dameFecha( fechasFF2 );
                facturasFechas( modeloFacturasFechas, inicioFactura, finFactura, cliente);
            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            } catch (NoExistenFacturasDeCliente noExistenFacturasDeCliente) {
                noExistenFacturasDeCliente.printStackTrace();
            }
        });
        aceptarConsultarFactura.addActionListener( l ->{
            try{
                facturaCodigo( modeloFacturaCliente, codFac.getText() );
            } catch (NoExisteFactura noExisteFactura) {
                noExisteFactura.printStackTrace();
            }
        });
        aceptarEmitirFactura.addActionListener( l->{
            try{
                Cliente cliente = clienteController.getCliente( nifEmitirFactura.getText());
                LocalDateTime inicioFacturacion = dameFecha(
                        new String[] {
                              diaE.getText(),
                              mesE.getText(),
                              anyoE.getText()
                        }
                );
                LocalDateTime finFacturacion = dameFecha(
                        new String[] {
                                diaE2.getText(),
                                mesE2.getText(),
                                anyoE2.getText()
                        }
                );
                Periodo periodo = new Periodo(inicioFacturacion, finFacturacion);
                facturaController.emitirFactura(periodo, cliente);

                //Si sale bien, se va al panel de buscar facturas
                botonConsultarFactura.doClick();

            } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
                noHayLlamadasCliente.printStackTrace();
            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            }
        });

        //=================================================================
        //=================================================================
        //=================================================================
        //*****************************************************************
        //=================================================================
        //=================================================================
        //=================================================================


        //=================================================================
        //Pestañas
        //=================================================================

        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Inicio", panelPrincipal);
        pestanyas.add("Gestion de Clientes", panelCliente);
        pestanyas.add("Gestion de Llamadas", panelLlamada);
        pestanyas.add("Gestion de Facturas", panelFactura);

        ventana.add(pestanyas);
        //=================================================================
        //Seteo de tamaño inicial y visibilidad
        //=================================================================

        ventana.setSize(1000, 800);
        ventana.setVisible(true);

    }
    //END
}
