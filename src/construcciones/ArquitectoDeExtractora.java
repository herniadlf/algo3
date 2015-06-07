package src.construcciones;

import src.mapa.Mapa;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class ArquitectoDeExtractora implements Arquitecto {

	public void construir(Mapa map, Extractora edificio) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionYaHayElementoEnLaPosicion{
		map.colocarExtractorEn(edificio.getPosicionX(), edificio.getPosicionY(), edificio);
	}

	@Override
	public void construir(Mapa map, Construccion edificio)
			throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso,
			ExcepcionYaHayElementoEnLaPosicion {
		map.colocarExtractorEn(edificio.getPosicionX(), edificio.getPosicionY(), (Extractora) edificio);
	}
}
