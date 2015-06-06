package src.unidades;

public class Escudo {
	
	private int resistencia; 
	private Unidad unidad;
	
	public Escudo(int resistencia, Unidad unidad) {
		
		this.resistencia = resistencia; 
		this.unidad = unidad;
		
	}
	
	public void atacar(int danio) {
		
		if(resistencia - danio < 0){
			
			int vidaAfectada= danio - resistencia;
			unidad.getVida().recibirDanio(vidaAfectada);
		}
		else {
			
			resistencia = resistencia - danio;
		}
		
	}
	
	public int obtenerResistenciaActual(){
		
		return resistencia;
		
	}
	
	private void recuperar(){
		
		// recuperar estado del escudo por turno
		
	}
	
	

}
