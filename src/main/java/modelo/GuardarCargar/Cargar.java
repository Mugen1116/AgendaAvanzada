package modelo.GuardarCargar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Cargar implements Serializable{

    private static final long serialVersionUID =

    try {

        FileInputStream fis = new FileInputStream("agenda.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        agenda = (Agenda)ois.readObject();
        ois.close();

    }catch(FileNotFoundException e){
        e.printStackTrace();
    }


}
