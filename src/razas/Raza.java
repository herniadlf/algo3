package src.razas;

import java.util.*;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import src.construcciones.Construccion;
import src.unidades.Unidad;

public abstract class Raza {
	
	protected LinkedList<Unidad> unidadesPosibles;
	protected LinkedList<Construccion> construccionesPosibles;
	
	public void verificarEdificioPosible(Construccion c) throws ExcepcionConstruccionNoCorrespondiente{
		int i = 0;
		Boolean founded = false;
		while ( (i < construccionesPosibles.size() ) && (!founded) ){
			founded = construccionesPosibles.get(i).esLoMismo(c);
			i++;
		}		
		if (!founded){
			throw new ExcepcionConstruccionNoCorrespondiente("Edificio no corresponde a la raza");
		}
		
	}
	
	public void actualizarEdificios(Construccion edificio) {
		
		construccionesPosibles.add(edificio);		
		
	}
		
	public abstract Construccion getEdificioPrincipal();

	public void pasoTurno() {
		int i=0;
		while (i < construccionesPosibles.size()){
			if ( construccionesPosibles.get(i).getVida().devolverEstadoDeVida() ) {
				construccionesPosibles.remove(i);
				i--;
			}
			i++;
		}		
	}
	
	public abstract String getNombre();
}

