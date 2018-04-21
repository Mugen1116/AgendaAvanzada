package modelo.direccion;

import java.io.Serializable;

public class Direccion implements Serializable{

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private int codigoPostal;
    private String provincia;
    private String poblacion;
    private static final long serialVersionUID = 7809069911800211254L;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================
    public Direccion(int codigoPostal, String provincia, String poblacion) {
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }
    public Direccion () {
        this.codigoPostal = 00000;
        this.provincia = "";
        this.poblacion = "";
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================
    public int getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    public String getPoblacion() { return poblacion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }
    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================
    @Override
    public String toString() {
        return codigoPostal + " " + poblacion.toUpperCase() + " " + provincia;
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
