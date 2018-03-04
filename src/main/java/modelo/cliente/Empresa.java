package modelo.cliente;

public class Empresa extends Cliente{

    public Empresa(){
        super();
    }

    public Empresa ( Cliente other ){
        this.setNombre( other.getNombre() );
        this.setNIF( other.getNIF() );
        this.setTarifa( other.getTarifa() );
        this.setEmail( other.getEmail() );
        this.setFechaAlta( other.getFechaAlta() );
        this.setDireccion( other.getDireccion() );
    }

}
