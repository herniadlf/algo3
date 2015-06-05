package src.construcciones;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;

public abstract class ConstructorStrategy {
		
	public void construir(Mapa map, Construccion edificio) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{}
	public void setConstruccion(Construccion construccion){};
	/*public void construir(NoExtractora edificio, Mapa map) throws ExcepcionPosicionInvalida {
		/*se me ocurre hacer algo de este estilo:
		 * if (tiempoDeConstruccion > 0) {
		 * 		tiempoDeConstruccion = tiempoDeConstruccion - 1
		 * }
		 * else {
		 * 		map.colocarEn(x,y,edificio);
		 * }
		
		map.colocarEn(posicionX, posicionY, edificio);		
	}
	
	public void construir(Extractora edificio, Mapa map) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		if ( (map.obtenerContenidoEnPosicion(this.posicionX, this.posicionY)).esLoMismo(edificio.getFuente()) ){
			map.colocarEn(this.posicionX,this.posicionY,edificio);
		}
		else {
			throw new ExcepcionExtractoraSinRecurso("No se puede construir edificio extractor sin fuente de recursos.");
		}
	}
	
	*/

}
