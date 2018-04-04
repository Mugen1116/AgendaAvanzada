package modelo.excepciones;

public class NoExistenFacturasDeCliente extends Exception {
    public NoExistenFacturasDeCliente(){
        super("No existe ninguna factura para el cliente introducido.");
    }
}
