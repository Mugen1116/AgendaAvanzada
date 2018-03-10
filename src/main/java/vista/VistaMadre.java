package vista;

public abstract class VistaMadre {

    void ejecuta() {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println( muestraOpciones() );
        recogeRespuesta();
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
    }

    abstract String muestraOpciones() ;

    abstract String recogeRespuesta();
}
