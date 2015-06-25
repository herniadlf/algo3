package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.AtaquesPermitidosPorTurno;
import src.Juego;
import src.Jugador;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.*;

public class UnidadesProtossTest{
	
	private Jugador jugador;
	private Mapa mapa;
	
	@Test
	public void testZealotSeCreaCon60escudoY100deVida(){
		
		Zealot zealot = new Zealot();		
		
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 60 );
		
	}
	
	@Test
	public void testDragonSeCreaCon80escudoY100deVida(){
		
		Dragon dragon = new Dragon();
		
		Assert.assertTrue((dragon.getVida().obtenerVida()) == 100);
		Assert.assertTrue((dragon.getEscudo().obtenerResistenciaActual()) == 80 );
		
	}
	

	@Test
	public void testScoutSeCreaCon100escudoY150deVida(){
		
		Scout scout = new Scout();
		
		Assert.assertTrue((scout.getVida().obtenerVida()) == 150);
		Assert.assertTrue((scout.getEscudo().obtenerResistenciaActual()) == 100 );
		
	}
	
	
	@Test
	public void testAtacarZealotNuevoPorMarineSoloDaniaEscudo() 
			throws ExcepcionNoPudoColocarseEdificio,
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
		
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
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido 
			{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
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
		
		Assert.assertTrue(dragon.getVida().estaMuerto());
		
	}
	
	@Test
	public void testAsesinatoDeProtosEliminacionDelMapa()
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, 
			ExcepcionNoHayLugarParaCrear, 
			ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido 
			{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
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
	public void unidadIntentaAtacarFueraDeSuAlcanceLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoHayLugarParaCrear, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 90, 90);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 10, 10);	
		
		Zealot zealot = new Zealot();	
		Marine marine = new Marine();
		
		acceso.colocarUnidad(zealot, mapa);
		jugador2.getUnidadesAlistades().add(zealot);
		barraca.colocarUnidad(marine, mapa);
		
		jugador2.atacarCon(zealot, marine);
		
	}
	
	@Test (expected = ExcepcionLaUnidadNoPertenceATuTropa.class)
	public void intentarAtacarConUnidadDelEnemigoLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionNoHayLugarParaCrear, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 90, 90);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 10, 10);	
		
		Zealot zealot = new Zealot();	
		Marine marine = new Marine();
		
		acceso.colocarUnidad(zealot, mapa);
		jugador2.getUnidadesAlistades().add(zealot);
		barraca.colocarUnidad(marine, mapa);
		jugador1.getUnidadesAlistades().add(marine);
		
		jugador2.atacarCon(marine, zealot);
	
	}
	
	@Test (expected = ExcepcionUnidadNoCorrespondiente.class)
	public void intentarCrearUnidadConEdificioIncorrectoLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionUnidadNoCorrespondiente, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido {
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 90, 90);		
		
		Zealot zealot = new Zealot();
		
		barraca.colocarUnidad(zealot, mapa);
	
	}
	
	@Test (expected = ExcepcionNoPuedeMoverseUnidad.class)
	public void moverUnidadAPosicionNoValidaLanzaExcepcion() throws ExcepcionNoPuedeMoverseUnidad, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoHayLugarParaCrear, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora acceso = (Creadora) jugador1.colocar(new Acceso(), mapa, 90, 90);		
		
		Zealot zealot = new Zealot();
		acceso.colocarUnidad(zealot, mapa);
		jugador1.getUnidadesAlistades().add(zealot);
		
		jugador1.moverUnidadAPosicion(zealot, 101, 101);
		
	}
	
	@Test (expected = ExcepcionLaUnidadNoPertenceATuTropa.class )
	public void intentarMoverUnidadDelEnemigoLanzaExcepcion() throws ExcepcionNoPuedeMoverseUnidad, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoHayLugarParaCrear, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 90, 90);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 10, 10);	
		
		Zealot zealot = new Zealot();	
		Marine marine = new Marine();
		
		acceso.colocarUnidad(zealot, mapa);
		jugador2.getUnidadesAlistades().add(zealot);
		barraca.colocarUnidad(marine, mapa);
		jugador1.getUnidadesAlistades().add(marine);
		
		jugador1.moverUnidadAPosicion(zealot, 50, 50);
		
		
	}
	
}	
