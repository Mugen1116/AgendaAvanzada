package modelo.excepciones;

public class NoHayClientes extends Exception {
    public NoHayClientes(){
        super("No hay clientes registrados");
    }
}
