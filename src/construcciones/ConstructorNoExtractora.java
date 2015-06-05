package src.construcciones;

import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;

public class ConstructorNoExtractora extends ConstructorStrategy {
		
	NoExtractora aConstruir;	
	
	public void setConstruccion(NoExtractora edificio){
		aConstruir = edificio;
	}
	public void construir(Mapa map, NoExtractora edificio) throws ExcepcionPosicionInvalida{
			 /* se me ocurre hacer algo de este estilo...
			  * if (tiempoDeConstruccion > 0) {
				 	tiempoDeConstruccion = tiempoDeConstruccion - 1
				 }
				 else {
				 		map.colocarEn(x,y,edificio);
				 }
				*/
			aConstruir = edificio;
			map.colocarEn(aConstruir.getPosicionX(), aConstruir.getPosicionY(), this.aConstruir);
		}
}
