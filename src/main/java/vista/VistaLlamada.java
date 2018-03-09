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
    public VistaLlamada( Scanner sc){
        this.sc = sc;
        llamadaController = new LlamadaController();
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    public LlamadaController getLlamadaController() {
        return llamadaController;
    }

    @Override
    public String muestraOpciones() {
        return null;
    }

    @Override
    public String recogeRespuesta() {
        return null;
    }


    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
