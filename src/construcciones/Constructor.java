package src.construcciones;

import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;

public class Constructor {
	int tiempoDeConstruccion;
	int posicionX;
	int posicionY;
	
	public Constructor(int tiempo, int ubicacionX, int ubicacionY){
		tiempoDeConstruccion = tiempo;
		posicionX = ubicacionX;
		posicionY = ubicacionY;
	}

	public void construir(Construccion edificio, Mapa map) throws ExcepcionPosicionInvalida {
		/*se me ocurre hacer algo de este estilo:
		 * if (tiempoDeConstruccion > 0) {
		 * 		tiempoDeConstruccion = tiempoDeConstruccion - 1
		 * }
		 * else {
		 * 		map.colocarEn(x,y,edificio);
		 * }
		 */
		map.colocarEn(posicionX, posicionY, edificio);		
	}
	
	
	

}
