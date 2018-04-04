package modelo.conjuntos;
import modelo.utils.DateInterface;

import java.io.Serializable;
import java.util.*;


//Devuelve un conjunto de objetos situados entre dos fechas
public class GetConjunto<T extends DateInterface> implements Serializable {

    private static final long serialVersionUID = -98556438460403684L;

    public GetConjunto () {
        super();
    }

    public List<T> situadosEntre(List<T> lista, Date fecha1, Date fecha2){

        LinkedList<T> sublista = new LinkedList<T>();
        Date fecha0;

        for (T aux : lista){

            fecha0 = aux.getFecha();

            if (fecha0.compareTo(fecha1)>=0  && fecha0.compareTo(fecha2)<=0){

                sublista.add(aux);

            }

        }

        return sublista;
    }

}