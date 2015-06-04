package test;

import org.junit.Assert;
import org.junit.Test;

import src.*;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
public class ConstruccionTest {
		
		@Test
		public void construccionExitosaPorRecursosSuficientes(){
				Construccion edificio = new Barraca();
				Jugador jug = new Jugador(); // Jugador se crea con 800 M
				
						
				jug.construir(edificio); // barraca cuesta 150 M
				
				Assert.assertEquals(650, jug.getDinero().getMinerales());
		}
			
}
