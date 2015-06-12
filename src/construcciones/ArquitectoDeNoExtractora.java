package src.construcciones;

import src.mapa.Escombros;
import src.mapa.Mapa;
import src.mapa.Posicion;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class ArquitectoDeNoExtractora implements Arquitecto {

	@Override
	public void colocar(Mapa map, Construccion edificio)
			throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso,
			ExcepcionYaHayElementoEnLaPosicion {
		map.eliminarElementoTerrestreEnPosicion(edificio.getPosicionX(), edificio.getPosicionY()); //remueve escombros
		map.colocarEn(edificio.getPosicionX(), edificio.getPosicionY(), edificio);
	}

	@Override
	public void verificarTerreno(Mapa map, int x, int y,Construccion construccion) throws ExcepcionPosicionInvalida,
			ExcepcionExtractoraSinRecurso, ExcepcionYaHayElementoEnLaPosicion {
		map.validarPosicionSinElemento(new Posicion(x,y), construccion.esTerrestre());
		map.colocarEn(x, y, new Escombros());
	}

	
}

