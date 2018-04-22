package modelo.excepciones;

public class NoExisteFactura extends Exception {
    public NoExisteFactura(){
        super("No existe la factura buscada.");
    }
}
