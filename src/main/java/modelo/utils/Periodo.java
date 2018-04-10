package modelo.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Periodo implements Serializable {


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private LocalDate inicio;
    private LocalDate fin;
    private static final long serialVersionUID = -8392447847953403241L;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================
    public Periodo(){
        super();
    }
    public Periodo( LocalDate inicio, LocalDate fin ){

        if ( inicio.compareTo(fin) == 1) {
            this.inicio = inicio;
            this.fin = fin;
        }
        else {
            this.fin = inicio;
            this.inicio = fin;
        }
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================

    public LocalDate getInicio() { return inicio; }
    public void setInicio( LocalDate inicio) { this.inicio = inicio; }
    public LocalDate getFin() { return fin; }
    public void setFin( LocalDate fin) { this.fin = fin; }

    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    @Override
    public String toString(){
        return "Desde: " + this.inicio +
                "\nHasta: " + this.fin;
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================


}
