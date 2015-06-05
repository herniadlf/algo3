package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import src.*;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Extractora;
import src.construcciones.NexoMineral;
import src.construcciones.Refineria;
import src.mapa.Mapa;
public class ConstruccionTest {
		
		@Test
		public void construccionExitosaPorRecursosSuficientes(){
				Mapa mapa = new Mapa(50);
				Jugador jug = new Jugador(); // Jugador se crea con 800 M
				
				try {
					jug.construir(new Barraca(),mapa,2,2);// BARRACA CUESTA 150 M
				} catch (ExcepcionPosicionInvalida e) {					
					e.printStackTrace();
				} 
				
				Assert.assertEquals(650, jug.getDinero().getMinerales());
		}
			

}
