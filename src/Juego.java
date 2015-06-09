package src;

import src.mapa.Mapa;

public class Juego {
	
	Mapa mapa;
	Jugador jugador1,jugador2;
	
	public Juego(Mapa map2, Jugador jug1, Jugador jug2) {
		super();
		mapa = map2;
		jugador1 = jug1;
		jugador2 = jug2;
	}

	public Mapa getMapa() {
		
		return mapa;
	}

	public Jugador getJugador1() {
		return jugador1;
	}
	
	public Jugador getJugador2(){
		return jugador2;
	}

	
}
