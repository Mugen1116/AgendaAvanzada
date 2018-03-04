package modelo.tarifa;

public class Tarifa {

    private float precio;

    public Tarifa () {
        this.precio =  0.05f;
    }

    public Tarifa ( float precio ) {
        this.precio = precio;
    }
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString () {
        return Float.toString( this.precio ) + " céntimos/minuto";
    }
}
