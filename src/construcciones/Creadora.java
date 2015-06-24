package src.construcciones;
import java.util.ArrayList;
import java.util.LinkedList;

import excepciones.ExcepcionEdificioDestruido;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Jugador;
import src.Turno;
import src.mapa.Mapa;
import src.mapa.Posicion;
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
		
		int i=0;
		Boolean founded = false;
		while ( (i < unidadesCreables.size()) && (!founded) ) {
			founded = unidadesCreables.get(i).esLoMismo(aEntrenar);
			i++;
		}
		if (!founded){
			throw new ExcepcionUnidadNoCorrespondiente("No corresponde la unidad a este edificio");
		}
		
	}
	
	public void colocarUnidad ( Unidad aColocar , Mapa map ) 
			throws ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, 
			ExcepcionUnidadNoCorrespondiente {		
			
			verificarUnidadCreable(aColocar);
			Posicion auxiliar = aColocar.getColocador().posicionAColocar(aColocar,map,alrededores);		
			map.colocarEn(auxiliar.getX(), auxiliar.getY(), aColocar);
			aColocar.setMapa(map);
			aColocar.setPosicion(auxiliar);
		
	}
	
	public void pasoTurno (Turno turno, Mapa map, Jugador jugadorActual) throws ExcepcionNoPudoColocarseUnidad, ExcepcionEdificioDestruido, ExcepcionUnidadNoCorrespondiente {
		
		int i=0;
		int turnosPasados;
		int turnoOrdenoFabricacion;
		if (vida.estaMuerto()){
			throw new ExcepcionEdificioDestruido();
		}
		while (this.unidadesEnFabricacion.size()>i){
			
			turnoOrdenoFabricacion = this.unidadesEnFabricacion.get(i).getTurnoDeEntrenamiento();
			turnosPasados = (turno.devolverTurnoActual()- turnoOrdenoFabricacion)/2; 
			
			if ( turnosPasados  >= unidadesEnFabricacion.get(i).getTiempoDeCreacion()){
				try{
					colocarUnidad(unidadesEnFabricacion.get(i), map);
					jugadorActual.agregarAUnidadesAlistadas(unidadesEnFabricacion.get(i));
					jugadorActual.actualizarPorNuevaUnidad(unidadesEnFabricacion.get(i),unidadesEnFabricacion.get(i).getEdifico(), map);				
					unidadesEnFabricacion.remove(i);
					i--;
				}
				catch ( ExcepcionNoHayLugarParaCrear e ) {}	
				catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion w){
					throw new ExcepcionNoPudoColocarseUnidad(w);
				}
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
