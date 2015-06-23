package src;


import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionElEdificioNoPerteneceATusConstrucciones;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuministrosInsuficientes;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTopeDePoblacionMaxima;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.unidades.Unidad;

public class Juego {
	
	Mapa mapa;
	Jugador jugador1,jugador2, jugadorActual;
	Turno turno;
	
	public Juego(Jugador jug1, Jugador jug2, int tamanioMapa, int cantidadArboles) throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos {
		
		super();
		jugador1 = jug1;
		jugador2 = jug2;
		mapa = new Ambientador().ambientarMapa(tamanioMapa, cantidadArboles, jug1, jug2);		
		jugadorActual = jug1;		
		turno = new Turno();
		
	}
	
	public Juego(){
		
		jugador1 = new Jugador();
		jugador2 = new Jugador();
		jugadorActual = jugador1;
		
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
	
	public void pasarTurno () throws ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente 	{
		
		turno.aumentarTurno();
		if ((turno.devolverTurnoActual()%2)==0){
			
			jugadorActual = jugador2;
			
		}
		else {
			
			jugadorActual = jugador1;
			
		}
		
		jugadorActual.pasoTurno(turno,mapa);
	
		
	} 

	public void ordenarFabricacionUnidad (Unidad unidad, Creadora edificio) throws ExcepcionNoPudoCrearseUnidad, ExcepcionElEdificioNoPerteneceATusConstrucciones {
		
		try {
			jugadorActual.verificacionUnidad(unidad, edificio);
			jugadorActual.verificarEdificio(edificio);
			unidad.setDuenio(jugadorActual.getNombre());
			unidad.setJugador(jugadorActual);
			unidad.setEdificio(edificio);
			unidad.setTurnoInicioDeEntrenamiento(turno.devolverTurnoActual());
			unidad.setAtaquesPermitidosPorTurno(jugadorActual.getAtaquesPermitidosPorTurno());
			edificio.agregarUnidadAEntrenamiento(unidad);
		} catch (ExcepcionUnidadNoCorrespondiente
				| ExcepcionRecursoInsuficiente
				| ExcepcionSuministrosInsuficientes
				| ExcepcionElEdificioNoPerteneceATusConstrucciones
				| ExcepcionTopeDePoblacionMaxima e) {
			throw new ExcepcionNoPudoCrearseUnidad(e);
		}
	
		
	}
	
	public void ordenFabricacionDeEdificios (Construccion construccion, int x, int y) throws ExcepcionNoPudoColocarseEdificio {
		
		try {
			jugadorActual.verificacionEdificio(construccion);
			construccion.setDuenio(jugadorActual.getNombre());
			construccion.setPosicionX(x);
			construccion.setPosicionY(y);
			construccion.verificarTerreno(mapa,x,y);
			construccion.setTurnoInicioDEConstruccion(turno.devolverTurnoActual());
			jugadorActual.obtenerConstruccionesEnCamino().add(construccion);		
			jugadorActual.gastarPlata(construccion.getCosto());
		} catch (ExcepcionConstruccionNoCorrespondiente
				| ExcepcionRecursoInsuficiente | 
				ExcepcionYaHayElementoEnLaPosicion | 
				ExcepcionExtractoraSinRecurso |ExcepcionPosicionInvalida e) {	
			throw new ExcepcionNoPudoColocarseEdificio(e);
		}	
	
	}

	public void setJugadorActual(Jugador jugador) {
	
			jugadorActual = jugador;	
	}		
	public int getTurno(){
		return (turno.devolverTurnoActual()/2);
	}
		
}