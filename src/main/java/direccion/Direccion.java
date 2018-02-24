package direccion;

public class Direccion {
    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private int codigoPostal;
    private String provincia;
    private String poblacion;
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
}
