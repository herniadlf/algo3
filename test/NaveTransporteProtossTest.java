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
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Marine;
import src.unidades.NaveTransporteProtoss;
import src.unidades.Zealot;
import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionElTransporteNoEstaEnElAlcancePermitido;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionNoSePuedenTransportasUnidadesVoladoras;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class NaveTransporteProtossTest {
	
	/*@Test
	public void construccionExitosaEnElMapa() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionTamanioDelMapaInvalido{
		Mapa mapa = new Mapa(50);
		Jugador jug = new Jugador("carlos","rojo",new Terran());
		jug.setDinero(9999999, 999999); // ya que pruebo construir en todos los puntos del mapa necesito mucha plata
		
		for (int i=1; i< mapa.getTamanioMapa();i++){
			for (int j=1; j<mapa.getTamanioMapa(); j++){
				Construccion b = new Barraca();
				b = jug.colocar(b, mapa, i, j);
				Assert.assertTrue( ( mapa.obtenerContenidoEnPosicion(i, j) ).getElementoEnTierra().esLoMismo(b));					
			}				
		}
	} */
	@Test (expected = ExcepcionNoSePuedeTransportar.class)
	public void unidadLejanaALaNaveIntentaSubirseYLanzaError() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoHayLugarParaCrear, ExcepcionNoSePuedeTransportar, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora acceso = (Creadora) jugador1.colocar(new Acceso(), mapa, 90, 50);	
		jugador1.getConstruccionesEnPie().add(acceso);
		Creadora puertoEstelar = (Creadora) jugador1.colocar(new PuertoEstelarProtoss(), mapa, 10, 10);
		
		NaveTransporteProtoss naveProtoss = new NaveTransporteProtoss();	
		Zealot zealot = new Zealot();
		
		acceso.colocarUnidad(zealot, mapa);
		puertoEstelar.colocarUnidad(naveProtoss, mapa);
		
		naveProtoss.llevar(zealot);
		
	}
	
	@Test
	public void testNaveTransporteProtossTransporta4Zealots() throws ExcepcionNoSePuedeTransportar, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora acceso = (Creadora) jugador1.colocar(new Acceso(), mapa, 50, 50);		
		jugador1.getConstruccionesEnPie().add(acceso);
		Creadora puertoEstelar = (Creadora) jugador1.colocar(new PuertoEstelarProtoss(), mapa, 49, 50);
		
		NaveTransporteProtoss naveProtoss = new NaveTransporteProtoss();
		puertoEstelar.colocarUnidad(naveProtoss, mapa);
		
		for(int i = 0 ; i < 4 ; i++){
			
			Zealot zealot = new Zealot();
			acceso.colocarUnidad(zealot, mapa);
			naveProtoss.llevar(zealot);
			
		}
		
		Assert.assertTrue(naveProtoss.cantidadPasajeros() == 4);
		
	}
	@Test
	public void testNaveTransporteProtossSoloSeMuevePorSuRangoDeVision() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoPuedeMoverseUnidad, ExcepcionNoHayLugarParaCrear, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionTamanioDelMapaInvalido{
		
		
		
		
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora acceso = (Creadora) jugador1.colocar(new Acceso(), mapa, 90, 50);		
		jugador1.getConstruccionesEnPie().add(acceso);
		Creadora puertoEstelar = (Creadora) jugador1.colocar(new PuertoEstelarProtoss(), mapa, 10, 10);
		
		NaveTransporteProtoss naveProtoss = new NaveTransporteProtoss();	
		
		mapa.colocarEn(1, 2, naveProtoss);
		Posicion posicion = new Posicion (1,2);
		naveProtoss.setPosicion(posicion);
		naveProtoss.setMapa(mapa);
		naveProtoss.setJugador(jugador1);
		
		try{
			naveProtoss.transportarUnidades(20, 20);
		}
		catch (ExcepcionNoPuedeMoverseUnidad e){
			e.printStackTrace();
		}
		
	}
	
}
