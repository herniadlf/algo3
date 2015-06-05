package src.mapa;

import src.GasVespeno;

public class FuenteDeGasVespeno extends FuenteDeRecurso {
			
	public FuenteDeGasVespeno (){
		super();		
		recurso = new GasVespeno(999999);
	}
}
