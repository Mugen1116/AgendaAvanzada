package modelo.llamada;

import modelo.utils.DateInterface;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
//import java.time.LocalDate;

public class Llamada implements DateInterface, Serializable{


    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private int telefonoLlamado;
    private LocalDateTime diaHora;
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
        this.diaHora = LocalDateTime.now();
        this.duracion = 0f;
    }
    public Llamada(int telefonoLlamado, LocalDateTime diaHora, float duracion) {
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
    public LocalDateTime getDiaHora() { return diaHora; }
    public void setDiaHora(LocalDateTime diaHora) { this.diaHora = diaHora; }
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
    public LocalDateTime getFecha() {
        return diaHora;
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================
}
