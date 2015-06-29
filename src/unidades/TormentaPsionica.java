package src.unidades;

import excepciones.ExcepcionPosicionInvalida;

public class TormentaPsionica extends Magia {
	
	public TormentaPsionica(Unidad atacado){
		
		danio = 100;
		energiaNecesaria = 75;
		this.mapa = atacado.getMapa();
		posicionX = atacado.getPosicionX();
		posicionY = atacado.getPosicionY();
		this.setAlrededores();
		nombre = "TormentaPsionica";
		
	}
	
	public void atacarEnEstaPosicion(int x,int y) {
		
		if(this.hayAtacableEnTierra(x, y)){	
				
			try {
				this.obtenerAtacadoEnTierra().afectadoPorTormentaPsionica(danio);
			} catch (ExcepcionPosicionInvalida e) {
				e.printStackTrace();
			}
						
		}		
				
		if(this.hayAtacableEnAire(x, y)){
				
			try {
				this.obtenerAtacadoEnAire().afectadoPorTormentaPsionica(danio);
			} catch (ExcepcionPosicionInvalida e) {
				e.printStackTrace();
			}	
		}
		
	}
	
}
