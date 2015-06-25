package src.unidades;

public class Radiacion extends Magia{
	
	public Radiacion(Unidad afectado) {
		
		danio = 30;
		energiaNecesaria = 75;
		this.mapa = afectado.getMapa();
		posicionX = afectado.getPosicionX();
		posicionY = afectado.getPosicionY();
		this.setAlrededores();
		nombre = "Radiacion";
		
	}
	
	public void atacarEnEstaPosicion(int x,int y){
		
		if(this.hayAtacableEnTierra(x, y)){
				
			this.obtenerAtacadoEnTierra().afectadoPorRadiacion(danio);
			
		}		
		
	}
	
	
}
