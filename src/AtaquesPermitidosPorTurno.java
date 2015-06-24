package src;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

	public class AtaquesPermitidosPorTurno {
	
		int ataques;
		Juego juego;
	
	
	public AtaquesPermitidosPorTurno (){
		
		this.ataques =0;
		
	}
	
	public void contabilizarAtaquesEnTurno () throws ExcepcionEdificioNoPuedeCrearUnidad,
		ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionErrorPasoDeTurno, 
		ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente{
		
		this.ataques = this.ataques +1;
		if (this.ataques ==10){
			
			this.ataques=0;
			juego.pasarTurno();
			
		}
		
	}
		
	public int obtenerAtaquesPermitidos (){
		
			return ataques;
			
	} 
	
	public void setJuego (Juego juego){
		
		this.juego= juego;
		
	}
	
	public Juego getJuego (){
		
		return this.juego;
		
	}
		
}

