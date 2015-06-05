package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import src.mapa.*;
import src.construcciones.*;
import excepciones.*;

public class MapaTest {
	

	@Test
	public void creacionDelMapaConPasto() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa(10);
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				Assert.assertTrue((mapa.obtenerContenidoEnPosicion(i,j)) instanceof Pasto);
			}
		}
	}
	
	@Test
	public void colocoYObtengoElementoEnElMapa() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5), unaBarraca);
		
	}
	
	
	@Test (expected = ExcepcionPosicionInvalida.class)
	public void ingresarPosicionNoValidaLanzaExcepcion() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(11, 11, unaBarraca);

	}
	
	
	@Test
	public void eliminarElementoDelMapa() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5), unaBarraca);
		
		mapa.eliminarElementoEnPosicion(5,5);
		
		Assert.assertTrue((mapa.obtenerContenidoEnPosicion(5,5)) instanceof Pasto);
		
	}
	
	@Test
	public void calcularDitanciaEntreDosPuntos() {
		
		Mapa mapa = new Mapa(10);
		
		Assert.assertEquals(mapa.distanciaEntreLosPuntos(7, 5, 4, 1), 5);
	}
		
}
