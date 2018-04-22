package modelo.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Periodo implements Serializable {


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private LocalDateTime inicio;
    private LocalDateTime fin;
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
    public Periodo( LocalDateTime inicio, LocalDateTime fin ){

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

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio( LocalDateTime inicio) { this.inicio = inicio; }
    public LocalDateTime getFin() { return fin; }
    public void setFin( LocalDateTime fin) { this.fin = fin; }

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
