package src.unidades;

import java.util.Iterator;
import java.util.LinkedList;

import src.mapa.Mapa;
import src.mapa.Posicion;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;

public class ColocadorUnidadTerrestre implements ColocadorDeUnidades {

	@Override
	public Posicion posicionAColocar(Unidad aEntrenar, Mapa map,
			LinkedList<Posicion> alrededores) throws ExcepcionPosicionInvalida,
			ExcepcionNoHayLugarParaCrear {
		
		Posicion auxiliar = alrededores.getFirst();
		Boolean hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnTierra().esOcupable();
		Iterator<Posicion> list = alrededores.iterator();
	
		while ( (hayLugar == false) && (list.hasNext()) ){
			auxiliar = (Posicion) list.next();
			hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnTierra().esOcupable();
		}		
		if ( !hayLugar ){
			throw new ExcepcionNoHayLugarParaCrear("No hay espacio disponible para la creacion");
		}
		return auxiliar;
	
	}

}
