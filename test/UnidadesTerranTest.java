package test;

import org.junit.Assert;
import org.junit.Test;
import src.*;


public class UnidadesTerranTest {
	
	@Test
	public void marineCuesta50mineralYseCreaCon40deVida() {
		
		Terran terran = new Terran();
		Posicion posicion = new Posicion(10,20);
		Barraca barraca = terran.construirBarraca(posicion); 
		Marine marine = barraca.crearMarine();
		Assert.assertTrue((marine.getVida().obtenerVida()) == 40);
		//falta chequear el mineral, no continue por superposicion de conceptos en cuanto a esta implementacion
		
		
	}
}
