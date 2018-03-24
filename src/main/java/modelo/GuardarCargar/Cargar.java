package modelo.GuardarCargar;

import modelo.agenda.Agenda;

import java.io.*;

public class Cargar implements Serializable{


    private static final long serialVersionUID = 778548155941304199L;

    public void Cargar ( String path ) {
        try {

            FileInputStream fis = new FileInputStream( path );
            ObjectInputStream ois = new ObjectInputStream(fis);
            Agenda agenda = (Agenda)ois.readObject();
            ois.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
