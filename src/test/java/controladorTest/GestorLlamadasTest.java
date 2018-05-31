package controladorTest;

import controlador.llamada.GestorLlamadas;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.FechaInvalida;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.excepciones.NoHayLlamadasEntreFechas;
import modelo.llamada.Llamada;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class GestorLlamadasTest {

    private GestorLlamadas controlador;
    private Cliente cliente;
    private Cliente cliente2;


    @BeforeEach
    public void SetUp(){
        controlador = new GestorLlamadas();
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
        LocalDateTime date = LocalDateTime.of(2018, 1, 30, 12, 50);
        llamada.setDiaHora( date );
        controlador.altaLlamada( cliente, llamada );

        Llamada llamada2 = new Llamada();
        llamada2.setDiaHora(  LocalDateTime.of(2018, 1, 30, 22, 50) );
        controlador.altaLlamada( cliente, llamada2  );

        //Llamadas realizadas entre dos fechas
        LocalDateTime desde = LocalDateTime.of(2017, 12, 30, 0, 0) ;
        LocalDateTime hasta = LocalDateTime.of(2018, 4, 1, 0, 0 ) ;

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
        desde =  LocalDateTime.of(2018, 1, 15, 0 ,0 ) ;
        hasta =  LocalDateTime.of(2018, 1, 30, 22 , 0) ;
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
        desde =  LocalDateTime.of(2018, 3, 15, 0 ,0 ) ;
        hasta =  LocalDateTime.of(2018, 4, 1, 0 , 0) ;
        try {
            assertThat( controlador.llamadasEntreFechas(cliente, desde, hasta).size() , is(0) );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (FechaInvalida fechaInvalida) {
            fechaInvalida.printStackTrace();
        } catch (NoHayLlamadasEntreFechas noHayLlamadasEntreFechas) {
            System.err.println(noHayLlamadasEntreFechas.getMessage());

        }

    }
}
