package modelo.llamada;

import modelo.utils.DateInterface;

import java.io.Serializable;
import java.util.Date;

public class Llamada implements DateInterface, Serializable{


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private int telefonoLlamado;
    private Date diaHora;
    private float duracion;
    private static final long serialVersionUID = -3560771105228664011L;
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

    //==================================================
    //----------------------METHODS---------------------
    //==================================================
    @Override
    public String toString(){
        return  "Telefono: " + telefonoLlamado
                + "\nFecha llamada: " + diaHora
                + "\nDuración: " + duracion;
    }
    public Date getFecha() {
        return diaHora;
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
