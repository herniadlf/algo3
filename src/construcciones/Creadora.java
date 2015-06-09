package src.construcciones;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;
import src.mapa.EspacioDisponible;
import src.mapa.Posicion;
import src.unidades.Unidad;

public abstract class Creadora extends NoExtractora {
	
	LinkedList<Unidad> unidadesCreables;	
	
	public Creadora (){
		super();
	}
	
	public Unidad crearUnidad(Unidad unidad){
		return unidad;
	}
	

	public boolean verificarUnidadCreable(Unidad aEntrenar) {
		Iterator list = unidadesCreables.iterator();		
		Boolean founded = unidadesCreables.element().esLoMismo(aEntrenar);
		while ( (list.hasNext()) && (founded == false) ){
			Construccion auxiliar = (Construccion) list.next();
			founded = auxiliar.esLoMismo(aEntrenar);
		}
		return founded;
	}

	public void entrenarUnidad(Unidad aEntrenar,Mapa map) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion {
		/*Posicion auxiliar = alrededores.getFirst();		
		Boolean hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).esOcupable();		
		Iterator list = alrededores.iterator();
		
		while ( (hayLugar == false) && (list.hasNext()) ){
			auxiliar = (Posicion) list.next();
			hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).esOcupable();
		}
		if (hayLugar){
			
			Posicion posicion = new Posicion (auxiliar.getX(),auxiliar.getY()); 
			
			
			map.colocarEn(auxiliar.getX(), auxiliar.getY(), aEntrenar);
			aEntrenar.setMapa(map);
			aEntrenar.setPosicion(posicion);
			
			
			
		}
		else{
			throw new ExcepcionNoHayLugarParaCrear("No hay lugar para crear la unidad");
		} 
		esta es la version que respeta polimorfismo, digamos. Abajo va la que anda con el nuevo mapa,
		aunque algo hay que tocar porque estoy preguntando todo el tiempo si es terrestre o no..
		*/
		Posicion auxiliar = alrededores.getFirst();
		Boolean hayLugar = false;
		Iterator<Posicion> list = alrededores.iterator();
		if (aEntrenar.esTerrestre()){
			hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnTierra().esOcupable();
			while ( (hayLugar == false) && (list.hasNext()) ){
				auxiliar = (Posicion) list.next();
				hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnTierra().esOcupable();
			}
		}
		else{
			hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnAire().esOcupable();
			while ( (hayLugar == false) && (list.hasNext()) ){
				auxiliar = (Posicion) list.next();
				hayLugar = (map.obtenerContenidoEnPosicion(auxiliar.getX(), auxiliar.getY())).getElementoEnAire().esOcupable();
			}
		}
		if (hayLugar){			
			Posicion posicion = new Posicion (auxiliar.getX(),auxiliar.getY()); 			
			map.colocarEn(auxiliar.getX(), auxiliar.getY(), aEntrenar);
			aEntrenar.setMapa(map);
			aEntrenar.setPosicion(posicion);			
		}
		else{
			throw new ExcepcionNoHayLugarParaCrear("No hay lugar para crear la unidad");
		} 
	}
				
}
