package test;

import junit.framework.TestCase;
import src.Juego;
import src.Jugador;
import src.construcciones.Barraca;
import src.construcciones.Creadora;
import src.construcciones.Fabrica;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Terran;
import src.unidades.*;

import org.junit.Assert;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionElTransporteNoEstaEnElAlcancePermitido;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionNoSePuedenTransportasUnidadesVoladoras;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class NaveTransporteTerranTest extends TestCase {
	
	private NaveTransporteTerran naveTransporte;
	private Mapa mapa;
	private Creadora barraca;
	private Creadora puertoEstelar;
	private Jugador jugador1;
	private Jugador jugador2;
	
	
	protected void setUp() throws Exception {
		
		
		super.setUp();
		jugador1 = new Jugador ("carlos","rojo",new Terran());
		jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
				
		barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 50, 50);
		jugador1.getConstruccionesEnPie().add(barraca);
		jugador1.colocar(new Fabrica(), mapa, 10, 10);
		jugador1.getConstruccionesEnPie().add(new Fabrica());
	 	puertoEstelar = (Creadora) jugador1.colocar(new PuertoEstelarTerran(), mapa, 49, 50);
		
		naveTransporte = new NaveTransporteTerran();
		puertoEstelar.colocarUnidad(naveTransporte, mapa);
	}
	
	public void testNaveTransporteSeCreaCon150deVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		Assert.assertTrue(naveTransporte.getVida().obtenerVida() == 150);
			
	}
	
	public void testNaveTransporteTerranTransporta8Marines() throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionNoSePuedeTransportar, ExcepcionUnidadNoCorrespondiente{
		
		for(int i = 0 ; i < 8 ; i++){
			
			Marine marine = new Marine();
			barraca.colocarUnidad(marine, mapa);
			naveTransporte.llevar(marine);
			
		}
		
		Assert.assertTrue(naveTransporte.cantidadPasajeros() == 8);		
			
	}
	
	public void testNaveTransporteSoloPuedeLlevar4Golliats() throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionNoSePuedeTransportar, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente{
		
		Creadora fabrica = (Creadora) jugador1.colocar(new Fabrica(), mapa, 51, 51);
		
		for(int i = 0 ; i < 4 ; i++){
			
			Golliat goliat = new Golliat();
			fabrica.colocarUnidad(goliat, mapa);
			naveTransporte.llevar(goliat);
			
		}
		
		Assert.assertTrue(naveTransporte.cantidadPasajeros() == 4);
	}
	
	
	public void testTransporteDeUnidadesAUnDeterminadoPunto() throws ExcepcionNoPudoColocarseEdificio, ExcepcionNoPudoCrearseUnidad, ExcepcionErrorPasoDeTurno, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionElTransporteEstaLleno, ExcepcionNoHayLugarParaCrear, ExcepcionNoSePuedenTransportasUnidadesVoladoras, ExcepcionElTransporteNoEstaEnElAlcancePermitido, ExcepcionNoSePuedeTransportar, ExcepcionNoPuedeMoverseUnidad, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido{
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jug2 = new Jugador ("Williams", "azul", new Terran());
		Juego juego = new Juego(jug1, jug2, 100, 0);
		
		
		Marine marine = new Marine();
		marine.setJugador(jug1);
		marine.setMapa(juego.getMapa());
		Posicion posicionMarine = new Posicion(1,1);
		marine.setPosicion(posicionMarine);
		
		
		Marine marine1= new Marine();
		Marine marine2= new Marine();
		Marine marine3= new Marine();
		marine1.setMapa(juego.getMapa());
		marine2.setMapa(juego.getMapa());
		marine3.setMapa(juego.getMapa());
		
		
		
		marine1.setJugador(jug1);
		marine2.setJugador(jug1);
		marine3.setJugador(jug1);
		
		naveTransporte.setJugador(jug1);
		
		
		mapa.colocarEn(1, 2, naveTransporte);
		Posicion posicion = new Posicion (1,2);
		naveTransporte.setPosicion(posicion);
		naveTransporte.setMapa(juego.getMapa());
		
		mapa.colocarEn(2,5,marine1);
		Posicion posicion1 = new Posicion (2,5);
		marine1.setPosicion(posicion1);
		mapa.colocarEn(3, 5, marine2);
		Posicion posicion2 = new Posicion (3,5);
		marine2.setPosicion(posicion2);
		mapa.colocarEn(4, 5, marine3);
		Posicion posicion3 = new Posicion (6,6);
		marine3.setPosicion(posicion3);
		
		naveTransporte.llevar(marine1);
		naveTransporte.llevar(marine2);
		naveTransporte.llevar(marine3);
		naveTransporte.transportarUnidades(8, 8);
		
		// Verifico que las unidades hallan viajado a la posicion determinada 
		Assert.assertTrue(marine1.getPosicionX() == 7);
		Assert.assertTrue(marine1.getPosicionY()==9);
		
		Assert.assertTrue(marine2.getPosicionX() == 8);
		Assert.assertTrue(marine2.getPosicionY()==9);
		
		Assert.assertTrue(marine3.getPosicionX() == 9);
		Assert.assertTrue(marine3.getPosicionY()==9);

	}
	
	public void testNaveTranporteNoPuedeLlevarEspectro() throws ExcepcionElTransporteEstaLleno, ExcepcionElTransporteNoEstaEnElAlcancePermitido, ExcepcionNoSePuedeTransportar, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionUnidadNoCorrespondiente{
		
		Espectro espectro = new Espectro();
		puertoEstelar.colocarUnidad(espectro, mapa);
		
		try {
			naveTransporte.llevar(espectro);
		} catch (ExcepcionNoSePuedeTransportar e) {
			e.printStackTrace();
		}
		
	}
	
	public void testNaveTranporteNoPuedeLlevarNaveCiencia() throws ExcepcionNoSePuedeTransportar, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionUnidadNoCorrespondiente{
		
		NaveCiencia naveCiencia = new NaveCiencia();
		puertoEstelar.colocarUnidad(naveCiencia, mapa);
		try {
			naveTransporte.llevar(naveCiencia);
		} catch (ExcepcionNoSePuedeTransportar e) {
			e.printStackTrace();
		}
		
	}
	
	public void testNaveTransporteNoPuedeLlevarOtraNaveDeTransporte() throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionUnidadNoCorrespondiente{
		
		NaveTransporteTerran otraNaveTransporte = new NaveTransporteTerran();
		puertoEstelar.colocarUnidad(otraNaveTransporte, mapa);
		try {
			naveTransporte.llevar(otraNaveTransporte);
		} catch (ExcepcionNoSePuedeTransportar e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
