package modelo.excepciones;

import java.io.Serializable;

public class ClienteNoExiste extends Exception {

    public ClienteNoExiste () {
        super("El cliente buscado no existe");
    }

}
