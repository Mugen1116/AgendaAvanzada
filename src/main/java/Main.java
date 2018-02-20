import es.uji.belfern.generador.GeneradorDatosINE;

public class Main {

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            GeneradorDatosINE generador = new GeneradorDatosINE();
            System.out.println(generador.getNombre());
            System.out.println(generador.getApellido());
            System.out.println(generador.getNIF());
        }
    }
}
