package src.unidades;

import java.util.ArrayList;
import excepciones.ExcepcionPosicionInvalida;
import src.Atacable;
import src.mapa.EspacioDisponible;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.mapa.Sector;

public class EMP extends PorRangoAtaque {
	
	protected ArrayList<Posicion> alrededores;	
	
	public EMP(Mapa mapa, int x, int y) {
		
		danio = 20; //supuesto
		energiaNecesaria = 100;
		this.mapa = mapa;
		posicionX = x;
		posicionY = y;
		this.setAlrededores();
		
	}
	

	public void atacarEnEstaPosicion(int x,int y){
		
		try {
			Sector sector = mapa.obtenerContenidoEnPosicion(x, y);
			if(!(sector.getElementoEnTierra().getClass() == new EspacioDisponible().getClass())){
				if(!(sector.getElementoEnTierra().getClass() == new FuenteDeRecurso().getClass())){
					Atacable objetoEnTierra = (Atacable)sector.getElementoEnTierra();
					objetoEnTierra.atacarConEMP(danio);		
				}		
			}	
			
		} catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();	
		}	
	
	}
	
}
