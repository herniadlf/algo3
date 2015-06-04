package src.mapa;

import src.Mineral;

public class FuenteDeMinerales extends FuenteDeRecurso {
	Mineral recursoMineral;
	Posicion posicion;
	
	public FuenteDeMinerales(Posicion p){
		posicion = p;
		recursoMineral.setCantidad(9999);
	}
}
