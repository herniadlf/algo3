package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.*;
import src.unidades.Espectro;
import src.construcciones.*;

public class MapaTest {
	

	@Test (expected = ExcepcionTamanioDelMapaInvalido.class)
	public void CrearMapaConTamanioNoPermitidoLanzaExcepcion() throws ExcepcionTamanioDelMapaInvalido{
		
		//el tamanio maximo permitod es de 800
		Mapa mapa = new Mapa (900);
	}
	
	@Test
	public void creacionDelMapaConSectores() throws ExcepcionPosicionInvalida, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				Assert.assertTrue((mapa.obtenerContenidoEnPosicion(i,j)).getElementoEnTierra().esOcupable());
				Assert.assertTrue((mapa.obtenerContenidoEnPosicion(i,j)).getElementoEnAire().esOcupable());
			}
		}
	}
	
	@Test
	public void colocoYObtengoElementoEnElMapa() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5).getElementoEnTierra(), unaBarraca);	
		
	}
	
	@Test
	public void enUnMismoSectorHayElementoTerrestreYAereo() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Espectro unEspectro = new Espectro();
		
		mapa.colocarEn(5, 5, unEspectro);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5).getElementoEnTierra(), unaBarraca);
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5).getElementoEnAire(), unEspectro);
	
	}
	
	@Test (expected = ExcepcionYaHayElementoEnLaPosicion.class)
	public void siYaHayElementoLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Refineria unaRefineria = new Refineria();
		
		mapa.colocarEn(5, 5, unaRefineria);
		
	}
	
	
	@Test (expected = ExcepcionPosicionInvalida.class)
	public void ColocarEnPosicionNoValidaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(110, 110, unaBarraca);

	}
	
	@Test
	public void eliminarElementoDelMapa() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertFalse((mapa.obtenerContenidoEnPosicion(5,5)).getElementoEnTierra().esOcupable());
		
		mapa.eliminarElementoTerrestreEnPosicion(5,5);
		
		Assert.assertTrue((mapa.obtenerContenidoEnPosicion(5,5)).getElementoEnTierra().esOcupable());
		
	}
	
	@Test
	public void calcularDitanciaEntreDosPuntos() throws ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Assert.assertEquals(mapa.distanciaEntreLosPuntos(7, 5, 4, 1), 5);
	}
	
	@Test
	public void colocarArbolesEnMapa() throws ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		mapa.crearCantidadDeArbolesEnMapa(10);
		
		int casillerosConArboles = 0;
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				if(mapa.obtenerContenidoEnPosicion(i,j).getElementoEnTierra().esLoMismo(new Arbol())) {
					
					casillerosConArboles++;
					
				}	
			}
		}
		
		Assert.assertEquals(casillerosConArboles, 10);	
		
	}
	
	@Test (expected = ExcepcionSuperaLimenteDeArbolesPermitos.class)
	public void colocarMasArbolesQueElDiezPorcientoDelMapaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionYaHayElementoEnLaPosicion, ExcepcionTamanioDelMapaInvalido {
		
	Mapa mapa = new Mapa(100);
		
	mapa.crearCantidadDeArbolesEnMapa(20);
		
	}
	
	@Test
	public void construyeExtractoraSobreRecurso() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionExtractoraSinRecurso, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		FuenteDeMinerales unaFuenteDeMineral = new FuenteDeMinerales();
		
		Extractora unExtractorMineral = new CentroDeMineral();
	
		mapa.colocarEn(5, 5, unaFuenteDeMineral);
		
		mapa.colocarExtractorEn(5, 5, unExtractorMineral);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5, 5).getElementoEnTierra(), unExtractorMineral);
		
		
		
		FuenteDeGasVespeno unaFuenteDeGas = new FuenteDeGasVespeno();
		
		Extractora unExtractorGas = new Asimilador();
	
		mapa.colocarEn(7, 7, unaFuenteDeGas);
		
		mapa.colocarExtractorEn(7, 7, unExtractorGas);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(7, 7).getElementoEnTierra(), unExtractorGas);
				 
	}
	
	@Test (expected = ExcepcionExtractoraSinRecurso.class)
	public void noConstruyeExtractorSiNoHayRecurso() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionTamanioDelMapaInvalido {
		
		Mapa mapa = new Mapa(100);
		
		Extractora unExtractor = new CentroDeMineral();
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				mapa.verificarFuente(i, j, unExtractor);
			}
		}
	}
	
		
}
