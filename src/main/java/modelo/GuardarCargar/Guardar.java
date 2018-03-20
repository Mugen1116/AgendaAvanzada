package modelo.GuardarCargar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Guardar implements Serializable{

    private static final long serialVersionUID =

    try {

        FileOutputStream fos = new FileOutputStream("agenda.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(agenda);
        oos.close();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }



}
