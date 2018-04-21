package modelo.excepciones;

public class FechaInvalida extends Exception {

    public FechaInvalida() {
        super("La fecha de inicio es posterior a la final.");
    }

}
