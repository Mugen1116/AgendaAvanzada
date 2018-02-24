import cliente.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsNull.notNullValue;


public class TestMain {

    private Cliente cliente;

    @BeforeEach
    public void SetUp(){
        cliente = new Cliente();
    }
    @AfterEach
    public void TearDown(){
        cliente = null;
    }

   @Test
   public void ClientesTest() {
        assertThat( cliente, notNullValue() );

    }
}
