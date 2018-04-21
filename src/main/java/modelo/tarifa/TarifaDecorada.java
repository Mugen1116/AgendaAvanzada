package modelo.tarifa;

import modelo.llamada.Llamada;

import java.io.Serializable;

public abstract class TarifaDecorada extends Tarifa implements Serializable {


    private static final long serialVersionUID = -3698701879346328931L;
    private Tarifa tarifa;

  public TarifaDecorada( Tarifa tarifa, float precio ) {
      super( precio );
      this.tarifa = tarifa;
  }
  public Tarifa getTarifa(){
      return this.tarifa;
  }

}
