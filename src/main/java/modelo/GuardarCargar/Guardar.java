package modelo.GuardarCargar;

import modelo.agenda.Agenda;

import java.io.*;

public class Guardar implements Serializable{


    private static final long serialVersionUID = 6795577305527676665L;

    public void guardar ( String path, Agenda agenda ) {
        try {

            FileOutputStream fos = new FileOutputStream( path );
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(agenda);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
