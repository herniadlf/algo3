package src.unidades;

import src.Atacable;
import src.mapa.EspacioDisponible;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Sector;
import excepciones.ExcepcionPosicionInvalida;

public class Radiacion extends PorRangoAtaque{
	
	public Radiacion(Unidad afectado, Mapa mapa) {
		
		danio = 30;
		energiaNecesaria = 75;
		this.mapa = mapa;
		posicionX = afectado.getPosicionX();
		posicionY = afectado.getPosicionY();
		this.setAlrededores();
		
	}
	
	public void atacarEnEstaPosicion(int x,int y){
		
		try {
			Sector sector = mapa.obtenerContenidoEnPosicion(x, y);
			if(!(sector.getElementoEnTierra().getClass() == new EspacioDisponible().getClass())){
				if(!(sector.getElementoEnTierra().getClass() == new FuenteDeRecurso().getClass())){
					Atacable objetoEnTierra = (Atacable)sector.getElementoEnTierra();
					objetoEnTierra.afectadoPorRadiacion(danio);		
				}		
			}	
			
		} catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();	
		}	
	
	}
	
	
	
}
