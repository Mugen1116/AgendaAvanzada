package controladorTest;

import controlador.factura.FacturaController;
import controlador.llamada.LlamadaController;
import modelo.cliente.Cliente;
import modelo.direccion.Direccion;
import modelo.llamada.Llamada;
import modelo.tarifa.Tarifa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

public class FacturaControllerTest {

    private FacturaController facturaController;
    private Cliente clientePruebas;
    private LlamadaController llamadaController;
    //Para poder tener las Llamadas y facturar

    //Antes de cada test, creamos unas cuantas llamadas
    @BeforeEach
    public void SetUp() {
        String nombre = "Nombre";
        String nif = "00000000A";
        Direccion direccion = new Direccion();
        String email = "email@email.com";
        Date fechaAlta = new Date();
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
    }

    @AfterEach
    public void TearDown() {
        clientePruebas = null;
        llamadaController = null;
        facturaController = null;

    }

}
