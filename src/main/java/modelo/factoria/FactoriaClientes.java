package modelo.factoria;

import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;

public class FactoriaClientes implements FactoriaClientesInterface {
    @Override
    public Cliente creaCliente(int tipoCliente) {
        Cliente cliente = null;
        switch ( tipoCliente ){
            case FactoriaClientesInterface.PARTICULAR :
                cliente = new Particular();
                break;
            case FactoriaClientesInterface.EMPRESA :
                cliente = new Empresa();
                break;
            default:
                cliente = new Particular();
                break;
        }
        return cliente;
    }

    @Override
    public Cliente creaCliente(int tipoCliente, Cliente inicial) {
        Cliente cliente = null;
        switch ( tipoCliente ){
            case FactoriaClientesInterface.PARTICULAR :
                cliente = new Particular( inicial );
                break;
            case FactoriaClientesInterface.EMPRESA :
                cliente = new Empresa( inicial );
                break;
            default:
                cliente = new Particular();
                break;
        }
        return cliente;
    }
}
