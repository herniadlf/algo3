package src.razas;

import java.util.*;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import src.construcciones.Construccion;
import src.unidades.Unidad;

public abstract class Raza {

		protected LinkedList<Unidad> unidadesPosibles;
		protected LinkedList<Construccion> construccionesPosibles;
		public void verificarEdificioPosible(Construccion c) throws ExcepcionConstruccionNoCorrespondiente{
			Iterator<Construccion> list = construccionesPosibles.iterator();
			Boolean founded = construccionesPosibles.element().esLoMismo(c);
			while ( (list.hasNext()) && (founded == false) ){
				Construccion auxiliar = (Construccion) list.next();
				founded = auxiliar.esLoMismo(c);
			}
			if (!founded){
				throw new ExcepcionConstruccionNoCorrespondiente("Edificio no corresponde a la raza");
			}
		}
		public void actualizarEdificios(Construccion edificio) {
			construccionesPosibles.add(edificio);		
		}
		
		public abstract Construccion getEdificioPrincipal();
					
}

