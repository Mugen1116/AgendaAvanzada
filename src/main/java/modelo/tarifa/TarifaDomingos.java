package modelo.tarifa;

import modelo.llamada.Llamada;

import java.time.DayOfWeek;

public class TarifaDomingos extends TarifaDecorada {


    private static final long serialVersionUID = -2237792593492529812L;

    //Le pasamos un precio, por si cambiara el precio de los domingos cambiara
    public TarifaDomingos(  Tarifa tarifa, float precio ){
        super( tarifa, precio );
    }

    @Override
    public float getPrecioLlamada(Llamada llamada) {
        if( llamada.getDiaHora().getDayOfWeek().equals( DayOfWeek.SUNDAY ) ){
            //Si es en domingo la llamada
            return 0.0f;
        }
        else {
            //Si no, se calcula el precio de la tarifa siguiente decorada
            return getTarifa().getPrecioLlamada( llamada );
        }

    }

    @Override
    public String toString() {
        return super.toString() + " + Tarifa de domingos gratis";
    }
}
