package src.construcciones;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;
import src.mapa.EspacioDisponible;
import src.mapa.Posicion;
import src.unidades.Entrenador;
import src.unidades.Unidad;

public abstract class Creadora extends NoExtractora {
	
	LinkedList<Unidad> unidadesCreables;		
	
	public Creadora (){
		super();
	}
	
	public Unidad crearUnidad(Unidad unidad){
		return unidad;
	}
	

	public void verificarUnidadCreable(Unidad aEntrenar) throws ExcepcionUnidadNoCorrespondiente {
		Iterator<Unidad> list = unidadesCreables.iterator();		
		Boolean founded = unidadesCreables.element().esLoMismo(aEntrenar);
		while ( (list.hasNext()) && (founded == false) ){
			Unidad auxiliar = (Unidad) list.next();
			founded = auxiliar.esLoMismo(aEntrenar);
		}
		if (!founded){
			throw new ExcepcionUnidadNoCorrespondiente("No corresponde la unidad a este edificio");
		}
	}
	public void entrenarUnidad ( Unidad aEntrenar , Mapa map ) 
			throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		
		Posicion auxiliar = aEntrenar.getEntrenador().posicionParaEntrenarA(aEntrenar,map,alrededores);
		
		System.out.print(auxiliar.getY());
			
		map.colocarEn(auxiliar.getX(), auxiliar.getY(), aEntrenar);
		aEntrenar.setMapa(map);
		aEntrenar.setPosicion(auxiliar);
		
	}
	/*
		esta es la version que respeta polimorfismo, digamos. Abajo va la que anda con el nuevo mapa,
		aunque algo hay que tocar porque estoy preguntando todo el tiempo si es terrestre o no..
		
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
	}*/
	
				
}
