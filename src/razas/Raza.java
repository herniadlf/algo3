package src.razas;

import java.util.*;

import src.construcciones.Construccion;
import src.unidades.Unidad;

public abstract class Raza {

		protected LinkedList<Unidad> unidadesPosibles;
		protected LinkedList<Construccion> construccionesPosibles;
		public boolean verificarEdificioPosible(Construccion c){
			Iterator list = construccionesPosibles.iterator();
			Construccion busqueda = construccionesPosibles.element();
			Boolean founded = busqueda.esLoMismo(c);
			while ( (list.hasNext()) && (founded == false) ){
				Construccion auxiliar = (Construccion) list.next();
				founded = auxiliar.esLoMismo(c);
			}
			return founded;
		}
		public void actualizarEdificios(Construccion edificio) {
			if (!verificarEdificioPosible(edificio)){
				construccionesPosibles.add(edificio);
			}
		}
					
}

