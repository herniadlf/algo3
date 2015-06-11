package test;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import junit.framework.TestCase;
import src.unidades.*;

import org.junit.Assert;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import src.*;
import src.construcciones.*;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Marine;

public class UnidadesTerranTest extends TestCase{
	
		private Mapa mapa;
		private Jugador jugador;
	
		protected void setUp() throws Exception {
			
			super.setUp();
			mapa = new Mapa(50);
			jugador = new Jugador("carlos","rojo", new Terran());
			
		}
		
		public void testmarineCuesta50mineralYseCreaCon40deVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso {

			Barraca barraca = new Barraca();
			try {
				jugador.construir(barraca,mapa,3,4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Marine marine = barraca.crearMarine();
			
			Assert.assertTrue((marine.getVida().obtenerVida()) == 40);
			//falta chequear el mineral
		
		}
		
		
		public void testgolliatSeCreaCon125DeVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
			Fabrica fabrica = new Fabrica();
			try {
				jugador.construir(fabrica,mapa,4,4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Golliat golliat = fabrica.crearGolliat();
			
			Assert.assertTrue((golliat.getVida().obtenerVida()) == 125);
			
		}
		
	
		public void testespectroSeCreaCon120deVidaYNaveTransporteCon150() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
			PuertoEstelarTerran puertoEstelar = new PuertoEstelarTerran();
			try {
				jugador.construir(puertoEstelar,mapa,30,30);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Espectro espectro = puertoEstelar.crearEspectro();
			NaveTransporteTerran naveTransporte = puertoEstelar.crearNaveTransporteTerran();
			
			Assert.assertTrue(espectro.getVida().obtenerVida() == 120);
			Assert.assertTrue(naveTransporte.getVida().obtenerVida() == 150);
			
		}
		
		
		public void testmarineAtacaZealotSoloRompeEscudo() throws ExcepcionEdificioNoPuedeCrearUnidad{
			
			/*--------------------------------------------------------------------------------*/
			Mapa mapa = new Mapa(50);
			Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
			
			jug1.construir(new DepositoDeSuministros(), mapa, 30, 30);
			jug2.construir(new Pilon(), mapa, 36, 36);
			Construccion barraca = jug1.construir(new Barraca(),mapa,5,5);
			Construccion acceso = jug2.construir(new Acceso(), mapa, 8, 8);
			Unidad marine =jug1.crearUnidad(new Marine(),(Creadora) barraca, mapa);
			Unidad zealot = jug2.crearUnidad(new Zealot(), (Creadora)acceso, mapa);
			
			marine.atacarEnAire(zealot);
			Vida vida= zealot.getVida();
			Assert.assertTrue((vida.obtenerVida()) == 100);
			
			
			
			
			
		
		}	
		
		public void testEspectroPuedeAtacarEdificiosEnemigos() throws ExcepcionEdificioNoPuedeCrearUnidad{
			
			Mapa mapa = new Mapa(50);
			Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jug2 = new Jugador ("Williams", "azul", new Protoss());
			jug1.setDinero(999999, 999999);
			jug2.setDinero(9999, 9999);
			
			Construccion barraca = jug1.construir(new Barraca(),mapa,5,5);
			Construccion fabrica = jug1.construir(new Fabrica(),mapa, 9,9);
			Construccion puertoEstelarTerran = jug1.construir(new PuertoEstelarTerran(), mapa, 16, 16);
			NexoMineral nexo =  (NexoMineral) jug2.construir(new NexoMineral(), mapa, 18, 18);
			//Como creo 8 espectrs que requieren 2 suministros, en total necesito 16 suministros,
			//por lo cual construyo 4 depositosdesuminitros 
			//para tener 20 suministros disponibles (5 suministros extras x edificio)
			jug1.construir(new DepositoDeSuministros(), mapa, 1, 1);
			jug1.construir(new DepositoDeSuministros(), mapa, 9, 8);
			jug1.construir(new DepositoDeSuministros(), mapa, 10, 2);
			jug1.construir(new DepositoDeSuministros(), mapa, 10, 4);
			
			Unidad espectro = jug1.crearUnidad(new Espectro(),(Creadora) puertoEstelarTerran, mapa);
			Assert.assertTrue(nexo.getEscudo().obtenerResistenciaActual() == 250 );
			espectro.atacarEnAire(nexo);
			Assert.assertTrue(nexo.getEscudo().obtenerResistenciaActual() == 230 );
			
			
			
			
			
			
			
			
			
			/*Espectro espectro = new Espectro();
			NexoMineral nexoMineral = new NexoMineral();
			Assert.assertTrue(nexoMineral.getEscudo().obtenerResistenciaActual() == 250 );
			espectro.atacarEnAire(nexoMineral);
			Assert.assertTrue(nexoMineral.getEscudo().obtenerResistenciaActual() == 230 );*/
			
		}
	
		
	
	
}
