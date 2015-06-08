package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionEdificioPrevioRequerido;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.*;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.Extractora;
import src.construcciones.Fabrica;
import src.construcciones.NexoMineral;
import src.construcciones.Refineria;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;
import src.mapa.Pasto;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Marine;
public class ConstruccionTest {
		
		@Test
		public void construccionExitosaPorRecursosSuficientes() throws ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion{
				Mapa mapa = new Mapa(50);
				Jugador jug = new Jugador("carlos","rojo",new Terran()); // Jugador se crea con 800 M
			
				try {
					Construccion barraca = jug.construir(new Barraca(),mapa,2,2);// BARRACA CUESTA 150 M
				} catch (ExcepcionPosicionInvalida | ExcepcionExtractoraSinRecurso e) {} 
			
				Assert.assertEquals(650, jug.getDinero().getMinerales());
		}
			
		@Test
		public void construccionExitosaEnElMapa() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo",new Terran());
			
			for (int i=1; i< mapa.getTamanioMapa();i++){
				for (int j=1; j<mapa.getTamanioMapa(); j++){
					Construccion b = new Barraca();
					b = jug.construir(b, mapa, i, j);
					Assert.assertTrue( ( mapa.obtenerContenidoEnPosicion(i, j) ).esLoMismo(b));					
				}				
			}
		} 
		
		@Test (expected = ExcepcionExtractoraSinRecurso.class)
		public void noConstruyeExtractoraSiNoHayFuente() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo",new Protoss());
					
			Construccion nexoMineral = jug.construir(new NexoMineral(), mapa, 10, 10);
				
			
		}
		
		@Test (expected = ExcepcionEdificioPrevioRequerido.class)
		public void noConstruyeFabricaSinBarraca() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo",new Terran());
			
			Construccion fabrica = jug.construir(new Fabrica(), mapa, 10, 10);			
					 
		}
		
		@Test
		public void construccionExitosaDeFabricaConBarraca() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo", new Terran());
			
			Construccion barraca = jug.construir(new Barraca(), mapa, 5,5);
			Construccion fabrica = jug.construir(new Fabrica(), mapa, 6, 8);
			
			Assert.assertTrue ( mapa.obtenerContenidoEnPosicion(6, 8).esLoMismo(fabrica));
		}
		
		@Test
		public void creacionDeUnidadesAlrededorDeEdificio() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion, ExcepcionNoHayLugarParaCrear, ExcepcionEdificioNoPuedeCrearUnidad{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador ("carlos","rojo",new Terran());
			
			Construccion barraca = jug.construir(new Barraca(),mapa,5,5);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			jug.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			
			// VERIFICO QUE SE CREAN A LOS COSTADO DEL EDIFICIO
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(4,4).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(4,5).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(4,6).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(6,4).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(6,5).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(6,6).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(5,4).esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(5,6).esLoMismo(new Marine()));
		}
}
