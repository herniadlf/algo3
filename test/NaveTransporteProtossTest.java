package test;

import org.junit.Assert;
import org.junit.Test;

import src.Juego;
import src.Jugador;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.construcciones.Creadora;
import src.construcciones.PuertoEstelarProtoss;
import src.mapa.Mapa;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Marine;
import src.unidades.NaveTransporteProtoss;
import src.unidades.Zealot;
import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionElTransporteNoEstaEnElAlcancePermitido;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionNoSePuedenTransportasUnidadesVoladoras;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class NaveTransporteProtossTest {
	
	@Test (expected = ExcepcionNoSePuedeTransportar.class)
	public void unidadLejanaALaNaveIntentaSubirseYLanzaError() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoHayLugarParaCrear, ExcepcionNoSePuedeTransportar {
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora acceso = (Creadora) jugador1.colocar(new Acceso(), mapa, 90, 50);		
		Creadora puertoEstelar = (Creadora) jugador1.colocar(new PuertoEstelarProtoss(), mapa, 10, 10);
		
		NaveTransporteProtoss naveProtoss = new NaveTransporteProtoss();	
		Zealot zealot = new Zealot();
		
		acceso.colocarUnidad(zealot, mapa);
		puertoEstelar.colocarUnidad(naveProtoss, mapa);
		
		naveProtoss.llevar(zealot);
		
	}
	
	@Test
	public void testNaveTransporteProtossTransporta8Marines() throws ExcepcionNoSePuedeTransportar, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 50, 50);		
		Creadora puertoEstelar = (Creadora) jugador1.colocar(new PuertoEstelarProtoss(), mapa, 49, 50);
		
		NaveTransporteProtoss naveProtoss = new NaveTransporteProtoss();
		puertoEstelar.colocarUnidad(naveProtoss, mapa);
		
		for(int i = 0 ; i < 8 ; i++){
			
			Marine marine = new Marine();
			barraca.colocarUnidad(marine, mapa);
			naveProtoss.llevar(marine);
			
		}
		
		Assert.assertTrue(naveProtoss.cantidadPasajeros() == 8);
		
	}
	
}
