package src.mapa;

import src.Dinero;
import src.GasVespeno;

public class FuenteDeGasVespeno extends FuenteDeRecurso {
			
	public FuenteDeGasVespeno (){
		super();		
		recurso = new GasVespeno(999999);
		nombre = "Gas Vespeno";
	}
	public Dinero extraer(){
		return new Dinero(0,RECURSOS_POR_TURNO);
	}
}
