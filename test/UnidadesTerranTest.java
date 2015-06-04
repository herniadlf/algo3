package test;



import src.unidades.*;

import org.junit.Assert;
import org.junit.Test;

import src.*;
import src.construcciones.Barraca;
import src.razas.Terran;
import src.unidades.Marine;


public class UnidadesTerranTest {
	
	@Test
	public void marineCuesta50mineralYseCreaCon40deVida() {
		
		Jugador jugador = new Jugador("carlos","rojo", new Terran());
		Barraca barraca = new Barraca();
		jugador.construir(barraca); 
		Marine marine = barraca.crearMarine();
		Assert.assertTrue((marine.getVida().obtenerVida()) == 40);
		//falta chequear el mineral, no continue por superposicion de conceptos en cuanto a esta implementacion
		
		
	}
	
	
		
		@Test
		public void marineAtacaZealotSinRomperEscudo (){
			
			Marine marine = new Marine();
			Zealot zealot = new Zealot ();
			marine.atacarEnAire(zealot);
		Vida vida= zealot.getVida();
		Assert.assertTrue((vida.obtenerVida()) == 40);
		
	
		
		
		
		
		
		
		
		
	}
	
	
}
