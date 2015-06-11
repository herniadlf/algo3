package test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;

import excepciones.ExcepcionGeneral;
import src.Jugador;
import src.construcciones.Acceso;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.*;
import junit.framework.TestCase;

public class UnidadesProtossTest extends TestCase{
	
	private Jugador jugador;
	private Mapa mapa;
	
	protected void setUp() throws Exception {		

		super.setUp();
		Mapa mapa = new Mapa(50);
		Jugador jugador = new Jugador("El cid campeador","rojo", new Terran());
		
	}
	
	
	public void testZealotSeCreaCon60escudoY100deVida(){
		
		Acceso acceso = new Acceso();
		Zealot zealot = acceso.crearZealot();		
		
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 60 );
		
	}
	
	public void testAtacarZealotNuevoPorMarineSoloDaniaEscudo(){
		
		Acceso acceso = new Acceso();
		Zealot zealot = acceso.crearZealot();
		Marine marine = new Marine();
		
		marine.atacarEnTierra(zealot);
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 54 );
		
	}
	
	
	public void testZealotRecibeMultiplesAtaques (){
		Acceso acceso = new Acceso ();
		Zealot zealot = acceso.crearZealot();
		
		Marine primerMarine = new Marine();
		primerMarine.atacarEnTierra(zealot);
		
		Marine segundoMarine = new Marine();
		segundoMarine.atacarEnTierra(zealot);
		Marine tercerMarine = new Marine();
		tercerMarine.atacarEnTierra(zealot);
		Marine cuartoMarine = new Marine();
		cuartoMarine.atacarEnTierra(zealot);
		
		int danioTotalRecibido= zealot.getVida().obtenerDanioRecibido();
		
		
		Assert.assertTrue(danioTotalRecibido == 24 );
		
		}
	
	public void testDestruccionDeDragon(){
		
		Dragon dragon= new Dragon();
		dragon.getVida().aumentarDanioARecibir(200);
		dragon.getVida().dismunuirVidaPorDanio();
		
		Assert.assertTrue(dragon.getVida().devolverEstadoDeVida()==true);
		
		
		
	}
	
	
	public void testNaveTransporteProtossTransporta8Pasajeros(){
		
		NaveTransporteProtoss naveTransporte = new NaveTransporteProtoss();
		ArrayList<Marine> marines = new ArrayList<>();
		
		for(int i = 0 ; i < 8 ; i++){
			
			Marine marine = new Marine();
			marines.add(marine);
			
		}
			
		Iterator<Marine> i = marines.iterator();
		
		while(i.hasNext()){
			
			try{
	
				naveTransporte.llevar(i.next());
			} catch (ExcepcionGeneral e){
				
				e.getMessage();
			}
		}
		
		Assert.assertTrue(naveTransporte.cantidadPasajeros() == 8);		
		
	}
	

}
