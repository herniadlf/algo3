package src;

import java.util.ArrayList;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.unidades.Unidad;

public class Juego {
	
	Mapa mapa;
	Jugador jugador1,jugador2, jugadorActual;
	Turno turno;
	
	public Juego(Mapa map2, Jugador jug1, Jugador jug2) {
		
		super();
		mapa = map2;
		jugador1 = jug1;
		jugador2 = jug2;
		jugadorActual = jug1;
		
		turno = new Turno();
	}

	public Mapa getMapa() {
		
		return mapa;
	}

	public Jugador getJugador1() {
		
		return jugador1;
		
	}
	
	public Jugador getJugador2() {
		
		return jugador2;
		
	}
	
	public Jugador getJugadorActual() {
		
		return jugadorActual;
		
	}
	
	public void pasarTurno () throws ExcepcionEdificioNoPuedeCrearUnidad{
		
		turno.aumentarTurno();
		if ((turno.devolverTurnoActual()%2)==0){
			
			jugadorActual = jugador2;
			
		}
		else {
			
			jugadorActual = jugador1;
			
		}
		
		jugadorActual.actualizarFabricacionUnidades(turno, mapa);	
		
	} 

	public void ordenarFabricacionUnidad (Unidad unidad, Creadora edificio){
		
		unidad.setEdificio(edificio);
		unidad.setTurnoInicioDeEntrenamiento(turno.devolverTurnoActual());
		jugadorActual.obtenerListaDeUnidadesAFabrica().add(unidad);		
		
	}
	
}
