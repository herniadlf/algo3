package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import src.mapa.*;
import src.construcciones.*;
import excepciones.*;

public class MapaTest {
	

	@Test
	public void CreacionDelMapaConPasto() {
		
		Mapa mapa = new Mapa();
		
		Assert.assertTrue((mapa.obtenerContenidoEnPosicion(10,10)) instanceof Pasto);
		
	}
	
	@Test
	public void colocoYObtengoElementoEnElMapa() {
		
		Mapa mapa = new Mapa();
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5), unaBarraca);
		
	}
	
	
	@Test (expected = ExcepcionPosicionInvalida.class)
	public void IngresarPosicionNoValidaLanzaExcepcion() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa();

		Posicion unaPosicion = new Posicion(500,500);
		
		mapa.validarCoordenadas(unaPosicion);
		
	}
		
}
