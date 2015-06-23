package src.unidades;

import java.util.LinkedList;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;
import src.mapa.Posicion;

public class ColocadorDeUnidades {

		public Posicion posicionAColocar(Unidad aColocar, Mapa map, LinkedList<Posicion> alrededores) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear {
			
			int i = 0;
			Boolean hayLugar = false;
			Posicion auxiliar = new Posicion(0,0);
			while ( (i < alrededores.size()) && (!hayLugar) ){
				auxiliar = alrededores.get(i);
				if(aColocar.esAereo()){
					hayLugar = map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY()).getElementoEnAire().esOcupable();
				}
				if(aColocar.esTerrestre()){
					hayLugar = map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY()).getElementoEnTierra().esOcupable();
				}
				i++;
			}		
			if ( !hayLugar ){
				throw new ExcepcionNoHayLugarParaCrear("No hay espacio disponible para la creacion");
			}
			return auxiliar;
		}
			
}
