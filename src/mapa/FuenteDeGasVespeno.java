package src.mapa;

import src.GasVespeno;

public class FuenteDeGasVespeno extends FuenteDeRecurso {
	GasVespeno recursoGasVespeno;
	Posicion posicion;
	
	public FuenteDeGasVespeno (Posicion p){
		posicion = p;
		recursoGasVespeno.setCantidad(9999);
	}
}
