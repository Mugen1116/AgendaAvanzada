package modelo.factoria;

import modelo.cliente.*;
import modelo.tarifa.*;

public interface FactoriaInterface {
    static final int PARTICULAR = 1;
    static final int EMPRESA = 2;
    //Por simplicidad vamos a utilizar la misma interfaz para los dos propósitos y así tener una única factoria/fabrica de objetos
    static final int BASICA = 3;
    static final int TARDES = 4;
    static final int DOMINGOS = 5;
    static final int TARDES_Y_DOMINGOS = 6;

    Cliente creaCliente( int tipoCliente );
    Tarifa creaTarifa ( int tipoTarifa);
}
