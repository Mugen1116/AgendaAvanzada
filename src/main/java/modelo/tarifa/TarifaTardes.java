package modelo.tarifa;

import  modelo.llamada.Llamada;

import java.io.Serializable;

public class TarifaTardes extends Tarifa implements Serializable{

    private static final long serialVersionUID = 4178039098460695702L;
    public TarifaTardes(Tarifa tarifa, float precio){
        super(tarifa, precio);
    }

    @Override public float getPrecioLlamada(Llamada llamada){
        int horaLlamada = llamada.getDiaHora().getHours();
        if ( horaLlamada >= 16 && horaLlamada < 20){
            return getPrecio()*llamada.getDuracion();
        }else{
            return
        }
    }
}
