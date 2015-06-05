package src.construcciones;

import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Mapeable;

public abstract class Extractora extends Construccion {
		FuenteDeRecurso fuente;
		
		public Extractora(FuenteDeRecurso recursoAExtraer){
			fuente = recursoAExtraer;
		}
		public void extraer(){};
		
}
