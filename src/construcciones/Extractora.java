package src.construcciones;

import src.Jugador;
import src.Turno;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Mapeable;

public abstract class Extractora extends Construccion {
	
		protected static final int RECURSOS_POR_TURNO = 10;
		FuenteDeRecurso fuente;
		
		public FuenteDeRecurso getFuente(){
			
			return fuente;
			
		}
		
		public void setFuente(FuenteDeRecurso fuente){
			
			this.fuente = fuente; 
		}
		
		public Extractora(){
			
			super();
			setArquitecto(new ArquitectoDeExtractora());
			
		}

		public void pasoTurno(Turno turno, Mapa mapa, Jugador jugadorActual){
			
		}
		
}
