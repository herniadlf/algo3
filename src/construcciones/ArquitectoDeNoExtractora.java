package src.construcciones;

import src.mapa.Mapa;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class ArquitectoDeNoExtractora implements Arquitecto {

	@Override
	public void construir(Mapa map, Construccion edificio)
			throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso,
			ExcepcionYaHayElementoEnLaPosicion {
		map.colocarEn(edificio.getPosicionX(), edificio.getPosicionY(), edificio);
	}

}
