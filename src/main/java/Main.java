import es.uji.belfern.generador.GeneradorDatosINE;

public class Main {

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            GeneradorDatosINE generator = new GeneradorDatosINE();
            System.out.println(generator.getNombre());
            System.out.println(generator.getApellido());
            System.out.println(generator.getNIF());
        }
    }
}
