package controladorTest;

import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.NoExisteFactura;
import modelo.excepciones.NoExistenFacturasDeCliente;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.factura.Factura;
import modelo.llamada.Llamada;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.utils.DateUtils;
import modelo.utils.Periodo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.*;

public class FacturaControllerTest {

    private FacturaController facturaController;
    private Cliente clientePruebas;
    private LlamadaController llamadaController;
    private Periodo periodo;
    //Para poder tener las Llamadas y facturar

    //Antes de cada test, creamos unas cuantas llamadas
    @BeforeEach
    public void SetUp() {
        String nombre = "Nombre";
        String nif = "00000000A";
        Direccion direccion = new Direccion();
        String email = "email@email.com";
        LocalDateTime fechaAlta = LocalDateTime.of( 2018, 1, 1, 0, 0);
        Tarifa tarifa = new TarifaBasica();
        clientePruebas = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);

        llamadaController = new LlamadaController();
        facturaController = new FacturaController( llamadaController );
        //Llenar con unas cuantas llamadas el controlador de llamadas

        Llamada nueva = new Llamada();
        nueva.setDuracion( 10f );
        llamadaController.altaLlamada( clientePruebas, nueva);
        nueva = new Llamada();
        nueva.setDuracion(30f);
        llamadaController.altaLlamada( clientePruebas, nueva);
        nueva = new Llamada();
        nueva.setDuracion( 5f );
        llamadaController.altaLlamada( clientePruebas, nueva);

        LocalDateTime inicio = LocalDateTime.of(2018, 1, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2018, 6, 1, 0, 0);
        periodo = new Periodo();
        periodo.setInicio(inicio);
        periodo.setFin(fin);

    }

    @AfterEach
    public void TearDown() {
        clientePruebas = null;
        llamadaController = null;
        facturaController = null;

    }

    @Test
    public void emitirFacturaTest() {

        Factura factura = null;
        try {
            factura = facturaController.emitirFactura(periodo, clientePruebas);
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
        assertThat( factura, notNullValue() );

        String nombre = "Nombre";
        String nif = "00000000B";
        Direccion direccion = new Direccion();
        String email = "email@email.com";
        LocalDateTime fechaAlta = LocalDateTime.of( 2018, 1, 1, 0, 0);
        Tarifa tarifa = new TarifaBasica();
        Cliente clienteInexistente = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);

        Factura factura_2 = null;
        try {
            factura_2 = facturaController.emitirFactura( periodo, clienteInexistente);
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            System.err.println( clienteNoExiste.getMessage() );

        }
        //No se puede emitir una factura a un cliente que no está en la base de datos
        assertThat( factura_2, nullValue() );
    }

    @Test
    public void listarFacturasTest() {
        List<Factura> lista = null;
        try {
            lista = facturaController.getFacturasCliente(clientePruebas);
        } catch (NoExistenFacturasDeCliente noExistenFacturasDeCliente) {
            System.err.println( noExistenFacturasDeCliente.getMessage() );

        }
        assertThat( lista, nullValue() );
        try {
            facturaController.emitirFactura( periodo, clientePruebas);
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
        try {
            facturaController.emitirFactura( periodo, clientePruebas);
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
        try {
            assertThat( facturaController.getFacturasCliente(clientePruebas).size(), is(2));
        } catch (NoExistenFacturasDeCliente noExistenFacturasDeCliente) {
            System.err.println( noExistenFacturasDeCliente.getMessage() );
        }
    }

    @Test
    public void getFacturaTest() {
        try {
            assertThat( facturaController.getFactura("000"), is( (Object) null ) );
        } catch (NoExisteFactura noExisteFactura) {
            System.err.println( noExisteFactura.getMessage());
        }
        Factura factura = null;
        try {
            factura = facturaController.emitirFactura(periodo, clientePruebas);
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
        try {
            assertThat( facturaController.getFactura(factura.getUniqueID()), is( factura ) );
        } catch (NoExisteFactura noExisteFactura) {
            noExisteFactura.printStackTrace();
        }
    }
    @Test
    public void entreFechasTest() {
        //Creamos dos facturas de un cliente y cambiamos la fecha de emisión para poder filtrar
        try {
            facturaController.emitirFactura( periodo, clientePruebas );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }
        try {
            facturaController.emitirFactura( periodo, clientePruebas );
        } catch (NoHayLlamadasCliente noHayLlamadasCliente) {
            noHayLlamadasCliente.printStackTrace();
        } catch (ClienteNoExiste clienteNoExiste) {
            clienteNoExiste.printStackTrace();
        }

        LocalDateTime desde = LocalDateTime.of(2017, 12, 30,0,0) ;
        LocalDateTime hasta = LocalDateTime.of(2018, 8, 1,0,0) ;

        try {
            assertThat( facturaController.facturasEntreFechas( clientePruebas, desde, hasta).size() , is(2) );
        } catch (NoExistenFacturasDeCliente noExistenFacturasDeCliente) {
            noExistenFacturasDeCliente.printStackTrace();
        }

        //Filtramos en una fecha que no coge la fecha de emisión
        desde = LocalDateTime.of(2019, 4, 1,0,0) ;
        hasta = LocalDateTime.of(2019, 4, 4,0,0) ;

        try {
            assertThat( facturaController.facturasEntreFechas( clientePruebas, desde, hasta).size() , is(0) );
        } catch (NoExistenFacturasDeCliente noExistenFacturasDeCliente) {
            noExistenFacturasDeCliente.printStackTrace();
        }


    }
}
