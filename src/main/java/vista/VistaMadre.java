package vista;

public abstract class VistaMadre {

    public void ejecuta() {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println( muestraOpciones() );
        recogeRespuesta()
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
    }

    public abstract String muestraOpciones() ;

    public abstract String recogeRespuesta();
}
