package modelo.direccion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestDireccion {

    private Direccion direccion;

    @BeforeEach
    public void SetUp(){ direccion = new Direccion();}
    @AfterEach
    public void TearDown() { direccion = null; }

    @Test
    public void DireccionesTest(){
        assertThat( direccion, notNullValue() );
    }
}
