package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGrafica {

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
        JButton cargar = new JButton("Cargar datos");
        //cargar.actionListener

        panelPrincipal.add(foto);
        panelPrincipal.add(guardar);
        panelPrincipal.add(cargar);
        ventana.getContentPane().add(panelPrincipal);

        //=================================================================
        //=================================================================
        //=================================================================

        //=================================================================
        //Menu Clientes
        //=================================================================

        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridBagLayout());

        JPanel panelMenuCliente = new JPanel();
        panelMenuCliente.setLayout(new GridLayout(3, 3, 10, 20));

        JButton botonCrearCliente = new JButton("Crear cliente");
        JButton botonBorrarCliente = new JButton("Borrar cliente");
        JButton botonMostrarCliente = new JButton("Mostrar clientes");
        JButton botonMostrarPorDni = new JButton("Buscar cliente");
        JButton botonCambiarTarifa = new JButton("Cambiar tarifa");
        JButton botonClienteFecha = new JButton("Mostrar clientes entre fechas");

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
        GridBagConstraints gridBagCliente = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

        //Campo Nombre

        panelCrearCliente.add(new JLabel("Nombre: "), gridBagCliente);
        final JTextField nombre = new JTextField(20);
        gridBagCliente = new GridBagConstraints(1, 1, 1, 1, 0.5, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(nombre, gridBagCliente);

        //Campo Apellidos
        gridBagCliente = new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(new JLabel("Apellidos: "), gridBagCliente);
        final JTextField apellido = new JTextField(20);
        gridBagCliente = new GridBagConstraints(3, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(apellido, gridBagCliente);

        //Campo NIF
        gridBagCliente = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(new JLabel("NIF: "), gridBagCliente);
        final JTextField nif = new JTextField(20);
        gridBagCliente = new GridBagConstraints(1, 2, 1, 1, 0.5, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(nif, gridBagCliente);

        //Campo Correo electronico
        gridBagCliente = new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(new JLabel("e-mail: "), gridBagCliente);
        final JTextField correo = new JTextField(20);
        gridBagCliente = new GridBagConstraints(3, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
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


        gridBagCliente = new GridBagConstraints(0, 3, 4, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(panelDireccion, gridBagCliente);

        //Campo Tarifa
        gridBagCliente = new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(new JLabel("Tarifa: "), gridBagCliente);
        final JTextField tarifa = new JTextField(20);
        gridBagCliente = new GridBagConstraints(1, 4, 1, 1, 0.5, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(tarifa, gridBagCliente);

        //Boton ACEPTAR
        JButton aceptarCrearCliente = new JButton("Crear");
        gridBagCliente = new GridBagConstraints(1, 5, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelCrearCliente.add(aceptarCrearCliente, gridBagCliente);

        //Panel Ayuda
        gridBagCliente = new GridBagConstraints(3, 5, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        String texto = "Menu ayuda \n" + "Ayudenme no más \n" + "Puta mierda.";
        JTextArea ayuda = new JTextArea(texto, 3, 1);
        ayuda.setEditable(false);
        panelCrearCliente.add(ayuda, gridBagCliente);


        panelCrearCliente.setVisible(false);


        //***************************************************************
        //***************************************************************


        //---------------------------------------------------------------
        //Panel Borrar Cliente
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
        //Panel Mostrar Clientes
        //---------------------------------------------------------------


        final JPanel panelMostrarClientes = new JPanel();
        panelMostrarClientes.setLayout(new GridBagLayout());

        //Añadimos una etiqueta no visible para llenar una celda y poder centrar el panel correctamente

        final JLabel relleno = new JLabel();
        gridBagCliente = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelMostrarClientes.add(relleno, gridBagCliente);


        gridBagCliente = new GridBagConstraints(0, 1, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        String[] columnNames = {"Nombre", "NIF", "Direccion", "e-mail", "Fecha Alta", "Tarifa"};
        String[][] lista = {{"Sergio", "Landeete", "aadasd", "asdasd", "Aada", "Base"}};


        final JTable tablaMostrarClientes = new JTable(lista, columnNames);


        // Object[][] data1 = modelo.datosClientes();
        // JTable tablaBuscarClientes = new JTable(data1, columnNames1);


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


        //ACEPTAR
        final JButton aceptarBuscarCliente = new JButton("Buscar");
        gridBagCliente = new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);


        panelBuscarCliente.add(aceptarBuscarCliente, gridBagCliente);
        gridBagCliente = new GridBagConstraints(0, 1, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        String[] columnNames1 = {"Nombre", "NIF", "Direccion", "e-mail", "Fecha Alta", "Tarifa"};
        String[][] lista2 = {{"Sergio", "Landeete", "aadasd", "asdasd", "Aada", "Base"}};


        //final JTable tablaMostrarClientes2 = new JTable(lista2, columnNames1);


        // Object[][] data1 = modelo.datosClientes();
        // JTable tablaBuscarClientes = new JTable(data1, columnNames1);
        JTable tablaBuscarClientes = new JTable(lista2, columnNames1);
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
        final String[] columnNames2 = {"Nombre", "NIF", "Direccion", "e-mail", "Fecha Alta", "Tarifa"};

        String[][] lista3 = {{"Sergio", "Landeete", "aadasd", "asdasd", "Aada", "Base"}};

        //Object[][] data2 = modelo.datosClientes();
        //JTable tablaClientesFecha = new JTable(data2, columnNames2);
        JTable tablaClientesFecha = new JTable(lista3, columnNames2);
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
        //ACCIONES BOTONES
        //***************************************************************

        botonCrearCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenCliente.setVisible(false);
                panelBorrarCliente.setVisible(false);
                panelMostrarClientes.setVisible(false);
                panelBuscarCliente.setVisible(false);
                panelCambiarTarifa.setVisible(false);
                panelClientesFecha.setVisible(false);
                panelCrearCliente.setVisible(true);
            }
        });


        botonBorrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenCliente.setVisible(false);
                panelCrearCliente.setVisible(false);
                panelMostrarClientes.setVisible(false);
                panelBuscarCliente.setVisible(false);
                panelCambiarTarifa.setVisible(false);
                panelClientesFecha.setVisible(false);
                panelBorrarCliente.setVisible(true);
            }
        });


        botonMostrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // panelMostrarClientes.removeAll();
                // Object[][] data = modelo.datosClientes();
                // JTable tablaMostrarClientes = new JTable(data, columnNames);
                // JScrollPane scrollPane = new JScrollPane(tablaMostrarClientes);
                // panelMostrarClientes.add(scrollPane);
                panelImagenCliente.setVisible(false);
                panelCrearCliente.setVisible(false);
                panelBorrarCliente.setVisible(false);
                panelBuscarCliente.setVisible(false);
                panelCambiarTarifa.setVisible(false);
                panelClientesFecha.setVisible(false);
                panelMostrarClientes.setVisible(true);
            }
        });

        botonMostrarPorDni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenCliente.setVisible(false);
                panelCrearCliente.setVisible(false);
                panelBorrarCliente.setVisible(false);
                panelMostrarClientes.setVisible(false);
                panelCambiarTarifa.setVisible(false);
                panelClientesFecha.setVisible(false);
                panelBuscarCliente.setVisible(true);
            }
        });

        botonCambiarTarifa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenCliente.setVisible(false);
                panelCrearCliente.setVisible(false);
                panelBorrarCliente.setVisible(false);
                panelMostrarClientes.setVisible(false);
                panelBuscarCliente.setVisible(false);
                panelClientesFecha.setVisible(false);
                panelCambiarTarifa.setVisible(true);
            }
        });

        botonClienteFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenCliente.setVisible(false);
                panelCrearCliente.setVisible(false);
                panelBorrarCliente.setVisible(false);
                panelMostrarClientes.setVisible(false);
                panelBuscarCliente.setVisible(false);
                panelCambiarTarifa.setVisible(false);
                panelClientesFecha.setVisible(true);
            }
        });

        //****
        //Botones para desactivar la entrada de apellidos si creamos una empresa
        //****

        particular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apellido.setEnabled(true);

            }
        });
        empresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apellido.setEnabled(false);

            }
        });


        //=================================================================
        //=================================================================
        //=================================================================
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

        //=========================================================================
        //Alta llamada
        //=========================================================================


        final JPanel panelAltaLlamada = new JPanel();
        panelAltaLlamada.setLayout(new GridLayout(4, 2, 10, 10));
        panelAltaLlamada.add(new JLabel("Nif del cliente: "));
        final JTextField nifAltaLlamada = new JTextField(8);
        panelAltaLlamada.add(nifAltaLlamada);
        panelAltaLlamada.add(new JLabel("Numero de telefono: "));
        final JTextField tel = new JTextField(8);
        panelAltaLlamada.add(tel);
        panelAltaLlamada.add(new JLabel("Duracion: "));
        final JTextField duracion = new JTextField(3);
        panelAltaLlamada.add(duracion);
        JButton aceptarAltaLlamada = new JButton("Aceptar");
        panelAltaLlamada.add(aceptarAltaLlamada);

        panelAltaLlamada.setVisible(false);


        //=========================================================================
        //Mostrar llamadas clientes
        //=========================================================================


        final JPanel panelLlamadasCliente = new JPanel();
        panelLlamadasCliente.setLayout(new GridBagLayout());

        //Inroduccion del NIF
        GridBagConstraints gbcLlam = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasCliente.add(new JLabel("NIF: "), gbcLlam);
        final JTextField nifCliLlam = new JTextField(20);
        gbcLlam = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasCliente.add(nifCliLlam, gbcLlam);

        //Boton ACEPTAR
        final JButton aceptarLlamadasCliente = new JButton("Aceptar");
        gbcLlam = new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasCliente.add(aceptarLlamadasCliente, gbcLlam);
        gbcLlam = new GridBagConstraints(0, 1, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        final String[] columnNamesLlamadas = {"Numero llamada", "Fecha", "Duracion"};
        Object[][] dataLlamadas = new Object[0][0];
        JTable tablaLlamadasCliente = new JTable(dataLlamadas, columnNamesLlamadas);
        JScrollPane scrollPaneLlamadas = new JScrollPane(tablaLlamadasCliente);
        panelLlamadasCliente.add(scrollPaneLlamadas, gbcLlam);

        panelLlamadasCliente.setVisible(false);

        //=========================================================================
        //Mostrar llamadas entre Fechas
        //=========================================================================


        final JPanel panelLlamadasFecha = new JPanel();
        panelLlamadasFecha.setLayout(new GridBagLayout());
        gbcLlam = new GridBagConstraints(0, 0, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
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

        gbcLlam = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasFecha.add(fecha3, gbcLlam);
        gbcLlam = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasFecha.add(new JLabel(" hasta "), gbcLlam);
        gbcLlam = new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasFecha.add(fecha4, gbcLlam);
        gbcLlam = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasFecha.add(new JLabel("NIF: "), gbcLlam);
        gbcLlam = new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        final JTextField nifLlamFechas = new JTextField(8);
        panelLlamadasFecha.add(nifLlamFechas, gbcLlam);
        gbcLlam = new GridBagConstraints(3, 1, 1, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);

        final JButton aceptarLlamadasFechas = new JButton("Aceptar");
        panelLlamadasFecha.add(aceptarLlamadasFechas, gbcLlam);
        Object[][] dataLlamadasFecha = new Object[0][0];
        JTable tablaLlamadasFecha = new JTable(dataLlamadasFecha, columnNamesLlamadas);
        JScrollPane scrollPaneLlamFecha = new JScrollPane(tablaLlamadasFecha);
        gbcLlam = new GridBagConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelLlamadasFecha.add(scrollPaneLlamFecha, gbcLlam);

        panelLlamadasFecha.setVisible(false);


        //Añadir Paneles de LLAMADAS al panel PRINCIPAL
        insets = new Insets(10, 7, 10, 7);
        gbcLlam = new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 0, 0);
        gridBagCliente = new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, insets, 0, 0);


        panelLlamada.add(panelMenuLlamada, gbcLlam);
        panelLlamada.add(panelImagenLlamada, gridBagCliente);
        panelLlamada.add(panelAltaLlamada, new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        panelLlamada.add(panelLlamadasCliente, gridBagCliente);
        panelLlamada.add(panelLlamadasFecha, gridBagCliente);


        //ACCIONES BOTONES

        botonAltaLlamada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelImagenLlamada.setVisible(false);
                panelLlamadasCliente.setVisible(false);
                panelLlamadasFecha.setVisible(false);
                panelAltaLlamada.setVisible(true);

            }
        });


        botonLlamadasCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenLlamada.setVisible(false);
                panelAltaLlamada.setVisible(false);
                panelLlamadasFecha.setVisible(false);
                panelLlamadasCliente.setVisible(true);
            }
        });


        botonLlamadasFechas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenLlamada.setVisible(false);
                panelAltaLlamada.setVisible(false);
                panelLlamadasCliente.setVisible(false);
                panelLlamadasFecha.setVisible(true);
            }
        });


        //=================================================================
        //=================================================================
        //=================================================================
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


        GridBagConstraints gbcFac = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelEmitirFactura.add(new JLabel("Fecha Emision"), gbcFac);
        gbcFac = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelEmitirFactura.add(new JLabel("Fecha Facturacion"), gbcFac);
        gbcFac = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelEmitirFactura.add(fechaE, gbcFac);
        gbcFac = new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelEmitirFactura.add(fechaE2, gbcFac);
        gbcFac = new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        JButton aceptarEmitirFactura = new JButton("Aceptar");
        panelEmitirFactura.add(aceptarEmitirFactura);

        panelEmitirFactura.setVisible(false);



        //CONSULTAR FACTURA
        final JPanel PanelConsultarFactura = new JPanel();
        PanelConsultarFactura.setLayout(new GridBagLayout());

        //INTRODUCIR NIF
        gbcFac = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        PanelConsultarFactura.add(new JLabel("Codigo Factura: "), gbcFac);
        final JTextField CodFac = new JTextField(20);
        gbcFac = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        PanelConsultarFactura.add(CodFac,gbcFac);

        //ACEPTAR
        final JButton aceptarConsultarFactura = new JButton("Aceptar");
        gbcFac = new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        PanelConsultarFactura.add(aceptarConsultarFactura, gbcFac);
        gbcFac = new GridBagConstraints(0, 1, 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        final String[] columnNamesFacturas = {"Codigo", "Tarifa", "Fecha Emision", "Fecha Facturacion", "Importe"};
        Object[][] dataFacturas = new Object[0][0];
        JTable tablaFacturasCliente = new JTable(dataFacturas, columnNamesFacturas);
        JScrollPane scrollPaneFacturas = new JScrollPane(tablaFacturasCliente);
        PanelConsultarFactura.add(scrollPaneFacturas,gbcFac);

        PanelConsultarFactura.setVisible(false);


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
        Object[][] dataFacturasFecha = new Object[0][0];
        JTable tablaFacturasFecha = new JTable(dataFacturasFecha, columnNamesFacturas);
        JScrollPane scrollPaneFacFecha = new JScrollPane(tablaFacturasFecha);
        gbcFac = new GridBagConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        panelFacturasFecha.add(scrollPaneFacFecha,gbcFac);

        panelFacturasFecha.setVisible(false);


        //A�ADIR A FACTURA
        insets = new Insets(10, 7, 10, 7);
        gbcFac =  new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, insets, 0, 0);
        panelFactura.add(panelMenuFactura,gbcFac);

        gridBagCliente = new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, insets, 0, 0);

        panelFactura.add(panelImagenFactura, gridBagCliente );
        panelFactura.add(panelEmitirFactura, new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        panelFactura.add(PanelConsultarFactura, gridBagCliente );
        panelFactura.add(panelFacturasFecha, gridBagCliente );











        //ACCIONES BOTONES
        botonEmitirFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelImagenFactura.setVisible(false);
                PanelConsultarFactura.setVisible(false);
                panelFacturasFecha.setVisible(false);
                panelEmitirFactura.setVisible(true);

            }
        });
        botonConsultarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenFactura.setVisible(false);
                panelEmitirFactura.setVisible(false);
                panelFacturasFecha.setVisible(false);
                PanelConsultarFactura.setVisible(true);
            }
        });
        botonFacturasFechas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panelImagenFactura.setVisible(false);
                panelEmitirFactura.setVisible(false);
                PanelConsultarFactura.setVisible(false);
                panelFacturasFecha.setVisible(true);
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
        //Pestanyas
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

    public void mensajes(String mensaje) {

    }

    public void llenarListaClientes() {

    }



}
