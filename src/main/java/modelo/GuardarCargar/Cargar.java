package modelo.GuardarCargar;

import modelo.agenda.Agenda;

import java.io.*;

public class Cargar implements Serializable{

    private static final long serialVersionUID = 778548155941304199L;

    public Agenda cargar ( String path ) {
        try {

            FileInputStream fis = new FileInputStream( path );
            ObjectInputStream ois = new ObjectInputStream(fis);
            Agenda agenda = (Agenda)ois.readObject();
            ois.close();
            return agenda;

        }catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el archivo, se creará al cerrar el Programa por primera vez.");
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }




}
