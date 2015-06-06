package src.construcciones;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;
import src.mapa.Pasto;
import src.mapa.Posicion;
import src.unidades.Unidad;

public abstract class Creadora extends NoExtractora {
	
	AbstractCollection<Unidad> unidadesCreables;	
	LinkedList<Posicion> alrededores;
	
	public Unidad crearUnidad(Unidad unidad){
		return unidad;
	}

	public boolean verificarUnidadCreable(Unidad aEntrenar) {
		Boolean founded = false;
		Iterator list = unidadesCreables.iterator();
		while ( (list.hasNext()) && (founded == false) ){
			Construccion auxiliar = (Construccion) list.next();
			founded = auxiliar.esLoMismo(aEntrenar);
		}
		return founded;
	}

	public void entrenarUnidad(Unidad aEntrenar,Mapa map) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion {
		Posicion auxiliar = alrededores.getFirst();		
		Boolean hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).esLoMismo(new Pasto());		
		Iterator list = alrededores.iterator();		
		while ( (hayLugar == false) && (list.hasNext()) ){
			auxiliar = (Posicion) list.next();
			hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).esLoMismo(new Pasto());
		}
		if (hayLugar){
			map.colocarEn(auxiliar.getX(), auxiliar.getY(), aEntrenar);
		}
		else{
			throw new ExcepcionNoHayLugarParaCrear("No hay lugar para crear la unidad");
		}
	}
				
}
