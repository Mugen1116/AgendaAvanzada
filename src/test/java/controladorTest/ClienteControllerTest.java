package controladorTest;

import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.excepciones.*;
import modelo.tarifa.Tarifa;
import modelo.utils.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uji.belfern.generador.GeneradorDatosINE;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClienteControllerTest {

    private ClienteController controlador;
    private GeneradorDatosINE generador;

    //Antes de lanzar cada test es necesario crear un nuevo controlador
    //Para no ir machacando el existente
    @BeforeEach
    public void SetUp(){
        controlador = new ClienteController();
        generador = new GeneradorDatosINE();
    }
    //Despues de cada test lo destruimos para asegurarnos de no estar reutilizando el que ya haya
    @AfterEach
    public void TearDown() {
        controlador = null;
    }

    //Ahora comienzan cada uno de los tests que se vayan a crear

    //Primero uno de multiples inserciones
    //Insertar clientes repetidos
    //INsertar clientes que no existan

    //Metodo auxiliar crear un cliente Random
    public Cliente ClienteNuevo(){

        Cliente nuevo = new Cliente();
        nuevo.setNIF( generador.getNIF() );
        nuevo.setNombre( generador.getNombre());
        nuevo.setFechaAlta( new Date() );
        nuevo.setTarifa( new Tarifa() );
        //Como no se pueden generar random, ponemos manualmente
        Direccion direction = new Direccion( 46520, "Valencia" , "Puerto de Sagunto");
        nuevo.setDireccion(direction);
        nuevo.setEmail( "patatita@uji.es" );

        return nuevo;
    }
    //Insertar clientes repetidos
    @Test
    public void insertsTest() throws NoHayClientes {
        //Insertar 10 clientes aleatorios
        for ( int i = 0; i < 10; i++ ) {
            try {
                assertThat( controlador.altaCliente( ClienteNuevo() ), is(true) );
            } catch (ClienteExistente clienteExistente) {
                clienteExistente.printStackTrace();
            }
        }
        //Intentar insertar un cliente dos veces
        //La segunda debe devolver falso
        Cliente prueba = ClienteNuevo();
        String dniPrueba = "46076356L";
        prueba.setNIF( dniPrueba );
        try {
            controlador.altaCliente(prueba);
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }
        prueba = ClienteNuevo();
        prueba.setNIF( dniPrueba );
        try {
            assertThat(controlador.altaCliente( prueba), is(false) );
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }
        System.out.println(controlador.listarClientes());

    }



    //Luego otro de borrados hasta quedarse en 0, y borrar cuando no queden clientes

    @Test
    public void deleteTest() throws NoHayClientes {
        //Primero insertar clientes
        for ( int i = 0; i < 10; i++ ) {
            try {
                assertThat( controlador.altaCliente( ClienteNuevo() ), is(true) );
            } catch (ClienteExistente clienteExistente) {
                clienteExistente.printStackTrace();
            }
        }

        Cliente ultimoBorrado = new Cliente();
        //Ahora borar más de los que hay
       List<Cliente> clientes = controlador.listarClientes();

        for( Cliente cliente:clientes ){
            ultimoBorrado = cliente;
            try {
                assertThat( controlador.bajaCliente(cliente), is(true) ) ;
            } catch (ClienteNoExiste clienteNoExiste) {
                clienteNoExiste.printStackTrace();
            }
        }
        //Intentar borrar un cliente que no existe en el diccionario
        try {
            assertThat( controlador.bajaCliente( ultimoBorrado ), is(false));
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
    }

    @Test
    public void changeTarifaTest(){
        Cliente nuevo = ClienteNuevo();
        try {
            assertThat( controlador.altaCliente( nuevo), is(true));
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }
        assertThat( controlador.cambiarTarifa(nuevo, new Tarifa()), is(true));
        //Cambiar tarifa a cliente que no exista

        assertThat( controlador.cambiarTarifa( ClienteNuevo(), new Tarifa()), is(false));

    }
    @Test
    public void entreFechasTest(){
        LocalDate fechaAlta = LocalDate.of( 2018, 1, 1);
        LocalDate fechaAlta2 = LocalDate.of( 2018, 2, 12);

        Cliente nuevo = ClienteNuevo();
        nuevo.setFechaAlta( DateUtils.asDate(fechaAlta) );
        try {
            controlador.altaCliente( nuevo );
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }

        Cliente nuevo2 = ClienteNuevo();
        nuevo2.setFechaAlta( DateUtils.asDate(fechaAlta2) );
        try {
            controlador.altaCliente( nuevo2 );
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }


        Date inicio = DateUtils.asDate( LocalDate.of(2017, 12, 30) );
        Date fin = DateUtils.asDate( LocalDate.of(2018, 4, 1) );


        //Ahora debería mostrar los dos clientes que se han creado, es decir
        //La lista tendrá 2 elementos
        try {
            assertThat(controlador.clientesEntreFechas(inicio, fin).size(), is(2));
        }
        catch ( FechaInvalida e) {
            e.getMessage();
        } catch (NoHayClientesEntreFechas noHayClientesEntreFechas) {
            noHayClientesEntreFechas.printStackTrace();
        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }

        //Si probamos entre dos fechas que no coja ningun cliente
        inicio = DateUtils.asDate( LocalDate.of(2018, 12, 30) );
        fin = DateUtils.asDate( LocalDate.of(2019, 4, 1) );
        try {
            assertThat(controlador.clientesEntreFechas(inicio, fin).size(), is(0));
        }
        catch ( FechaInvalida e) {
            e.getMessage();
        } catch (NoHayClientesEntreFechas noHayClientesEntreFechas) {
            noHayClientesEntreFechas.printStackTrace();
        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }

        //Que solo coja a un cliente
        inicio = DateUtils.asDate( LocalDate.of(2018, 1, 30) );
        fin = DateUtils.asDate( LocalDate.of(2019, 4, 1) );
        try {
            assertThat(controlador.clientesEntreFechas(inicio, fin).size(), is(1));
        }
        catch ( FechaInvalida e) {
            e.getMessage();
        } catch (NoHayClientesEntreFechas noHayClientesEntreFechas) {
            noHayClientesEntreFechas.printStackTrace();
        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }


    }

}
