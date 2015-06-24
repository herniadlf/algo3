package src.mapa;

import src.Dinero;
import src.Mineral;

public class FuenteDeMinerales extends FuenteDeRecurso {
			
	public FuenteDeMinerales(){
		
		super();
		recurso = new Mineral(999999);	
		nombre = "Mineral";
		
	}
	
	public Dinero extraer(){
		
		return new Dinero(RECURSOS_POR_TURNO,0);
		
	}
	
}
