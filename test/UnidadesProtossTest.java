package test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionGeneral;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
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
import src.construcciones.PuertoEstelarProtoss;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.*;
import junit.framework.TestCase;

public class UnidadesProtossTest{
	
	private Jugador jugador;
	private Mapa mapa;
	
	@Test
	public void testZealotSeCreaCon60escudoY100deVida(){
		
		Acceso acceso = new Acceso();
		Zealot zealot = acceso.crearZealot();		
		
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 60 );
		
	}
	
	@Test
	public void testDragonSeCreaCon80escudoY100deVida(){
		
		Acceso acceso = new Acceso();
		Dragon dragon = acceso.crearDragon();		
		
		Assert.assertTrue((dragon.getVida().obtenerVida()) == 100);
		Assert.assertTrue((dragon.getEscudo().obtenerResistenciaActual()) == 80 );
		
	}
	
	@Test
	public void testAtacarZealotNuevoPorMarineSoloDaniaEscudo() 
			throws ExcepcionNoPudoColocarseEdificio,
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa {
		
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		jug1.setDinero(9999, 9999);
		Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
		Juego juego = new Juego(jug1, jug2, 100, 0);
		Mapa mapa = juego.getMapa();
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
		ataques.setJuego(juego);
		
		Creadora barraca = (Creadora) jug1.colocar(new Barraca(),mapa,5,5);
		Creadora acceso = (Creadora) jug2.colocar(new Acceso(), mapa, 8, 8);
		Unidad marine = new Marine();
		barraca.colocarUnidad(marine, mapa);
		jug1.getUnidadesAlistades().add(marine);
		
		marine.setAtaquesPermitidosPorTurno(ataques);
		
		Zealot zealot =  new Zealot();
		acceso.colocarUnidad(zealot, mapa);
		jug1.atacarCon(marine, zealot);
		
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 54 );
		
	}
	
	@Test
	public void testZealotRecibeMultiplesAtaques () 
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa 
			{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
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
		jugador1.getUnidadesAlistades().add(primerMarine);
		jugador1.getUnidadesAlistades().add(segundoMarine);
		jugador1.getUnidadesAlistades().add(tercerMarine);
		
		acceso.colocarUnidad(zealot, mapa);
				
		jugador1.atacarCon(primerMarine, zealot);
		jugador1.atacarCon(segundoMarine, zealot);
		jugador1.atacarCon(tercerMarine, zealot);
		
		int danioTotalRecibido= zealot.getVida().obtenerDanioRecibido();
		
		
		Assert.assertTrue(danioTotalRecibido == 18 );
		
		}
	
	@Test
	public void testDestruccionDeDragon(){
		
		Dragon dragon= new Dragon();
		dragon.getVida().aumentarDanioARecibir(200);
		dragon.getVida().disminuirVidaPorDanio();
		
		Assert.assertTrue(dragon.getVida().devolverEstadoDeVida());
		
	}
	
	@Test
	public void testAsesinatoDeProtosEliminacionDelMapa()
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos 
			{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);	
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 2, 2);		
	
		Zealot zealot = new Zealot();	
		
		acceso.colocarUnidad(zealot, mapa);
		
		// El Zealot fue colocado en la posicion (1,3)
		
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(1, 3).getElementoEnTierra().getNombre()=="Zealot");
				
		zealot.getVida().aumentarDanioARecibir(70);
		zealot.recibirDanio();
		
		Assert.assertTrue(zealot.getVida().obtenerVida()==90);
		
		zealot.getVida().aumentarDanioARecibir(90);
		zealot.recibirDanio();
		
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(1, 3).getElementoEnTierra().getNombre()=="Espacio Disponible");

		} 
	
	@Test (expected = ExcepcionElementoFueraDelRangoDeAtaque.class)
	public void unidadIntentaAtacarFueraDeSuAlcanceLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoHayLugarParaCrear, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 90, 90);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 10, 10);	
		
		Zealot zealot = new Zealot();	
		Marine marine = new Marine();
		
		acceso.colocarUnidad(zealot, mapa);
		jugador1.getUnidadesAlistades().add(zealot);
		barraca.colocarUnidad(marine, mapa);
		
		jugador1.atacarCon(zealot, marine);
		
	}
	
	@Test (expected = ExcepcionLaUnidadNoPertenceATuTropa.class)
	public void intentarAtacarConUnidadDelEnemigoLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoHayLugarParaCrear, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 90, 90);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 10, 10);	
		
		Zealot zealot = new Zealot();	
		Marine marine = new Marine();
		
		acceso.colocarUnidad(zealot, mapa);
		jugador1.getUnidadesAlistades().add(zealot);
		barraca.colocarUnidad(marine, mapa);
		jugador2.getUnidadesAlistades().add(marine);
		
		jugador1.atacarCon(marine, zealot);
	
	}
	
}	
