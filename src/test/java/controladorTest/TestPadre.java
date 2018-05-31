package controladorTest;

import es.uji.belfern.generador.GeneradorDatosINE;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.factoria.FactoriaClientes;
import modelo.factoria.FactoriaTarifas;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.utils.Periodo;

import java.time.LocalDateTime;

public class TestPadre {


    GeneradorDatosINE generador;
    public static FactoriaTarifas factoriaTarifas;
    public static FactoriaClientes factoriaClientes;

    TestPadre() {
        generador = new GeneradorDatosINE();
        factoriaClientes = new FactoriaClientes();
        factoriaTarifas = new FactoriaTarifas();
    }

    public Periodo iniciaPeriodo(){
        LocalDateTime inicio = LocalDateTime.of(2018, 1, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2018, 6, 1, 0, 0);
        Periodo periodo = new Periodo();
        periodo.setInicio(inicio);
        periodo.setFin(fin);
        return periodo;
    }

    public Cliente creaClientePruebas() {

            Cliente nuevo = new Cliente();
            nuevo.setNIF( generador.getNIF() );
            nuevo.setNombre( generador.getNombre());
            nuevo.setFechaAlta( LocalDateTime.now() );
            nuevo.setTarifa( new TarifaBasica() );
            //Como no se pueden generar random, ponemos manualmente
            Direccion direction = new Direccion( 46520, "Valencia" , "Puerto de Sagunto");
            nuevo.setDireccion(direction);
            nuevo.setEmail( "patatita@uji.es" );
            nuevo.setTarifa( factoriaTarifas.creaTarifa( FactoriaTarifas.BASICA) );

            return nuevo;
    }


}
