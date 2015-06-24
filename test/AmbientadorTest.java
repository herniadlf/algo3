package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Ambientador;
import src.Jugador;
import src.mapa.Arbol;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.razas.Protoss;
import src.razas.Terran;
import src.construcciones.*;

public class AmbientadorTest {
	
	@Test
	public void ambientaElMapaCorrectamenteConLasBasesDeLosJugadores() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido {
		
		Jugador jugador1 = new Jugador("p1", "rojo", new Terran());
		
		Jugador jugador2 = new Jugador("p1", "rojo", new Protoss());
		
		Mapa mapa = new Ambientador().ambientarMapa(200, 20, jugador1, jugador2);
		
		int posPrincipal1 = (int) ((200) - (200 / 4));
		
		int posPrincipal2 = (200 - posPrincipal1);
		
		Mapeable base1 = mapa.obtenerContenidoEnPosicion(posPrincipal1, posPrincipal1).getElementoEnTierra();
		
		Assert.assertTrue(base1.esLoMismo(new DepositoDeSuministros()));
		
		Mapeable base2 = mapa.obtenerContenidoEnPosicion(posPrincipal2, posPrincipal2).getElementoEnTierra();
		
		Assert.assertTrue(base2.esLoMismo(new Pilon()));
		
	}
	
	@Test
	public void ambientaElMapaCorrectamenteConLasFuentesDeRecuros() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido {
		
		Jugador jugador1 = new Jugador("p1", "rojo", new Terran());
		
		Jugador jugador2 = new Jugador("p1", "rojo", new Protoss());
		
		Mapa mapa = new Ambientador().ambientarMapa(200, 20, jugador1, jugador2);
		
		int fuenteMinerales = 0;
		
		int fuenteGasVespeno = 0;
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				if(mapa.obtenerContenidoEnPosicion(i, j).getElementoEnTierra().esLoMismo(new FuenteDeMinerales())) {
					
					fuenteMinerales++;
					
				}
				
				if(mapa.obtenerContenidoEnPosicion(i, j).getElementoEnTierra().esLoMismo(new FuenteDeGasVespeno())) {
					 
					fuenteGasVespeno++;
					
				}
				
			}
		}
		
		Assert.assertEquals(fuenteMinerales, 8);
		
		Assert.assertEquals(fuenteGasVespeno, 8);
		
	}
	
	@Test
	public void ambientaElMapaCorrectamenteConArboles() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido {
		
		Jugador jugador1 = new Jugador("p1", "rojo", new Terran());
		
		Jugador jugador2 = new Jugador("p1", "rojo", new Protoss());
		
		Mapa mapa = new Ambientador().ambientarMapa(200, 20, jugador1, jugador2);
		
		int cantArboles = 0;

		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				if(mapa.obtenerContenidoEnPosicion(i,j).getElementoEnTierra().esLoMismo(new Arbol())) {
					
					cantArboles++;
					
				}	
			}
		}
		
		Assert.assertEquals(cantArboles, 20);
	}
	

}
