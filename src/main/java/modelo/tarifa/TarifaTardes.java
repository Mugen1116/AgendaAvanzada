package modelo.tarifa;

import  modelo.llamada.Llamada;

import java.io.Serializable;

public class TarifaTardes extends TarifaDecorada {

    private static final long serialVersionUID = 4178039098460695702L;
    private final int INICIO = 16;
    private final int FIN = 20;

    public TarifaTardes(Tarifa tarifa, float precio){
        super(tarifa, precio);
    }

    @Override public float getPrecioLlamada(Llamada llamada){
        int horaLlamada = llamada.getDiaHora().getHour();
        if ( horaLlamada >= INICIO && horaLlamada < FIN ){
            float precioTardes = getTarifa().getPrecioLlamada( llamada );
            float precioMadre =  super.getPrecio() * llamada.getDuracion();
            return ( precioTardes  <  precioMadre ) ? precioTardes : precioMadre ;

        }
        //Si no ha sido por la tarde
        //Se pasa a la madre como en el caso de los domingos
        return getTarifa().getPrecioLlamada( llamada );
    }

    @Override
    public String toString() {
        return super.toString() + " + Tarifa de tardes, de 16:00 a 20:00 horas a " + getPrecio() + " centimos/min";
    }
}
