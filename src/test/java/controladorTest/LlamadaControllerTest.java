package controladorTest;

import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.llamada.Llamada;
import modelo.tarifa.Tarifa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uji.belfern.generador.GeneradorDatosINE;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class LlamadaControllerTest {

    private LlamadaController controlador;

    @BeforeEach
    public void SetUp(){
        controlador = new LlamadaController();
        String nombre = "nombre";
        String nif = "00000000A";
        Direccion direccion = new Direccion();
        String email = "email";
        Date fechaAlta = new Date();
        Tarifa tarifa = new Tarifa(0.0f);

        Cliente cliente = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);
        Cliente cliente2 = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);
    }

    @AfterEach
    public void TakeDown(){
        controlador = null;
    }

    @Test
    public void altaLlamadaTest(Cliente cliente, Cliente cliente2){
        Llamada nueva = new Llamada();

        assertThat(controlador.altaLlamada(cliente, nueva), is(true));
        assertThat(controlador.altaLlamada(cliente2, nueva), is(false));
    }

    @Test
    public void listaLlamadasTest(Cliente cliente){

        LinkedList<Llamada> lista = new LinkedList<Llamada>();
        LinkedList<Llamada> lista2 = new LinkedList<Llamada>();
        Llamada nueva = new Llamada();
        lista.add(nueva);
        controlador.altaLlamada(cliente, nueva);

        assertThat(controlador.listaLlamadas(cliente) == lista, is(true));
        assertThat(controlador.listaLlamadas(cliente) == lista2, is (false));
    }
}
