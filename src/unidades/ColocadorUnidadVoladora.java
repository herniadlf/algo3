package src.unidades;

import java.util.Iterator;
import java.util.LinkedList;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;
import src.mapa.Posicion;

public class ColocadorUnidadVoladora implements ColocadorDeUnidades {

	@Override
	public Posicion posicionAColocar(Unidad aColocar, Mapa map,
			LinkedList<Posicion> alrededores) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear {
		
		Posicion auxiliar = alrededores.getFirst();
		Boolean hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnAire().esOcupable();
		Iterator<Posicion> list = alrededores.iterator();
	
		while ( (hayLugar == false) && (list.hasNext()) ){
			auxiliar = (Posicion) list.next();
			hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnAire().esOcupable();
		}		
		if ( !hayLugar ){
			throw new ExcepcionNoHayLugarParaCrear("No hay espacio disponible para la creacion");
		}
		return auxiliar;
	}

	

	

}
