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
		
		int i = 0;
		Boolean hayLugar = false;
		Posicion auxiliar = new Posicion(0,0);
		while ( (i < alrededores.size()) && (!hayLugar) ){
			auxiliar = alrededores.get(i);
			hayLugar = map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY()).getElementoEnAire().esOcupable();
			i++;
		}		
		if ( !hayLugar ){
			throw new ExcepcionNoHayLugarParaCrear("No hay espacio disponible para la creacion");
		}
		return auxiliar;
	}

	

	

}
