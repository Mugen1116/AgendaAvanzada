package modelo.tarifa;

import modelo.llamada.Llamada;

import java.io.Serializable;

public class TarifaBasica extends Tarifa {

    private static final long serialVersionUID = 4178039098460695702L;
    public TarifaBasica(){
       super(0.15f);
    }
    public TarifaBasica ( float precio ) {
        super( precio );
    }

    @Override
    public float getPrecioLlamada(Llamada llamada){
        return getPrecio() * llamada.getDuracion();
    }
    @Override
    public String toString(){
        return "Tarifa base de 15 cts/min";
    }
}
