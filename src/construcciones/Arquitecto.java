package src.construcciones;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;

public interface Arquitecto {
		
	public void construir(Mapa map, Construccion edificio) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionYaHayElementoEnLaPosicion;
	

}
