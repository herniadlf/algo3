package src;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionFinDeTurnoPorMaximoDeAtaques;
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
	
	public void contabilizarAtaquesEnTurno () throws ExcepcionFinDeTurnoPorMaximoDeAtaques {
		
		ataques = ataques +1;
		if (ataques >= 10){			
			throw new ExcepcionFinDeTurnoPorMaximoDeAtaques("Maximo de ataques alcanzado");						
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
	
	public void reinicioAtaques(){
		ataques = 0;
	}
		
}

