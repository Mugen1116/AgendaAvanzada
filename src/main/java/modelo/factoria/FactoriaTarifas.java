package modelo.factoria;

import modelo.cliente.Cliente;
import modelo.cliente.Empresa;
import modelo.cliente.Particular;
import modelo.tarifa.Tarifa;
import modelo.tarifa.TarifaBasica;
import modelo.tarifa.TarifaDomingos;
import modelo.tarifa.TarifaTardes;

public class FactoriaTarifas implements FactoriaTarifasInterface {



    @Override
    public Tarifa creaTarifa(int tipoTarifa) {
        Tarifa tarifa = null;

        switch ( tipoTarifa ){
            case FactoriaTarifasInterface.BASICA:
                tarifa = new TarifaBasica();
                break;
            case FactoriaTarifasInterface.TARDES:
                tarifa = new TarifaTardes( new TarifaBasica(), 0.05f);
                break;
            case FactoriaTarifas.DOMINGOS:
                tarifa = new TarifaDomingos( new TarifaBasica(), 0f);
                break;
            case FactoriaTarifas.TARDES_Y_DOMINGOS:
                tarifa = new TarifaDomingos( new TarifaTardes( new TarifaBasica(), 0.05f), 0f);
                break;
            default:
                tarifa = new TarifaBasica();
                break;
        }
        return tarifa;
    }
}
