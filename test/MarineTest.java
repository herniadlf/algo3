package test;

import org.junit.Assert;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionElEdificioNoPerteneceATusConstrucciones;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Dinero;
import src.Juego;
import src.Jugador;
import src.construcciones.Barraca;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Marine;
import junit.framework.TestCase;

public class MarineTest extends TestCase{
	
	private Mapa mapa;
	private Jugador jugador;
	private Marine marine;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		mapa = new Mapa(50);
		jugador = new Jugador("Lionel Messi","rojo", new Terran());
		marine = new Marine();	
	}
	
	public void testMarineOcupa1enTransporte(){
		
		Assert.assertTrue(marine.getTransporte() == 1);
		
	}
	
	public void testMarineCuesta50deMineral() throws ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoCrearseUnidad, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionErrorPasoDeTurno, ExcepcionUnidadNoCorrespondiente{
		
		//Empieza con 800 de minerales 
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		
		Assert.assertEquals(jugador1.getDinero().getMinerales(),800);
		
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);	
		jugador1.getConstruccionesEnPie().add(barraca);
		juego.ordenarFabricacionUnidad(marine,(Creadora) barraca);
		
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		
		Assert.assertEquals(jugador1.getDinero().getMinerales(),750);
		
	}
	
	
	public void testmarineCuesta50mineralYseCreaCon40deVida() {

		Marine marine = new Marine();
			
		Assert.assertTrue((marine.getVida().obtenerVida()) == 40);
		Assert.assertEquals(marine.getCosto().getMinerales(),50);
		
	}
	
	public void testMarineSePuedeMoverHasta7posiciones() throws 
		ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos{	
		
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jug2 = new Jugador ("Williams", "azul", new Protoss());
		Juego juego = new Juego(jug1, jug2, 100, 0);
		
		Marine marine = new Marine();
		marine.setJugador(jug1);
		marine.setMapa(juego.getMapa());
		Posicion posicionMarine = new Posicion(1,1);
		marine.setPosicion(posicionMarine);
		
		try {
			marine.moverAPosicionDeterminada(1, 4);
		} catch (ExcepcionNoPuedeMoverseUnidad e) {
			e.printStackTrace();
		}
		
		Assert.assertTrue(marine.getPosicionX() == 1 & marine.getPosicionY() == 4);
		
		try {
			marine.moverAPosicionDeterminada(1, 11);
		} catch (ExcepcionNoPuedeMoverseUnidad e) {
			e.printStackTrace();
		}
		
		Assert.assertTrue(marine.getPosicionX() == 1 & marine.getPosicionY() == 11);
		
	}
	
	
	
}
