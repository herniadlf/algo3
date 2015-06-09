package test;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import src.Juego;
import src.Jugador;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.mapa.Mapa;
import src.razas.Protoss;
import src.razas.Terran;

public class JuegoTest extends TestCase{
		
		Mapa map;
		Jugador jug1,jug2;
		Juego juego;
		
		protected void setUp() throws Exception{
			super.setUp();
			map = new Mapa(50);
			jug1 = new Jugador("Carlos","verde",new Terran());			
			jug2 = new Jugador("Fernando","rojo",new Protoss());
			juego = new Juego(map,jug1,jug2);
		}
		
		
		public void testNuevaPartidaGeneraMapaYJugadores(){				
					
			assertEquals(map, juego.getMapa());
			assertEquals(jug1, juego.getJugador1());
			assertEquals(jug2, juego.getJugador2());
		}
		
	
		public void testConstruccionDeAmbosJugadores() throws ExcepcionPosicionInvalida{
			juego.getJugador1().construir(new Barraca(), map, 4, 4);			
			assertTrue ( map.obtenerContenidoEnPosicion(4, 4).getElementoEnTierra().esLoMismo(new Barraca()) );
			
			juego.getJugador2().construir(new Acceso(), map, 8, 8);
			assertTrue ( map.obtenerContenidoEnPosicion(8, 8).getElementoEnTierra().esLoMismo(new Acceso()));		
			
		}
}
