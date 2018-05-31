package modelo.factoria;

import modelo.cliente.Cliente;

public interface FactoriaClientesInterface {
    static final int PARTICULAR = 1;
    static final int EMPRESA = 2;

    Cliente creaCliente(int tipoCliente );

    Cliente creaCliente ( int tipoCliente, Cliente inicial);
}
