package src;

import src.unidades.Unidad;

public class Escudo{
	
	private int resistencia; 
	private Atacable protegido;
	
	public Escudo(int resistencia, Atacable protegido) {
		
		this.resistencia = resistencia; 
		this.protegido = protegido;
		
	}
	
	public void atacar(int danio) {
		
		if(resistencia - danio < 0){
			
			int vidaAfectada= danio - resistencia;
			protegido.getVida().dismunuirVidaPorDanio();
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
