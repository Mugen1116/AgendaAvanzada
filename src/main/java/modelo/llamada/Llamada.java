package modelo.llamada;

import java.util.Date;

public class Llamada {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private int telefonoLlamado;
    private Date diaHora;
    private float duracion;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================
    public Llamada() {
        this.telefonoLlamado = 000000000 ;
        this.diaHora = new Date();
        this.duracion = 0f;
    }
    public Llamada(int telefonoLlamado, Date diaHora, float duracion) {
        this.telefonoLlamado = telefonoLlamado;
        this.diaHora = diaHora;
        this.duracion = duracion;
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================
    public int getTelefonoLlamado() { return telefonoLlamado; }
    public void setTelefonoLlamado(int telefonoLlamado) { this.telefonoLlamado = telefonoLlamado; }
    public Date getDiaHora() { return diaHora; }
    public void setDiaHora(Date diaHora) { this.diaHora = diaHora; }
    public float getDuracion() { return duracion; }
    public void setDuracion(float duracion) { this.duracion = duracion; }
    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================

}