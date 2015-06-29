package src.unidades;

public class EMP extends Magia {
	
	public EMP(Unidad atacado) {
		
		danio = 20; //Supuesto
		energiaNecesaria = 100;
		this.mapa = atacado.getMapa();
		posicionX = atacado.getPosicionX();
		posicionY = atacado.getPosicionY();
		this.setAlrededores();
		nombre = "EMP";
		
	}
	
	public void atacarEnEstaPosicion(int x,int y){
		
		if(this.hayAtacableEnTierra(x, y)){
			
			this.obtenerAtacadoEnTierra().atacarConEMP(danio);
			
		}	
		
	}
	
}
