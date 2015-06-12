package src;

import java.util.ArrayList;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuministrosInsuficientes;
import excepciones.ExcepcionTopeDePoblacionMaxima;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.mapa.Escombros;
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
	
	public void pasarTurno () 
	
			throws ExcepcionEdificioNoPuedeCrearUnidad,
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear,
			ExcepcionYaHayElementoEnLaPosicion{
		
		turno.aumentarTurno();
		if ((turno.devolverTurnoActual()%2)==0){
			
			jugadorActual = jugador2;
			
		}
		else {
			
			jugadorActual = jugador1;
			
		}
		
		jugadorActual.pasoTurno(turno,mapa);
	
		
	} 

	public void ordenarFabricacionUnidad (Unidad unidad, Creadora edificio) 
	
		throws ExcepcionUnidadNoCorrespondiente, 
		ExcepcionRecursoInsuficiente, ExcepcionSuministrosInsuficientes, 
		ExcepcionTopeDePoblacionMaxima{
		
		jugadorActual.verificacionUnidad(unidad, edificio);
		unidad.setEdificio(edificio);
		unidad.setTurnoInicioDeEntrenamiento(turno.devolverTurnoActual());
		edificio.agregarUnidadAEntrenamiento(unidad);
		
	}
	
	public void ordenFabricacionDeEdificios (Construccion construccion, int x, int y)
			throws ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, 
			ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionExtractoraSinRecurso{
		
		jugadorActual.verificacionEdificio(construccion);	
		construccion.setPosicionX(x);
		construccion.setPosicionY(y);
		construccion.verificarTerreno(mapa,x,y);
		construccion.setTurnoInicioDEConstruccion(turno.devolverTurnoActual());
		jugadorActual.obtenerConstruccionesEnCamino().add(construccion);		
		
	}
		
	
}
