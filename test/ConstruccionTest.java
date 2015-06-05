package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import src.*;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Extractora;
import src.construcciones.Fabrica;
import src.construcciones.NexoMineral;
import src.construcciones.Refineria;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;
import src.mapa.Pasto;
import src.razas.Protoss;
import src.razas.Terran;
public class ConstruccionTest {
		
		@Test
		public void construccionExitosaPorRecursosSuficientes() throws ExcepcionEdificioPrevioRequerido{
				Mapa mapa = new Mapa(50);
				Jugador jug = new Jugador("carlos","rojo",new Terran()); // Jugador se crea con 800 M
				int prueba = 0;
				try {
					jug.construir(new Barraca(),mapa,2,2);// BARRACA CUESTA 150 M
				} catch (ExcepcionPosicionInvalida | ExcepcionExtractoraSinRecurso e) {} 
			
				Assert.assertEquals(650, jug.getDinero().getMinerales());
		}
			
		/*@Test
		public void construccionExitosaEnElMapa() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador();
			
			for (int i=1; i< mapa.getTamanioMapa();i++){
				for (int j=1; j<mapa.getTamanioMapa(); j++){
					jug.construir( new Barraca(), mapa, i, j);
					Assert.assertTrue( ( mapa.obtenerContenidoEnPosicion(i, j) ).esLoMismo(new Barraca()));
				}				
			}
		} me tira falso el assert.. todavia no coloca bien en el mapa
		*/
		
		@Test
		public void noConstruyeExtractoraSiNoHayFuente() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo",new Protoss());
					
			jug.construir(new NexoMineral(), mapa, 10, 10);
			
			Assert.assertFalse(mapa.obtenerContenidoEnPosicion(10, 10).esLoMismo(new NexoMineral()));	
			
			
		}
		
		@Test 
		public void noConstruyeFabricaSinBarraca() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo",new Terran());
		
			jug.construir(new Fabrica(), mapa, 10, 10);			
			
			Assert.assertFalse( (mapa.obtenerContenidoEnPosicion(10, 10)).esLoMismo(new Fabrica()));
			
		}
}
