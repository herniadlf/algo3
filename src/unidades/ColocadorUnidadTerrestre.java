package src.unidades;

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
		int i = 0;
		Boolean hayLugar = false;
		Posicion auxiliar = new Posicion(0,0);
		while ( (i < alrededores.size()) && (!hayLugar) ){
			auxiliar = alrededores.get(i);
			hayLugar = map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY()).getElementoEnTierra().esOcupable();
			i++;
		}		
		if ( !hayLugar ){
			throw new ExcepcionNoHayLugarParaCrear("No hay espacio disponible para la creacion");
		}
		return auxiliar;
	
	}

}
