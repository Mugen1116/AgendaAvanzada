package modelo.factoria;

import modelo.cliente.*;
import modelo.tarifa.*;

public interface FactoriaTarifasInterface {

    static final int BASICA = 3;
    static final int TARDES = 4;
    static final int DOMINGOS = 5;
    static final int TARDES_Y_DOMINGOS = 6;

    Tarifa creaTarifa ( int tipoTarifa);
}
