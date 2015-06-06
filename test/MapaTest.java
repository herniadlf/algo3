package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.*;
import src.construcciones.*;
import excepciones.*;

public class MapaTest {
	

	@Test
	public void creacionDelMapaConPasto() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa(10);
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				Assert.assertTrue((mapa.obtenerContenidoEnPosicion(i,j)).esPisable());
			}
		}
	}
	
	@Test
	public void colocoYObtengoElementoEnElMapa() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5), unaBarraca);
		
	}
	
	@Test (expected = ExcepcionYaHayElementoEnLaPosicion.class)
	public void siYaHayElementoLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Refineria unaRefineria = new Refineria();
		
		mapa.colocarEn(5, 5, unaRefineria);
		
	}
	
	
	@Test (expected = ExcepcionPosicionInvalida.class)
	public void ingresarPosicionNoValidaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(11, 11, unaBarraca);

	}
	
	
	@Test
	public void eliminarElementoDelMapa() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertFalse((mapa.obtenerContenidoEnPosicion(5,5)).esPisable());
		
		mapa.eliminarElementoEnPosicion(5,5);
		
		Assert.assertTrue((mapa.obtenerContenidoEnPosicion(5,5)).esPisable());
		
	}
	
	@Test
	public void calcularDitanciaEntreDosPuntos() {
		
		Mapa mapa = new Mapa(10);
		
		Assert.assertEquals(mapa.distanciaEntreLosPuntos(7, 5, 4, 1), 5);
	}
	
	@Test
	public void colocarArbolesEnMapa() throws ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(100);
		
		mapa.crearCantidadDeArbolesEnMapa(10);
		
		int casillerosNoPisables = 0;
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				if(!mapa.obtenerContenidoEnPosicion(i,j).esPisable()) {
					
					casillerosNoPisables++;
					
				}	
			}
		}
		
		Assert.assertEquals(casillerosNoPisables, 10);	
		
	}
	
	@Test (expected = ExcepcionSuperaLimenteDeArbolesPermitos.class)
	public void colocarMasArbolesQueElDiezPorcientoDelMapaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionYaHayElementoEnLaPosicion {
		
	Mapa mapa = new Mapa(100);
		
	mapa.crearCantidadDeArbolesEnMapa(20);
		
	}
		
}
