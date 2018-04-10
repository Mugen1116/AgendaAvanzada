package modelo.excepciones;

public class NoHayLlamadasCliente extends Exception {
    public NoHayLlamadasCliente() {
        super("No existen llamadas realizadas por este cliente");
    }

}
