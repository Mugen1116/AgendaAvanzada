package vista;

import controlador.llamada.LlamadaController;

import java.util.Scanner;

public class VistaLlamada extends VistaMadre {


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    Scanner sc;
    LlamadaController llamadaController;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTOR--------------------
    //==================================================
    VistaLlamada( Scanner sc){
        this.sc = sc;
        llamadaController = new LlamadaController();
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    LlamadaController getLlamadaController() {
        return llamadaController;
    }

    @Override
    String muestraOpciones() {
        return null;
    }

    @Override
    String recogeRespuesta() {
        return null;
    }


    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
