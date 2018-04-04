package controladorTest;

import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.FechaInvalida;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.excepciones.NoHayLlamadasEntreFechas;
import modelo.llamada.Llamada;
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
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class LlamadaControllerTest {

    private LlamadaController controlador;
    private Cliente cliente;
    private Cliente cliente2;


    @BeforeEach
    public void SetUp(){
        controlador = new LlamadaController();
        String nombre = "nombre";
        String nif = "00000000A";
        Direccion direccion = new Direccion();
        String email = "email";
        Date fechaAlta = new Date();
        Tarifa tarifa = new Tarifa(0.0f);

        cliente = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);
        cliente2 = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);
    }

    @AfterEach
    public void TakeDown(){
        controlador = null;
    }

    @Test
    public void altaLlamadaTest(){
        Llamada nueva = new Llamada();

        assertThat(controlador.altaLlamada(cliente, nueva), is(true));
        assertThat(controlador.altaLlamada(cliente2, nueva), is(true));
    }

    @Test
    public void listaLlamadasTest(){

        LinkedList<Llamada> lista = new LinkedList<Llamada>();
        LinkedList<Llamada> lista2 = new LinkedList<Llamada>();
        Llamada nueva = new Llamada();
        lista.add(nueva);
        controlador.altaLlamada(cliente, nueva);

        try {
            assertThat(controlador.listaLlamadas(cliente).equals(lista), is(true));
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
        try {
            assertThat(controlador.listaLlamadas(cliente).equals(lista2), is (false));
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
    }

    @Test
    public void entreFechasTest(){

        //Registraremos dos llamadas para probar
        Llamada llamada = new Llamada();
        llamada.setDiaHora( DateUtils.asDate( LocalDate.of(2018, 1, 30) ) );
        controlador.altaLlamada( cliente, llamada );

        Llamada llamada2 = new Llamada();
        llamada2.setDiaHora( DateUtils.asDate( LocalDate.of(2018, 2, 20) ) );
        controlador.altaLlamada( cliente, llamada2  );

        //Llamadas realizadas entre dos fechas
        Date desde = DateUtils.asDate( LocalDate.of(2017, 12, 30) );
        Date hasta = DateUtils.asDate( LocalDate.of(2018, 4, 1) );

        //Probamos a filtrar entre fechas que sean validas
        try {
            assertThat( controlador.llamadasEntreFechas(cliente, desde, hasta).size() , is(2) );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (FechaInvalida fechaInvalida) {
            fechaInvalida.printStackTrace();
        } catch (NoHayLlamadasEntreFechas noHayLlamadasEntreFechas) {
            noHayLlamadasEntreFechas.printStackTrace();
        }

        //Probamos a coger una sola llamada
        desde = DateUtils.asDate( LocalDate.of(2018, 1, 15) );
        hasta = DateUtils.asDate( LocalDate.of(2018, 2, 1) );
        try {
            assertThat( controlador.llamadasEntreFechas(cliente, desde, hasta).size() , is(1) );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (FechaInvalida fechaInvalida) {
            fechaInvalida.printStackTrace();
        } catch (NoHayLlamadasEntreFechas noHayLlamadasEntreFechas) {
            noHayLlamadasEntreFechas.printStackTrace();
        }

        //Probamos a no coger ninguna
        desde = DateUtils.asDate( LocalDate.of(2018, 3, 15) );
        hasta = DateUtils.asDate( LocalDate.of(2018, 4, 1) );
        try {
            assertThat( controlador.llamadasEntreFechas(cliente, desde, hasta).size() , is(0) );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (FechaInvalida fechaInvalida) {
            fechaInvalida.printStackTrace();
        } catch (NoHayLlamadasEntreFechas noHayLlamadasEntreFechas) {
            noHayLlamadasEntreFechas.printStackTrace();
        }

    }
}
