package modelo.excepciones;

public class NoHayClientesEntreFechas extends Exception {
    public NoHayClientesEntreFechas(){
        super("No existen clientes entre las fechas introducidas.");
    }
}
