package test;



import src.unidades.*;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionPosicionInvalida;
import src.*;
import src.construcciones.*;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.Marine;


public class UnidadesTerranTest {
	
		@Test
		public void marineCuesta50mineralYseCreaCon40deVida() throws ExcepcionPosicionInvalida {
			Mapa mapa = new Mapa(50);
			Jugador jugador = new Jugador("carlos","rojo", new Terran());
			Barraca barraca = new Barraca();
			jugador.construir(barraca,mapa,3,4); 
			Marine marine = barraca.crearMarine();
			
			Assert.assertTrue((marine.getVida().obtenerVida()) == 40);
			//falta chequear el mineral
		
		}
		
		@Test
		public void golliatSeCreaCon125DeVida() throws ExcepcionPosicionInvalida{
			Mapa mapa = new Mapa(50);
			Jugador jugador = new Jugador("carlos","rojo", new Terran());
			Fabrica fabrica = new Fabrica();
			jugador.construir(fabrica,mapa,4,4); 
			Golliat golliat = fabrica.crearGolliat();
			
			Assert.assertTrue((golliat.getVida().obtenerVida()) == 125);
			
		}
		
		@Test
		public void espectroSeCreaCon120deVidaNaveCienciaCon200yNaveTransporteCon150() throws ExcepcionPosicionInvalida{
			Mapa mapa = new Mapa(50);
			Jugador jugador = new Jugador("carlos","rojo", new Terran());
			PuertoEstelarTerran puertoEstelar = new PuertoEstelarTerran();
			jugador.construir(puertoEstelar,mapa,30,30);
			Espectro espectro = puertoEstelar.crearEspectro();
			NaveCiencia naveCiencia = puertoEstelar.crearNaveCiencia();
			NaveTransporteTerran naveTransporte = puertoEstelar.crearNaveTransporteTerran();
			
			Assert.assertTrue(espectro.getVida().obtenerVida() == 120);
			Assert.assertTrue(naveCiencia.getVida().obtenerVida() == 200);
			Assert.assertTrue(naveTransporte.getVida().obtenerVida() == 150);
			
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
