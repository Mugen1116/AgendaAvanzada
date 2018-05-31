package controladorTest;

import controlador.cliente.GestorClientes;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.excepciones.*;
import modelo.factoria.FactoriaClientes;
import modelo.factoria.FactoriaTarifas;
import modelo.tarifa.TarifaBasica;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uji.belfern.generador.GeneradorDatosINE;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GestorClientesTest  extends TestPadre {

    private GestorClientes controlador;
    private GeneradorDatosINE generador;

    /*
    private static FactoriaTarifas factoriaTarifas;
    private static FactoriaClientes factoriaClientes;

    //Constantes de tarifas

    static final String BASICA = "Tarifa base de 15 cts/min";
    static final String TARDES = "Tarifa base de 15 cts/min + Tarifa de tardes, de 16:00 a 20:00 horas a 0.05 centimos/min";
    static final String DOMINGOS = "Tarifa base de 15 cts/min + Tarifa de domingos gratis";
    static final String TARDES_Y_DOMINGOS = "Tarifa base de 15 cts/min + Tarifa de tardes, de 16:00 a 20:00 horas a 0.05 centimos/min + Tarifa de domingos gratis";

    */

//    @BeforeAll
//    public static void InitAll(){
//        factoriaTarifas = new FactoriaTarifas();
//        factoriaClientes = new FactoriaClientes();
//    }


    //Antes de lanzar cada test es necesario crear un nuevo controlador
    //Para no ir machacando el existente
    @BeforeEach
    public void SetUp(){
        controlador = new GestorClientes();
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

    //Insertar clientes repetidos
    @Test
    public void insertsTest() throws NoHayClientes {
        //Insertar 10 clientes aleatorios
        for ( int i = 0; i < 10; i++ ) {
            try {
                assertThat( controlador.altaCliente( creaClientePruebas() ), is(true) );
            } catch (ClienteExistente clienteExistente) {
                clienteExistente.printStackTrace();
            }
        }
        //Intentar insertar un cliente dos veces
        //La segunda debe devolver falso
        Cliente prueba = creaClientePruebas();
        String dniPrueba = "46076356L";
        prueba.setNIF( dniPrueba );
        try {
            controlador.altaCliente(prueba);
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }
        prueba = creaClientePruebas();
        prueba.setNIF( dniPrueba );
        try {
            assertThat(controlador.altaCliente( prueba), is(false) );
        } catch (ClienteExistente clienteExistente) {
            System.err.println( clienteExistente.getMessage());
        }

    }



    //Luego otro de borrados hasta quedarse en 0, y borrar cuando no queden clientes

    @Test
    public void deleteTest() throws NoHayClientes {
        //Primero insertar clientes
        for ( int i = 0; i < 10; i++ ) {
            try {
                assertThat( controlador.altaCliente( creaClientePruebas() ), is(true) );
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
            controlador.bajaCliente( ultimoBorrado );
        } catch (ClienteNoExiste clienteNoExiste) {
            assertThat( true, is(true) );
            System.err.println( clienteNoExiste.getMessage() );
            //Si lanza la excepcion pasa el test
        }
        catch (NoHayClientes noHayClientes ){
            assertThat( true, is(true) );
            System.err.println( noHayClientes.getMessage() );
            //Si lanza la excepcion pasa el test

        }
    }

    @Test
    public void changeTarifaTest(){
        Cliente nuevo = creaClientePruebas();
        try {
            assertThat( controlador.altaCliente( nuevo), is(true));
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }
        assertThat( controlador.cambiarTarifa(nuevo , FactoriaTarifas.BASICA ), is(true));
        //Cambiar tarifa a cliente que no exista

        assertThat( controlador.cambiarTarifa( creaClientePruebas(), FactoriaTarifas.BASICA), is(false));

    }
    @Test
    public void entreFechasTest(){
        LocalDateTime fechaAlta = LocalDateTime.of( 2018, 1, 1, 0, 0);
        LocalDateTime fechaAlta2 = LocalDateTime.of( 2018, 2, 12, 0 ,0);

        Cliente nuevo = creaClientePruebas();
        nuevo.setFechaAlta( fechaAlta );
        try {
            controlador.altaCliente( nuevo );
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }

        Cliente nuevo2 = creaClientePruebas();
        nuevo2.setFechaAlta( fechaAlta2 );
        try {
            controlador.altaCliente( nuevo2 );
        } catch (ClienteExistente clienteExistente) {
            clienteExistente.printStackTrace();
        }


        LocalDateTime inicio = LocalDateTime.of(2017, 12, 30, 0 , 0) ;
        LocalDateTime fin = LocalDateTime.of(2018, 4, 1 ,0,0 ) ;


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
        inicio =  LocalDateTime.of(2018, 12, 30, 0 , 0) ;
        fin = LocalDateTime.of(2019, 4, 1, 0, 0) ;
        try {
            assertThat(controlador.clientesEntreFechas(inicio, fin).size(), is(0));
        }
        catch ( FechaInvalida e) {
            e.getMessage();
        } catch (NoHayClientesEntreFechas noHayClientesEntreFechas) {
            System.err.println( noHayClientesEntreFechas.getMessage());
        } catch (NoHayClientes noHayClientes) {
            noHayClientes.printStackTrace();
        }

        //Que solo coja a un cliente

        inicio =  LocalDateTime.of(2018, 1, 30, 0 , 0) ;
        fin = LocalDateTime.of(2019, 4, 1, 0, 0) ;
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

    /*
    @Test
    public void factoriaTarifasTest() {
        Cliente clientePruebas = new Cliente();
        assertThat( clientePruebas.getTarifa().toString(), is (BASICA));
        //Le añadimos la tarifa de TARDES a la que ya tenía
        clientePruebas.setTarifa( factoriaTarifas.creaTarifa( FactoriaTarifas.TARDES) );
        assertThat( clientePruebas.getTarifa().toString(), is (TARDES));
        //Ahora la cambiamos por solo de domingos (+ la básica)
        clientePruebas.setTarifa( factoriaTarifas.creaTarifa( FactoriaTarifas.TARDES_Y_DOMINGOS));
        assertThat( clientePruebas.getTarifa().toString(), is(TARDES_Y_DOMINGOS));
        //Volvemos a dejarle la básica
        clientePruebas.setTarifa( factoriaTarifas.creaTarifa(FactoriaTarifas.BASICA) );
        assertThat( clientePruebas.getTarifa().toString(), is(BASICA) );
        //Y ahora que tenga las 3 al mismo tiempo
        clientePruebas.setTarifa( factoriaTarifas.creaTarifa(FactoriaTarifas.TARDES_Y_DOMINGOS) );
        assertThat( clientePruebas.getTarifa().toString(), is(TARDES_Y_DOMINGOS));
    }
    @Test
    public void factoriaClientesTest(){
        Cliente clientePruebas = new Cliente();
        assertThat( clientePruebas.getTipo(), is( "Generico"));
        clientePruebas = factoriaClientes.creaCliente( FactoriaClientes.PARTICULAR);
        assertThat( clientePruebas.getTipo(), is("Particular"));
        clientePruebas = factoriaClientes.creaCliente( FactoriaClientes.EMPRESA);
        assertThat( clientePruebas.getTipo(), is("Empresa"));
    }
    */
}
