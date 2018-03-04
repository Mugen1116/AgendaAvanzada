package controladorTest;

import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.tarifa.Tarifa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uji.belfern.generador.GeneradorDatosINE;

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
    public void InsertsTest(){
        //Insertar 10 clientes aleatorios
        for ( int i = 0; i < 10; i++ )
            assertThat( controlador.altaCliente( ClienteNuevo() ), is(true) );
        //Intentar insertar un cliente dos veces
        //La segunda debe devolver falso
        Cliente prueba = ClienteNuevo();
        String dniPrueba = "46076356L";
        prueba.setNIF( dniPrueba );
        controlador.altaCliente(prueba);
        prueba = ClienteNuevo();
        prueba.setNIF( dniPrueba );
        assertThat(controlador.altaCliente( prueba), is(false) );
        System.out.println(controlador.listarClientes());

    }



    //Luego otro de borrados hasta quedarse en 0, y borrar cuando no queden clientes

    @Test
    public void DeleteTest(){
        //Primero insertar clientes
        for ( int i = 0; i < 10; i++ )
            assertThat( controlador.altaCliente( ClienteNuevo() ), is(true) );

        Cliente ultimoBorrado = new Cliente();
        //Ahora borar más de los que hay
       List<Cliente> clientes = controlador.listarClientes();

        for( Cliente cliente:clientes ){
            ultimoBorrado = cliente;
            assertThat( controlador.bajaCliente(cliente), is(true) ) ;
        }
        //Intentar borrar un cliente que no existe en el diccionario
       assertThat( controlador.bajaCliente( ultimoBorrado ), is(false));
    }

    @Test
    public void ChangeTarifaTest(){
        Cliente nuevo = ClienteNuevo();
        assertThat( controlador.altaCliente( nuevo), is(true));
        assertThat( controlador.cambiarTarifa(nuevo, new Tarifa()), is(true));
        //Cambiar tarifa a cliente que no exista

        assertThat( controlador.cambiarTarifa( ClienteNuevo(), new Tarifa()), is(false));

    }

}
