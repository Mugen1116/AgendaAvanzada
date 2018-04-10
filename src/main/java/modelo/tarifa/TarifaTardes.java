package modelo.tarifa;

import  modelo.llamada.Llamada;

import java.io.Serializable;

public class TarifaTardes extends Tarifa implements Serializable{

    private static final long serialVersionUID = 4178039098460695702L;
    public TarifaTardes(float precio){
        super(precio);
    }

    @Override public float getPrecioLlamada(Llamada llamada){
        get
    }
}
