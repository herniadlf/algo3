package src.mapa;

import src.Mineral;

public class FuenteDeMinerales extends FuenteDeRecurso {
			
	public FuenteDeMinerales(){
		super();
		recurso = new Mineral(999999);	
		nombre = "Mineral";
	}
	
}
