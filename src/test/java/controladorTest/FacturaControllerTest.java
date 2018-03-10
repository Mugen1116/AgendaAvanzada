package controladorTest;

import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.factura.Factura;
import modelo.llamada.Llamada;
import modelo.tarifa.Tarifa;
import modelo.utils.Periodo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

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
        LocalDate alta = LocalDate.of( 2018, 1, 1);
        Date date = Date.from(alta.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaAlta = date;
        Tarifa tarifa = new Tarifa(0.05f);
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

        LocalDate inicio = LocalDate.of(2018, 1, 1);
        LocalDate fin = LocalDate.of(2018, 6, 1);
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

        Factura factura =  facturaController.emitirFactura(periodo, clientePruebas) ;
        assertThat( factura, notNullValue() );

        String nombre = "Nombre";
        String nif = "00000000B";
        Direccion direccion = new Direccion();
        String email = "email@email.com";
        LocalDate alta = LocalDate.of( 2018, 1, 1);
        Date date = Date.from(alta.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaAlta = date;
        Tarifa tarifa = new Tarifa(0.05f);
        Cliente clienteInexistente = new Cliente(nombre, nif, direccion, email, fechaAlta, tarifa);

        Factura factura_2 = facturaController.emitirFactura( periodo, clienteInexistente);
        assertThat( factura_2, notNullValue() );
    }

    @Test
    public void listarFacturasTest() {
        List<Factura> lista = facturaController.getFacturasCliente(clientePruebas);
        assertThat( lista.isEmpty(), is(true) );
        facturaController.emitirFactura( periodo, clientePruebas);
        facturaController.emitirFactura( periodo, clientePruebas);
        assertThat( facturaController.getFacturasCliente(clientePruebas).size(), is(2));
    }

    @Test
    public void getFacturaTest() {
        assertThat( facturaController.getFactura("000"), is( (Object) null ) );
        Factura factura = facturaController.emitirFactura(periodo, clientePruebas);
        assertThat( facturaController.getFactura(factura.getUniqueID()), is( factura ) );
    }
}
