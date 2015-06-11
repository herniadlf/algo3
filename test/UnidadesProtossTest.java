package test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionGeneral;
import src.Jugador;
import src.Vida;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Pilon;
import src.mapa.Mapa;
import src.razas.Protoss;
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
	
	public void testAtacarZealotNuevoPorMarineSoloDaniaEscudo() throws ExcepcionEdificioNoPuedeCrearUnidad{
		
		Mapa mapa = new Mapa(50);
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		jug1.setDinero(9999, 9999);
		Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
		
		
		jug1.construir(new DepositoDeSuministros(), mapa, 30, 30);
		jug2.construir(new Pilon(), mapa, 36, 36);
		Construccion barraca = jug1.construir(new Barraca(),mapa,5,5);
		Construccion acceso = jug2.construir(new Acceso(), mapa, 8, 8);
		Unidad marine =jug1.crearUnidad(new Marine(),(Creadora) barraca, mapa);
		
		
		
		Zealot zealot =  (Zealot) jug2.crearUnidad(new Zealot(), (Creadora)acceso, mapa);
		
		
		
		
		marine.atacarEnTierra(zealot);
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 54 );
		
	}
	
	
	public void testZealotRecibeMultiplesAtaques () throws ExcepcionEdificioNoPuedeCrearUnidad{
		Mapa mapa = new Mapa(50);
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		jug1.setDinero(9999, 9999);
		Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
		
		
		jug1.construir(new DepositoDeSuministros(), mapa, 30, 30);
		jug2.construir(new Pilon(), mapa, 36, 36);
		Construccion barraca = jug1.construir(new Barraca(),mapa,5,5);
		Construccion acceso = jug2.construir(new Acceso(), mapa, 8, 8);
		Unidad primerMarine =jug1.crearUnidad(new Marine(),(Creadora) barraca, mapa);
		Unidad segundoMarine = jug1.crearUnidad(new Marine(),(Creadora) barraca, mapa);
		Unidad tercerMarine = jug1.crearUnidad(new Marine(),(Creadora) barraca, mapa);
		Unidad zealot = jug2.crearUnidad(new Zealot(), (Creadora)acceso, mapa);
		
	
		primerMarine.atacarEnTierra(zealot);
		
		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		int danioTotalRecibido= zealot.getVida().obtenerDanioRecibido();
		
		
		Assert.assertTrue(danioTotalRecibido == 18 );
		
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
