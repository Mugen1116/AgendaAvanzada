package controladorTest;

import controlador.factura.GestorFacturas;
import controlador.llamada.GestorLlamadas;
import modelo.cliente.Cliente;
import modelo.excepciones.ClienteNoExiste;
import modelo.excepciones.NoExistenFacturasDeCliente;
import modelo.excepciones.NoHayLlamadasCliente;
import modelo.factoria.FactoriaClientes;
import modelo.factoria.FactoriaTarifas;
import modelo.factura.Factura;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.tarifa.TarifaDomingos;
import modelo.tarifa.TarifaTardes;
import modelo.utils.Periodo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PatronesTest extends TestPadre {


    private GestorFacturas gestorFacturas;
    private GestorLlamadas gestorLlamadas;
    //IMPORTANTE
    //EL TEST DEL PATRON DECORADOR ESTA IMPLEMENTADO EN LA CLASE
    //GESTORFACTURASTEST PORQUE SE TIENEN ALLI LOS OBJETOS CON LOS QUE OPERAR

    //Constantes de tarifas

    static final String BASICA = "Tarifa base de 15 cts/min";
    static final String TARDES = "Tarifa base de 15 cts/min + Tarifa de tardes, de 16:00 a 20:00 horas a 0.05 centimos/min";
    static final String DOMINGOS = "Tarifa base de 15 cts/min + Tarifa de domingos gratis";
    static final String TARDES_Y_DOMINGOS = "Tarifa base de 15 cts/min + Tarifa de tardes, de 16:00 a 20:00 horas a 0.05 centimos/min + Tarifa de domingos gratis";
    static final String DOMINGOS_Y_TARDES = "Tarifa base de 15 cts/min + Tarifa de domingos gratis + Tarifa de tardes, de 16:00 a 20:00 horas a 0.05 centimos/min";



    @BeforeAll
    public static void InitAll(){
        factoriaTarifas = new FactoriaTarifas();
        factoriaClientes = new FactoriaClientes();
    }
    @BeforeEach
    public void SetUp() {
        gestorLlamadas = new GestorLlamadas();
        gestorFacturas = new GestorFacturas( gestorLlamadas );
    }


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


    //Aunque el comportamiento del decorador se prueba en el siguiente test vamos a probar uno más simple pero igualmente válido
     @Test
     public void decoradorTarifasTest() {
        Tarifa tarifa = new TarifaBasica();
        //Partimos de una tarifa base
         assertThat( tarifa.toString(), is(BASICA));
         //Ahora la decoramos primero con una tarifa de Domingos
         tarifa = new TarifaDomingos(tarifa, 0f);
         assertThat( tarifa.toString(), is ( DOMINGOS ));
         //Si ahora le añadimos la de tardes, tendrá las dos a la vez
         tarifa = new TarifaTardes( tarifa, 0.05f );
         assertThat( tarifa.toString(), is( DOMINGOS_Y_TARDES ));

     }



}
