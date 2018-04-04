package modelo.excepciones;

import java.io.Serializable;

public class ClienteExistente extends Exception  {



    public ClienteExistente () {
        super ( "Ya existe este cliente, no se insertará." );
    }
}
