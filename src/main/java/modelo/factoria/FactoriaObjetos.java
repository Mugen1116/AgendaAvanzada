package modelo.factoria;

import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.tarifa.TarifaDomingos;
import modelo.tarifa.TarifaTardes;

public class FactoriaObjetos implements FactoriaInterface {

    @Override
    public Cliente creaCliente(int tipoCliente) {
        Cliente cliente = null;
        switch ( tipoCliente ){
            case FactoriaInterface.PARTICULAR :
                cliente = new Particular();
                break;
            case FactoriaInterface.EMPRESA :
                cliente = new Empresa();
                break;
            default:
                cliente = new Particular();
                break;
        }
        return cliente;
    }

    @Override
    public Tarifa creaTarifa(int tipoTarifa) {
        Tarifa tarifa = null;

        switch ( tipoTarifa ){
            case FactoriaInterface.BASICA:
                tarifa = new TarifaBasica();
                break;
            case FactoriaInterface.TARDES:
                tarifa = new TarifaTardes( new TarifaBasica(), 0.05f);
                break;
            case FactoriaObjetos.DOMINGOS:
                tarifa = new TarifaDomingos( new TarifaBasica(), 0f);
                break;
            case FactoriaObjetos.TARDES_Y_DOMINGOS:
                tarifa = new TarifaDomingos( new TarifaTardes( new TarifaBasica(), 0.05f), 0f);
                break;
            default:
                tarifa = new TarifaBasica();
                break;
        }
        return tarifa;
    }
}
