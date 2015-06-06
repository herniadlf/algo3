package test;



import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import junit.framework.TestCase;
import src.unidades.*;

import org.junit.Assert;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import src.*;
import src.construcciones.*;
import src.mapa.Mapa;
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
		
	
		public void testespectroSeCreaCon120deVidaNaveCienciaCon200yNaveTransporteCon150() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
			PuertoEstelarTerran puertoEstelar = new PuertoEstelarTerran();
			try {
				jugador.construir(puertoEstelar,mapa,30,30);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Espectro espectro = puertoEstelar.crearEspectro();
			NaveCiencia naveCiencia = puertoEstelar.crearNaveCiencia();
			NaveTransporteTerran naveTransporte = puertoEstelar.crearNaveTransporteTerran();
			
			Assert.assertTrue(espectro.getVida().obtenerVida() == 120);
			Assert.assertTrue(naveCiencia.getVida().obtenerVida() == 200);
			Assert.assertTrue(naveTransporte.getVida().obtenerVida() == 150);
			
		}
		
		
		public void testmarineAtacaZealotSoloRompeEscudo(){
			
			Marine marine = new Marine();
			Zealot zealot = new Zealot ();
			marine.atacarEnAire(zealot);
			Vida vida= zealot.getVida();
			Assert.assertTrue((vida.obtenerVida()) == 100);	
		
		}
		
		public void testNaveCienciaUsaRadiacionContraEnemigoYPierde75Energia(){
			
			NaveCiencia naveCiencia = new NaveCiencia();
			Dragon dragon = new Dragon();
			
			Assert.assertTrue(naveCiencia.obtenerEnergia() == 50);
			
			naveCiencia.pasoTurno();
			naveCiencia.pasoTurno();
			naveCiencia.pasoTurno();
			
			Assert.assertTrue(naveCiencia.obtenerEnergia() == 80);
			naveCiencia.atacarConRadiacion(dragon);
			Assert.assertTrue(naveCiencia.obtenerEnergia() == 5);
			
			
		}
		
	
}
