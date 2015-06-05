package src.construcciones;

import src.mapa.Mapa;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;

public class ConstructorExtractora extends ConstructorStrategy {
	
	Extractora aConstruir;
		
	public void setConstruccion(Extractora edificio){
		aConstruir = edificio;
	}
	public void construir(Mapa map,Extractora edificio) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		aConstruir = edificio;
		if ( (map.obtenerContenidoEnPosicion(aConstruir.getPosicionX(), aConstruir.getPosicionY())).esLoMismo(this.aConstruir.getFuente()) ){
			map.colocarEn(aConstruir.getPosicionX(),aConstruir.getPosicionY(),this.aConstruir);
		}
		else {
			throw new ExcepcionExtractoraSinRecurso("No se puede construir edificio extractor sin fuente de recursos.");
		}
	}
}
