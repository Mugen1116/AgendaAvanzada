package modeloTest.cliente;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestParticular {

    private Particular cliente;
    @BeforeEach
    public void SetUp(){
        cliente = new Particular();
    }
    @AfterEach
    public void TearDown(){
        cliente = null;
    }

    @Test
    public void ParticularesTest() {

        System.out.println(cliente);
        /*System.out.println("Apellidos: " +  particular.getApellidos() );*/
        assertThat( cliente, notNullValue() );

    }

}
