package modelo.excepciones;

public class NoHayLlamadasEntreFechas extends Exception {
    public NoHayLlamadasEntreFechas() {
        super("No existen llamadas realizadas entre las fechas introducidas");
    }
}
