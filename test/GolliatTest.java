package test;
import java.util.ArrayList;

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
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;
import src.Jugador;
import src.construcciones.Barraca;
import src.construcciones.Creadora;
import src.construcciones.Fabrica;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Golliat;
import src.unidades.Marine;
import src.unidades.Unidad;
import junit.framework.TestCase;

public class GolliatTest extends TestCase{
	
	private Mapa mapa;
	private Jugador jugador;
	private Golliat golliat;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		mapa = new Mapa(100);
		jugador = new Jugador("Lionel Messi","rojo", new Terran());
		golliat = new Golliat();	
		
	}
	
	public void testgolliatSeCreaCon125DeVida() {
		
		Golliat golliat = new Golliat();		
		Assert.assertTrue((golliat.getVida().obtenerVida()) == 125);
			
	}
	
	public void testGolliatDemora3TurnosEnDesarrollarse() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionNoPudoCrearseUnidad, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionErrorPasoDeTurno, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		
		Assert.assertEquals(jugador1.getDinero().getMinerales(),200);
		Assert.assertEquals(jugador1.getDinero().getGasVespeno(),200);
		
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);	
		jugador1.getConstruccionesEnPie().add(barraca);
		
		Creadora fabrica = (Creadora) jugador1.colocar(new Fabrica(), mapa, 5, 5);	
		jugador1.getConstruccionesEnPie().add(fabrica);
		juego.ordenarFabricacionUnidad(golliat,(Creadora)fabrica);
		
		for (int i = 0; i<6;i++){ // pasar turnos para que se cree
			juego.pasarTurno();
			juego.pasarTurno();
		}	
		
		Assert.assertTrue(jugador1.contieneALaUnidad(golliat));
	
		
	}
	
	public void testGolliatCuesta100deMineral50Gas() throws ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoCrearseUnidad, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionErrorPasoDeTurno, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		//Empieza con 800 de minerales y 400 de gas vespeno
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		
		Assert.assertEquals(jugador1.getDinero().getMinerales(),200);
		Assert.assertEquals(jugador1.getDinero().getGasVespeno(),200);
		
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);	
		jugador1.getConstruccionesEnPie().add(barraca);
		
		Creadora fabrica = (Creadora) jugador1.colocar(new Fabrica(), mapa, 5, 5);	
		jugador1.getConstruccionesEnPie().add(fabrica);
		juego.ordenarFabricacionUnidad(golliat,(Creadora)fabrica);
		
		for (int i = 0; i<6;i++){ // pasar turnos para que se cree
			juego.pasarTurno();
			juego.pasarTurno();
		}	
		
		Assert.assertEquals(jugador1.getDinero().getMinerales(),100);
		Assert.assertEquals(jugador1.getDinero().getGasVespeno(),150);
		
	}
	
	public void testGolliatSePuedeMoverHasta8posiciones() throws 
		ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido{	
	
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jug2 = new Jugador ("Williams", "azul", new Protoss());
		Juego juego = new Juego(jug1, jug2, 100, 0);
	
		Golliat golliat = new Golliat();
		golliat.setJugador(jug1);
		golliat.setMapa(juego.getMapa());
		Posicion posicionMarine = new Posicion(1,1);
		golliat.setPosicion(posicionMarine);
	
		try {
			golliat.moverAPosicionDeterminada(1, 4);
		} catch (ExcepcionNoPuedeMoverseUnidad e) {
			e.printStackTrace();
		}
	
		Assert.assertTrue(golliat.getPosicionX() == 1 & golliat.getPosicionY() == 4);
	
		try {
			golliat.moverAPosicionDeterminada(1, 12);
		} catch (ExcepcionNoPuedeMoverseUnidad e) {
			e.printStackTrace();
		}
	
		Assert.assertTrue(golliat.getPosicionX() == 1 & golliat.getPosicionY() == 12);
	
	}


}
