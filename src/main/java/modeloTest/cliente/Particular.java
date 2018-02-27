package modeloTest.cliente;

public class Particular extends Cliente {

    //==================================================
    //-------------------ATRIBUTOS----------------------
    //==================================================
    private String apellidos;
    //==================================================
    //-------------------END ATRIBUTOS------------------
    //==================================================

    //==================================================
    //-------------------CONSTRUCTORS-------------------
    //==================================================
    public Particular() {
        super();
    }
    public Particular(String apellidos) {
        this.apellidos = apellidos;
    }
    //==================================================
    //-----------------END CONSTRUCTORS-----------------
    //==================================================

    //==================================================
    //----------------GETTERS Y SETTERS-----------------
    //==================================================
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    //==================================================
    //---------------END GETTERS Y SETTERS--------------
    //==================================================

    //==================================================
    //----------------------METHODS---------------------
    //==================================================

    @Override
    public String toString(){
        //super.toString() + "\nApellidos: " + apellidos;
        return "Nombre: " + super.getNombre() + "\nApellidos: " + this.apellidos
                + "\nDNI: " + super.getNIF() + "\nDirección: "
                + super.getDireccion() + "\nEMail: " + super.getEmail() + "\nTarifa: "
                + super.getTarifa() + "\nFecha de Alta: " + super.getFechaAlta();
    }
    //==================================================
    //--------------------END METHODS-------------------
    //==================================================

}
