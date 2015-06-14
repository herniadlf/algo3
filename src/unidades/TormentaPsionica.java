package src.unidades;

import src.Atacable;
import src.mapa.EspacioDisponible;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Sector;
import excepciones.ExcepcionPosicionInvalida;

public class TormentaPsionica extends PorRangoAtaque {
	
	public TormentaPsionica(Mapa mapa, int x, int y){
		
		danio = 100;
		energiaNecesaria = 75;
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
					objetoEnTierra.afectadoPorTormentaPsionica(danio);		
				}		
			}	
			if(!(sector.getElementoEnAire().getClass() == new EspacioDisponible().getClass())){
					Atacable objetoEnAire = (Atacable)sector.getElementoEnAire();
					objetoEnAire.afectadoPorTormentaPsionica(danio);	
			
			}
			
		} catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();	
		}	
	
	}
	
}
