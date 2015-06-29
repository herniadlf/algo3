package src.razas;

import java.util.*;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import src.construcciones.Construccion;
import src.unidades.Unidad;

public abstract class Raza {
	
	protected LinkedList<Unidad> unidadesPosibles;
	protected LinkedList<Construccion> construccionesPosibles;
	
	public void verificarEdificioPosible(Construccion c) throws ExcepcionConstruccionNoCorrespondiente{
	
		if (!encontradoEnlaLista(c)){
			throw new ExcepcionConstruccionNoCorrespondiente("Edificio no habilitado");
		}
		
	}
	
	public void actualizarEdificios(Construccion edificio) {
		if (!encontradoEnlaLista(edificio)) {
			construccionesPosibles.add(edificio);
		}
	}
		
	public abstract Construccion getEdificioPrincipal();
	
	private Boolean encontradoEnlaLista(Construccion elementoBuscado){
		int i= 0;
		Boolean founded = false;
		while ( (i < construccionesPosibles.size()) && !founded ) {
			founded = (construccionesPosibles.get(i).esLoMismo(elementoBuscado));
			i++;
		}
		return founded;
	}
	
	public LinkedList<Construccion> getConstruccionesPosibles(){
		
		return construccionesPosibles;
		
	}

	public void pasoTurno() {
		
		int i=0;
		while (i < construccionesPosibles.size()){
			if ( construccionesPosibles.get(i).getVida().estaMuerto() ) {
				construccionesPosibles.remove(i);
				i--;
			}
			i++;
		}	
		
	}
	
	public abstract String getNombre();

	public LinkedList<Unidad> getUnidadesPosibles() {
		
		return unidadesPosibles;
		
	}
	
}

