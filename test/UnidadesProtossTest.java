package test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionGeneral;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.AtaquesPermitidosPorTurno;
import src.Juego;
import src.Jugador;
import src.Vida;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Pilon;
import src.mapa.Mapa;
import src.mapa.Posicion;
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
	
	public void testAtacarZealotNuevoPorMarineSoloDaniaEscudo() 
			throws ExcepcionNoPudoColocarseEdificio,
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno {
		
		Mapa mapa = new Mapa(50);
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		jug1.setDinero(9999, 9999);
		Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
		Juego juego = new Juego(mapa, jug1, jug2);
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
		ataques.setJuego(juego);
		
		Creadora barraca = (Creadora) jug1.colocar(new Barraca(),mapa,5,5);
		Creadora acceso = (Creadora) jug2.colocar(new Acceso(), mapa, 8, 8);
		Unidad marine = new Marine();
		barraca.colocarUnidad(marine, mapa);
		
		marine.setAtaquesPermitidosPorTurno(ataques);
		
		Zealot zealot =  new Zealot();
		acceso.colocarUnidad(zealot, mapa);
		
		marine.atacarEnTierra(zealot);
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 54 );
		
	}
	
	
	public void testZealotRecibeMultiplesAtaques () 
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno 
			{
		
		Mapa mapa = new Mapa(50);
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(mapa, jugador1, jugador2);
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 2, 2);		
		
		Unidad primerMarine = new Marine();
		Unidad segundoMarine = new Marine();
		Unidad tercerMarine = new Marine();
		Unidad zealot = new Zealot();	
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
		primerMarine.setAtaquesPermitidosPorTurno(ataques);
		segundoMarine.setAtaquesPermitidosPorTurno(ataques);
		tercerMarine.setAtaquesPermitidosPorTurno(ataques);
		ataques.setJuego(juego);
		
		barraca.colocarUnidad(primerMarine, mapa);	
		barraca.colocarUnidad(segundoMarine, mapa);
		barraca.colocarUnidad(tercerMarine, mapa);
		acceso.colocarUnidad(zealot, mapa);
				
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		
		int danioTotalRecibido= zealot.getVida().obtenerDanioRecibido();
		
		
		Assert.assertTrue(danioTotalRecibido == 18 );
		
		}
	
	public void testDestruccionDeDragon(){
		
		Dragon dragon= new Dragon();
		dragon.getVida().aumentarDanioARecibir(200);
		dragon.getVida().disminuirVidaPorDanio();
		
		Assert.assertTrue(dragon.getVida().devolverEstadoDeVida()==true);
		
		
		
	}
	
	
	public void testNaveTransporteProtossTransporta8Marines(){
		
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
	
/*	public void testAsesinatoDeProtosEliminacionDelMapa()
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno 
			{
		
		Mapa mapa = new Mapa(50);
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(mapa, jugador1, jugador2);
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 2, 2);		
		
		Unidad primerMarine = new Marine();
		Unidad segundoMarine = new Marine();
		Unidad tercerMarine = new Marine();
		Zealot zealot = new Zealot();	
		
		barraca.colocarUnidad(primerMarine, mapa);	
		barraca.colocarUnidad(segundoMarine, mapa);
		barraca.colocarUnidad(tercerMarine, mapa);
		acceso.colocarUnidad(zealot, mapa);
				
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);
		
		// El Zealot fue colocado en la posicion (1,3)
		
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(1, 3).getElementoEnTierra().getNombre()=="Zealot");
		
	
		
		
		Empieza a atacar la vida
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);		
		segundoMarine.atacarEnTierra(zealot);
		tercerMarine.atacarEnTierra(zealot);
		primerMarine.atacarEnTierra(zealot);
		
		
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(1, 3).getElementoEnTierra().getNombre()=="Espacio Disponible");
	
	
	
	

} */
	
	
}
