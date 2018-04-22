package modelo.conjuntos;
import modelo.utils.DateInterface;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


//Devuelve un conjunto de objetos situados entre dos fechas
public class GetConjunto<T extends DateInterface> implements Serializable {

    private static final long serialVersionUID = -98556438460403684L;

    public GetConjunto () {
        super();
    }

    public List<T> situadosEntre(List<T> lista, LocalDateTime fecha1, LocalDateTime fecha2){

        LinkedList<T> sublista = new LinkedList<T>();
        LocalDateTime fecha0;

        for (T aux : lista){

            fecha0 = aux.getFecha();

           /* System.out.println("Compare Inicio: ");
            System.out.println( fecha0.compareTo(fecha1));

            System.out.println("Compare Fin: ");
            System.out.println("0: " + fecha0);
            System.out.println("2: " + fecha2);
            System.out.println( fecha0.compareTo(fecha2));*/

            if (fecha0.compareTo(fecha1)>=0  && fecha0.compareTo(fecha2)<=0){

                sublista.add(aux);

            }

        }

        return sublista;
    }

}