package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Mineral;
import src.mapa.*;
import src.unidades.Espectro;
import src.unidades.Marine;
import src.construcciones.*;
import excepciones.*;

public class MapaTest {
	

	@Test
	public void creacionDelMapaConSectores() throws ExcepcionPosicionInvalida {
		
		Mapa mapa = new Mapa(10);
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				
				Assert.assertTrue((mapa.obtenerContenidoEnPosicion(i,j)).getElementoEnTierra().esOcupable());
				Assert.assertTrue((mapa.obtenerContenidoEnPosicion(i,j)).getElementoEnAire().esOcupable());
			}
		}
	}
	
	@Test
	public void colocoYObtengoElementoEnElMapa() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5).getElementoEnTierra(), unaBarraca);	
		
	}
	
	@Test
	public void enUnMismoSectorHayElementoTerrestreYAereo() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Espectro unEspectro = new Espectro();
		
		mapa.colocarEn(5, 5, unEspectro);
		
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5).getElementoEnTierra(), unaBarraca);
		Assert.assertEquals(mapa.obtenerContenidoEnPosicion(5,5).getElementoEnAire(), unEspectro);
	
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
	public void ColocarEnPosicionNoValidaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(11, 11, unaBarraca);

	}
	
	@Test
	public void eliminarElementoDelMapa() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Mapa mapa = new Mapa(10);
		
		Barraca unaBarraca = new Barraca();
		
		mapa.colocarEn(5, 5, unaBarraca);
		
		Assert.assertFalse((mapa.obtenerContenidoEnPosicion(5,5)).getElementoEnTierra().esOcupable());
		
		mapa.eliminarElementoTerrestreEnPosicion(5,5);
		
		Assert.assertTrue((mapa.obtenerContenidoEnPosicion(5,5)).getElementoEnTierra().esOcupable());
		
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
	public void colocarMasArbolesQueElDiezPorcientoDelMapaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionYaHayElementoEnLaPosicion {
		
	Mapa mapa = new Mapa(100);
		
	mapa.crearCantidadDeArbolesEnMapa(20);
		
	}
	
	@Test
	public void construyeExtractoraSobreRecurso() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionExtractoraSinRecurso {
		
		Mapa mapa = new Mapa(10);
		
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
	public void noConstruyeExtractorSiNoHayRecurso() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso {
		
		Mapa mapa = new Mapa(10);
		
		Extractora unExtractor = new CentroDeMineral();
		
		for (int i = 1; i <= mapa.getTamanioMapa(); i++) {
			for (int j = 1; j <= mapa.getTamanioMapa(); j++) {
				mapa.verificarFuente(i, j, unExtractor);
			}
		}
	}
	
		
}
