package modelo.tarifa;

import  modelo.llamada.Llamada;

import java.io.Serializable;

public class TarifaTardes extends TarifaDecorada implements Serializable{

    private static final long serialVersionUID = 4178039098460695702L;
    public TarifaTardes(Tarifa tarifa, float precio){
        super(tarifa, precio);
    }

    @Override public float getPrecioLlamada(Llamada llamada){
        int horaLlamada = llamada.getDiaHora().getHour();
        if ( horaLlamada >= 16 && horaLlamada < 20){
            return getPrecio()*llamada.getDuracion();
        }else{
            return 0.0f;
        }
    }
}
