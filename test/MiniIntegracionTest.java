package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuministrosInsuficientes;
import excepciones.ExcepcionTopeDePoblacionMaxima;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;
import src.Jugador;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Fabrica;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.EspacioDisponible;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.Espectro;
import src.unidades.Marine;
import src.unidades.Unidad;

public class MiniIntegracionTest {
	
	
	@Test
	public void testCrearUnidadesSegunTurno() 
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionNoPudoCrearseUnidad, 
			ExcepcionErrorPasoDeTurno, 
			ExcepcionPosicionInvalida {
		Mapa mapa = new Mapa(50);
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(mapa, jugador1, jugador2);
		jugador1.setDinero(99999, 99999);
		
		jugador1.colocar(new DepositoDeSuministros(), mapa, 30, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 40, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 10, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 20, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 20, 42);		
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);		
		jugador1.getConstruccionesEnPie().add(barraca);
		
		Unidad marine = new Marine();
		Unidad marine2 = new Marine ();
		Unidad marine3 = new Marine ();		
		
		juego.ordenarFabricacionUnidad(marine,(Creadora) barraca);
		juego.ordenarFabricacionUnidad(marine2,(Creadora) barraca);
		juego.ordenarFabricacionUnidad(marine3,(Creadora) barraca);
		
		for (int i = 0; i<4;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();
		}			
		
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(4,6).getElementoEnTierra().esLoMismo(new Marine()));
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(6,6).getElementoEnTierra().esLoMismo(new Marine()));
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(5,6).getElementoEnTierra().esLoMismo(new Marine()));
		
	

		
		
	}
	
	@Test
	public void testCrearEdificiosSegunTurno()
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, ExcepcionErrorPasoDeTurno {
		Mapa mapa = new Mapa(50);
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(mapa, jugador1, jugador2);
		jugador1.setDinero(99999, 99999);
		
		Construccion barraca = new Barraca();
		
		juego.ordenFabricacionDeEdificios(barraca, 10, 10);

		
	Assert.assertFalse(juego.getMapa().obtenerContenidoEnPosicion(10,10).getElementoEnTierra().esLoMismo(new Barraca()));
	
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	juego.pasarTurno();
	
	
	
	Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(10,10).getElementoEnTierra().esLoMismo(new Barraca()));
	}
	
	@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
	public void noConstruyeFabricaSinBarraca() throws ExcepcionNoPudoColocarseEdificio
			 {
		Mapa mapa = new Mapa(50);
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		Juego juego = new Juego (mapa, jugador1, jugador2);
		
		juego.ordenFabricacionDeEdificios(new Fabrica(), 10, 10);			
				 
	}
	

}
