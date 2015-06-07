package src.construcciones;

import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Mapeable;

public abstract class Extractora extends Construccion {
		FuenteDeRecurso fuente;
		
		public FuenteDeRecurso getFuente(){
			return fuente;
		}
		public void setFuente(FuenteDeRecurso f){
			fuente = f; 
		}
		public Extractora(){
			setConstructor(new ArquitectoDeExtractora());
		}

		public void extraer(){};
		
}
