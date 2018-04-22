package modelo.tarifa;

import modelo.llamada.Llamada;

import java.io.Serializable;


//Esta es la clase base, Tarifa Básica será la Base Decorada
//Y crearemos una clase por cada tipo de tarifa que decorará la TarifaBásica

public abstract class Tarifa implements Serializable{

    private static final long serialVersionUID = 4178039098460695702L;
    private float precio;

    public Tarifa ( float precio ) {
        this.precio = precio;
    }
    public float getPrecio() {
        return precio;
    }

    public abstract float getPrecioLlamada(Llamada llamada);

    @Override
    public String toString () {
        return Float.toString( this.precio ) + " céntimos/minuto";
    }
}
