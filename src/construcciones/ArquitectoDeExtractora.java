package src.construcciones;

import src.mapa.Mapa;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class ArquitectoDeExtractora implements Arquitecto {

	public void colocar(Mapa map, Construccion edificio)
			throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso,
			ExcepcionYaHayElementoEnLaPosicion {
		
		map.eliminarElementoTerrestreEnPosicion(edificio.getPosicionX(), edificio.getPosicionY());
		map.colocarExtractorEn(edificio.getPosicionX(), edificio.getPosicionY(), (Extractora) edificio);
		
	}

	public void verificarTerreno(Mapa map, int x, int y, Construccion construccion) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso {
		
		Extractora auxiliar = (Extractora) construccion;
		map.verificarFuente(x,y,auxiliar);		
	}

}
