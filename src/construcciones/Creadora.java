package src.construcciones;
import java.util.ArrayList;

import src.unidades.Unidad;

public abstract class Creadora extends Construccion {
	
	ArrayList<Unidad> unidadesCreables;
	
	public void crearUnidad(){};
	
}
