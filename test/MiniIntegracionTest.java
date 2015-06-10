package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionPosicionInvalida;
import src.Juego;
import src.Jugador;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.EspacioDisponible;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.Espectro;
import src.unidades.Marine;
import src.unidades.Unidad;

public class MiniIntegracionTest {
	
	
	@Test
	public void testCrearUnidadesSegunTurno() throws ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionPosicionInvalida{
		Mapa mapa = new Mapa(50);
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(mapa, jugador1, jugador2);
		
		jugador1.construir(new DepositoDeSuministros(), mapa, 30, 30);
		//jugador1.construir(new DepositoDeSuministros(), mapa, 40, 30);
		//jugador1.construir(new DepositoDeSuministros(), mapa, 10, 30);
		Construccion barraca = jugador1.construir(new Barraca(),mapa,5,5);
		
	
		
		Unidad marine = new Marine();
		
		
		
		
		juego.ordenarFabricacionUnidad(marine,(Creadora) barraca);
		
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		
		
	Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(4,6).getElementoEnTierra().esLoMismo(new Marine()));
	//Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(4,18).getElementoEnTierra().esLoMismo(new Marine()));
	

		
		
	}

}
