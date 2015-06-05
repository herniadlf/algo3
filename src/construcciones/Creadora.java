package src.construcciones;
import java.util.AbstractCollection;
import java.util.ArrayList;

import src.unidades.Unidad;

public abstract class Creadora extends NoExtractora {
	
	AbstractCollection<Unidad> unidadesCreables;
	
	public Unidad crearUnidad(Unidad unidad){
		return unidad;
	}
		
}
