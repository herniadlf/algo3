package src.construcciones;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Jugador;
import src.Turno;
import src.mapa.Mapa;
import src.mapa.EspacioDisponible;
import src.mapa.Posicion;
import src.unidades.ColocadorDeUnidades;
import src.unidades.Unidad;

public abstract class Creadora extends NoExtractora {
	
	LinkedList<Unidad> unidadesCreables;
	ArrayList <Unidad> unidadesEnFabricacion;
	
	public Creadora (){
		
		super();
		unidadesEnFabricacion= new ArrayList<Unidad>();
		
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
	
	public void colocarUnidad ( Unidad aColocar , Mapa map ) 
			throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		
		Posicion auxiliar = aColocar.getColocador().posicionAColocar(aColocar,map,alrededores);	
			
		map.colocarEn(auxiliar.getX(), auxiliar.getY(), aColocar);
		aColocar.setMapa(map);
		aColocar.setPosicion(auxiliar);
		
	}
	
	public void pasoTurno (Turno turno, Mapa map, Jugador jugadorActual) 
			throws ExcepcionEdificioNoPuedeCrearUnidad,
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		int i=0;
		int turnosPasados;
		int turnoOrdenoFabricacion;
		while (this.unidadesEnFabricacion.size()>i){
			
			turnoOrdenoFabricacion = this.unidadesEnFabricacion.get(i).getTurnoDeEntrenamiento();
			turnosPasados = (turno.devolverTurnoActual()- turnoOrdenoFabricacion)/2; 
			
			if ( turnosPasados  >= unidadesEnFabricacion.get(i).getTiempoDeCreacion()){
				try{
					colocarUnidad(unidadesEnFabricacion.get(i), map);
					jugadorActual.actualizarPorNuevaUnidad(unidadesEnFabricacion.get(i),unidadesEnFabricacion.get(i).getEdifico(), map);				
					unidadesEnFabricacion.remove(i);
				}
				catch ( ExcepcionNoHayLugarParaCrear e ) {}							
			} 
			
			i++;	
		}
		
	}
		
		
	public ArrayList<Unidad> obtenerListaDeUnidadesAFabricar (){
			
		return unidadesEnFabricacion;	
			
	}

	public void agregarUnidadAEntrenamiento(Unidad unidad) {
		
		unidadesEnFabricacion.add(unidad);		
		
	}	
				
}
